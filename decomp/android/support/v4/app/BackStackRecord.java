// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.content.Context;
import android.support.v4.util.LogWriter;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

// Referenced classes of package android.support.v4.app:
//            FragmentTransaction, Fragment, FragmentManagerImpl, FragmentHostCallback

final class BackStackRecord extends FragmentTransaction
    implements FragmentManager.BackStackEntry, FragmentManagerImpl.OpGenerator
{
    static final class Op
    {

        int cmd;
        int enterAnim;
        int exitAnim;
        Fragment fragment;
        int popEnterAnim;
        int popExitAnim;

        Op()
        {
        }
    }


    public BackStackRecord(FragmentManagerImpl fragmentmanagerimpl)
    {
        mOps = new ArrayList();
        mAllowAddToBackStack = true;
        mIndex = -1;
        mAllowOptimization = false;
        mManager = fragmentmanagerimpl;
    }

    private void doAddOp(int i, Fragment fragment, String s, int j)
    {
        Class class1 = fragment.getClass();
        int k = class1.getModifiers();
        if(class1.isAnonymousClass() || !Modifier.isPublic(k) || class1.isMemberClass() && !Modifier.isStatic(k))
            throw new IllegalStateException((new StringBuilder()).append("Fragment ").append(class1.getCanonicalName()).append(" must be a public static class to be  properly recreated from").append(" instance state.").toString());
        fragment.mFragmentManager = mManager;
        if(s != null)
        {
            if(fragment.mTag != null && !s.equals(fragment.mTag))
                throw new IllegalStateException((new StringBuilder()).append("Can't change tag of fragment ").append(fragment).append(": was ").append(fragment.mTag).append(" now ").append(s).toString());
            fragment.mTag = s;
        }
        if(i != 0)
        {
            if(i == -1)
                throw new IllegalArgumentException((new StringBuilder()).append("Can't add fragment ").append(fragment).append(" with tag ").append(s).append(" to container view with no id").toString());
            if(fragment.mFragmentId != 0 && fragment.mFragmentId != i)
                throw new IllegalStateException((new StringBuilder()).append("Can't change container ID of fragment ").append(fragment).append(": was ").append(fragment.mFragmentId).append(" now ").append(i).toString());
            fragment.mFragmentId = i;
            fragment.mContainerId = i;
        }
        s = new Op();
        s.cmd = j;
        s.fragment = fragment;
        addOp(s);
    }

    private static boolean isFragmentPostponed(Op op)
    {
        op = op.fragment;
        return ((Fragment) (op)).mAdded && ((Fragment) (op)).mView != null && !((Fragment) (op)).mDetached && !((Fragment) (op)).mHidden && op.isPostponed();
    }

    public FragmentTransaction add(int i, Fragment fragment)
    {
        doAddOp(i, fragment, null, 1);
        return this;
    }

    public FragmentTransaction add(int i, Fragment fragment, String s)
    {
        doAddOp(i, fragment, s, 1);
        return this;
    }

    public FragmentTransaction add(Fragment fragment, String s)
    {
        doAddOp(0, fragment, s, 1);
        return this;
    }

    void addOp(Op op)
    {
        mOps.add(op);
        op.enterAnim = mEnterAnim;
        op.exitAnim = mExitAnim;
        op.popEnterAnim = mPopEnterAnim;
        op.popExitAnim = mPopExitAnim;
    }

    public FragmentTransaction addSharedElement(View view, String s)
    {
        if(SUPPORTS_TRANSITIONS)
        {
            view = ViewCompat.getTransitionName(view);
            if(view == null)
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            if(mSharedElementSourceNames == null)
            {
                mSharedElementSourceNames = new ArrayList();
                mSharedElementTargetNames = new ArrayList();
            } else
            {
                if(mSharedElementTargetNames.contains(s))
                    throw new IllegalArgumentException((new StringBuilder()).append("A shared element with the target name '").append(s).append("' has already been added to the transaction.").toString());
                if(mSharedElementSourceNames.contains(view))
                    throw new IllegalArgumentException((new StringBuilder()).append("A shared element with the source name '").append(view).append(" has already been added to the transaction.").toString());
            }
            mSharedElementSourceNames.add(view);
            mSharedElementTargetNames.add(s);
        }
        return this;
    }

    public FragmentTransaction addToBackStack(String s)
    {
        if(!mAllowAddToBackStack)
        {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        } else
        {
            mAddToBackStack = true;
            mName = s;
            return this;
        }
    }

    public FragmentTransaction attach(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 7;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    void bumpBackStackNesting(int i)
    {
        if(mAddToBackStack)
        {
            if(FragmentManagerImpl.DEBUG)
                Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting in ").append(this).append(" by ").append(i).toString());
            int k = mOps.size();
            int j = 0;
            while(j < k) 
            {
                Op op = (Op)mOps.get(j);
                if(op.fragment != null)
                {
                    Fragment fragment = op.fragment;
                    fragment.mBackStackNesting = fragment.mBackStackNesting + i;
                    if(FragmentManagerImpl.DEBUG)
                        Log.v("FragmentManager", (new StringBuilder()).append("Bump nesting of ").append(op.fragment).append(" to ").append(op.fragment.mBackStackNesting).toString());
                }
                j++;
            }
        }
    }

    public int commit()
    {
        return commitInternal(false);
    }

    public int commitAllowingStateLoss()
    {
        return commitInternal(true);
    }

    int commitInternal(boolean flag)
    {
        if(mCommitted)
            throw new IllegalStateException("commit already called");
        if(FragmentManagerImpl.DEBUG)
        {
            Log.v("FragmentManager", (new StringBuilder()).append("Commit: ").append(this).toString());
            PrintWriter printwriter = new PrintWriter(new LogWriter("FragmentManager"));
            dump("  ", null, printwriter, null);
            printwriter.close();
        }
        mCommitted = true;
        if(mAddToBackStack)
            mIndex = mManager.allocBackStackIndex(this);
        else
            mIndex = -1;
        mManager.enqueueAction(this, flag);
        return mIndex;
    }

    public void commitNow()
    {
        disallowAddToBackStack();
        mManager.execSingleAction(this, false);
    }

    public void commitNowAllowingStateLoss()
    {
        disallowAddToBackStack();
        mManager.execSingleAction(this, true);
    }

    public FragmentTransaction detach(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 6;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    public FragmentTransaction disallowAddToBackStack()
    {
        if(mAddToBackStack)
        {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        } else
        {
            mAllowAddToBackStack = false;
            return this;
        }
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        dump(s, printwriter, true);
    }

    public void dump(String s, PrintWriter printwriter, boolean flag)
    {
        int i;
        int j;
        if(flag)
        {
            printwriter.print(s);
            printwriter.print("mName=");
            printwriter.print(mName);
            printwriter.print(" mIndex=");
            printwriter.print(mIndex);
            printwriter.print(" mCommitted=");
            printwriter.println(mCommitted);
            if(mTransition != 0)
            {
                printwriter.print(s);
                printwriter.print("mTransition=#");
                printwriter.print(Integer.toHexString(mTransition));
                printwriter.print(" mTransitionStyle=#");
                printwriter.println(Integer.toHexString(mTransitionStyle));
            }
            if(mEnterAnim != 0 || mExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("mEnterAnim=#");
                printwriter.print(Integer.toHexString(mEnterAnim));
                printwriter.print(" mExitAnim=#");
                printwriter.println(Integer.toHexString(mExitAnim));
            }
            if(mPopEnterAnim != 0 || mPopExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("mPopEnterAnim=#");
                printwriter.print(Integer.toHexString(mPopEnterAnim));
                printwriter.print(" mPopExitAnim=#");
                printwriter.println(Integer.toHexString(mPopExitAnim));
            }
            if(mBreadCrumbTitleRes != 0 || mBreadCrumbTitleText != null)
            {
                printwriter.print(s);
                printwriter.print("mBreadCrumbTitleRes=#");
                printwriter.print(Integer.toHexString(mBreadCrumbTitleRes));
                printwriter.print(" mBreadCrumbTitleText=");
                printwriter.println(mBreadCrumbTitleText);
            }
            if(mBreadCrumbShortTitleRes != 0 || mBreadCrumbShortTitleText != null)
            {
                printwriter.print(s);
                printwriter.print("mBreadCrumbShortTitleRes=#");
                printwriter.print(Integer.toHexString(mBreadCrumbShortTitleRes));
                printwriter.print(" mBreadCrumbShortTitleText=");
                printwriter.println(mBreadCrumbShortTitleText);
            }
        }
        if(mOps.isEmpty())
            break MISSING_BLOCK_LABEL_712;
        printwriter.print(s);
        printwriter.println("Operations:");
        (new StringBuilder()).append(s).append("    ").toString();
        j = mOps.size();
        i = 0;
_L11:
        Op op;
        if(i >= j)
            break MISSING_BLOCK_LABEL_712;
        op = (Op)mOps.get(i);
        op.cmd;
        JVM INSTR tableswitch 0 7: default 444
    //                   0 648
    //                   1 656
    //                   2 664
    //                   3 672
    //                   4 680
    //                   5 688
    //                   6 696
    //                   7 704;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_704;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        String s1 = (new StringBuilder()).append("cmd=").append(op.cmd).toString();
_L12:
        printwriter.print(s);
        printwriter.print("  Op #");
        printwriter.print(i);
        printwriter.print(": ");
        printwriter.print(s1);
        printwriter.print(" ");
        printwriter.println(op.fragment);
        if(flag)
        {
            if(op.enterAnim != 0 || op.exitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("enterAnim=#");
                printwriter.print(Integer.toHexString(op.enterAnim));
                printwriter.print(" exitAnim=#");
                printwriter.println(Integer.toHexString(op.exitAnim));
            }
            if(op.popEnterAnim != 0 || op.popExitAnim != 0)
            {
                printwriter.print(s);
                printwriter.print("popEnterAnim=#");
                printwriter.print(Integer.toHexString(op.popEnterAnim));
                printwriter.print(" popExitAnim=#");
                printwriter.println(Integer.toHexString(op.popExitAnim));
            }
        }
        i++;
        if(true) goto _L11; else goto _L10
_L10:
        s1 = "NULL";
          goto _L12
_L3:
        s1 = "ADD";
          goto _L12
_L4:
        s1 = "REPLACE";
          goto _L12
_L5:
        s1 = "REMOVE";
          goto _L12
_L6:
        s1 = "HIDE";
          goto _L12
_L7:
        s1 = "SHOW";
          goto _L12
_L8:
        s1 = "DETACH";
          goto _L12
        s1 = "ATTACH";
          goto _L12
    }

    void executeOps()
    {
        int i;
        int j;
        j = mOps.size();
        i = 0;
_L9:
        Op op;
        Fragment fragment;
        if(i >= j)
            break MISSING_BLOCK_LABEL_278;
        op = (Op)mOps.get(i);
        fragment = op.fragment;
        fragment.setNextTransition(mTransition, mTransitionStyle);
        op.cmd;
        JVM INSTR tableswitch 1 7: default 92
    //                   1 123
    //                   2 92
    //                   3 173
    //                   4 194
    //                   5 215
    //                   6 236
    //                   7 257;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L7
_L7:
        break MISSING_BLOCK_LABEL_257;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown cmd: ").append(op.cmd).toString());
_L2:
        fragment.setNextAnim(op.enterAnim);
        mManager.addFragment(fragment, false);
_L10:
        if(!mAllowOptimization && op.cmd != 1)
            mManager.moveFragmentToExpectedState(fragment);
        i++;
        if(true) goto _L9; else goto _L8
_L8:
        fragment.setNextAnim(op.exitAnim);
        mManager.removeFragment(fragment);
          goto _L10
_L4:
        fragment.setNextAnim(op.exitAnim);
        mManager.hideFragment(fragment);
          goto _L10
_L5:
        fragment.setNextAnim(op.enterAnim);
        mManager.showFragment(fragment);
          goto _L10
_L6:
        fragment.setNextAnim(op.exitAnim);
        mManager.detachFragment(fragment);
          goto _L10
        fragment.setNextAnim(op.enterAnim);
        mManager.attachFragment(fragment);
          goto _L10
        if(!mAllowOptimization)
            mManager.moveToState(mManager.mCurState, true);
        return;
    }

    void executePopOps(boolean flag)
    {
        int i = mOps.size() - 1;
_L9:
        Op op;
        Fragment fragment;
        if(i < 0)
            break MISSING_BLOCK_LABEL_282;
        op = (Op)mOps.get(i);
        fragment = op.fragment;
        fragment.setNextTransition(FragmentManagerImpl.reverseTransit(mTransition), mTransitionStyle);
        op.cmd;
        JVM INSTR tableswitch 1 7: default 96
    //                   1 127
    //                   2 96
    //                   3 176
    //                   4 198
    //                   5 219
    //                   6 240
    //                   7 261;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6 _L7
_L7:
        break MISSING_BLOCK_LABEL_261;
_L3:
        break; /* Loop/switch isn't completed */
_L1:
        throw new IllegalArgumentException((new StringBuilder()).append("Unknown cmd: ").append(op.cmd).toString());
_L2:
        fragment.setNextAnim(op.popExitAnim);
        mManager.removeFragment(fragment);
_L10:
        if(!mAllowOptimization && op.cmd != 3)
            mManager.moveFragmentToExpectedState(fragment);
        i--;
        if(true) goto _L9; else goto _L8
_L8:
        fragment.setNextAnim(op.popEnterAnim);
        mManager.addFragment(fragment, false);
          goto _L10
_L4:
        fragment.setNextAnim(op.popEnterAnim);
        mManager.showFragment(fragment);
          goto _L10
_L5:
        fragment.setNextAnim(op.popExitAnim);
        mManager.hideFragment(fragment);
          goto _L10
_L6:
        fragment.setNextAnim(op.popEnterAnim);
        mManager.attachFragment(fragment);
          goto _L10
        fragment.setNextAnim(op.popExitAnim);
        mManager.detachFragment(fragment);
          goto _L10
        if(!mAllowOptimization && flag)
            mManager.moveToState(mManager.mCurState, true);
        return;
    }

    void expandReplaceOps(ArrayList arraylist)
    {
        int i = 0;
_L7:
        int j;
        Op op;
        if(i >= mOps.size())
            break MISSING_BLOCK_LABEL_327;
        op = (Op)mOps.get(i);
        j = i;
        op.cmd;
        JVM INSTR tableswitch 1 7: default 76
    //                   1 85
    //                   2 115
    //                   3 100
    //                   4 78
    //                   5 78
    //                   6 100
    //                   7 85;
           goto _L1 _L2 _L3 _L4 _L5 _L5 _L4 _L2
_L3:
        break MISSING_BLOCK_LABEL_115;
_L2:
        break; /* Loop/switch isn't completed */
_L5:
        break; /* Loop/switch isn't completed */
_L1:
        j = i;
_L8:
        i = j + 1;
        if(true) goto _L7; else goto _L6
_L6:
        arraylist.add(op.fragment);
        j = i;
          goto _L8
_L4:
        arraylist.remove(op.fragment);
        j = i;
          goto _L8
        Fragment fragment = op.fragment;
        int l = fragment.mContainerId;
        boolean flag = false;
        j = arraylist.size() - 1;
        while(j >= 0) 
        {
            Fragment fragment1 = (Fragment)arraylist.get(j);
            boolean flag1 = flag;
            int k = i;
            if(fragment1.mContainerId == l)
                if(fragment1 == fragment)
                {
                    flag1 = true;
                    k = i;
                } else
                {
                    Op op1 = new Op();
                    op1.cmd = 3;
                    op1.fragment = fragment1;
                    op1.enterAnim = op.enterAnim;
                    op1.popEnterAnim = op.popEnterAnim;
                    op1.exitAnim = op.exitAnim;
                    op1.popExitAnim = op.popExitAnim;
                    mOps.add(i, op1);
                    arraylist.remove(fragment1);
                    k = i + 1;
                    flag1 = flag;
                }
            j--;
            flag = flag1;
            i = k;
        }
        if(flag)
        {
            mOps.remove(i);
            j = i - 1;
        } else
        {
            op.cmd = 1;
            arraylist.add(fragment);
            j = i;
        }
          goto _L8
    }

    public boolean generateOps(ArrayList arraylist, ArrayList arraylist1)
    {
        if(FragmentManagerImpl.DEBUG)
            Log.v("FragmentManager", (new StringBuilder()).append("Run: ").append(this).toString());
        arraylist.add(this);
        arraylist1.add(Boolean.valueOf(false));
        if(mAddToBackStack)
            mManager.addBackStackState(this);
        return true;
    }

    public CharSequence getBreadCrumbShortTitle()
    {
        if(mBreadCrumbShortTitleRes != 0)
            return mManager.mHost.getContext().getText(mBreadCrumbShortTitleRes);
        else
            return mBreadCrumbShortTitleText;
    }

    public int getBreadCrumbShortTitleRes()
    {
        return mBreadCrumbShortTitleRes;
    }

    public CharSequence getBreadCrumbTitle()
    {
        if(mBreadCrumbTitleRes != 0)
            return mManager.mHost.getContext().getText(mBreadCrumbTitleRes);
        else
            return mBreadCrumbTitleText;
    }

    public int getBreadCrumbTitleRes()
    {
        return mBreadCrumbTitleRes;
    }

    public int getId()
    {
        return mIndex;
    }

    public String getName()
    {
        return mName;
    }

    public int getTransition()
    {
        return mTransition;
    }

    public int getTransitionStyle()
    {
        return mTransitionStyle;
    }

    public FragmentTransaction hide(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 4;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    boolean interactsWith(int i)
    {
        int k = mOps.size();
        for(int j = 0; j < k; j++)
            if(((Op)mOps.get(j)).fragment.mContainerId == i)
                return true;

        return false;
    }

    boolean interactsWith(ArrayList arraylist, int i, int j)
    {
        if(j != i)
        {
            int l1 = mOps.size();
            int l = -1;
            int k = 0;
            while(k < l1) 
            {
                int k1 = ((Op)mOps.get(k)).fragment.mContainerId;
                int j1 = l;
                if(k1 != 0)
                {
                    j1 = l;
                    if(k1 != l)
                    {
                        l = k1;
                        int i1 = i;
                        do
                        {
                            j1 = l;
                            if(i1 >= j)
                                break;
                            BackStackRecord backstackrecord = (BackStackRecord)arraylist.get(i1);
                            int i2 = backstackrecord.mOps.size();
                            for(j1 = 0; j1 < i2; j1++)
                                if(((Op)backstackrecord.mOps.get(j1)).fragment.mContainerId == k1)
                                    return true;

                            i1++;
                        } while(true);
                    }
                }
                k++;
                l = j1;
            }
        }
        return false;
    }

    public boolean isAddToBackStackAllowed()
    {
        return mAllowAddToBackStack;
    }

    public boolean isEmpty()
    {
        return mOps.isEmpty();
    }

    boolean isPostponed()
    {
        for(int i = 0; i < mOps.size(); i++)
            if(isFragmentPostponed((Op)mOps.get(i)))
                return true;

        return false;
    }

    public FragmentTransaction remove(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 3;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    public FragmentTransaction replace(int i, Fragment fragment)
    {
        return replace(i, fragment, null);
    }

    public FragmentTransaction replace(int i, Fragment fragment, String s)
    {
        if(i == 0)
        {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        } else
        {
            doAddOp(i, fragment, s, 2);
            return this;
        }
    }

    public FragmentTransaction setAllowOptimization(boolean flag)
    {
        mAllowOptimization = flag;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(int i)
    {
        mBreadCrumbShortTitleRes = i;
        mBreadCrumbShortTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbShortTitle(CharSequence charsequence)
    {
        mBreadCrumbShortTitleRes = 0;
        mBreadCrumbShortTitleText = charsequence;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(int i)
    {
        mBreadCrumbTitleRes = i;
        mBreadCrumbTitleText = null;
        return this;
    }

    public FragmentTransaction setBreadCrumbTitle(CharSequence charsequence)
    {
        mBreadCrumbTitleRes = 0;
        mBreadCrumbTitleText = charsequence;
        return this;
    }

    public FragmentTransaction setCustomAnimations(int i, int j)
    {
        return setCustomAnimations(i, j, 0, 0);
    }

    public FragmentTransaction setCustomAnimations(int i, int j, int k, int l)
    {
        mEnterAnim = i;
        mExitAnim = j;
        mPopEnterAnim = k;
        mPopExitAnim = l;
        return this;
    }

    void setOnStartPostponedListener(Fragment.OnStartEnterTransitionListener onstartentertransitionlistener)
    {
        for(int i = 0; i < mOps.size(); i++)
        {
            Op op = (Op)mOps.get(i);
            if(isFragmentPostponed(op))
                op.fragment.setOnStartEnterTransitionListener(onstartentertransitionlistener);
        }

    }

    public FragmentTransaction setTransition(int i)
    {
        mTransition = i;
        return this;
    }

    public FragmentTransaction setTransitionStyle(int i)
    {
        mTransitionStyle = i;
        return this;
    }

    public FragmentTransaction show(Fragment fragment)
    {
        Op op = new Op();
        op.cmd = 5;
        op.fragment = fragment;
        addOp(op);
        return this;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append("BackStackEntry{");
        stringbuilder.append(Integer.toHexString(System.identityHashCode(this)));
        if(mIndex >= 0)
        {
            stringbuilder.append(" #");
            stringbuilder.append(mIndex);
        }
        if(mName != null)
        {
            stringbuilder.append(" ");
            stringbuilder.append(mName);
        }
        stringbuilder.append("}");
        return stringbuilder.toString();
    }

    void trackAddedFragmentsInPop(ArrayList arraylist)
    {
        int i = 0;
_L2:
        Op op;
        if(i >= mOps.size())
            break MISSING_BLOCK_LABEL_103;
        op = (Op)mOps.get(i);
        switch(op.cmd)
        {
        case 2: // '\002'
        case 4: // '\004'
        case 5: // '\005'
        default:
            break;

        case 1: // '\001'
        case 7: // '\007'
            break; /* Loop/switch isn't completed */

        case 3: // '\003'
        case 6: // '\006'
            break;
        }
        break MISSING_BLOCK_LABEL_91;
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        arraylist.remove(op.fragment);
          goto _L3
        arraylist.add(op.fragment);
          goto _L3
    }

    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SHOW = 5;
    static final boolean SUPPORTS_TRANSITIONS;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    boolean mAllowOptimization;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    int mIndex;
    final FragmentManagerImpl mManager;
    String mName;
    ArrayList mOps;
    int mPopEnterAnim;
    int mPopExitAnim;
    ArrayList mSharedElementSourceNames;
    ArrayList mSharedElementTargetNames;
    int mTransition;
    int mTransitionStyle;

    static 
    {
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            flag = true;
        else
            flag = false;
        SUPPORTS_TRANSITIONS = flag;
    }
}
