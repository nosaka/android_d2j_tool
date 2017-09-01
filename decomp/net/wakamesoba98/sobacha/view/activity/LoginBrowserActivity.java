// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.*;
import net.wakamesoba98.sobacha.view.activity.base.ThemeActivity;

public class LoginBrowserActivity extends ThemeActivity
{

    public LoginBrowserActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030021);
        bundle = getIntent();
        if(bundle == null)
        {
            return;
        } else
        {
            bundle = bundle.getStringExtra("url");
            WebView webview = (WebView)findViewById(0x7f0e0080);
            webview.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 4.4.4; Nexus 7 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.89 Safari/537.36");
            webview.setWebViewClient(new WebViewClient() {

                public boolean shouldOverrideUrlLoading(WebView webview1, String s)
                {
                    webview1.loadUrl(s);
                    return false;
                }

                final LoginBrowserActivity this$0;

            
            {
                this$0 = LoginBrowserActivity.this;
                super();
            }
            }
);
            webview.loadUrl(bundle);
            return;
        }
    }

    private static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 4.4.4; Nexus 7 Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.89 Safari/537.36";
}
