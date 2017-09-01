// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.nfc;

import android.app.Activity;
import android.nfc.*;
import net.wakamesoba98.sobacha.compatible.SystemVersion;

// Referenced classes of package net.wakamesoba98.sobacha.nfc:
//            OnNfcPushCompleteListener

public class NfcManager
{
    private class CreateMessage
        implements android.nfc.NfcAdapter.CreateNdefMessageCallback
    {

        private NdefRecord createMime(String s, byte abyte0[])
        {
            return new NdefRecord((short)2, s.getBytes(), new byte[0], abyte0);
        }

        public NdefMessage createNdefMessage(NfcEvent nfcevent)
        {
            nfcevent = activity.getPackageName();
            return new NdefMessage(new NdefRecord[] {
                createMime((new StringBuilder()).append("application/").append(nfcevent).append(".nfc").toString(), text.getBytes()), NdefRecord.createApplicationRecord(nfcevent)
            });
        }

        private String text;
        final NfcManager this$0;

        CreateMessage(String s)
        {
            this$0 = NfcManager.this;
            super();
            text = s;
        }
    }

    private class NfcPushComplete
        implements android.nfc.NfcAdapter.OnNdefPushCompleteCallback
    {

        public void onNdefPushComplete(NfcEvent nfcevent)
        {
            listener.onNfcPushComplete();
        }

        final NfcManager this$0;

        private NfcPushComplete()
        {
            this$0 = NfcManager.this;
            super();
        }

    }


    public NfcManager(Activity activity1, OnNfcPushCompleteListener onnfcpushcompletelistener)
    {
        activity = activity1;
        listener = onnfcpushcompletelistener;
        nfcAdapter = NfcAdapter.getDefaultAdapter(activity1);
    }

    public boolean isBeamEnabled()
    {
        if(SystemVersion.isJellyBeanOrLater())
            return nfcAdapter.isNdefPushEnabled();
        else
            return true;
    }

    public boolean isNfcAvailable()
    {
        return nfcAdapter != null;
    }

    public boolean isNfcEnabled()
    {
        return nfcAdapter.isEnabled();
    }

    public void sendText(String s)
    {
        nfcAdapter.setNdefPushMessageCallback(new CreateMessage(s), activity, new Activity[0]);
        nfcAdapter.setOnNdefPushCompleteCallback(new NfcPushComplete(), activity, new Activity[0]);
    }

    private Activity activity;
    private OnNfcPushCompleteListener listener;
    private NfcAdapter nfcAdapter;


}
