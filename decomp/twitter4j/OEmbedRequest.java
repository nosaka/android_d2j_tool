// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package twitter4j:
//            HttpParameter, StringUtil

public final class OEmbedRequest
    implements Serializable
{
    public static final class Align extends Enum
    {

        public static Align valueOf(String s)
        {
            return (Align)Enum.valueOf(twitter4j/OEmbedRequest$Align, s);
        }

        public static Align[] values()
        {
            return (Align[])$VALUES.clone();
        }

        private static final Align $VALUES[];
        public static final Align CENTER;
        public static final Align LEFT;
        public static final Align NONE;
        public static final Align RIGHT;

        static 
        {
            LEFT = new Align("LEFT", 0);
            CENTER = new Align("CENTER", 1);
            RIGHT = new Align("RIGHT", 2);
            NONE = new Align("NONE", 3);
            $VALUES = (new Align[] {
                LEFT, CENTER, RIGHT, NONE
            });
        }

        private Align(String s, int i)
        {
            super(s, i);
        }
    }


    public OEmbedRequest(long l, String s)
    {
        hideMedia = true;
        hideThread = true;
        omitScript = false;
        align = Align.NONE;
        related = new String[0];
        statusId = l;
        url = s;
    }

    private void appendParameter(String s, long l, List list)
    {
        if(0L <= l)
            list.add(new HttpParameter(s, String.valueOf(l)));
    }

    private void appendParameter(String s, String s1, List list)
    {
        if(s1 != null)
            list.add(new HttpParameter(s, s1));
    }

    public OEmbedRequest HideMedia(boolean flag)
    {
        hideMedia = flag;
        return this;
    }

    public OEmbedRequest HideThread(boolean flag)
    {
        hideThread = flag;
        return this;
    }

    public OEmbedRequest MaxWidth(int i)
    {
        maxWidth = i;
        return this;
    }

    public OEmbedRequest align(Align align1)
    {
        align = align1;
        return this;
    }

    HttpParameter[] asHttpParameterArray()
    {
        ArrayList arraylist = new ArrayList(12);
        appendParameter("id", statusId, arraylist);
        appendParameter("url", url, arraylist);
        appendParameter("maxwidth", maxWidth, arraylist);
        arraylist.add(new HttpParameter("hide_media", hideMedia));
        arraylist.add(new HttpParameter("hide_thread", hideThread));
        arraylist.add(new HttpParameter("omit_script", omitScript));
        arraylist.add(new HttpParameter("align", align.name().toLowerCase()));
        if(related.length > 0)
            appendParameter("related", StringUtil.join(related), arraylist);
        appendParameter("lang", lang, arraylist);
        return (HttpParameter[])arraylist.toArray(new HttpParameter[arraylist.size()]);
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (OEmbedRequest)obj;
            if(hideMedia != ((OEmbedRequest) (obj)).hideMedia)
                return false;
            if(hideThread != ((OEmbedRequest) (obj)).hideThread)
                return false;
            if(maxWidth != ((OEmbedRequest) (obj)).maxWidth)
                return false;
            if(omitScript != ((OEmbedRequest) (obj)).omitScript)
                return false;
            if(statusId != ((OEmbedRequest) (obj)).statusId)
                return false;
            if(align != ((OEmbedRequest) (obj)).align)
                return false;
            if(lang == null ? ((OEmbedRequest) (obj)).lang != null : !lang.equals(((OEmbedRequest) (obj)).lang))
                return false;
            if(!Arrays.equals(related, ((OEmbedRequest) (obj)).related))
                return false;
            if(url == null ? ((OEmbedRequest) (obj)).url != null : !url.equals(((OEmbedRequest) (obj)).url))
                return false;
        }
        return true;
    }

    public int hashCode()
    {
        int l = 1;
        int k1 = 0;
        int l1 = (int)(statusId ^ statusId >>> 32);
        int i;
        int j;
        int k;
        int i1;
        int j1;
        int i2;
        if(url != null)
            i = url.hashCode();
        else
            i = 0;
        i2 = maxWidth;
        if(hideMedia)
            j = 1;
        else
            j = 0;
        if(hideThread)
            k = 1;
        else
            k = 0;
        if(!omitScript)
            l = 0;
        if(align != null)
            i1 = align.hashCode();
        else
            i1 = 0;
        if(related != null)
            j1 = Arrays.hashCode(related);
        else
            j1 = 0;
        if(lang != null)
            k1 = lang.hashCode();
        return (((((((l1 * 31 + i) * 31 + i2) * 31 + j) * 31 + k) * 31 + l) * 31 + i1) * 31 + j1) * 31 + k1;
    }

    public OEmbedRequest lang(String s)
    {
        lang = s;
        return this;
    }

    public OEmbedRequest omitScript(boolean flag)
    {
        omitScript = flag;
        return this;
    }

    public OEmbedRequest related(String as[])
    {
        related = as;
        return this;
    }

    public void setAlign(Align align1)
    {
        align = align1;
    }

    public void setHideMedia(boolean flag)
    {
        hideMedia = flag;
    }

    public void setHideThread(boolean flag)
    {
        hideThread = flag;
    }

    public void setLang(String s)
    {
        lang = s;
    }

    public void setMaxWidth(int i)
    {
        maxWidth = i;
    }

    public void setOmitScript(boolean flag)
    {
        omitScript = flag;
    }

    public void setRelated(String as[])
    {
        related = as;
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("OEmbedRequest{statusId=").append(statusId).append(", url='").append(url).append('\'').append(", maxWidth=").append(maxWidth).append(", hideMedia=").append(hideMedia).append(", hideThread=").append(hideThread).append(", omitScript=").append(omitScript).append(", align=").append(align).append(", related=");
        List list;
        if(related == null)
            list = null;
        else
            list = Arrays.asList(related);
        return stringbuilder.append(list).append(", lang='").append(lang).append('\'').append('}').toString();
    }

    private static final long serialVersionUID = 0x4692eabdL;
    private Align align;
    private boolean hideMedia;
    private boolean hideThread;
    private String lang;
    private int maxWidth;
    private boolean omitScript;
    private String related[];
    private final long statusId;
    private final String url;
}
