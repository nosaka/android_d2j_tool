// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.design.widget;

import android.graphics.*;
import android.view.*;

class ViewGroupUtilsHoneycomb
{

    ViewGroupUtilsHoneycomb()
    {
    }

    static void offsetDescendantMatrix(ViewParent viewparent, View view, Matrix matrix)
    {
        Object obj = view.getParent();
        if((obj instanceof View) && obj != viewparent)
        {
            obj = (View)obj;
            offsetDescendantMatrix(viewparent, ((View) (obj)), matrix);
            matrix.preTranslate(-((View) (obj)).getScrollX(), -((View) (obj)).getScrollY());
        }
        matrix.preTranslate(view.getLeft(), view.getTop());
        if(!view.getMatrix().isIdentity())
            matrix.preConcat(view.getMatrix());
    }

    public static void offsetDescendantRect(ViewGroup viewgroup, View view, Rect rect)
    {
        Matrix matrix = (Matrix)sMatrix.get();
        if(matrix == null)
        {
            matrix = new Matrix();
            sMatrix.set(matrix);
        } else
        {
            matrix.reset();
        }
        offsetDescendantMatrix(viewgroup, view, matrix);
        view = (RectF)sRectF.get();
        viewgroup = view;
        if(view == null)
        {
            viewgroup = new RectF();
            sRectF.set(viewgroup);
        }
        viewgroup.set(rect);
        matrix.mapRect(viewgroup);
        rect.set((int)(((RectF) (viewgroup)).left + 0.5F), (int)(((RectF) (viewgroup)).top + 0.5F), (int)(((RectF) (viewgroup)).right + 0.5F), (int)(((RectF) (viewgroup)).bottom + 0.5F));
    }

    private static final ThreadLocal sMatrix = new ThreadLocal();
    private static final ThreadLocal sRectF = new ThreadLocal();

}
