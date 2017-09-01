// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import net.wakamesoba98.sobacha.compatible.*;
import net.wakamesoba98.sobacha.core.LoadMode;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.view.activity.base.TranslucentThemeFragmentActivity;
import net.wakamesoba98.sobacha.view.activity.util.AccountUtil;
import net.wakamesoba98.sobacha.view.card.PostCardManager;
import net.wakamesoba98.sobacha.view.card.UploadCardManager;

public class ShareActivity extends TranslucentThemeFragmentActivity
{

    public ShareActivity()
    {
    }

    private void cardInitialize()
    {
        SobaChaApplication sobachaapplication = (SobaChaApplication)getApplication();
        sobachaapplication.loadPreferences(LoadMode.ONLY_TOKEN);
        postCardManager = new PostCardManager(this, sobachaapplication.getUserAccount(), new UploadCardManager(this));
    }

    private void manageIntent(Intent intent)
    {
        if(intent != null)
        {
            String s = intent.getAction();
            if(s != null)
            {
                if(s.equals("android.intent.action.SEND"))
                {
                    imageUri = (Uri)intent.getParcelableExtra("android.intent.extra.STREAM");
                    String s1;
                    String s3;
                    if(imageUri != null)
                        if(PermissionUtil.checkSelfPermissions(this, new String[] {
    "android.permission.WRITE_EXTERNAL_STORAGE"
}))
                            setImage();
                        else
                            requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", 1);
                    s1 = intent.getStringExtra("android.intent.extra.TEXT");
                    s3 = intent.getStringExtra("android.intent.extra.SUBJECT");
                    intent = s1;
                    if(s3 != null)
                    {
                        intent = s1;
                        if(!s3.isEmpty())
                            intent = (new StringBuilder()).append(s3).append(" ").append(s1).toString();
                    }
                    postCardManager.setText(intent);
                }
            } else
            {
                intent = intent.getExtras();
                String s2 = intent.getString("text");
                long l = intent.getLong("in_reply_to", -1L);
                if(s2 != null)
                {
                    postCardManager.setText(s2);
                    postCardManager.showIme();
                }
                if(l > 0L)
                {
                    postCardManager.setInReplyToId(l);
                    return;
                }
            }
        }
    }

    private void progressInitialize()
    {
        ((ProgressBar)findViewById(0x7f0e0133)).setVisibility(8);
    }

    private void requestPermission(String s, int i)
    {
        if(SystemVersion.isMarshmallowOrLater())
            (new RequestPermission()).request(this, s, i);
    }

    private void setImage()
    {
        postCardManager.setUploadImage(imageUri);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030025);
        getWindow().setSoftInputMode(2);
        bundle = (SobaChaApplication)getApplication();
        if((new AccountUtil()).accountInitialize(this, bundle.getUserId()))
        {
            return;
        } else
        {
            cardInitialize();
            manageIntent(getIntent());
            progressInitialize();
            return;
        }
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
label0:
        {
            super.onRequestPermissionsResult(i, as, ai);
            if(i == 1 && ai.length > 0)
            {
                if(!PermissionUtil.verifyPermissions(ai))
                    break label0;
                setImage();
            }
            return;
        }
        Notificator.toast(this, 0x7f070082);
        finish();
    }

    private static final int PERMISSION_REQUEST_PICK = 1;
    private Uri imageUri;
    private PostCardManager postCardManager;
}
