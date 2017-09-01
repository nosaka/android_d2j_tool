// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.support.design.internal.*;
import android.support.v4.content.ContextCompat;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.view.*;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.*;

// Referenced classes of package android.support.design.widget:
//            ThemeUtils

public class NavigationView extends ScrimInsetsFrameLayout
{
    public static interface OnNavigationItemSelectedListener
    {

        public abstract boolean onNavigationItemSelected(MenuItem menuitem);
    }

    public static class SavedState extends AbsSavedState
    {

        public void writeToParcel(Parcel parcel, int i)
        {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(menuState);
        }

        public static final android.os.Parcelable.Creator CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks() {

            public SavedState createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return new SavedState(parcel, classloader);
            }

            public volatile Object createFromParcel(Parcel parcel, ClassLoader classloader)
            {
                return createFromParcel(parcel, classloader);
            }

            public SavedState[] newArray(int i)
            {
                return new SavedState[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
);
        public Bundle menuState;


        public SavedState(Parcel parcel, ClassLoader classloader)
        {
            super(parcel, classloader);
            menuState = parcel.readBundle(classloader);
        }

        public SavedState(Parcelable parcelable)
        {
            super(parcelable);
        }
    }


    public NavigationView(Context context)
    {
        this(context, null);
    }

    public NavigationView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public NavigationView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        mPresenter = new NavigationMenuPresenter();
        ThemeUtils.checkAppCompatTheme(context);
        mMenu = new NavigationMenu(context);
        TintTypedArray tinttypedarray = TintTypedArray.obtainStyledAttributes(context, attributeset, android.support.design.R.styleable.NavigationView, i, android.support.design.R.style.Widget_Design_NavigationView);
        ViewCompat.setBackground(this, tinttypedarray.getDrawable(android.support.design.R.styleable.NavigationView_android_background));
        if(tinttypedarray.hasValue(android.support.design.R.styleable.NavigationView_elevation))
            ViewCompat.setElevation(this, tinttypedarray.getDimensionPixelSize(android.support.design.R.styleable.NavigationView_elevation, 0));
        ViewCompat.setFitsSystemWindows(this, tinttypedarray.getBoolean(android.support.design.R.styleable.NavigationView_android_fitsSystemWindows, false));
        mMaxWidth = tinttypedarray.getDimensionPixelSize(android.support.design.R.styleable.NavigationView_android_maxWidth, 0);
        boolean flag;
        ColorStateList colorstatelist;
        Object obj;
        if(tinttypedarray.hasValue(android.support.design.R.styleable.NavigationView_itemIconTint))
            colorstatelist = tinttypedarray.getColorStateList(android.support.design.R.styleable.NavigationView_itemIconTint);
        else
            colorstatelist = createDefaultColorStateList(0x1010038);
        flag = false;
        i = 0;
        if(tinttypedarray.hasValue(android.support.design.R.styleable.NavigationView_itemTextAppearance))
        {
            i = tinttypedarray.getResourceId(android.support.design.R.styleable.NavigationView_itemTextAppearance, 0);
            flag = true;
        }
        attributeset = null;
        if(tinttypedarray.hasValue(android.support.design.R.styleable.NavigationView_itemTextColor))
            attributeset = tinttypedarray.getColorStateList(android.support.design.R.styleable.NavigationView_itemTextColor);
        obj = attributeset;
        if(!flag)
        {
            obj = attributeset;
            if(attributeset == null)
                obj = createDefaultColorStateList(0x1010036);
        }
        attributeset = tinttypedarray.getDrawable(android.support.design.R.styleable.NavigationView_itemBackground);
        mMenu.setCallback(new android.support.v7.view.menu.MenuBuilder.Callback() {

            public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
            {
                return mListener != null && mListener.onNavigationItemSelected(menuitem);
            }

            public void onMenuModeChange(MenuBuilder menubuilder)
            {
            }

            final NavigationView this$0;

            
            {
                this$0 = NavigationView.this;
                super();
            }
        }
);
        mPresenter.setId(1);
        mPresenter.initForMenu(context, mMenu);
        mPresenter.setItemIconTintList(colorstatelist);
        if(flag)
            mPresenter.setItemTextAppearance(i);
        mPresenter.setItemTextColor(((ColorStateList) (obj)));
        mPresenter.setItemBackground(attributeset);
        mMenu.addMenuPresenter(mPresenter);
        addView((View)mPresenter.getMenuView(this));
        if(tinttypedarray.hasValue(android.support.design.R.styleable.NavigationView_menu))
            inflateMenu(tinttypedarray.getResourceId(android.support.design.R.styleable.NavigationView_menu, 0));
        if(tinttypedarray.hasValue(android.support.design.R.styleable.NavigationView_headerLayout))
            inflateHeaderView(tinttypedarray.getResourceId(android.support.design.R.styleable.NavigationView_headerLayout, 0));
        tinttypedarray.recycle();
    }

    private ColorStateList createDefaultColorStateList(int i)
    {
        TypedValue typedvalue = new TypedValue();
        if(getContext().getTheme().resolveAttribute(i, typedvalue, true))
        {
            ColorStateList colorstatelist = AppCompatResources.getColorStateList(getContext(), typedvalue.resourceId);
            if(getContext().getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.colorPrimary, typedvalue, true))
            {
                i = typedvalue.data;
                int j = colorstatelist.getDefaultColor();
                int ai[] = DISABLED_STATE_SET;
                int ai1[] = CHECKED_STATE_SET;
                int ai2[] = EMPTY_STATE_SET;
                int k = colorstatelist.getColorForState(DISABLED_STATE_SET, j);
                return new ColorStateList(new int[][] {
                    ai, ai1, ai2
                }, new int[] {
                    k, i, j
                });
            }
        }
        return null;
    }

    private MenuInflater getMenuInflater()
    {
        if(mMenuInflater == null)
            mMenuInflater = new SupportMenuInflater(getContext());
        return mMenuInflater;
    }

    public void addHeaderView(View view)
    {
        mPresenter.addHeaderView(view);
    }

    public int getHeaderCount()
    {
        return mPresenter.getHeaderCount();
    }

    public View getHeaderView(int i)
    {
        return mPresenter.getHeaderView(i);
    }

    public Drawable getItemBackground()
    {
        return mPresenter.getItemBackground();
    }

    public ColorStateList getItemIconTintList()
    {
        return mPresenter.getItemTintList();
    }

    public ColorStateList getItemTextColor()
    {
        return mPresenter.getItemTextColor();
    }

    public Menu getMenu()
    {
        return mMenu;
    }

    public View inflateHeaderView(int i)
    {
        return mPresenter.inflateHeaderView(i);
    }

    public void inflateMenu(int i)
    {
        mPresenter.setUpdateSuspended(true);
        getMenuInflater().inflate(i, mMenu);
        mPresenter.setUpdateSuspended(false);
        mPresenter.updateMenuView(false);
    }

    protected void onInsetsChanged(WindowInsetsCompat windowinsetscompat)
    {
        mPresenter.dispatchApplyWindowInsets(windowinsetscompat);
    }

    protected void onMeasure(int i, int j)
    {
        int k = i;
        android.view.View.MeasureSpec.getMode(i);
        JVM INSTR lookupswitch 3: default 40
    //                   -2147483648: 49
    //                   0: 70
    //                   1073741824: 42;
           goto _L1 _L2 _L3 _L4
_L4:
        break; /* Loop/switch isn't completed */
_L1:
        k = i;
_L6:
        super.onMeasure(k, j);
        return;
_L2:
        k = android.view.View.MeasureSpec.makeMeasureSpec(Math.min(android.view.View.MeasureSpec.getSize(i), mMaxWidth), 0x40000000);
        continue; /* Loop/switch isn't completed */
_L3:
        k = android.view.View.MeasureSpec.makeMeasureSpec(mMaxWidth, 0x40000000);
        if(true) goto _L6; else goto _L5
_L5:
    }

    protected void onRestoreInstanceState(Parcelable parcelable)
    {
        if(!(parcelable instanceof SavedState))
        {
            super.onRestoreInstanceState(parcelable);
            return;
        } else
        {
            parcelable = (SavedState)parcelable;
            super.onRestoreInstanceState(parcelable.getSuperState());
            mMenu.restorePresenterStates(((SavedState) (parcelable)).menuState);
            return;
        }
    }

    protected Parcelable onSaveInstanceState()
    {
        SavedState savedstate = new SavedState(super.onSaveInstanceState());
        savedstate.menuState = new Bundle();
        mMenu.savePresenterStates(savedstate.menuState);
        return savedstate;
    }

    public void removeHeaderView(View view)
    {
        mPresenter.removeHeaderView(view);
    }

    public void setCheckedItem(int i)
    {
        MenuItem menuitem = mMenu.findItem(i);
        if(menuitem != null)
            mPresenter.setCheckedItem((MenuItemImpl)menuitem);
    }

    public void setItemBackground(Drawable drawable)
    {
        mPresenter.setItemBackground(drawable);
    }

    public void setItemBackgroundResource(int i)
    {
        setItemBackground(ContextCompat.getDrawable(getContext(), i));
    }

    public void setItemIconTintList(ColorStateList colorstatelist)
    {
        mPresenter.setItemIconTintList(colorstatelist);
    }

    public void setItemTextAppearance(int i)
    {
        mPresenter.setItemTextAppearance(i);
    }

    public void setItemTextColor(ColorStateList colorstatelist)
    {
        mPresenter.setItemTextColor(colorstatelist);
    }

    public void setNavigationItemSelectedListener(OnNavigationItemSelectedListener onnavigationitemselectedlistener)
    {
        mListener = onnavigationitemselectedlistener;
    }

    private static final int CHECKED_STATE_SET[] = {
        0x10100a0
    };
    private static final int DISABLED_STATE_SET[] = {
        0xfefeff62
    };
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    OnNavigationItemSelectedListener mListener;
    private int mMaxWidth;
    private final NavigationMenu mMenu;
    private MenuInflater mMenuInflater;
    private final NavigationMenuPresenter mPresenter;

}
