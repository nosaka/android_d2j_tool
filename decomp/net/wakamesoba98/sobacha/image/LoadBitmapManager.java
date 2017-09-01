// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.image;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.widget.ImageView;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import net.wakamesoba98.sobacha.compatible.Flavor;
import net.wakamesoba98.sobacha.compatible.SystemVersion;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.media.MediaURL;

// Referenced classes of package net.wakamesoba98.sobacha.image:
//            ImageThresholdConstants, ImageUrlReader

public class LoadBitmapManager
    implements ImageThresholdConstants
{
    private class DownloadWorker
        implements Runnable
    {

        public void run()
        {
_L1:
            Object obj = null;
            LoadBitmapItem loadbitmapitem = (LoadBitmapItem)downloadQueue.take();
            Bitmap bitmap = imageUrlReader.getBitmap(context, loadbitmapitem.getMediaURL(), loadbitmapitem.isThumbnail(), userAccount, 320);
            obj = bitmap;
_L2:
            loadbitmapitem.setBitmap(((Bitmap) (obj)));
            obj = new Message();
            obj.obj = loadbitmapitem;
            handler.sendMessage(((Message) (obj)));
              goto _L1
            Exception exception;
            exception;
            exception.printStackTrace();
              goto _L1
            Exception exception1;
            exception1;
            exception1.printStackTrace();
              goto _L2
        }

        final LoadBitmapManager this$0;

        private DownloadWorker()
        {
            this$0 = LoadBitmapManager.this;
            super();
        }

    }

    private class LoadBitmapItem
    {

        public Bitmap getBitmap()
        {
            return bitmap;
        }

        ImageView getImageView()
        {
            return (ImageView)imageViewReference.get();
        }

        MediaURL getMediaURL()
        {
            return mediaURL;
        }

        boolean isRound()
        {
            return round;
        }

        boolean isThumbnail()
        {
            return isThumbnail;
        }

        public void setBitmap(Bitmap bitmap1)
        {
            bitmap = bitmap1;
        }

        private Bitmap bitmap;
        private WeakReference imageViewReference;
        private boolean isThumbnail;
        private MediaURL mediaURL;
        private boolean round;
        final LoadBitmapManager this$0;

        LoadBitmapItem(ImageView imageview, MediaURL mediaurl, boolean flag, boolean flag1)
        {
            this$0 = LoadBitmapManager.this;
            super();
            imageViewReference = new WeakReference(imageview);
            mediaURL = mediaurl;
            isThumbnail = flag;
            round = flag1;
        }
    }


    public LoadBitmapManager(Context context1, UserAccount useraccount)
    {
        context = context1;
        lruCache = new LruCache(MEM_CACHE_SIZE) {

            protected volatile void entryRemoved(boolean flag, Object obj, Object obj1, Object obj2)
            {
                entryRemoved(flag, (String)obj, (Bitmap)obj1, (Bitmap)obj2);
            }

            protected void entryRemoved(boolean flag, String s, Bitmap bitmap, Bitmap bitmap1)
            {
                if(bitmap != null && !bitmap.isRecycled())
                    bitmap.recycle();
            }

            protected volatile int sizeOf(Object obj, Object obj1)
            {
                return sizeOf((String)obj, (Bitmap)obj1);
            }

            protected int sizeOf(String s, Bitmap bitmap)
            {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }

            final LoadBitmapManager this$0;

            
            {
                this$0 = LoadBitmapManager.this;
                super(i);
            }
        }
;
        downloadQueue = new LinkedBlockingQueue();
        imageUrlReader = new ImageUrlReader();
        for(int i = 0; i < 5; i++)
            (new Thread(new DownloadWorker())).start();

        handler = new Handler() {

            public void handleMessage(Message message)
            {
                LoadBitmapItem loadbitmapitem = (LoadBitmapItem)message.obj;
                if(loadbitmapitem != null)
                {
                    String s = loadbitmapitem.getMediaURL().getUrl(loadbitmapitem.isThumbnail()).toString();
                    ImageView imageview = loadbitmapitem.getImageView();
                    if(imageview != null && imageview.getTag().toString().equals(s) && loadbitmapitem.getBitmap() != null && !loadbitmapitem.getBitmap().isRecycled())
                    {
                        Object obj = loadbitmapitem.getBitmap();
                        message = ((Message) (obj));
                        if(isMateCha)
                        {
                            message = ((Message) (obj));
                            if(loadbitmapitem.isRound())
                                message = crop(((Bitmap) (obj)));
                        }
                        obj = (BitmapDrawable)imageview.getDrawable();
                        if(obj != null)
                        {
                            obj = ((BitmapDrawable) (obj)).getBitmap();
                            if(obj != null && !((Bitmap) (obj)).isRecycled())
                                ((Bitmap) (obj)).recycle();
                        }
                        imageview.setImageBitmap(message);
                        if(lruCache.get(s) == null || ((Bitmap)lruCache.get(s)).isRecycled())
                        {
                            lruCache.put(s, message);
                            return;
                        }
                    }
                }
            }

            final LoadBitmapManager this$0;

            
            {
                this$0 = LoadBitmapManager.this;
                super();
            }
        }
;
        userAccount = useraccount;
        isMateCha = Flavor.isMateCha();
    }

    private Bitmap crop(Bitmap bitmap)
    {
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle((float)bitmap.getWidth() / 2.0F, (float)bitmap.getHeight() / 2.0F, (float)Math.min(bitmap.getWidth(), bitmap.getHeight()) / 2.0F, paint);
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return bitmap1;
    }

    public void doDownloadBitmap(ImageView imageview, MediaURL mediaurl, boolean flag, boolean flag1)
    {
        Object obj = mediaurl.getUrl(flag).toString();
        obj = (Bitmap)lruCache.get(obj);
        if(obj != null && !((Bitmap) (obj)).isRecycled())
        {
            imageview.setImageBitmap(((Bitmap) (obj)));
            return;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), 0x7f0200b2);
        obj = bitmap;
        if(isMateCha)
        {
            obj = bitmap;
            if(flag1)
                obj = crop(bitmap);
        }
        imageview.setImageBitmap(((Bitmap) (obj)));
        imageview = new LoadBitmapItem(imageview, mediaurl, flag, flag1);
        downloadQueue.offer(imageview);
    }

    private static final int MEM_CACHE_SIZE;
    private static final int THREAD_MAX_NUM = 5;
    private Context context;
    private BlockingQueue downloadQueue;
    private Handler handler;
    private ImageUrlReader imageUrlReader;
    private boolean isMateCha;
    private LruCache lruCache;
    private UserAccount userAccount;

    static 
    {
        int i;
        if(SystemVersion.isHoneycombOrLater())
            i = 0xa00000;
        else
            i = 0x300000;
        MEM_CACHE_SIZE = i;
    }








}
