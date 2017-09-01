// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.fragment.other;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.*;
import android.view.animation.*;
import android.widget.ImageView;
import android.widget.TextView;
import net.wakamesoba98.sobacha.compatible.*;
import net.wakamesoba98.sobacha.core.ResourceHelper;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.image.LoadBitmapManager;
import net.wakamesoba98.sobacha.preference.PreferenceUtil;
import net.wakamesoba98.sobacha.preference.key.EnumPrefs;
import net.wakamesoba98.sobacha.preference.prefs.AccountHolder;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.UserApi;
import net.wakamesoba98.sobacha.twitter.listener.OnRelationshipGotListener;
import net.wakamesoba98.sobacha.twitter.listener.OnUserDetailGotListener;
import net.wakamesoba98.sobacha.twitter.media.MediaURL;
import net.wakamesoba98.sobacha.twitter.util.StatusUrlUtils;
import net.wakamesoba98.sobacha.view.activity.ImageViewerActivity;
import net.wakamesoba98.sobacha.view.activity.ViewPagerActivity;
import net.wakamesoba98.sobacha.view.card.UserCardManager;
import net.wakamesoba98.sobacha.view.fragment.parent.ViewPagerFragment;
import net.wakamesoba98.sobacha.view.imageview.OnTryToUseRecycledBitmapListener;
import net.wakamesoba98.sobacha.view.imageview.RecyclableImageView;
import net.wakamesoba98.sobacha.view.textview.MutableOnTouchListener;
import net.wakamesoba98.sobacha.view.textview.TextViewLinkUtils;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import twitter4j.*;

public class UserDetailFragment extends Fragment
    implements OnUserDetailGotListener, OnRelationshipGotListener, AccountHolder
{
    private class ViewHolder
    {

        View cardUserBio;
        View cardUserInfo;
        View cardUserMenu;
        View cardUserRelationship;
        RecyclableImageView imageViewBanner;
        RecyclableImageView imageViewIcon;
        ImageView imageViewProtectedUser;
        ImageView imageViewVerifiedUser;
        TextView textViewBannerOverLay;
        TextView textViewBio;
        TextView textViewProfileInfo;
        TextView textViewRelationship;
        TextView textViewTweets;
        TextView textViewUserName;
        final UserDetailFragment this$0;

        private ViewHolder()
        {
            this$0 = UserDetailFragment.this;
            super();
        }

    }


    public UserDetailFragment()
    {
    }

    private void cardAnimation1()
    {
        Animation animation = getTranslateAnimation();
        viewHolder.cardUserMenu.startAnimation(animation);
        userCardManager.setVisibility(true);
    }

    private void cardAnimation2(boolean flag)
    {
        if(flag)
        {
            Animation animation = getTranslateAnimation();
            animation.setStartOffset(150L);
            viewHolder.cardUserBio.startAnimation(animation);
            viewHolder.cardUserBio.setVisibility(0);
        }
        Animation animation1 = getTranslateAnimation();
        animation1.setStartOffset(300L);
        viewHolder.cardUserInfo.startAnimation(animation1);
        viewHolder.cardUserInfo.setVisibility(0);
    }

    private void cardAnimation3()
    {
        Animation animation = getTranslateAnimation();
        viewHolder.cardUserRelationship.startAnimation(animation);
        viewHolder.cardUserRelationship.setVisibility(0);
    }

    private void findView()
    {
        viewHolder = new ViewHolder();
        viewHolder.imageViewIcon = (RecyclableImageView)getView().findViewById(0x7f0e00e1);
        viewHolder.imageViewBanner = (RecyclableImageView)getView().findViewById(0x7f0e00df);
        viewHolder.imageViewProtectedUser = (ImageView)getView().findViewById(0x7f0e00e2);
        viewHolder.imageViewVerifiedUser = (ImageView)getView().findViewById(0x7f0e00e3);
        viewHolder.textViewBannerOverLay = (TextView)getView().findViewById(0x7f0e00e0);
        viewHolder.textViewUserName = (TextView)getView().findViewById(0x7f0e00e4);
        viewHolder.textViewTweets = (TextView)getView().findViewById(0x7f0e00e5);
        viewHolder.textViewBio = (TextView)getView().findViewById(0x7f0e00e8);
        viewHolder.textViewProfileInfo = (TextView)getView().findViewById(0x7f0e00ea);
        viewHolder.textViewRelationship = (TextView)getView().findViewById(0x7f0e00ec);
        viewHolder.cardUserMenu = getView().findViewById(0x7f0e00e6);
        viewHolder.cardUserBio = getView().findViewById(0x7f0e00e7);
        viewHolder.cardUserInfo = getView().findViewById(0x7f0e00e9);
        viewHolder.cardUserRelationship = getView().findViewById(0x7f0e00eb);
    }

    private Animation getTranslateAnimation()
    {
        TranslateAnimation translateanimation = new TranslateAnimation(1, 0.0F, 1, 0.0F, 2, 1.5F, 1, 0.0F);
        translateanimation.setDuration(500L);
        translateanimation.setInterpolator(new DecelerateInterpolator());
        return translateanimation;
    }

    private void setTheme()
    {
        viewHolder.textViewBannerOverLay.setBackgroundColor(themeManager.getThemeColor(0x7f0d0010));
        viewHolder.cardUserMenu.setBackgroundResource(themeManager.getThemeDrawableId(0x7f020071));
        viewHolder.cardUserBio.setBackgroundResource(themeManager.getThemeDrawableId(0x7f020071));
        viewHolder.cardUserInfo.setBackgroundResource(themeManager.getThemeDrawableId(0x7f020071));
        viewHolder.cardUserRelationship.setBackgroundResource(themeManager.getThemeDrawableId(0x7f020071));
        viewHolder.cardUserBio.setVisibility(8);
        viewHolder.cardUserInfo.setVisibility(8);
        viewHolder.cardUserRelationship.setVisibility(8);
    }

    private void showFriendship(Relationship relationship)
    {
        if(getActivity() != null && relationship != null)
        {
            parentFragment.setRelationship(relationship);
            userCardManager.setRelationship(relationship);
            if(relationship.getSourceUserId() != relationship.getTargetUserId())
            {
                if(relationship.isTargetFollowingSource())
                    viewHolder.textViewRelationship.setText(ResourceHelper.getString(getActivity(), 0x7f0700a0));
                else
                    viewHolder.textViewRelationship.setText(ResourceHelper.getString(getActivity(), 0x7f0700cf));
                cardAnimation3();
                return;
            }
        }
    }

    private void showUserDetail(final User user)
    {
        if(getActivity() == null || user == null)
            return;
        StatusUrlUtils statusurlutils = new StatusUrlUtils();
        TextViewLinkUtils textviewlinkutils = new TextViewLinkUtils();
        parentFragment.setUser(user);
        userCardManager.setUser(user);
        viewHolder.imageViewIcon.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                view = getActivity();
                Object obj = HttpsMediaURL.getOriginalProfileImageURL(user);
                Bundle bundle = new Bundle();
                bundle.putStringArray("image_urls", new String[] {
                    obj
                });
                bundle.putString("screen_name", user.getScreenName());
                obj = new Intent(view, net/wakamesoba98/sobacha/view/activity/ImageViewerActivity);
                ((Intent) (obj)).putExtras(bundle);
                view.startActivity(((Intent) (obj)));
                if(Flavor.isMateCha())
                    getActivity().overridePendingTransition(0x7f040010, 0x7f040012);
            }

            final UserDetailFragment this$0;
            final User val$user;

            
            {
                this$0 = UserDetailFragment.this;
                user = user1;
                super();
            }
        }
);
        final LoadBitmapManager manager = ((SobaChaApplication)getActivity().getApplication()).getLoadBitmapManager();
        final MediaURL mediaURL = HttpsMediaURL.getBiggerProfileImageURL(user);
        if(mediaURL != null)
        {
            viewHolder.imageViewIcon.setTag(mediaURL);
            mediaURL = new MediaURL(mediaURL);
            viewHolder.imageViewIcon.setOnTryToUseRecycledBitmapListener(new OnTryToUseRecycledBitmapListener() {

                public void onTryToUseRecycledBitmap(ImageView imageview)
                {
                    manager.doDownloadBitmap(imageview, mediaURL, false, true);
                }

                final UserDetailFragment this$0;
                final LoadBitmapManager val$manager;
                final MediaURL val$mediaURL;

            
            {
                this$0 = UserDetailFragment.this;
                manager = loadbitmapmanager;
                mediaURL = mediaurl;
                super();
            }
            }
);
            manager.doDownloadBitmap(viewHolder.imageViewIcon, mediaURL, false, true);
        }
        mediaURL = user.getProfileBannerURL();
        if(mediaURL != null)
        {
            viewHolder.imageViewBanner.setTag(mediaURL);
            mediaURL = new MediaURL(mediaURL);
            viewHolder.imageViewBanner.setOnTryToUseRecycledBitmapListener(new OnTryToUseRecycledBitmapListener() {

                public void onTryToUseRecycledBitmap(ImageView imageview)
                {
                    manager.doDownloadBitmap(imageview, mediaURL, false, false);
                }

                final UserDetailFragment this$0;
                final LoadBitmapManager val$manager;
                final MediaURL val$mediaURL;

            
            {
                this$0 = UserDetailFragment.this;
                manager = loadbitmapmanager;
                mediaURL = mediaurl;
                super();
            }
            }
);
            manager.doDownloadBitmap(viewHolder.imageViewBanner, mediaURL, false, false);
        }
        boolean flag;
        if(user.isProtected())
        {
            viewHolder.imageViewProtectedUser.setImageResource(themeManager.getThemeDrawableId(0x7f0200b4));
            viewHolder.imageViewProtectedUser.setVisibility(0);
        } else
        {
            viewHolder.imageViewProtectedUser.setImageDrawable(null);
            viewHolder.imageViewProtectedUser.setVisibility(8);
        }
        if(user.isVerified())
        {
            viewHolder.imageViewVerifiedUser.setImageResource(themeManager.getThemeDrawableId(0x7f020113));
            viewHolder.imageViewVerifiedUser.setVisibility(0);
        } else
        {
            viewHolder.imageViewVerifiedUser.setImageDrawable(null);
            viewHolder.imageViewVerifiedUser.setVisibility(8);
        }
        viewHolder.textViewUserName.setText(user.getName());
        viewHolder.textViewTweets.setText((new StringBuilder()).append(user.getStatusesCount()).append(" ").append(ResourceHelper.getString(getActivity(), 0x7f070129)).toString());
        if(user.getDescription() == null || user.getDescription().trim().equals(""))
        {
            flag = false;
        } else
        {
            flag = true;
            manager = statusurlutils.replaceToDisplayURL(user.getDescription(), user.getDescriptionURLEntities());
            viewHolder.textViewBio.setOnTouchListener(new MutableOnTouchListener());
            viewHolder.textViewBio.setText(textviewlinkutils.createClickableString((ViewPagerActivity)getActivity(), manager, -1, false, user.getScreenName(), user.getDescriptionURLEntities()));
        }
        manager = "";
        if(!user.getLocation().equals(""))
            manager = (new StringBuilder()).append("").append(ResourceHelper.getString(getActivity(), 0x7f0700be)).append("\n").append(user.getLocation()).append("\n\n").toString();
        mediaURL = manager;
        if(user.getURL() != null)
        {
            mediaURL = manager;
            if(!user.getURL().trim().equals(""))
                mediaURL = (new StringBuilder()).append(manager).append(ResourceHelper.getString(getActivity(), 0x7f07013c)).append("\n").append(user.getURLEntity().getDisplayURL()).append("\n\n").toString();
        }
        manager = (new StringBuilder()).append(mediaURL).append(ResourceHelper.getString(getActivity(), 0x7f07009e)).append("\n").append(String.valueOf(user.getFriendsCount())).append("\n\n").append(ResourceHelper.getString(getActivity(), 0x7f07009d)).append("\n").append(String.valueOf(user.getFollowersCount())).append("\n\n").append(ResourceHelper.getString(getActivity(), 0x7f0700bc)).append("\n").append(String.valueOf(user.getFavouritesCount())).toString();
        if(user.getURL() != null && !user.getURL().trim().equals(""))
        {
            manager = statusurlutils.replaceToDisplayURL(manager, user.getURLEntity());
            viewHolder.textViewProfileInfo.setOnTouchListener(new MutableOnTouchListener());
            viewHolder.textViewProfileInfo.setText(textviewlinkutils.createClickableString((ViewPagerActivity)getActivity(), manager, -1, false, user.getScreenName(), new URLEntity[] {
                user.getURLEntity()
            }));
        } else
        {
            viewHolder.textViewProfileInfo.setText(manager);
        }
        cardAnimation1();
        cardAnimation2(flag);
    }

    private void start(UserAccount useraccount, User user, Relationship relationship, long l)
    {
        ViewPagerActivity viewpageractivity = (ViewPagerActivity)getActivity();
        userCardManager = null;
        userCardManager = new UserCardManager(getActivity(), this, viewpageractivity.getPostCardManager(), useraccount);
        setTheme();
        userCardManager.setVisibility(false);
        if(user != null)
            showUserDetail(user);
        else
            (new UserApi(getActivity(), useraccount)).getUserDetail(this, l);
        if(relationship != null)
            showFriendship(relationship);
    }

    public void gotFriendship(Relationship relationship)
    {
        showFriendship(relationship);
    }

    public void gotUserDetail(User user)
    {
        if(user != null && getActivity() != null)
        {
            showUserDetail(user);
            UserAccount useraccount = ((SobaChaApplication)getActivity().getApplication()).getUserAccount();
            long l = useraccount.getId();
            (new UserApi(getActivity(), useraccount)).getFriendship(this, l, user.getId());
        }
    }

    public void onAccountChanged(UserAccount useraccount)
    {
        start(useraccount, null, null, targetUserId);
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        findView();
        themeManager = new ThemeManager(getActivity());
        targetUserId = getArguments().getLong("user_id");
        if(targetUserId <= 0L)
            targetUserId = bundle.getLong("user_id");
        parentFragment = (ViewPagerFragment)getParentFragment();
        bundle = parentFragment.getUser();
        Relationship relationship = parentFragment.getRelationship();
        SobaChaApplication sobachaapplication = (SobaChaApplication)getActivity().getApplication();
        sobachaapplication.registerAccountHolder(this);
        start(sobachaapplication.getUserAccount(), bundle, relationship, targetUserId);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        boolean flag = DualModeUtil.canUseDualModeLayout(getActivity());
        boolean flag1 = PreferenceUtil.getBooleanPreference(getActivity(), EnumPrefs.DUAL_TIMELINE_MODE);
        if(flag && flag1)
            return layoutinflater.inflate(0x7f030048, viewgroup, false);
        else
            return layoutinflater.inflate(0x7f030047, viewgroup, false);
    }

    public void onDetach()
    {
        super.onDetach();
        ((SobaChaApplication)getActivity().getApplication()).releaseAccountHolder(this);
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putLong("user_id", targetUserId);
    }

    private ViewPagerFragment parentFragment;
    private long targetUserId;
    private ThemeManager themeManager;
    private UserCardManager userCardManager;
    private ViewHolder viewHolder;
}
