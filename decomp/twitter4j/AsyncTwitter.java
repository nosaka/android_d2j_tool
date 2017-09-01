// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package twitter4j;

import java.io.Serializable;
import twitter4j.api.DirectMessagesResourcesAsync;
import twitter4j.api.FavoritesResourcesAsync;
import twitter4j.api.FriendsFollowersResourcesAsync;
import twitter4j.api.HelpResourcesAsync;
import twitter4j.api.ListsResourcesAsync;
import twitter4j.api.PlacesGeoResourcesAsync;
import twitter4j.api.SavedSearchesResourcesAsync;
import twitter4j.api.SearchResourceAsync;
import twitter4j.api.SpamReportingResourceAsync;
import twitter4j.api.SuggestedUsersResourcesAsync;
import twitter4j.api.TimelinesResourcesAsync;
import twitter4j.api.TrendsResourcesAsync;
import twitter4j.api.TweetsResourcesAsync;
import twitter4j.api.UsersResourcesAsync;
import twitter4j.auth.AsyncOAuth2Support;
import twitter4j.auth.AsyncOAuthSupport;
import twitter4j.auth.OAuth2Support;
import twitter4j.auth.OAuthSupport;

// Referenced classes of package twitter4j:
//            TwitterBase, TwitterListener

public interface AsyncTwitter
    extends Serializable, OAuthSupport, OAuth2Support, AsyncOAuthSupport, AsyncOAuth2Support, TwitterBase, TimelinesResourcesAsync, TweetsResourcesAsync, SearchResourceAsync, DirectMessagesResourcesAsync, FriendsFollowersResourcesAsync, UsersResourcesAsync, SuggestedUsersResourcesAsync, FavoritesResourcesAsync, ListsResourcesAsync, SavedSearchesResourcesAsync, PlacesGeoResourcesAsync, TrendsResourcesAsync, SpamReportingResourceAsync, HelpResourcesAsync
{

    public abstract void addListener(TwitterListener twitterlistener);

    public abstract void shutdown();
}
