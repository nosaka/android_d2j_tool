// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import java.util.*;

// Referenced classes of package twitter4j:
//            HttpParameter, GeoLocation

public final class Query
    implements Serializable
{
    public static final class ResultType extends Enum
    {

        public static ResultType valueOf(String s)
        {
            return (ResultType)Enum.valueOf(twitter4j/Query$ResultType, s);
        }

        public static ResultType[] values()
        {
            return (ResultType[])$VALUES.clone();
        }

        private static final ResultType $VALUES[];
        public static final ResultType mixed;
        public static final ResultType popular;
        public static final ResultType recent;

        static 
        {
            popular = new ResultType("popular", 0);
            mixed = new ResultType("mixed", 1);
            recent = new ResultType("recent", 2);
            $VALUES = (new ResultType[] {
                popular, mixed, recent
            });
        }

        private ResultType(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Unit extends Enum
    {

        public static Unit valueOf(String s)
        {
            return (Unit)Enum.valueOf(twitter4j/Query$Unit, s);
        }

        public static Unit[] values()
        {
            return (Unit[])$VALUES.clone();
        }

        private static final Unit $VALUES[];
        public static final Unit km;
        public static final Unit mi;

        static 
        {
            mi = new Unit("mi", 0);
            km = new Unit("km", 1);
            $VALUES = (new Unit[] {
                mi, km
            });
        }

        private Unit(String s, int i)
        {
            super(s, i);
        }
    }


    public Query()
    {
        query = null;
        lang = null;
        locale = null;
        maxId = -1L;
        count = -1;
        since = null;
        sinceId = -1L;
        geocode = null;
        until = null;
        resultType = null;
        nextPageQuery = null;
    }

    public Query(String s)
    {
        query = null;
        lang = null;
        locale = null;
        maxId = -1L;
        count = -1;
        since = null;
        sinceId = -1L;
        geocode = null;
        until = null;
        resultType = null;
        nextPageQuery = null;
        query = s;
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

    static Query createWithNextPageQuery(String s)
    {
        Query query1;
label0:
        {
            LinkedHashMap linkedhashmap;
label1:
            {
                query1 = new Query();
                query1.nextPageQuery = s;
                if(s == null)
                    break label0;
                s = s.substring(1, s.length());
                linkedhashmap = new LinkedHashMap();
                HttpParameter httpparameter;
                for(s = HttpParameter.decodeParameters(s).iterator(); s.hasNext(); linkedhashmap.put(httpparameter.getName(), httpparameter.getValue()))
                    httpparameter = (HttpParameter)s.next();

                if(linkedhashmap.containsKey("q"))
                    query1.setQuery((String)linkedhashmap.get("q"));
                if(linkedhashmap.containsKey("lang"))
                    query1.setLang((String)linkedhashmap.get("lang"));
                if(linkedhashmap.containsKey("locale"))
                    query1.setLocale((String)linkedhashmap.get("locale"));
                if(linkedhashmap.containsKey("max_id"))
                    query1.setMaxId(Long.parseLong((String)linkedhashmap.get("max_id")));
                if(linkedhashmap.containsKey("count"))
                    query1.setCount(Integer.parseInt((String)linkedhashmap.get("count")));
                if(!linkedhashmap.containsKey("geocode"))
                    break label1;
                s = ((String)linkedhashmap.get("geocode")).split(",");
                double d2 = Double.parseDouble(s[0]);
                double d3 = Double.parseDouble(s[1]);
                double d1 = 0.0D;
                Object obj = null;
                String s1 = s[2];
                Unit aunit[] = Unit.values();
                int j = aunit.length;
                int i = 0;
                double d;
label2:
                do
                {
label3:
                    {
                        d = d1;
                        s = obj;
                        if(i < j)
                        {
                            s = aunit[i];
                            if(!s1.endsWith(s.name()))
                                break label3;
                            d = Double.parseDouble(s1.substring(0, s1.length() - 2));
                        }
                        if(s == null)
                            throw new IllegalArgumentException((new StringBuilder()).append("unrecognized geocode radius: ").append(s1).toString());
                        break label2;
                    }
                    i++;
                } while(true);
                query1.setGeoCode(new GeoLocation(d2, d3), d, s);
            }
            if(linkedhashmap.containsKey("result_type"))
                query1.setResultType(ResultType.valueOf((String)linkedhashmap.get("result_type")));
        }
        return query1;
    }

    HttpParameter[] asHttpParameterArray()
    {
        ArrayList arraylist = new ArrayList(12);
        appendParameter("q", query, arraylist);
        appendParameter("lang", lang, arraylist);
        appendParameter("locale", locale, arraylist);
        appendParameter("max_id", maxId, arraylist);
        appendParameter("count", count, arraylist);
        appendParameter("since", since, arraylist);
        appendParameter("since_id", sinceId, arraylist);
        appendParameter("geocode", geocode, arraylist);
        appendParameter("until", until, arraylist);
        if(resultType != null)
            arraylist.add(new HttpParameter("result_type", resultType.name()));
        arraylist.add(WITH_TWITTER_USER_ID);
        return (HttpParameter[])arraylist.toArray(new HttpParameter[arraylist.size()]);
    }

    public Query count(int i)
    {
        setCount(i);
        return this;
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (Query)obj;
            if(maxId != ((Query) (obj)).maxId)
                return false;
            if(count != ((Query) (obj)).count)
                return false;
            if(sinceId != ((Query) (obj)).sinceId)
                return false;
            if(geocode == null ? ((Query) (obj)).geocode != null : !geocode.equals(((Query) (obj)).geocode))
                return false;
            if(lang == null ? ((Query) (obj)).lang != null : !lang.equals(((Query) (obj)).lang))
                return false;
            if(locale == null ? ((Query) (obj)).locale != null : !locale.equals(((Query) (obj)).locale))
                return false;
            if(nextPageQuery == null ? ((Query) (obj)).nextPageQuery != null : !nextPageQuery.equals(((Query) (obj)).nextPageQuery))
                return false;
            if(query == null ? ((Query) (obj)).query != null : !query.equals(((Query) (obj)).query))
                return false;
            if(resultType == null ? ((Query) (obj)).resultType != null : !resultType.equals(((Query) (obj)).resultType))
                return false;
            if(since == null ? ((Query) (obj)).since != null : !since.equals(((Query) (obj)).since))
                return false;
            if(until == null ? ((Query) (obj)).until != null : !until.equals(((Query) (obj)).until))
                return false;
        }
        return true;
    }

    public Query geoCode(GeoLocation geolocation, double d, String s)
    {
        setGeoCode(geolocation, d, s);
        return this;
    }

    public int getCount()
    {
        return count;
    }

    public String getGeocode()
    {
        return geocode;
    }

    public String getLang()
    {
        return lang;
    }

    public String getLocale()
    {
        return locale;
    }

    public long getMaxId()
    {
        return maxId;
    }

    public String getQuery()
    {
        return query;
    }

    public ResultType getResultType()
    {
        return resultType;
    }

    public String getSince()
    {
        return since;
    }

    public long getSinceId()
    {
        return sinceId;
    }

    public String getUntil()
    {
        return until;
    }

    public int hashCode()
    {
        int l1 = 0;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int i2;
        int j2;
        int k2;
        if(query != null)
            i = query.hashCode();
        else
            i = 0;
        if(lang != null)
            j = lang.hashCode();
        else
            j = 0;
        if(locale != null)
            k = locale.hashCode();
        else
            k = 0;
        i2 = (int)(maxId ^ maxId >>> 32);
        j2 = count;
        if(since != null)
            l = since.hashCode();
        else
            l = 0;
        k2 = (int)(sinceId ^ sinceId >>> 32);
        if(geocode != null)
            i1 = geocode.hashCode();
        else
            i1 = 0;
        if(until != null)
            j1 = until.hashCode();
        else
            j1 = 0;
        if(resultType != null)
            k1 = resultType.hashCode();
        else
            k1 = 0;
        if(nextPageQuery != null)
            l1 = nextPageQuery.hashCode();
        return (((((((((i * 31 + j) * 31 + k) * 31 + i2) * 31 + j2) * 31 + l) * 31 + k2) * 31 + i1) * 31 + j1) * 31 + k1) * 31 + l1;
    }

    public Query lang(String s)
    {
        setLang(s);
        return this;
    }

    public Query locale(String s)
    {
        setLocale(s);
        return this;
    }

    public Query maxId(long l)
    {
        setMaxId(l);
        return this;
    }

    String nextPage()
    {
        return nextPageQuery;
    }

    public Query query(String s)
    {
        setQuery(s);
        return this;
    }

    public Query resultType(ResultType resulttype)
    {
        setResultType(resulttype);
        return this;
    }

    public void setCount(int i)
    {
        count = i;
    }

    public void setGeoCode(GeoLocation geolocation, double d, String s)
    {
        geocode = (new StringBuilder()).append(geolocation.getLatitude()).append(",").append(geolocation.getLongitude()).append(",").append(d).append(s).toString();
    }

    public void setGeoCode(GeoLocation geolocation, double d, Unit unit)
    {
        geocode = (new StringBuilder()).append(geolocation.getLatitude()).append(",").append(geolocation.getLongitude()).append(",").append(d).append(unit.name()).toString();
    }

    public void setLang(String s)
    {
        lang = s;
    }

    public void setLocale(String s)
    {
        locale = s;
    }

    public void setMaxId(long l)
    {
        maxId = l;
    }

    public void setQuery(String s)
    {
        query = s;
    }

    public void setResultType(ResultType resulttype)
    {
        resultType = resulttype;
    }

    public void setSince(String s)
    {
        since = s;
    }

    public void setSinceId(long l)
    {
        sinceId = l;
    }

    public void setUntil(String s)
    {
        until = s;
    }

    public Query since(String s)
    {
        setSince(s);
        return this;
    }

    public Query sinceId(long l)
    {
        setSinceId(l);
        return this;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Query{query='").append(query).append('\'').append(", lang='").append(lang).append('\'').append(", locale='").append(locale).append('\'').append(", maxId=").append(maxId).append(", count=").append(count).append(", since='").append(since).append('\'').append(", sinceId=").append(sinceId).append(", geocode='").append(geocode).append('\'').append(", until='").append(until).append('\'').append(", resultType='").append(resultType).append('\'').append(", nextPageQuery='").append(nextPageQuery).append('\'').append('}').toString();
    }

    public Query until(String s)
    {
        setUntil(s);
        return this;
    }

    public static final Unit KILOMETERS;
    public static final Unit MILES;
    public static final ResultType MIXED;
    public static final ResultType POPULAR;
    public static final ResultType RECENT;
    private static final HttpParameter WITH_TWITTER_USER_ID = new HttpParameter("with_twitter_user_id", "true");
    private static final long serialVersionUID = 0x328258c3L;
    private int count;
    private String geocode;
    private String lang;
    private String locale;
    private long maxId;
    private String nextPageQuery;
    private String query;
    private ResultType resultType;
    private String since;
    private long sinceId;
    private String until;

    static 
    {
        MILES = Unit.mi;
        KILOMETERS = Unit.km;
        MIXED = ResultType.mixed;
        POPULAR = ResultType.popular;
        RECENT = ResultType.recent;
    }
}
