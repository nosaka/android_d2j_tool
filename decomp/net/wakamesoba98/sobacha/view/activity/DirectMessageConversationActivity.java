// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.os.Bundle;
import net.wakamesoba98.sobacha.view.card.PostCardManager;
import net.wakamesoba98.sobacha.view.fragment.parent.ViewPagerFragment;
import net.wakamesoba98.sobacha.view.tab.EnumViewPagerFragment;

// Referenced classes of package net.wakamesoba98.sobacha.view.activity:
//            ViewPagerActivity

public class DirectMessageConversationActivity extends ViewPagerActivity
{

    public DirectMessageConversationActivity()
    {
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = (Bundle)getIntent().getExtras().clone();
        bundle.putInt("activity", EnumViewPagerFragment.DIRECT_MESSAGE_CONVERSATION.ordinal());
        Object obj = new ViewPagerFragment();
        ((ViewPagerFragment) (obj)).setArguments(bundle);
        setFragment(((android.support.v4.app.Fragment) (obj)), "directMessageConversationFragment", 0x7f0e0085);
        obj = getPostCardManager();
        ((PostCardManager) (obj)).setTargetUserId(bundle.getLong("user_id"));
        ((PostCardManager) (obj)).setDirectMessageMode(true);
        ((PostCardManager) (obj)).showIme();
    }
}
