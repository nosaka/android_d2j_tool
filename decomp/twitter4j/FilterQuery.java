// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

// Referenced classes of package twitter4j:
//            HttpParameter, StringUtil

public final class FilterQuery
    implements Serializable
{

    public FilterQuery()
    {
        count = 0;
        follow = null;
        track = null;
        locations = (double[][])null;
        language = null;
        filterLevel = null;
    }

    public transient FilterQuery(int i, long al[])
    {
        this();
        count = i;
        follow = al;
    }

    public FilterQuery(int i, long al[], String as[])
    {
        this();
        count = i;
        follow = al;
        track = as;
    }

    public FilterQuery(int i, long al[], String as[], double ad[][])
    {
        count = i;
        follow = al;
        track = as;
        locations = ad;
    }

    public FilterQuery(int i, long al[], String as[], double ad[][], String as1[])
    {
        count = i;
        follow = al;
        track = as;
        locations = ad;
        language = as1;
    }

    public transient FilterQuery(long al[])
    {
        this();
        count = 0;
        follow = al;
    }

    public transient FilterQuery(String as[])
    {
        this();
        count = 0;
        track = as;
    }

    private String toLocationsString(double ad[][])
    {
        StringBuilder stringbuilder = new StringBuilder(ad.length * 20 * 2);
        int j = ad.length;
        for(int i = 0; i < j; i++)
        {
            double ad1[] = ad[i];
            if(stringbuilder.length() != 0)
                stringbuilder.append(",");
            stringbuilder.append(ad1[0]);
            stringbuilder.append(",");
            stringbuilder.append(ad1[1]);
        }

        return stringbuilder.toString();
    }

    HttpParameter[] asHttpParameterArray(HttpParameter httpparameter)
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(new HttpParameter("count", count));
        if(follow != null && follow.length > 0)
            arraylist.add(new HttpParameter("follow", StringUtil.join(follow)));
        if(track != null && track.length > 0)
            arraylist.add(new HttpParameter("track", StringUtil.join(track)));
        if(locations != null && locations.length > 0)
            arraylist.add(new HttpParameter("locations", toLocationsString(locations)));
        if(language != null && language.length > 0)
            arraylist.add(new HttpParameter("language", StringUtil.join(language)));
        if(filterLevel != null)
            arraylist.add(new HttpParameter("filter_level", filterLevel));
        arraylist.add(httpparameter);
        return (HttpParameter[])arraylist.toArray(new HttpParameter[arraylist.size()]);
    }

    public FilterQuery count(int i)
    {
        count = i;
        return this;
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (FilterQuery)obj;
            if(count != ((FilterQuery) (obj)).count)
                return false;
            if(!Arrays.equals(follow, ((FilterQuery) (obj)).follow))
                return false;
            if(!Arrays.equals(track, ((FilterQuery) (obj)).track))
                return false;
            if(!Arrays.equals(language, ((FilterQuery) (obj)).language))
                return false;
            if(filterLevel != null ? !filterLevel.equals(((FilterQuery) (obj)).filterLevel) : ((FilterQuery) (obj)).filterLevel != null)
                return false;
        }
        return true;
    }

    public FilterQuery filterLevel(String s)
    {
        filterLevel = s;
        return this;
    }

    public transient FilterQuery follow(long al[])
    {
        follow = al;
        return this;
    }

    public int hashCode()
    {
        int l = 0;
        int i1 = count;
        int i;
        int j;
        int k;
        if(follow != null)
            i = Arrays.hashCode(follow);
        else
            i = 0;
        if(track != null)
            j = Arrays.hashCode(track);
        else
            j = 0;
        if(language != null)
            k = Arrays.hashCode(language);
        else
            k = 0;
        if(filterLevel != null)
            l = filterLevel.hashCode();
        return (((i1 * 31 + i) * 31 + j) * 31 + k) * 31 + l;
    }

    public transient FilterQuery language(String as[])
    {
        language = as;
        return this;
    }

    public transient FilterQuery locations(double ad[][])
    {
        locations = ad;
        return this;
    }

    public String toString()
    {
        Object obj = null;
        StringBuilder stringbuilder = (new StringBuilder()).append("FilterQuery{count=").append(count).append(", follow=").append(Arrays.toString(follow)).append(", track=");
        java.util.List list;
        if(track == null)
            list = null;
        else
            list = Arrays.asList(track);
        stringbuilder = stringbuilder.append(list).append(", locations=");
        if(locations == null)
            list = null;
        else
            list = Arrays.asList(locations);
        stringbuilder = stringbuilder.append(list).append(", language=");
        if(language == null)
            list = obj;
        else
            list = Arrays.asList(language);
        return stringbuilder.append(list).append(", filter_level=").append(filterLevel).append('}').toString();
    }

    public transient FilterQuery track(String as[])
    {
        track = as;
        return this;
    }

    private static final long serialVersionUID = 0xbc901c4eL;
    private int count;
    private String filterLevel;
    private long follow[];
    private String language[];
    private double locations[][];
    private String track[];
}
