// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.api;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.*;
import java.io.*;
import java.util.*;
import net.wakamesoba98.sobacha.image.*;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.exception.TwitterErrorMessage;
import net.wakamesoba98.sobacha.twitter.listener.OnStatusUpdatedListener;
import net.wakamesoba98.sobacha.twitter.util.TwitterUtils;
import twitter4j.*;
import twitter4j.media.ImageUpload;

class UpdateApi
    implements ImageThresholdConstants
{
    private class ImagePrepareTask extends AsyncTask
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient String doInBackground(String as[])
        {
            Object obj;
            String s;
            UriResolver uriresolver;
            s = as[0];
            int k = PreferenceUtil.getIntPreference(context, EnumPrefs.AUTO_RESIZE);
            as = new ImageFileWriter();
            obj = new ArrayList();
            uriresolver = new UriResolver();
            int i = 0;
            while(i < images.length) 
            {
                long l1 = uriresolver.getFileSize(context, images[i]);
                if(k > 0)
                    ((List) (obj)).add(as.outputResizeImage(context, images[i], k, i));
                else
                if(l1 > 0x300000L)
                    ((List) (obj)).add(as.outputResizeImage(context, images[i], 1600, i));
                else
                    ((List) (obj)).add(images[i]);
                i++;
            }
            images = (Uri[])((List) (obj)).toArray(new Uri[((List) (obj)).size()]);
            obj = PreferenceUtil.getStringPreference(context, EnumPrefs.UPLOAD_PROVIDER);
            if(!((String) (obj)).equals("TWITPIC") && !((String) (obj)).equals("IMG_LY") && !((String) (obj)).equals("TWIPPLE")) goto _L2; else goto _L1
_L1:
            Object obj7 = (new TwitterUtils()).getImageUploadInstance(context, userAccount.getAccessToken(context));
            if(images == null || images.length <= 0) goto _L4; else goto _L3
_L3:
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            ArrayList arraylist;
            arraylist = null;
            obj4 = null;
            obj = null;
            obj5 = null;
            obj2 = arraylist;
            obj3 = obj4;
            as = ((String []) (obj));
            Object obj6 = new ArrayList();
            obj2 = arraylist;
            obj3 = obj4;
            as = ((String []) (obj));
            Uri auri[] = images;
            obj2 = arraylist;
            obj3 = obj4;
            as = ((String []) (obj));
            int l = auri.length;
            int j;
            j = 0;
            obj = obj5;
_L9:
            if(j >= l) goto _L6; else goto _L5
_L5:
            obj5 = auri[j];
            if(obj5 != null) goto _L8; else goto _L7
_L7:
            obj2 = obj;
            obj3 = obj;
            as = ((String []) (obj));
            Notificator.toast(context, 0x7f070097);
            if(obj != null)
                try
                {
                    ((InputStream) (obj)).close();
                }
                // Misplaced declaration of an exception variable
                catch(String as[])
                {
                    as.printStackTrace();
                    return null;
                }
_L13:
            return null;
_L8:
            obj2 = obj;
            obj3 = obj;
            as = ((String []) (obj));
            obj4 = uriresolver.getFileName(context, ((Uri) (obj5)));
            obj2 = obj;
            obj3 = obj;
            as = ((String []) (obj));
            obj = context.getContentResolver().openInputStream(((Uri) (obj5)));
            obj2 = obj;
            obj3 = obj;
            as = ((String []) (obj));
            ((List) (obj6)).add(((ImageUpload) (obj7)).upload(((String) (obj4)), ((InputStream) (obj)), s));
            j++;
              goto _L9
_L6:
            obj2 = obj;
            obj3 = obj;
            as = ((String []) (obj));
            obj4 = ((List) (obj6)).iterator();
_L11:
            obj2 = obj;
            obj3 = obj;
            as = ((String []) (obj));
            if(!((Iterator) (obj4)).hasNext())
                break; /* Loop/switch isn't completed */
            obj2 = obj;
            obj3 = obj;
            as = ((String []) (obj));
            obj5 = (String)((Iterator) (obj4)).next();
            obj2 = obj;
            obj3 = obj;
            as = ((String []) (obj));
            s = (new StringBuilder()).append(s).append(" ").append(((String) (obj5))).toString();
            if(true) goto _L11; else goto _L10
_L10:
            as = s;
            if(obj == null)
                break MISSING_BLOCK_LABEL_635;
            ((InputStream) (obj)).close();
            as = s;
_L12:
            return as;
            as;
            as.printStackTrace();
            as = s;
              goto _L12
            obj;
            as = ((String []) (obj2));
            ((TwitterException) (obj)).printStackTrace();
            as = ((String []) (obj2));
            obj = TwitterErrorMessage.getErrorMessage(context, ((TwitterException) (obj)));
            as = ((String []) (obj2));
            Notificator.toast(context, 0x7f070097, ((String) (obj)));
            if(obj2 != null)
            {
                try
                {
                    ((InputStream) (obj2)).close();
                }
                // Misplaced declaration of an exception variable
                catch(String as[])
                {
                    as.printStackTrace();
                    return null;
                }
                return null;
            }
              goto _L13
            obj;
            as = ((String []) (obj3));
            ((FileNotFoundException) (obj)).printStackTrace();
            if(obj3 == null) goto _L13; else goto _L14
_L14:
            try
            {
                ((InputStream) (obj3)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String as[])
            {
                as.printStackTrace();
                return null;
            }
            return null;
            obj;
            if(as != null)
                try
                {
                    as.close();
                }
                // Misplaced declaration of an exception variable
                catch(String as[])
                {
                    as.printStackTrace();
                }
            throw obj;
_L4:
            Notificator.toast(context, 0x7f070097);
            as = s;
              goto _L12
_L2:
            as = s;
            if(!((String) (obj)).equals("TWITTER")) goto _L12; else goto _L15
_L15:
            obj6 = (new TwitterUtils()).getTwitterInstance(context, userAccount.getAccessToken(context));
            arraylist = new ArrayList();
            auri = images;
            l = auri.length;
            j = 0;
_L16:
            Uri uri;
            if(j >= l)
                break MISSING_BLOCK_LABEL_1139;
            uri = auri[j];
            obj3 = null;
            obj5 = null;
            obj4 = null;
            if(uri != null)
                break MISSING_BLOCK_LABEL_916;
            obj = obj4;
            obj2 = obj3;
            as = ((String []) (obj5));
            Notificator.toast(context, 0x7f070097);
            if(false)
            {
                try
                {
                    throw new NullPointerException();
                }
                // Misplaced declaration of an exception variable
                catch(String as[])
                {
                    as.printStackTrace();
                }
                return null;
            }
              goto _L13
            obj = obj4;
            obj2 = obj3;
            as = ((String []) (obj5));
            obj7 = uriresolver.getFileName(context, uri);
            obj = obj4;
            obj2 = obj3;
            as = ((String []) (obj5));
            obj3 = context.getContentResolver().openInputStream(uri);
            obj = obj3;
            obj2 = obj3;
            as = ((String []) (obj3));
            arraylist.add(((Twitter) (obj6)).uploadMedia(((String) (obj7)), ((InputStream) (obj3))));
            if(obj3 != null)
                try
                {
                    ((InputStream) (obj3)).close();
                }
                // Misplaced declaration of an exception variable
                catch(String as[])
                {
                    as.printStackTrace();
                }
            j++;
              goto _L16
            obj2;
            as = ((String []) (obj));
            ((TwitterException) (obj2)).printStackTrace();
            as = ((String []) (obj));
            obj2 = TwitterErrorMessage.getErrorMessage(context, ((TwitterException) (obj2)));
            as = ((String []) (obj));
            Notificator.toast(context, 0x7f070097, ((String) (obj2)));
            if(obj != null)
            {
                try
                {
                    ((InputStream) (obj)).close();
                }
                // Misplaced declaration of an exception variable
                catch(String as[])
                {
                    as.printStackTrace();
                    return null;
                }
                return null;
            }
              goto _L13
            Object obj1;
            obj1;
            as = ((String []) (obj2));
            ((FileNotFoundException) (obj1)).printStackTrace();
            if(obj2 == null) goto _L13; else goto _L17
_L17:
            try
            {
                ((InputStream) (obj2)).close();
            }
            // Misplaced declaration of an exception variable
            catch(String as[])
            {
                as.printStackTrace();
                return null;
            }
            return null;
            obj1;
            if(as != null)
                try
                {
                    as.close();
                }
                // Misplaced declaration of an exception variable
                catch(String as[])
                {
                    as.printStackTrace();
                }
            throw obj1;
            l = arraylist.size();
            ids = new long[l];
            j = 0;
_L19:
            as = s;
            if(j >= l) goto _L12; else goto _L18
_L18:
            ids[j] = ((UploadedMedia)arraylist.get(j)).getMediaId();
            j++;
              goto _L19
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((String)obj);
        }

        protected void onPostExecute(String s)
        {
            if(s != null)
                updateStatus(listener, s, attachmentUrl, inReplyToId, ids);
        }

        private String attachmentUrl;
        private long ids[];
        private Uri images[];
        private long inReplyToId;
        private OnStatusUpdatedListener listener;
        final UpdateApi this$0;

        ImagePrepareTask(OnStatusUpdatedListener onstatusupdatedlistener, String s, long l, Uri auri[])
        {
            this$0 = UpdateApi.this;
            super();
            ids = new long[0];
            listener = onstatusupdatedlistener;
            inReplyToId = l;
            attachmentUrl = s;
            images = auri;
        }
    }


    UpdateApi(Context context1, UserAccount useraccount)
    {
        twitterUtils = new TwitterUtils();
        context = context1;
        userAccount = useraccount;
    }

    private void updateStatus(final OnStatusUpdatedListener listener, String s, String s1, long l, long al[])
    {
        AsyncTwitter asynctwitter = twitterUtils.getAsyncTwitterInstance(context, userAccount.getAccessToken(context));
        asynctwitter.addListener(new TwitterAdapter() {

            public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
            {
                twitterexception.printStackTrace();
                twitterexception = TwitterErrorMessage.getErrorMessage(context, twitterexception);
                Notificator.toast(context, 0x7f070093, twitterexception);
                (new Handler(Looper.getMainLooper())).post(new Runnable() {

                    public void run()
                    {
                        listener.updatedStatus(null);
                    }

                    final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
                }
);
            }

            public void updatedStatus(Status status)
            {
                Notificator.toast(context, 0x7f070128);
                (new Handler(Looper.getMainLooper())).post(status. new Runnable() {

                    public void run()
                    {
                        listener.updatedStatus(status);
                    }

                    final _cls1 this$1;
                    final Status val$status;

            
            {
                this$1 = final__pcls1;
                status = Status.this;
                super();
            }
                }
);
            }

            final UpdateApi this$0;
            final OnStatusUpdatedListener val$listener;

            
            {
                this$0 = UpdateApi.this;
                listener = onstatusupdatedlistener;
                super();
            }
        }
);
        listener = new StatusUpdate(s);
        if(l > 0L)
            listener.setInReplyToStatusId(l);
        if(al != null && al.length > 0)
            listener.setMediaIds(al);
        if(s1 != null)
            listener.setAttachmentUrl(s1);
        asynctwitter.updateStatus(listener);
    }

    void update(OnStatusUpdatedListener onstatusupdatedlistener, String s, String s1, long l, Uri auri[])
    {
        if(auri != null && auri.length > 0)
        {
            (new ImagePrepareTask(onstatusupdatedlistener, s1, l, auri)).execute(new String[] {
                s
            });
            return;
        } else
        {
            updateStatus(onstatusupdatedlistener, s, s1, l, new long[0]);
            return;
        }
    }

    private Context context;
    private TwitterUtils twitterUtils;
    private UserAccount userAccount;



}
