// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.card;

import android.animation.*;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.wakamesoba98.sobacha.compatible.Flavor;
import net.wakamesoba98.sobacha.compatible.SystemVersion;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.database.AccountsIdDatabase;
import net.wakamesoba98.sobacha.database.data.AccountData;
import net.wakamesoba98.sobacha.dialog.ScreenNameDialog;
import net.wakamesoba98.sobacha.dialog.SpinnerDialog;
import net.wakamesoba98.sobacha.image.ImageFileReader;
import net.wakamesoba98.sobacha.image.LoadBitmapManager;
import net.wakamesoba98.sobacha.menu.ImageSelectMenu;
import net.wakamesoba98.sobacha.menu.ProfileButtonMenu;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumExtraPrefs;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.preference.value.EnumShortcutKey;
import net.wakamesoba98.sobacha.twitter.api.StatusActionApiDialog;
import net.wakamesoba98.sobacha.twitter.api.UserApi;
import net.wakamesoba98.sobacha.twitter.listener.*;
import net.wakamesoba98.sobacha.twitter.media.MediaURL;
import net.wakamesoba98.sobacha.twitter.stream.StreamManager;
import net.wakamesoba98.sobacha.view.activity.*;
import net.wakamesoba98.sobacha.view.activity.util.IntentUtil;
import net.wakamesoba98.sobacha.view.autocomplete.AutoCompleteManager;
import net.wakamesoba98.sobacha.view.edittext.ImeDetectableEditText;
import net.wakamesoba98.sobacha.view.imageview.OnTryToUseRecycledBitmapListener;
import net.wakamesoba98.sobacha.view.imageview.RecyclableImageButton;
import net.wakamesoba98.sobacha.view.ime.ImeUtil;
import net.wakamesoba98.sobacha.view.listview.adapter.AdapterHolder;
import net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import net.wakamesoba98.sobacha.view.util.DisplayMetricsUtil;
import twitter4j.*;

// Referenced classes of package net.wakamesoba98.sobacha.view.card:
//            UploadCardManager, StatusCardManager

public class PostCardManager
    implements android.view.View.OnClickListener, OnStatusUpdatedListener, OnDirectMessageSentListener, OnUserDetailGotListener
{
    private class EditTextWatchHandler
        implements TextWatcher
    {

        private void calcLeft(String s)
        {
            int i = pattern.matcher(s).replaceAll("https://t.co/----------").length();
            buttonPost.setText(String.valueOf(140 - i));
        }

        public void afterTextChanged(Editable editable)
        {
        }

        public void beforeTextChanged(CharSequence charsequence, int i, int j, int k)
        {
        }

        public void onTextChanged(CharSequence charsequence, int i, int j, int k)
        {
            text = charsequence.toString();
            calcLeft(text);
        }

        private static final int MAX_LENGTH = 140;
        private static final String T_CO = "https://t.co/----------";
        private static final String URL_REGEX = "https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        private Pattern pattern;
        final PostCardManager this$0;

        EditTextWatchHandler()
        {
            this$0 = PostCardManager.this;
            super();
            pattern = Pattern.compile("https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
        }
    }

    private class HeightAnimator
        implements android.animation.ValueAnimator.AnimatorUpdateListener
    {

        public void onAnimationUpdate(ValueAnimator valueanimator)
        {
            android.view.ViewGroup.LayoutParams layoutparams = target.getLayoutParams();
            layoutparams.height = ((Integer)valueanimator.getAnimatedValue()).intValue();
            target.setLayoutParams(layoutparams);
        }

        private View target;
        final PostCardManager this$0;

        private HeightAnimator(View view)
        {
            this$0 = PostCardManager.this;
            super();
            target = view;
        }

    }

    private class PostShortcutListener
        implements android.view.View.OnKeyListener
    {

        public boolean onKey(View view, int i, KeyEvent keyevent)
        {
            while(postKey == EnumShortcutKey.NONE.ordinal() || postKey == EnumShortcutKey.SHIFT_ENTER.ordinal() && (keyevent.getMetaState() & 1) == 0 || keyevent.getAction() != 0 || i != 66) 
                return false;
            post();
            return true;
        }

        private int postKey;
        final PostCardManager this$0;

        PostShortcutListener()
        {
            this$0 = PostCardManager.this;
            super();
            postKey = PreferenceUtil.getIntPreference(activity, EnumPrefs.POST_KEY);
        }
    }


    public PostCardManager(Activity activity1, UserAccount useraccount, UploadCardManager uploadcardmanager)
    {
        inReplyToId = -1L;
        targetUserId = -1L;
        activity = activity1;
        uploadCardManager = uploadcardmanager;
        themeManager = new ThemeManager(activity1);
        directMessageMode = false;
        isMenuVisible = false;
        text = "";
        viewInitialize();
        setUserAccount(useraccount);
        setQuoteText(null, null, -1L);
    }

    private int getButtonResourceId(View view)
    {
        switch(view.getId())
        {
        case 2131624097: 
        case 2131624099: 
        case 2131624100: 
        default:
            return 0;

        case 2131624098: 
            return 0x7f02008f;

        case 2131624096: 
            return 0x7f020107;

        case 2131624101: 
            return 0x7f0200b2;

        case 2131624102: 
            return 0x7f0200d7;

        case 2131624103: 
            return 0x7f02005f;

        case 2131624104: 
            return 0x7f020100;

        case 2131624105: 
            return 0x7f0200fc;

        case 2131624095: 
            return 0x7f02006f;
        }
    }

    private void loadUserIcon()
    {
        if(userAccount == null)
            return;
        RecyclableImageButton recyclableimagebutton = (RecyclableImageButton)activity.findViewById(0x7f0e00a5);
        long l = userAccount.getId();
        AccountsIdDatabase accountsiddatabase = new AccountsIdDatabase(activity);
        accountsiddatabase.openDatabase();
        if(accountsiddatabase.getCount() > 0)
        {
            final MediaURL mediaURL = accountsiddatabase.getAccount(l).getIconUrl();
            recyclableimagebutton.setTag(mediaURL);
            mediaURL = new MediaURL(mediaURL);
            final LoadBitmapManager manager = ((SobaChaApplication)activity.getApplicationContext()).getLoadBitmapManager();
            recyclableimagebutton.setOnTryToUseRecycledBitmapListener(new OnTryToUseRecycledBitmapListener() {

                public void onTryToUseRecycledBitmap(ImageView imageview)
                {
                    manager.doDownloadBitmap(imageview, mediaURL, false, true);
                }

                final PostCardManager this$0;
                final LoadBitmapManager val$manager;
                final MediaURL val$mediaURL;

            
            {
                this$0 = PostCardManager.this;
                manager = loadbitmapmanager;
                mediaURL = mediaurl;
                super();
            }
            }
);
            manager.doDownloadBitmap(recyclableimagebutton, mediaURL, false, true);
        }
        accountsiddatabase.closeDatabase();
    }

    private void post()
    {
        if(directMessageMode)
        {
            if(text.length() > 0)
            {
                buttonPost.setEnabled(false);
                ProgressBar progressbar = (ProgressBar)activity.findViewById(0x7f0e0133);
                (new StatusActionApiDialog(activity, userAccount, progressbar)).send(this, targetUserId, text, buttonPost);
            }
        } else
        {
            Uri auri[] = uploadCardManager.getUploadImages();
            if(text.length() > 0 || auri != null && auri.length > 0)
            {
                buttonPost.setEnabled(false);
                ProgressBar progressbar1 = (ProgressBar)activity.findViewById(0x7f0e0133);
                (new StatusActionApiDialog(activity, userAccount, progressbar1)).update(this, text, quoteStatusUrl, inReplyToId, auri, buttonPost);
                return;
            }
        }
    }

    private void setChildProperties(ViewGroup viewgroup)
    {
        int i = 0;
_L2:
        Object obj;
label0:
        {
            if(i < viewgroup.getChildCount())
            {
                obj = viewgroup.getChildAt(i);
                if(obj != null)
                    break label0;
            }
            return;
        }
        if(!(obj instanceof ViewGroup))
            break; /* Loop/switch isn't completed */
        setChildProperties((ViewGroup)obj);
_L3:
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        if(obj instanceof ImageButton)
        {
            ImageButton imagebutton = (ImageButton)obj;
            imagebutton.setImageResource(themeManager.getThemeDrawableId(getButtonResourceId(((View) (obj)))));
            imagebutton.setOnClickListener(this);
        } else
        if(obj instanceof Button)
        {
            obj = (Button)obj;
            ((Button) (obj)).setBackgroundResource(themeManager.getThemeDrawableId(0x7f020069));
            ((Button) (obj)).setTextColor(themeManager.getThemeColor(0x7f0d00ba));
            ((Button) (obj)).setOnClickListener(this);
        } else
        if(obj instanceof ImageView)
            ((ImageView)obj).setImageResource(themeManager.getThemeDrawableId(getButtonResourceId(((View) (obj)))));
        else
        if(obj instanceof EditText)
        {
            obj = (EditText)obj;
            ((EditText) (obj)).setTextColor(themeManager.getThemeColor(0x7f0d00ba));
            ((EditText) (obj)).setBackgroundColor(themeManager.getThemeColor(0x7f0d00bd));
        }
          goto _L3
        if(true) goto _L2; else goto _L4
_L4:
    }

    private void showMenu(boolean flag, View view, ImageView imageview)
    {
        if(flag)
        {
            view.setVisibility(0);
            imageview.setImageResource(themeManager.getThemeDrawableId(0x7f02008f));
            return;
        } else
        {
            view.setVisibility(8);
            imageview.setImageResource(themeManager.getThemeDrawableId(0x7f02010d));
            return;
        }
    }

    private void showMenuWithAnimation(boolean flag, final View viewSettings, final ImageView imageMenu)
    {
        int i = DisplayMetricsUtil.convertDipToPixel(activity, 48);
        if(flag)
            if(!isMenuVisible)
            {
                if(closeAnimator != null && closeAnimator.isRunning())
                    closeAnimator.cancel();
                if(openAnimator == null)
                {
                    openAnimator = ValueAnimator.ofInt(new int[] {
                        0, i
                    });
                    openAnimator.addUpdateListener(new HeightAnimator(viewSettings));
                    openAnimator.addListener(new AnimatorListenerAdapter() {

                        public void onAnimationEnd(Animator animator)
                        {
                            imageMenu.setImageResource(themeManager.getThemeDrawableId(0x7f02008f));
                        }

                        public void onAnimationStart(Animator animator)
                        {
                            viewSettings.setVisibility(0);
                        }

                        final PostCardManager this$0;
                        final ImageView val$imageMenu;
                        final View val$viewSettings;

            
            {
                this$0 = PostCardManager.this;
                viewSettings = view;
                imageMenu = imageview;
                super();
            }
                    }
);
                    openAnimator.setDuration(200L);
                    openAnimator.setInterpolator(new LinearOutSlowInInterpolator());
                }
                openAnimator.start();
                return;
            } else
            {
                viewSettings.setVisibility(0);
                imageMenu.setImageResource(themeManager.getThemeDrawableId(0x7f02008f));
                return;
            }
        if(isMenuVisible)
        {
            if(openAnimator != null && openAnimator.isRunning())
                openAnimator.cancel();
            if(closeAnimator == null)
            {
                closeAnimator = ValueAnimator.ofInt(new int[] {
                    i, 0
                });
                closeAnimator.addUpdateListener(new HeightAnimator(viewSettings));
                closeAnimator.addListener(new AnimatorListenerAdapter() {

                    public void onAnimationEnd(Animator animator)
                    {
                        viewSettings.setVisibility(8);
                        imageMenu.setImageResource(themeManager.getThemeDrawableId(0x7f02010d));
                    }

                    final PostCardManager this$0;
                    final ImageView val$imageMenu;
                    final View val$viewSettings;

            
            {
                this$0 = PostCardManager.this;
                viewSettings = view;
                imageMenu = imageview;
                super();
            }
                }
);
                closeAnimator.setDuration(200L);
                closeAnimator.setInterpolator(new FastOutLinearInInterpolator());
            }
            closeAnimator.start();
            return;
        } else
        {
            viewSettings.setVisibility(8);
            imageMenu.setImageResource(themeManager.getThemeDrawableId(0x7f02010d));
            return;
        }
    }

    public String getText()
    {
        return text;
    }

    public void gotUserDetail(User user)
    {
label0:
        {
            if(spinnerDialog != null)
                spinnerDialog.dismiss();
            if(user != null)
            {
                long l = user.getId();
                user = new Bundle();
                user.putLong("user_id", l);
                IntentUtil intentutil = new IntentUtil();
                if(!(activity instanceof ViewPagerActivity))
                    break label0;
                intentutil.startActivityOrAddFragment((ViewPagerActivity)activity, net/wakamesoba98/sobacha/view/activity/UserPageActivity, EnumViewPagerFragment.USER_PAGE, user);
            }
            return;
        }
        Intent intent = new Intent(activity, net/wakamesoba98/sobacha/view/activity/UserPageActivity);
        intent.putExtras(user);
        activity.startActivity(intent);
    }

    void hideIme()
    {
        (new ImeUtil()).hideIme(activity, editText);
    }

    public boolean isMenuVisible()
    {
        return isMenuVisible;
    }

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR tableswitch 2131624091 2131624105: default 80
    //                   2131624091 81
    //                   2131624092 80
    //                   2131624093 80
    //                   2131624094 80
    //                   2131624095 401
    //                   2131624096 80
    //                   2131624097 80
    //                   2131624098 80
    //                   2131624099 101
    //                   2131624100 80
    //                   2131624101 106
    //                   2131624102 137
    //                   2131624103 187
    //                   2131624104 223
    //                   2131624105 366;
           goto _L1 _L2 _L1 _L1 _L1 _L3 _L1 _L1 _L1 _L4 _L1 _L5 _L6 _L7 _L8 _L9
_L1:
        return;
_L2:
        boolean flag;
        if(!isMenuVisible)
            flag = true;
        else
            flag = false;
        setMenuVisibility(flag);
        return;
_L4:
        post();
        return;
_L5:
        (new ProfileButtonMenu((ViewPagerActivity)activity, userAccount.getId())).show(view.findViewById(0x7f0e00a5));
        return;
_L6:
        view = new ScreenNameDialog() {

            public void onPositiveButtonClick(String s)
            {
                if(!"".equals(s.trim()))
                {
                    spinnerDialog = new SpinnerDialog(activity);
                    spinnerDialog.show();
                    (new UserApi(activity, userAccount)).getUserDetail(PostCardManager.this, s.trim());
                }
            }

            final PostCardManager this$0;

            
            {
                this$0 = PostCardManager.this;
                super();
            }
        }
;
        view.setFriends((new AutoCompleteManager()).loadFriendsDatabase(activity, userAccount.getId()));
        view.setButtonText(0x7f070108);
        view.build(activity);
        return;
_L7:
        if(uploadCardManager.getCount() < 4)
        {
            (new ImageSelectMenu((ViewPagerActivity)activity)).show(view.findViewById(0x7f0e00a7));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        boolean flag1;
        flag1 = PreferenceUtil.getBooleanPreference(activity, EnumPrefs.KEEP_SCREEN_ON);
        view = userAccount.getStreamManager();
        if(view == null)
            continue; /* Loop/switch isn't completed */
        if(!view.isEnabled())
            break; /* Loop/switch isn't completed */
        view.shutdown();
        Notificator.toast(activity, 0x7f070045);
        PreferenceUtil.putPreference(activity, EnumExtraPrefs.USER_STREAM, Boolean.valueOf(false));
        if(flag1)
        {
            activity.getWindow().clearFlags(128);
            return;
        }
        if(true) goto _L1; else goto _L10
_L10:
        view.user();
        if(activity instanceof AdapterHolder)
            view.setAdapters((AdapterHolder)activity);
        Notificator.toast(activity, 0x7f070039);
        PreferenceUtil.putPreference(activity, EnumExtraPrefs.USER_STREAM, Boolean.valueOf(true));
        if(flag1)
        {
            activity.getWindow().addFlags(128);
            return;
        }
          goto _L1
_L9:
        view = new Intent(activity, net/wakamesoba98/sobacha/view/activity/AppPreferenceActivity);
        view.setData(Uri.parse("sobachapref://0"));
        activity.startActivity(view);
        return;
_L3:
        setQuoteText(null, null, -1L);
        return;
    }

    void removeReplyToUser(String s)
    {
        if(inReplyToId > 0L)
            setText(text.replaceFirst((new StringBuilder()).append("@").append(s).append("\\s").toString(), ""));
    }

    public void sentDirectMessage(DirectMessage directmessage)
    {
        ((ProgressBar)activity.findViewById(0x7f0e0133)).setVisibility(8);
        buttonPost.setEnabled(true);
        if(directmessage == null)
        {
            return;
        } else
        {
            setText("");
            ((ViewPagerActivity)activity).getStatusCard().close();
            hideIme();
            return;
        }
    }

    public void setDirectMessageMode(boolean flag)
    {
        directMessageMode = flag;
        if(flag)
        {
            editText.setHint(ResourceHelper.getString(activity, 0x7f0700c5));
            return;
        } else
        {
            editText.setHint(ResourceHelper.getString(activity, 0x7f070125));
            return;
        }
    }

    public void setInReplyToId(long l)
    {
        inReplyToId = l;
    }

    public void setMenuVisibility(boolean flag)
    {
        View view = activity.findViewById(0x7f0e0099);
        ImageView imageview = (ImageView)activity.findViewById(0x7f0e00a2);
        if(Flavor.isMateCha())
        {
            if(SystemVersion.isHoneycombOrLater())
                showMenuWithAnimation(flag, view, imageview);
            else
                showMenu(flag, view, imageview);
        } else
        {
            showMenu(flag, view, imageview);
        }
        isMenuVisible = flag;
    }

    void setQuoteText(String s, String s1, long l)
    {
        TextView textview = (TextView)activity.findViewById(0x7f0e009e);
        View view = activity.findViewById(0x7f0e009d);
        view.setBackgroundResource(themeManager.getThemeDrawableId(0x7f020073));
        quoteStatusUrl = s1;
        quoteStatusText = s;
        setInReplyToId(l);
        if(quoteStatusText == null)
        {
            view.setVisibility(8);
            textview.setText("");
            return;
        } else
        {
            view.setVisibility(0);
            textview.setText(quoteStatusText);
            showIme();
            return;
        }
    }

    void setReplyToUser(String s)
    {
        setReplyToUser(s, null);
    }

    void setReplyToUser(String s, UserMentionEntity ausermentionentity[])
    {
        String s1 = "";
        if(!text.contains((new StringBuilder()).append("@").append(s).toString()))
        {
            s1 = (new StringBuilder()).append("").append("@").toString();
            s = (new StringBuilder()).append(s1).append(s).toString();
            s1 = (new StringBuilder()).append(s).append(" ").toString();
        }
        s = s1;
        if(ausermentionentity != null)
        {
            s = s1;
            if(ausermentionentity.length > 0)
            {
                int j = ausermentionentity.length;
                int i = 0;
                do
                {
                    s = s1;
                    if(i >= j)
                        break;
                    UserMentionEntity usermentionentity = ausermentionentity[i];
                    s = s1;
                    if(usermentionentity.getId() != userAccount.getId())
                    {
                        s = s1;
                        if(!text.contains((new StringBuilder()).append("@").append(usermentionentity.getScreenName()).toString()))
                        {
                            s = s1;
                            if(!s1.contains((new StringBuilder()).append("@").append(usermentionentity.getScreenName()).toString()))
                            {
                                s = (new StringBuilder()).append(s1).append("@").toString();
                                s = (new StringBuilder()).append(s).append(usermentionentity.getScreenName()).toString();
                                s = (new StringBuilder()).append(s).append(" ").toString();
                            }
                        }
                    }
                    i++;
                    s1 = s;
                } while(true);
            }
        }
        setText((new StringBuilder()).append(s).append(text).toString());
        showIme();
    }

    public void setStreamButtonResource(boolean flag)
    {
        ImageButton imagebutton = (ImageButton)activity.findViewById(0x7f0e00a8);
        if(flag)
        {
            imagebutton.setImageResource(themeManager.getThemeDrawableId(0x7f020102));
            return;
        } else
        {
            imagebutton.setImageResource(themeManager.getThemeDrawableId(0x7f020100));
            return;
        }
    }

    public void setTargetUserId(long l)
    {
        targetUserId = l;
    }

    public void setText(String s)
    {
        text = s;
        editText.setText(s);
    }

    public void setUploadImage(Uri uri)
    {
        android.graphics.Bitmap bitmap = (new ImageFileReader()).getThumbnail(activity, uri);
        if(bitmap != null)
        {
            uploadCardManager.add(uri, bitmap);
            return;
        } else
        {
            Notificator.toast(activity, 0x7f070085);
            return;
        }
    }

    public void setUserAccount(UserAccount useraccount)
    {
        userAccount = useraccount;
        loadUserIcon();
    }

    public void showIme()
    {
        (new ImeUtil()).showIme(activity, editText);
    }

    public void updatedStatus(Status status)
    {
        ((ProgressBar)activity.findViewById(0x7f0e0133)).setVisibility(8);
        buttonPost.setEnabled(true);
        if(status == null)
            return;
        setText("");
        setQuoteText(null, null, -1L);
        setInReplyToId(-1L);
        if(activity instanceof ShareActivity)
        {
            activity.finish();
            return;
        } else
        {
            uploadCardManager.removeAll();
            ((ViewPagerActivity)activity).getStatusCard().close();
            hideIme();
            return;
        }
    }

    public void viewInitialize()
    {
        editText = (ImeDetectableEditText)activity.findViewById(0x7f0e009c);
        buttonPost = (Button)activity.findViewById(0x7f0e00a3);
        setText(text);
        setChildProperties((ViewGroup)activity.findViewById(0x7f0e0098));
        if(!(activity instanceof ViewPagerActivity))
        {
            ImageView imageview = (ImageView)activity.findViewById(0x7f0e00a2);
            ((Button)activity.findViewById(0x7f0e009b)).setVisibility(8);
            imageview.setVisibility(8);
        }
        ImeDetectableEditText imedetectableedittext = editText;
        int i = PreferenceUtil.getIntPreference(activity, EnumPrefs.TWEET_HINT);
        imedetectableedittext.setHint((new int[] {
            0x7f07013d, 0x7f070125
        })[i]);
        editText.addTextChangedListener(new EditTextWatchHandler());
        editText.setOnKeyListener(new PostShortcutListener());
        setMenuVisibility(isMenuVisible);
        loadUserIcon();
        if(inReplyToId > 0L)
            setQuoteText(quoteStatusText, quoteStatusUrl, inReplyToId);
        else
            setQuoteText(null, null, -1L);
        setStreamButtonResource(PreferenceUtil.getBooleanPreference(activity, EnumExtraPrefs.USER_STREAM));
    }

    private static final int MENU_DURATION = 200;
    private Activity activity;
    private Button buttonPost;
    private ValueAnimator closeAnimator;
    private boolean directMessageMode;
    private ImeDetectableEditText editText;
    private long inReplyToId;
    private boolean isMenuVisible;
    private ValueAnimator openAnimator;
    private String quoteStatusText;
    private String quoteStatusUrl;
    private SpinnerDialog spinnerDialog;
    private long targetUserId;
    private String text;
    private ThemeManager themeManager;
    private UploadCardManager uploadCardManager;
    private UserAccount userAccount;



/*
    static SpinnerDialog access$002(PostCardManager postcardmanager, SpinnerDialog spinnerdialog)
    {
        postcardmanager.spinnerDialog = spinnerdialog;
        return spinnerdialog;
    }

*/





/*
    static String access$302(PostCardManager postcardmanager, String s)
    {
        postcardmanager.text = s;
        return s;
    }

*/



}
