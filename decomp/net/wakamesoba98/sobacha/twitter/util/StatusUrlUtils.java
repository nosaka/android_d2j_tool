// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.twitter.util;

import java.util.ArrayList;
import java.util.List;
import twitter4j.*;

public class StatusUrlUtils
{

    public StatusUrlUtils()
    {
    }

    private String replaceToDisplayURL(String s, URLEntity aurlentity[], MediaEntity amediaentity[])
    {
        boolean flag = false;
        int k = aurlentity.length;
        for(int i = 0; i < k; i++)
        {
            URLEntity urlentity = aurlentity[i];
            s = s.replace(urlentity.getURL(), urlentity.getDisplayURL());
        }

        k = amediaentity.length;
        for(int j = ((flag) ? 1 : 0); j < k; j++)
        {
            aurlentity = amediaentity[j];
            s = s.replace(aurlentity.getURL(), aurlentity.getDisplayURL());
        }

        return s;
    }

    public String createStatusUrl(String s, long l)
    {
        return (new StringBuilder()).append("https://twitter.com/").append(s).append("/status/").append(l).toString();
    }

    public String getClientName(Status status)
    {
        Status status1 = status;
        if(status.isRetweet())
            status1 = status.getRetweetedStatus();
        status = status1.getSource().split("[<>]");
        if(status.length > 1)
            return status[2];
        else
            return status[0];
    }

    public long getStatusId(String s)
    {
        if(!s.matches("https?://(mobile\\.)?twitter\\.com/(i/)?[a-zA-Z0-9_]+/status/\\d+"))
            break MISSING_BLOCK_LABEL_33;
        s = s.split("/");
        long l = Long.parseLong(s[s.length - 1]);
        return l;
        s;
        s.printStackTrace();
        return -1L;
    }

    public String replaceToDisplayURL(String s, URLEntity urlentity)
    {
        return s.replace(urlentity.getURL(), urlentity.getDisplayURL());
    }

    public String replaceToDisplayURL(String s, URLEntity aurlentity[])
    {
        int j = aurlentity.length;
        for(int i = 0; i < j; i++)
        {
            URLEntity urlentity = aurlentity[i];
            s = s.replace(urlentity.getURL(), urlentity.getDisplayURL());
        }

        return s;
    }

    public String replaceToDisplayURL(DirectMessage directmessage)
    {
        return replaceToDisplayURL(directmessage.getText(), directmessage.getURLEntities(), directmessage.getMediaEntities());
    }

    public String replaceToDisplayURL(Status status)
    {
        Status status1 = status;
        if(status.isRetweet())
            status1 = status.getRetweetedStatus();
        if(status1.getQuotedStatus() != null)
        {
            StatusUrlUtils statusurlutils = new StatusUrlUtils();
            ArrayList arraylist = new ArrayList();
            status = status1.getText();
            URLEntity aurlentity[] = status1.getURLEntities();
            int j = aurlentity.length;
            int i = 0;
            while(i < j) 
            {
                URLEntity urlentity = aurlentity[i];
                if(statusurlutils.getStatusId(urlentity.getExpandedURL()) == status1.getQuotedStatusId())
                    status = status.replace(urlentity.getURL(), "");
                else
                    arraylist.add(urlentity);
                i++;
            }
            return replaceToDisplayURL(((String) (status)), (URLEntity[])arraylist.toArray(new URLEntity[arraylist.size()]), status1.getMediaEntities());
        } else
        {
            return replaceToDisplayURL(status1.getText(), status1.getURLEntities(), status1.getMediaEntities());
        }
    }

    public static final String STATUS_REGEX = "https?://(mobile\\.)?twitter\\.com/(i/)?[a-zA-Z0-9_]+/status/\\d+";
    private static final String STATUS_URL_ADD = "/status/";
    private static final String STATUS_URL_DOMAIN = "https://twitter.com/";
}
