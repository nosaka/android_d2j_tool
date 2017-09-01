// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import java.util.List;
import net.wakamesoba98.sobacha.compatible.SystemVersion;
import net.wakamesoba98.sobacha.database.MuteDatabase;
import net.wakamesoba98.sobacha.nfc.*;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.view.activity.base.ThemeActivity;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;

public class NfcSendActivity extends ThemeActivity
    implements OnNfcPushCompleteListener
{

    public NfcSendActivity()
    {
    }

    private void sendDatabase(NfcManager nfcmanager, String s, String s1)
    {
        if(s.equals("mute"))
        {
            MuteDatabase mutedatabase = new MuteDatabase(this, s1);
            mutedatabase.openDatabase();
            List list = mutedatabase.getAllMutes();
            mutedatabase.closeDatabase();
            nfcmanager.sendText((new NfcData(this, s, 1, s1, (String[])list.toArray(new String[list.size()]))).toCSV());
        }
    }

    private void setThemeImage()
    {
        ThemeManager thememanager = new ThemeManager(this);
        ((ImageView)findViewById(0x7f0e0089)).setImageResource(thememanager.getThemeDrawableId(0x7f0200c6));
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030024);
        setThemeImage();
        Object obj = getIntent();
        if(obj == null)
            return;
        bundle = ((Intent) (obj)).getStringExtra("database");
        obj = ((Intent) (obj)).getStringExtra("table");
        NfcManager nfcmanager = new NfcManager(this, this);
        if(nfcmanager.isNfcAvailable())
        {
            if(SystemVersion.isJellyBeanOrLater())
            {
                if(!nfcmanager.isNfcEnabled())
                {
                    Notificator.toast(this, 0x7f0700dc);
                    startActivity(new Intent("android.settings.NFC_SETTINGS"));
                    return;
                }
                if(!nfcmanager.isBeamEnabled())
                {
                    Notificator.toast(this, 0x7f0700db);
                    startActivity(new Intent("android.settings.NFCSHARING_SETTINGS"));
                    return;
                } else
                {
                    sendDatabase(nfcmanager, bundle, ((String) (obj)));
                    return;
                }
            }
            if(!nfcmanager.isNfcEnabled())
            {
                Notificator.toast(this, 0x7f0700dc);
                startActivity(new Intent("android.settings.WIRELESS_SETTINGS"));
                return;
            } else
            {
                sendDatabase(nfcmanager, bundle, ((String) (obj)));
                return;
            }
        } else
        {
            Notificator.toast(this, 0x7f0700cd);
            finish();
            return;
        }
    }

    public void onNfcPushComplete()
    {
        Notificator.toast(this, 0x7f070104);
        finish();
    }
}
