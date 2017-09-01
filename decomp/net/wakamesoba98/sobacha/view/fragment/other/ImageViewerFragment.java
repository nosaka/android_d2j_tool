// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.other;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.widget.ImageView;
import android.widget.ProgressBar;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ViewPagerControllable;
import it.sephiroth.android.library.imagezoom.graphics.FastBitmapDrawable;
import java.net.URL;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.image.LoadImageTask;
import net.wakamesoba98.sobacha.twitter.listener.OnLoadedImageListener;
import net.wakamesoba98.sobacha.twitter.media.MediaURL;
import net.wakamesoba98.sobacha.view.activity.ImageViewerActivity;

public class ImageViewerFragment extends Fragment
    implements OnLoadedImageListener
{

    public ImageViewerFragment()
    {
    }

    private void loadImage(MediaURL mediaurl)
    {
        if(mediaurl != null)
        {
            Object obj = (SobaChaApplication)getActivity().getApplication();
            obj = new LoadImageTask(getActivity(), ((SobaChaApplication) (obj)).getUserAccount(), imageView, this);
            imageView.setTag(mediaurl.getUrl(false).toString());
            progressBar.setVisibility(0);
            ((LoadImageTask) (obj)).execute(new MediaURL[] {
                mediaurl
            });
        }
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        imageView = (ImageView)getView().findViewById(0x7f0e00da);
        bundle = (ImageViewTouch)imageView;
        bundle.setDisplayType(it.sephiroth.android.library.imagezoom.ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
        bundle.setViewPagerControllable((ViewPagerControllable)getActivity());
        progressBar = (ProgressBar)getView().findViewById(0x7f0e0133);
        position = getArguments().getInt("position");
        loadImage(new MediaURL(getArguments().getString("url")));
        ((ImageViewerActivity)getActivity()).putImageView(position, imageView);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f030044, viewgroup, false);
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        android.graphics.drawable.Drawable drawable = imageView.getDrawable();
        if(drawable != null)
            ((FastBitmapDrawable)drawable).getBitmap().recycle();
        ((ImageViewerActivity)getActivity()).removeImageView(position);
    }

    public void onLoadedImage()
    {
        progressBar.setVisibility(8);
    }

    private ImageView imageView;
    private int position;
    private ProgressBar progressBar;
}
