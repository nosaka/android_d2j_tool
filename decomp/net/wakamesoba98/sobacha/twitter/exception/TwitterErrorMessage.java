// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.exception;

import android.content.Context;
import java.text.DateFormat;
import java.util.Date;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import twitter4j.RateLimitStatus;
import twitter4j.TwitterException;

public class TwitterErrorMessage
{

    public TwitterErrorMessage()
    {
    }

    private static int getErrorCode(TwitterException twitterexception)
    {
        switch(twitterexception.getErrorCode())
        {
        default:
            return getHttpErrorCode(twitterexception);

        case 32: // ' '
            return 0x7f070050;

        case 34: // '"'
            return 0x7f070051;

        case 64: // '@'
            return 0x7f070052;

        case 68: // 'D'
            return 0x7f070053;

        case 89: // 'Y'
            return 0x7f070055;

        case 92: // '\\'
            return 0x7f070056;

        case 130: 
            return 0x7f070057;

        case 131: 
            return 0x7f070058;

        case 135: 
            return 0x7f070059;

        case 161: 
            return 0x7f07005a;

        case 179: 
            return 0x7f07005b;

        case 185: 
            return 0x7f07005c;

        case 187: 
            return 0x7f07005d;

        case 215: 
            return 0x7f07005e;

        case 226: 
            return 0x7f07005f;

        case 231: 
            return 0x7f070060;

        case 251: 
            return 0x7f070061;

        case 261: 
            return 0x7f070062;

        case 271: 
            return 0x7f070063;

        case 272: 
            return 0x7f070064;
        }
    }

    public static String getErrorMessage(Context context, TwitterException twitterexception)
    {
        switch(twitterexception.getErrorCode())
        {
        default:
            return ResourceHelper.getString(context, getErrorCode(twitterexception));

        case 88: // 'X'
            context = ResourceHelper.getString(context, 0x7f070054);
            break;
        }
        twitterexception = new Date((long)twitterexception.getRateLimitStatus().getResetTimeInSeconds() * 1000L);
        return String.format(context, new Object[] {
            DateFormat.getDateTimeInstance(3, 3).format(twitterexception)
        });
    }

    private static int getHttpErrorCode(TwitterException twitterexception)
    {
        switch(twitterexception.getStatusCode())
        {
        default:
            return getOtherErrorCode(twitterexception);

        case 400: 
            return 0x7f070066;

        case 401: 
            return 0x7f070067;

        case 403: 
            return 0x7f070068;

        case 404: 
            return 0x7f070069;

        case 406: 
            return 0x7f07006a;

        case 410: 
            return 0x7f07006b;

        case 420: 
            return 0x7f07006c;

        case 422: 
            return 0x7f07006d;

        case 429: 
            return 0x7f07006e;

        case 500: 
            return 0x7f07006f;

        case 502: 
            return 0x7f070070;

        case 503: 
            return 0x7f070071;

        case 504: 
            return 0x7f070072;
        }
    }

    private static int getOtherErrorCode(TwitterException twitterexception)
    {
        return !twitterexception.isCausedByNetworkIssue() ? 0x7f070065 : 0x7f07012c;
    }
}
