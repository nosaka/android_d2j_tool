// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j.api;


public interface SavedSearchesResourcesAsync
{

    public abstract void createSavedSearch(String s);

    public abstract void destroySavedSearch(int i);

    public abstract void getSavedSearches();

    public abstract void showSavedSearch(int i);
}
