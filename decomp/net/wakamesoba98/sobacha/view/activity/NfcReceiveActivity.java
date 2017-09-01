// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.os.AsyncTask;
import android.os.Bundle;
import java.util.List;
import net.wakamesoba98.sobacha.database.MuteDatabase;
import net.wakamesoba98.sobacha.dialog.SpinnerDialog;
import net.wakamesoba98.sobacha.nfc.NfcData;
import net.wakamesoba98.sobacha.view.activity.base.ThemeActivity;

public class NfcReceiveActivity extends ThemeActivity
{
    private class WriteDatabaseTask extends AsyncTask
    {

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((NfcData[])aobj);
        }

        protected transient Void doInBackground(NfcData anfcdata[])
        {
            int i = 0;
            NfcData nfcdata = anfcdata[0];
            if(nfcdata.getDBName().equals("mute"))
            {
                anfcdata = new MuteDatabase(NfcReceiveActivity.this, nfcdata.getDBTable());
                anfcdata.openDatabase();
                List list = anfcdata.getAllMutes();
                String as[] = nfcdata.getDBData();
                for(int j = as.length; i < j; i++)
                {
                    String s = as[i];
                    if(!list.contains(s))
                        anfcdata.putString(s);
                }

                anfcdata.closeDatabase();
            }
            return null;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((Void)obj);
        }

        protected void onPostExecute(Void void1)
        {
            if(dialog != null)
                dialog.dismiss();
        }

        protected void onPreExecute()
        {
            dialog = new SpinnerDialog(NfcReceiveActivity.this);
            dialog.show();
        }

        private SpinnerDialog dialog;
        final NfcReceiveActivity this$0;

        private WriteDatabaseTask()
        {
            this$0 = NfcReceiveActivity.this;
            super();
        }

    }


    public NfcReceiveActivity()
    {
    }

    private void processIntent(Intent intent)
    {
        intent = new String(((NdefMessage)intent.getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES")[0]).getRecords()[0].getPayload());
        (new WriteDatabaseTask()).execute(new NfcData[] {
            new NfcData(intent)
        });
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f030023);
        if("android.nfc.action.NDEF_DISCOVERED".equals(getIntent().getAction()))
            processIntent(getIntent());
    }

    public void onNewIntent(Intent intent)
    {
        setIntent(intent);
    }
}
