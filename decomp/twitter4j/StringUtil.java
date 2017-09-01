// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;


class StringUtil
{

    private StringUtil()
    {
        throw new AssertionError();
    }

    public static String join(long al[])
    {
        StringBuilder stringbuilder = new StringBuilder(al.length * 11);
        int j = al.length;
        for(int i = 0; i < j; i++)
        {
            long l = al[i];
            if(stringbuilder.length() != 0)
                stringbuilder.append(",");
            stringbuilder.append(l);
        }

        return stringbuilder.toString();
    }

    public static String join(String as[])
    {
        StringBuilder stringbuilder = new StringBuilder(as.length * 11);
        int j = as.length;
        for(int i = 0; i < j; i++)
        {
            String s = as[i];
            if(stringbuilder.length() != 0)
                stringbuilder.append(",");
            stringbuilder.append(s);
        }

        return stringbuilder.toString();
    }
}
