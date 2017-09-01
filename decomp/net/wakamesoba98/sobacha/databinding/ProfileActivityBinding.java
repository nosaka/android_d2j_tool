// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.databinding;

import android.databinding.*;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.design.widget.FloatingActionButton;
import android.util.SparseIntArray;
import android.view.*;
import android.widget.EditText;
import android.widget.RelativeLayout;
import net.wakamesoba98.sobacha.view.activity.data.Profile;
import net.wakamesoba98.sobacha.view.activity.handler.ProfileEditHandlers;
import net.wakamesoba98.sobacha.view.imageview.RecyclableImageButton;

public class ProfileActivityBinding extends ViewDataBinding
{
    public static class OnClickListenerImpl
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            value.onUpdateButtonClick(view);
        }

        public OnClickListenerImpl setValue(ProfileEditHandlers profileedithandlers)
        {
            value = profileedithandlers;
            OnClickListenerImpl onclicklistenerimpl = this;
            if(profileedithandlers == null)
                onclicklistenerimpl = null;
            return onclicklistenerimpl;
        }

        private ProfileEditHandlers value;

        public OnClickListenerImpl()
        {
        }
    }

    public static class OnClickListenerImpl1
        implements android.view.View.OnClickListener
    {

        public void onClick(View view)
        {
            value.onIconClick(view);
        }

        public OnClickListenerImpl1 setValue(ProfileEditHandlers profileedithandlers)
        {
            value = profileedithandlers;
            OnClickListenerImpl1 onclicklistenerimpl1 = this;
            if(profileedithandlers == null)
                onclicklistenerimpl1 = null;
            return onclicklistenerimpl1;
        }

        private ProfileEditHandlers value;

        public OnClickListenerImpl1()
        {
        }
    }


    public ProfileActivityBinding(DataBindingComponent databindingcomponent, View view)
    {
        super(databindingcomponent, view, 1);
        editTextProfileDescriptionandroidTextAttrChanged = new InverseBindingListener() {

            public void onChange()
            {
                String s = TextViewBindingAdapter.getTextString(editTextProfileDescription);
                Profile profile = mProfile;
                boolean flag;
                if(profile != null)
                    flag = true;
                else
                    flag = false;
                if(flag)
                    profile.setDescription(s);
            }

            final ProfileActivityBinding this$0;

            
            {
                this$0 = ProfileActivityBinding.this;
                super();
            }
        }
;
        editTextProfileLocationandroidTextAttrChanged = new InverseBindingListener() {

            public void onChange()
            {
                String s = TextViewBindingAdapter.getTextString(editTextProfileLocation);
                Profile profile = mProfile;
                boolean flag;
                if(profile != null)
                    flag = true;
                else
                    flag = false;
                if(flag)
                    profile.setLocation(s);
            }

            final ProfileActivityBinding this$0;

            
            {
                this$0 = ProfileActivityBinding.this;
                super();
            }
        }
;
        editTextProfileNameandroidTextAttrChanged = new InverseBindingListener() {

            public void onChange()
            {
                String s = TextViewBindingAdapter.getTextString(editTextProfileName);
                Profile profile = mProfile;
                boolean flag;
                if(profile != null)
                    flag = true;
                else
                    flag = false;
                if(flag)
                    profile.setName(s);
            }

            final ProfileActivityBinding this$0;

            
            {
                this$0 = ProfileActivityBinding.this;
                super();
            }
        }
;
        editTextProfileUrlandroidTextAttrChanged = new InverseBindingListener() {

            public void onChange()
            {
                String s = TextViewBindingAdapter.getTextString(editTextProfileUrl);
                Profile profile = mProfile;
                boolean flag;
                if(profile != null)
                    flag = true;
                else
                    flag = false;
                if(flag)
                    profile.setUrl(s);
            }

            final ProfileActivityBinding this$0;

            
            {
                this$0 = ProfileActivityBinding.this;
                super();
            }
        }
;
        mDirtyFlags = -1L;
        databindingcomponent = ((DataBindingComponent) (mapBindings(databindingcomponent, view, 7, sIncludes, sViewsWithIds)));
        buttonChangeProfileImage = (RecyclableImageButton)databindingcomponent[1];
        buttonChangeProfileImage.setTag(null);
        buttonUpdateProfile = (FloatingActionButton)databindingcomponent[6];
        buttonUpdateProfile.setTag(null);
        editTextProfileDescription = (EditText)databindingcomponent[5];
        editTextProfileDescription.setTag(null);
        editTextProfileLocation = (EditText)databindingcomponent[3];
        editTextProfileLocation.setTag(null);
        editTextProfileName = (EditText)databindingcomponent[2];
        editTextProfileName.setTag(null);
        editTextProfileUrl = (EditText)databindingcomponent[4];
        editTextProfileUrl.setTag(null);
        mboundView0 = (RelativeLayout)databindingcomponent[0];
        mboundView0.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    public static ProfileActivityBinding bind(View view)
    {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ProfileActivityBinding bind(View view, DataBindingComponent databindingcomponent)
    {
        if(!"layout/profile_activity_0".equals(view.getTag()))
            throw new RuntimeException((new StringBuilder()).append("view tag isn't correct on view:").append(view.getTag()).toString());
        else
            return new ProfileActivityBinding(databindingcomponent, view);
    }

    public static ProfileActivityBinding inflate(LayoutInflater layoutinflater)
    {
        return inflate(layoutinflater, DataBindingUtil.getDefaultComponent());
    }

    public static ProfileActivityBinding inflate(LayoutInflater layoutinflater, DataBindingComponent databindingcomponent)
    {
        return bind(layoutinflater.inflate(0x7f030062, null, false), databindingcomponent);
    }

    public static ProfileActivityBinding inflate(LayoutInflater layoutinflater, ViewGroup viewgroup, boolean flag)
    {
        return inflate(layoutinflater, viewgroup, flag, DataBindingUtil.getDefaultComponent());
    }

    public static ProfileActivityBinding inflate(LayoutInflater layoutinflater, ViewGroup viewgroup, boolean flag, DataBindingComponent databindingcomponent)
    {
        return (ProfileActivityBinding)DataBindingUtil.inflate(layoutinflater, 0x7f030062, viewgroup, flag, databindingcomponent);
    }

    private boolean onChangeProfile(Profile profile, int i)
    {
        i;
        JVM INSTR tableswitch 0 6: default 44
    //                   0 138
    //                   1 115
    //                   2 44
    //                   3 69
    //                   4 46
    //                   5 44
    //                   6 92;
           goto _L1 _L2 _L3 _L1 _L4 _L5 _L1 _L6
_L1:
        return false;
_L5:
        this;
        JVM INSTR monitorenter ;
        mDirtyFlags = mDirtyFlags | 4L;
        this;
        JVM INSTR monitorexit ;
        return true;
        profile;
        this;
        JVM INSTR monitorexit ;
        throw profile;
_L4:
        this;
        JVM INSTR monitorenter ;
        mDirtyFlags = mDirtyFlags | 8L;
        this;
        JVM INSTR monitorexit ;
        return true;
        profile;
        this;
        JVM INSTR monitorexit ;
        throw profile;
_L6:
        this;
        JVM INSTR monitorenter ;
        mDirtyFlags = mDirtyFlags | 16L;
        this;
        JVM INSTR monitorexit ;
        return true;
        profile;
        this;
        JVM INSTR monitorexit ;
        throw profile;
_L3:
        this;
        JVM INSTR monitorenter ;
        mDirtyFlags = mDirtyFlags | 32L;
        this;
        JVM INSTR monitorexit ;
        return true;
        profile;
        this;
        JVM INSTR monitorexit ;
        throw profile;
_L2:
        this;
        JVM INSTR monitorenter ;
        mDirtyFlags = mDirtyFlags | 1L;
        this;
        JVM INSTR monitorexit ;
        return true;
        profile;
        this;
        JVM INSTR monitorexit ;
        throw profile;
    }

    protected void executeBindings()
    {
        this;
        JVM INSTR monitorenter ;
        long l;
        l = mDirtyFlags;
        mDirtyFlags = 0L;
        this;
        JVM INSTR monitorexit ;
        String s5 = null;
        String s = null;
        String s4 = null;
        String s1 = null;
        Object obj = null;
        ProfileEditHandlers profileedithandlers = mHandler;
        Profile profile = mProfile;
        Object obj2 = null;
        String s3 = null;
        Object obj4 = null;
        Object obj3 = null;
        OnClickListenerImpl1 onclicklistenerimpl1 = obj2;
        OnClickListenerImpl onclicklistenerimpl = obj;
        if((66L & l) != 0L)
        {
            onclicklistenerimpl1 = obj2;
            onclicklistenerimpl = obj;
            if(profileedithandlers != null)
            {
                Object obj1;
                String s2;
                if(mHandlerOnUpdateButtonClickAndroidViewViewOnClickListener == null)
                {
                    obj1 = new OnClickListenerImpl();
                    mHandlerOnUpdateButtonClickAndroidViewViewOnClickListener = ((OnClickListenerImpl) (obj1));
                } else
                {
                    obj1 = mHandlerOnUpdateButtonClickAndroidViewViewOnClickListener;
                }
                onclicklistenerimpl = ((OnClickListenerImpl) (obj1)).setValue(profileedithandlers);
                if(mHandlerOnIconClickAndroidViewViewOnClickListener == null)
                {
                    obj1 = new OnClickListenerImpl1();
                    mHandlerOnIconClickAndroidViewViewOnClickListener = ((OnClickListenerImpl1) (obj1));
                } else
                {
                    obj1 = mHandlerOnIconClickAndroidViewViewOnClickListener;
                }
                onclicklistenerimpl1 = ((OnClickListenerImpl1) (obj1)).setValue(profileedithandlers);
            }
        }
        s2 = obj3;
        if((125L & l) != 0L)
        {
            obj1 = s;
            if((81L & l) != 0L)
            {
                obj1 = s;
                if(profile != null)
                    obj1 = profile.getUrl();
            }
            s = s1;
            if((69L & l) != 0L)
            {
                s = s1;
                if(profile != null)
                    s = profile.getName();
            }
            s1 = obj4;
            if((73L & l) != 0L)
            {
                s1 = obj4;
                if(profile != null)
                    s1 = profile.getLocation();
            }
            s2 = obj3;
            s3 = s1;
            s4 = s;
            s5 = ((String) (obj1));
            if((97L & l) != 0L)
            {
                s2 = obj3;
                s3 = s1;
                s4 = s;
                s5 = ((String) (obj1));
                if(profile != null)
                {
                    s2 = profile.getDescription();
                    s5 = ((String) (obj1));
                    s4 = s;
                    s3 = s1;
                }
            }
        }
        if((66L & l) != 0L)
        {
            buttonChangeProfileImage.setOnClickListener(onclicklistenerimpl1);
            buttonUpdateProfile.setOnClickListener(onclicklistenerimpl);
        }
        if((97L & l) != 0L)
            TextViewBindingAdapter.setText(editTextProfileDescription, s2);
        if((64L & l) != 0L)
        {
            TextViewBindingAdapter.setTextWatcher(editTextProfileDescription, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, editTextProfileDescriptionandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(editTextProfileLocation, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, editTextProfileLocationandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(editTextProfileName, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, editTextProfileNameandroidTextAttrChanged);
            TextViewBindingAdapter.setTextWatcher(editTextProfileUrl, (android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, editTextProfileUrlandroidTextAttrChanged);
        }
        if((73L & l) != 0L)
            TextViewBindingAdapter.setText(editTextProfileLocation, s3);
        if((69L & l) != 0L)
            TextViewBindingAdapter.setText(editTextProfileName, s4);
        if((81L & l) != 0L)
            TextViewBindingAdapter.setText(editTextProfileUrl, s5);
        return;
        obj1;
        this;
        JVM INSTR monitorexit ;
        throw obj1;
    }

    public ProfileEditHandlers getHandler()
    {
        return mHandler;
    }

    public Profile getProfile()
    {
        return mProfile;
    }

    public boolean hasPendingBindings()
    {
        this;
        JVM INSTR monitorenter ;
        if(mDirtyFlags != 0L)
            return true;
        this;
        JVM INSTR monitorexit ;
        return false;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void invalidateAll()
    {
        this;
        JVM INSTR monitorenter ;
        mDirtyFlags = 64L;
        this;
        JVM INSTR monitorexit ;
        requestRebind();
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected boolean onFieldChange(int i, Object obj, int j)
    {
        switch(i)
        {
        default:
            return false;

        case 0: // '\0'
            return onChangeProfile((Profile)obj, j);
        }
    }

    public void setHandler(ProfileEditHandlers profileedithandlers)
    {
        mHandler = profileedithandlers;
        this;
        JVM INSTR monitorenter ;
        mDirtyFlags = mDirtyFlags | 2L;
        this;
        JVM INSTR monitorexit ;
        notifyPropertyChanged(2);
        super.requestRebind();
        return;
        profileedithandlers;
        this;
        JVM INSTR monitorexit ;
        throw profileedithandlers;
    }

    public void setProfile(Profile profile)
    {
        updateRegistration(0, profile);
        mProfile = profile;
        this;
        JVM INSTR monitorenter ;
        mDirtyFlags = mDirtyFlags | 1L;
        this;
        JVM INSTR monitorexit ;
        notifyPropertyChanged(5);
        super.requestRebind();
        return;
        profile;
        this;
        JVM INSTR monitorexit ;
        throw profile;
    }

    public boolean setVariable(int i, Object obj)
    {
        switch(i)
        {
        case 3: // '\003'
        case 4: // '\004'
        default:
            return false;

        case 2: // '\002'
            setHandler((ProfileEditHandlers)obj);
            return true;

        case 5: // '\005'
            setProfile((Profile)obj);
            break;
        }
        return true;
    }

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    public final RecyclableImageButton buttonChangeProfileImage;
    public final FloatingActionButton buttonUpdateProfile;
    public final EditText editTextProfileDescription;
    private InverseBindingListener editTextProfileDescriptionandroidTextAttrChanged;
    public final EditText editTextProfileLocation;
    private InverseBindingListener editTextProfileLocationandroidTextAttrChanged;
    public final EditText editTextProfileName;
    private InverseBindingListener editTextProfileNameandroidTextAttrChanged;
    public final EditText editTextProfileUrl;
    private InverseBindingListener editTextProfileUrlandroidTextAttrChanged;
    private long mDirtyFlags;
    private ProfileEditHandlers mHandler;
    private OnClickListenerImpl1 mHandlerOnIconClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl mHandlerOnUpdateButtonClickAndroidViewViewOnClickListener;
    private Profile mProfile;
    private final RelativeLayout mboundView0;


}
