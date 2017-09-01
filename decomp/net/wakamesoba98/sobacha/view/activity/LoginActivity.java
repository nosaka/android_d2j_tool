// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.content.pm.*;
import android.net.Uri;
import android.os.*;
import android.view.View;
import android.view.Window;
import android.widget.*;
import net.wakamesoba98.sobacha.consumer.Consumer;
import net.wakamesoba98.sobacha.core.LoadMode;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.database.AccountsIdDatabase;
import net.wakamesoba98.sobacha.database.data.AccountData;
import net.wakamesoba98.sobacha.dialog.SpinnerDialog;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumExtraPrefs;
import net.wakamesoba98.sobacha.view.activity.base.ThemeActivity;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

// Referenced classes of package net.wakamesoba98.sobacha.view.activity:
//            LoginBrowserActivity, AccountsActivity, MainActivity

public class LoginActivity extends ThemeActivity
    implements android.view.View.OnClickListener
{
    private class OAuthListener extends TwitterAdapter
    {

        public void gotOAuthAccessToken(AccessToken accesstoken)
        {
            accessToken = accesstoken;
            twitter.showUser(accesstoken.getUserId());
        }

        public void gotOAuthRequestToken(RequestToken requesttoken)
        {
            dialog.dismiss();
            requestToken = requesttoken;
            PreferenceUtil.putPreference(LoginActivity.this, EnumExtraPrefs.REQUEST_TOKEN, requestToken.getToken());
            PreferenceUtil.putPreference(LoginActivity.this, EnumExtraPrefs.REQUEST_TOKEN_SECRET, requestToken.getTokenSecret());
            String s = (new StringBuilder()).append(requestToken.getAuthorizationURL()).append("&force_login=1").toString();
            requesttoken = new Intent("android.intent.action.VIEW", Uri.parse(s));
            requesttoken = getPackageManager().resolveActivity(requesttoken, 0).activityInfo.applicationInfo.packageName;
            if(requesttoken.equals("com.android.chrome") || requesttoken.equals("org.mozilla.firefox") || requesttoken.equals("org.chromium.arc.helper"))
            {
                requesttoken = new Intent("android.intent.action.VIEW", Uri.parse(s));
            } else
            {
                requesttoken = new Intent(LoginActivity.this, net/wakamesoba98/sobacha/view/activity/LoginBrowserActivity);
                requesttoken.putExtra("url", s);
            }
            startActivity(requesttoken);
        }

        public void gotUserDetail(User user)
        {
            if(dialog != null)
                dialog.dismiss();
            user = new AccountData(user, accessToken.getToken(), accessToken.getTokenSecret());
            AccountsIdDatabase accountsiddatabase = new AccountsIdDatabase(LoginActivity.this);
            accountsiddatabase.openDatabase();
            int i = accountsiddatabase.getCount();
            accountsiddatabase.putAccount(user);
            accountsiddatabase.closeDatabase();
            if(i > 0)
            {
                user = new Intent(LoginActivity.this, net/wakamesoba98/sobacha/view/activity/AccountsActivity);
                startActivity(user);
            } else
            {
                (new Handler(Looper.getMainLooper())).post(user. new Runnable() {

                    public void run()
                    {
                        PreferenceUtil.putPreference(_fld0, EnumExtraPrefs.LAST_USER_ID, Long.valueOf(account.getUserId()));
                        ((SobaChaApplication)getApplication()).loadPreferences(LoadMode.FORCE_ALL);
                        Intent intent = new Intent(_fld0, net/wakamesoba98/sobacha/view/activity/MainActivity);
                        startActivity(intent);
                    }

                    final OAuthListener this$1;
                    final AccountData val$account;

            
            {
                this$1 = final_oauthlistener;
                account = AccountData.this;
                super();
            }
                }
);
            }
            finish();
        }

        public void onException(TwitterException twitterexception, TwitterMethod twittermethod)
        {
            twitterexception.printStackTrace();
            if(dialog != null)
            {
                dialog.dismiss();
                Notificator.toast(LoginActivity.this, 0x7f07008f);
            }
        }

        final LoginActivity this$0;

        private OAuthListener()
        {
            this$0 = LoginActivity.this;
            super();
        }

    }


    public LoginActivity()
    {
    }

    private void cardInitialize()
    {
        ThemeManager thememanager = new ThemeManager(this);
        ImageButton imagebutton = (ImageButton)findViewById(0x7f0e0091);
        imagebutton.setImageResource(thememanager.getThemeDrawableId(0x7f020107));
        imagebutton.setOnClickListener(this);
        ((EditText)findViewById(0x7f0e0090)).setTextColor(thememanager.getThemeColor(0x7f0d00ba));
    }

    public void onClick(View view)
    {
        view.getId();
        JVM INSTR lookupswitch 2: default 32
    //                   2131624063: 33
    //                   2131624081: 64;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        dialog = new SpinnerDialog(this);
        dialog.show();
        twitter.getOAuthRequestTokenAsync("oob");
        return;
_L3:
        view = ((EditText)findViewById(0x7f0e0090)).getText().toString();
        if(view.matches("\\d{7}") && requestToken != null)
        {
            twitter.getOAuthAccessTokenAsync(requestToken, view);
            PreferenceUtil.putPreference(this, EnumExtraPrefs.REQUEST_TOKEN, "");
            PreferenceUtil.putPreference(this, EnumExtraPrefs.REQUEST_TOKEN_SECRET, "");
            dialog = new SpinnerDialog(this);
            dialog.show();
            return;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030020);
        getWindow().setSoftInputMode(2);
        twitter = (new AsyncTwitterFactory()).getInstance();
        twitter.setOAuthConsumer(Consumer.getConsumer(this), Consumer.getSecret(this));
        twitter.addListener(new OAuthListener());
        if(requestToken == null)
        {
            bundle = PreferenceUtil.getStringPreference(this, EnumExtraPrefs.REQUEST_TOKEN);
            String s = PreferenceUtil.getStringPreference(this, EnumExtraPrefs.REQUEST_TOKEN_SECRET);
            if(!"".equals(bundle) && !"".equals(s))
                requestToken = new RequestToken(bundle, s);
        }
        ((Button)findViewById(0x7f0e007f)).setOnClickListener(this);
        cardInitialize();
    }

    private static final String ARC_WELDER_PACKAGE_NAME = "org.chromium.arc.helper";
    private static final String CALLBACK_URL = "oob";
    private static final String CHROME_PACKAGE_NAME = "com.android.chrome";
    private static final String FIREFOX_PACKAGE_NAME = "org.mozilla.firefox";
    private AccessToken accessToken;
    private SpinnerDialog dialog;
    private RequestToken requestToken;
    private AsyncTwitter twitter;




/*
    static RequestToken access$202(LoginActivity loginactivity, RequestToken requesttoken)
    {
        loginactivity.requestToken = requesttoken;
        return requesttoken;
    }

*/



/*
    static AccessToken access$302(LoginActivity loginactivity, AccessToken accesstoken)
    {
        loginactivity.accessToken = accesstoken;
        return accesstoken;
    }

*/

}
