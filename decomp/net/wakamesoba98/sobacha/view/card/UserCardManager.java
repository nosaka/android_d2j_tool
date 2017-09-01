// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.card;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.menu.UserMenu;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.UserApi;
import net.wakamesoba98.sobacha.twitter.api.UserApiDialog;
import net.wakamesoba98.sobacha.twitter.relationship.EnumRelationshipType;
import net.wakamesoba98.sobacha.twitter.relationship.RelationshipUtil;
import net.wakamesoba98.sobacha.view.activity.DirectMessageConversationActivity;
import net.wakamesoba98.sobacha.view.activity.ProfileEditActivity;
import net.wakamesoba98.sobacha.view.fragment.other.UserDetailFragment;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import twitter4j.Relationship;
import twitter4j.User;

// Referenced classes of package net.wakamesoba98.sobacha.view.card:
//            PostCardManager

public class UserCardManager
    implements android.view.View.OnClickListener
{
    private class ViewHolder
    {

        Button buttonFollow;
        ImageButton buttonUserMenu;
        final UserCardManager this$0;
        View viewCard;
        ViewGroup viewGroupCard;

        private ViewHolder()
        {
            this$0 = UserCardManager.this;
            super();
        }

    }


    public UserCardManager(Context context1, UserDetailFragment userdetailfragment, PostCardManager postcardmanager, UserAccount useraccount)
    {
        context = context1;
        themeManager = new ThemeManager(context1);
        userDetailFragment = userdetailfragment;
        postCardManager = postcardmanager;
        userAccount = useraccount;
        findView();
        setButtonProperties();
    }

    private void accountAction(Relationship relationship1)
    {
        UserApi userapi = new UserApi(context, userAccount);
        UserApiDialog userapidialog = new UserApiDialog(userAccount);
        RelationshipUtil relationshiputil = new RelationshipUtil();
        static class _cls1
        {

            static final int $SwitchMap$net$wakamesoba98$sobacha$twitter$relationship$EnumRelationshipType[];

            static 
            {
                $SwitchMap$net$wakamesoba98$sobacha$twitter$relationship$EnumRelationshipType = new int[EnumRelationshipType.values().length];
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$twitter$relationship$EnumRelationshipType[EnumRelationshipType.ME.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$twitter$relationship$EnumRelationshipType[EnumRelationshipType.BLOCKING.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$twitter$relationship$EnumRelationshipType[EnumRelationshipType.FOLLOWING.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$net$wakamesoba98$sobacha$twitter$relationship$EnumRelationshipType[EnumRelationshipType.NOT_FOLLOWING.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror)
                {
                    return;
                }
            }
        }

        switch(_cls1..SwitchMap.net.wakamesoba98.sobacha.twitter.relationship.EnumRelationshipType[relationshiputil.getRelationshipType(relationship1).ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            relationship1 = new Intent(context, net/wakamesoba98/sobacha/view/activity/ProfileEditActivity);
            context.startActivity(relationship1);
            return;

        case 2: // '\002'
            userapidialog.destroyBlock(context, relationship1.getSourceUserId(), relationship1.getTargetUserId(), userDetailFragment);
            return;

        case 3: // '\003'
            userapidialog.destroyFriendship(context, relationship1.getSourceUserId(), relationship1.getTargetUserId(), userDetailFragment);
            return;

        case 4: // '\004'
            userapi.createFriendship(context, userDetailFragment, relationship1.getSourceUserId(), relationship1.getTargetUserId());
            break;
        }
    }

    private void findView()
    {
        viewHolder = new ViewHolder(null);
        viewHolder.viewGroupCard = (ViewGroup)userDetailFragment.getView().findViewById(0x7f0e00b6);
        viewHolder.viewCard = userDetailFragment.getView().findViewById(0x7f0e00e6);
        viewHolder.buttonUserMenu = (ImageButton)userDetailFragment.getView().findViewById(0x7f0e00b7);
        viewHolder.buttonFollow = (Button)userDetailFragment.getView().findViewById(0x7f0e00ba);
    }

    private int getButtonResourceId(View view)
    {
        switch(view.getId())
        {
        default:
            return 0;

        case 2131624119: 
            return 0x7f0200bb;

        case 2131624120: 
            return 0x7f02008a;

        case 2131624121: 
            return 0x7f0200d3;
        }
    }

    private void setButtonProperties()
    {
        viewHolder.viewCard.setBackgroundResource(themeManager.getThemeDrawableId(0x7f020071));
        int i = 0;
        while(i < viewHolder.viewGroupCard.getChildCount()) 
        {
            Object obj = viewHolder.viewGroupCard.getChildAt(i);
            if(obj instanceof ImageButton)
            {
                ImageButton imagebutton = (ImageButton)obj;
                imagebutton.setImageResource(themeManager.getThemeDrawableId(getButtonResourceId(((View) (obj)))));
                imagebutton.setOnClickListener(this);
            } else
            if(obj instanceof Button)
            {
                obj = (Button)obj;
                ((Button) (obj)).setBackgroundResource(themeManager.getThemeDrawableId(0x7f020069));
                ((Button) (obj)).setTextColor(themeManager.getThemeColor(0x7f0d00ba));
                ((Button) (obj)).setTextSize(14F);
                ((Button) (obj)).setOnClickListener(this);
            }
            i++;
        }
    }

    private void setFollowButtonText(Relationship relationship1)
    {
        RelationshipUtil relationshiputil = new RelationshipUtil();
        switch(_cls1..SwitchMap.net.wakamesoba98.sobacha.twitter.relationship.EnumRelationshipType[relationshiputil.getRelationshipType(relationship1).ordinal()])
        {
        default:
            return;

        case 1: // '\001'
            viewHolder.buttonFollow.setText(ResourceHelper.getString(context, 0x7f07004e));
            return;

        case 2: // '\002'
            viewHolder.buttonFollow.setText(ResourceHelper.getString(context, 0x7f07012d));
            return;

        case 3: // '\003'
            viewHolder.buttonFollow.setText(ResourceHelper.getString(context, 0x7f07012f));
            return;

        case 4: // '\004'
            viewHolder.buttonFollow.setText(ResourceHelper.getString(context, 0x7f070099));
            break;
        }
    }

    private void setMessageButtonResource(Relationship relationship1)
    {
        ImageButton imagebutton = (ImageButton)userDetailFragment.getView().findViewById(0x7f0e00b8);
        int i;
        if(relationship1.canSourceDm())
            i = 0x7f02008b;
        else
            i = 0x7f02008a;
        imagebutton.setImageResource(themeManager.getThemeDrawableId(i));
    }

    public void onClick(View view)
    {
        if(relationship != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        switch(view.getId())
        {
        default:
            return;

        case 2131624119: 
            if(userDetailFragment != null)
            {
                (new UserMenu(context, postCardManager, userAccount, relationship, userDetailFragment)).show(viewHolder.buttonUserMenu);
                return;
            }
            break;

        case 2131624120: 
            if(relationship.canSourceDm())
            {
                view = new Bundle();
                view.putLong("user_id", user.getId());
                Intent intent = new Intent(context, net/wakamesoba98/sobacha/view/activity/DirectMessageConversationActivity);
                intent.putExtras(view);
                intent.setFlags(0x10000000);
                context.startActivity(intent);
                return;
            }
            break;

        case 2131624121: 
            view = new Intent("android.intent.action.VIEW", Uri.parse((new StringBuilder()).append("http://twitter.com/").append(relationship.getTargetUserScreenName()).toString()));
            context.startActivity(view);
            return;

        case 2131624122: 
            accountAction(relationship);
            return;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    public void setRelationship(Relationship relationship1)
    {
        relationship = relationship1;
        if(userDetailFragment.getView() == null)
        {
            return;
        } else
        {
            setFollowButtonText(relationship1);
            setMessageButtonResource(relationship1);
            return;
        }
    }

    public void setUser(User user1)
    {
        user = user1;
    }

    public void setVisibility(boolean flag)
    {
        if(flag)
        {
            viewHolder.viewCard.setVisibility(0);
            return;
        } else
        {
            viewHolder.viewCard.setVisibility(8);
            return;
        }
    }

    private static final int BUTTON_TEXT_SIZE = 14;
    private Context context;
    private PostCardManager postCardManager;
    private Relationship relationship;
    private ThemeManager themeManager;
    private User user;
    private UserAccount userAccount;
    private UserDetailFragment userDetailFragment;
    private ViewHolder viewHolder;
}
