// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;


// Referenced classes of package android.support.design.widget:
//            ValueAnimatorCompat, ValueAnimatorCompatImplHoneycombMr1, ValueAnimatorCompatImplGingerbread

class ViewUtils
{

    ViewUtils()
    {
    }

    static ValueAnimatorCompat createAnimator()
    {
        return DEFAULT_ANIMATOR_CREATOR.createAnimator();
    }

    static boolean objectEquals(Object obj, Object obj1)
    {
        return obj == obj1 || obj != null && obj.equals(obj1);
    }

    static android.graphics.PorterDuff.Mode parseTintMode(int i, android.graphics.PorterDuff.Mode mode)
    {
        switch(i)
        {
        default:
            return mode;

        case 3: // '\003'
            return android.graphics.PorterDuff.Mode.SRC_OVER;

        case 5: // '\005'
            return android.graphics.PorterDuff.Mode.SRC_IN;

        case 9: // '\t'
            return android.graphics.PorterDuff.Mode.SRC_ATOP;

        case 14: // '\016'
            return android.graphics.PorterDuff.Mode.MULTIPLY;

        case 15: // '\017'
            return android.graphics.PorterDuff.Mode.SCREEN;
        }
    }

    static final ValueAnimatorCompat.Creator DEFAULT_ANIMATOR_CREATOR = new ValueAnimatorCompat.Creator() {

        public ValueAnimatorCompat createAnimator()
        {
            Object obj;
            if(android.os.Build.VERSION.SDK_INT >= 12)
                obj = new ValueAnimatorCompatImplHoneycombMr1();
            else
                obj = new ValueAnimatorCompatImplGingerbread();
            return new ValueAnimatorCompat(((ValueAnimatorCompat.Impl) (obj)));
        }

    }
;

}
