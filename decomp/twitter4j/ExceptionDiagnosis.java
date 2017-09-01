// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;

final class ExceptionDiagnosis
    implements Serializable
{

    ExceptionDiagnosis(Throwable throwable)
    {
        this(throwable, new String[0]);
    }

    ExceptionDiagnosis(Throwable throwable, String as[])
    {
        hexString = "";
        StackTraceElement astacktraceelement[] = throwable.getStackTrace();
        stackLineHash = 0;
        lineNumberHash = 0;
        int i = astacktraceelement.length - 1;
label0:
        do
        {
            if(i >= 0)
            {
                StackTraceElement stacktraceelement = astacktraceelement[i];
                int k = as.length;
                int j = 0;
                do
                {
label1:
                    {
                        if(j < k)
                        {
                            String s = as[j];
                            if(!stacktraceelement.getClassName().startsWith(s))
                                break label1;
                            j = stacktraceelement.getClassName().hashCode();
                            int l = stacktraceelement.getMethodName().hashCode();
                            stackLineHash = stackLineHash * 31 + (j + l);
                            lineNumberHash = lineNumberHash * 31 + stacktraceelement.getLineNumber();
                        }
                        i--;
                        continue label0;
                    }
                    j++;
                } while(true);
            }
            hexString = (new StringBuilder()).append(hexString).append(toHexString(stackLineHash)).append("-").append(toHexString(lineNumberHash)).toString();
            if(throwable.getCause() != null)
                hexString = (new StringBuilder()).append(hexString).append(" ").append((new ExceptionDiagnosis(throwable.getCause(), as)).asHexString()).toString();
            return;
        } while(true);
    }

    private String toHexString(int i)
    {
        String s = (new StringBuilder()).append("0000000").append(Integer.toHexString(i)).toString();
        return s.substring(s.length() - 8, s.length());
    }

    String asHexString()
    {
        return hexString;
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (ExceptionDiagnosis)obj;
            if(lineNumberHash != ((ExceptionDiagnosis) (obj)).lineNumberHash)
                return false;
            if(stackLineHash != ((ExceptionDiagnosis) (obj)).stackLineHash)
                return false;
        }
        return true;
    }

    int getLineNumberHash()
    {
        return lineNumberHash;
    }

    String getLineNumberHashAsHex()
    {
        return toHexString(lineNumberHash);
    }

    int getStackLineHash()
    {
        return stackLineHash;
    }

    String getStackLineHashAsHex()
    {
        return toHexString(stackLineHash);
    }

    public int hashCode()
    {
        return stackLineHash * 31 + lineNumberHash;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ExceptionDiagnosis{stackLineHash=").append(stackLineHash).append(", lineNumberHash=").append(lineNumberHash).append('}').toString();
    }

    private static final long serialVersionUID = 0x38250289L;
    private String hexString;
    private int lineNumberHash;
    private int stackLineHash;
}
