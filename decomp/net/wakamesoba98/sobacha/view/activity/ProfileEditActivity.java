// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package net.wakamesoba98.sobacha.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import java.io.File;
import net.wakamesoba98.sobacha.compatible.*;
import net.wakamesoba98.sobacha.core.SobaChaApplication;
import net.wakamesoba98.sobacha.databinding.ProfileActivityBinding;
import net.wakamesoba98.sobacha.image.*;
import net.wakamesoba98.sobacha.notification.Notificator;
import net.wakamesoba98.sobacha.preference.prefs.UserAccount;
import net.wakamesoba98.sobacha.twitter.api.ProfileLoadApi;
import net.wakamesoba98.sobacha.twitter.api.ProfileUpdateApi;
import net.wakamesoba98.sobacha.twitter.listener.OnProfileUpdatedListener;
import net.wakamesoba98.sobacha.twitter.listener.OnUserProfileGotListener;
import net.wakamesoba98.sobacha.twitter.media.MediaURL;
import net.wakamesoba98.sobacha.view.activity.base.ThemeActivity;
import net.wakamesoba98.sobacha.view.activity.data.Profile;
import net.wakamesoba98.sobacha.view.activity.handler.ProfileEditHandlers;
import net.wakamesoba98.sobacha.view.imageview.OnTryToUseRecycledBitmapListener;
import net.wakamesoba98.sobacha.view.imageview.RecyclableImageButton;
import net.wakamesoba98.sobacha.view.theme.ThemeManager;
import twitter4j.User;

public class ProfileEditActivity extends ThemeActivity
    implements ProfileEditHandlers, OnProfileUpdatedListener, OnUserProfileGotListener, ImageThresholdConstants
{

    public ProfileEditActivity()
    {
    }

    private void setProfileImage(Uri uri)
    {
        android.graphics.Bitmap bitmap = (new ImageFileReader()).getBitmap(this, uri, 320);
        if(bitmap != null)
        {
            newProfileImage = uri;
            binding.buttonChangeProfileImage.setImageBitmap(bitmap);
            return;
        } else
        {
            Notificator.toast(this, 0x7f070085);
            return;
        }
    }

    public void gotUserProfile(final User manager)
    {
        String s = HttpsMediaURL.getBiggerProfileImageURL(manager);
        beforeProfile = new Profile(manager.getName(), manager.getLocation(), manager.getURL(), manager.getDescription());
        bindProfile = new Profile(manager.getName(), manager.getLocation(), manager.getURL(), manager.getDescription());
        binding.setProfile(bindProfile);
        manager = ((SobaChaApplication)getApplication()).getLoadBitmapManager();
        final MediaURL mediaURL = new MediaURL(s);
        binding.buttonChangeProfileImage.setTag(s);
        binding.buttonChangeProfileImage.setOnTryToUseRecycledBitmapListener(new OnTryToUseRecycledBitmapListener() {

            public void onTryToUseRecycledBitmap(ImageView imageview)
            {
                manager.doDownloadBitmap(imageview, mediaURL, false, false);
            }

            final ProfileEditActivity this$0;
            final LoadBitmapManager val$manager;
            final MediaURL val$mediaURL;

            
            {
                this$0 = ProfileEditActivity.this;
                manager = loadbitmapmanager;
                mediaURL = mediaurl;
                super();
            }
        }
);
        manager.doDownloadBitmap(binding.buttonChangeProfileImage, mediaURL, false, false);
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        ExternalDirectory externaldirectory = new ExternalDirectory();
        i;
        JVM INSTR tableswitch 1 2: default 32
    //                   1 33
    //                   2 239;
           goto _L1 _L2 _L3
_L1:
        return;
_L2:
        if(j == -1)
        {
            Uri uri = intent.getData();
            EnumImageFormat enumimageformat = (new UriResolver()).getImageFormat(this, uri);
            if(enumimageformat == EnumImageFormat.PNG)
                intent = ".png";
            else
                intent = ".jpg";
            cacheUri = Uri.fromFile(new File((new StringBuilder()).append(externaldirectory.getCacheDir(this)).append("/").append(String.valueOf(userAccount.getId())).append(intent).toString()));
            intent = (new Intent("com.android.camera.action.CROP")).setData(uri).putExtra("outputX", 320).putExtra("outputY", 320).putExtra("aspectX", 1).putExtra("aspectY", 1).putExtra("scale", true).putExtra("noFaceDetection", true).putExtra("output", cacheUri);
            if(enumimageformat == EnumImageFormat.PNG)
                intent.putExtra("outputFormat", android.graphics.Bitmap.CompressFormat.PNG.name());
            else
                intent.putExtra("outputFormat", android.graphics.Bitmap.CompressFormat.JPEG.name());
            startActivityForResult(intent, 2);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if(j == -1)
        {
            setProfileImage(cacheUri);
            return;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        binding = (ProfileActivityBinding)DataBindingUtil.setContentView(this, 0x7f030062);
        binding.setHandler(this);
        bundle = new ThemeManager(this);
        if(Flavor.isMateCha())
            binding.buttonUpdateProfile.setImageResource(0x7f020054);
        else
            binding.buttonUpdateProfile.setImageResource(bundle.getThemeDrawableId(0x7f020055));
        userAccount = ((SobaChaApplication)getApplication()).getUserAccount();
        (new ProfileLoadApi(this, userAccount)).getUser(this, userAccount.getId());
    }

    protected void onDestroy()
    {
        super.onDestroy();
        binding.buttonChangeProfileImage.setImageBitmap(null);
    }

    public void onIconClick(View view)
    {
        view = new Intent("android.intent.action.PICK");
        view.setType("image/*");
        startActivityForResult(view, 1);
    }

    public void onUpdateButtonClick(View view)
    {
        boolean flag;
        if(!beforeProfile.equals(bindProfile))
            flag = true;
        else
            flag = false;
        view = new ProfileUpdateApi(this, userAccount);
        if(newProfileImage != null)
        {
            view.updateProfileWithImage(this, newProfileImage, bindProfile, flag);
            return;
        }
        if(flag)
        {
            view.updateProfile(this, bindProfile);
            return;
        } else
        {
            finish();
            return;
        }
    }

    public void updatedProfile()
    {
        finish();
    }

    private static final int REQUEST_PICK_CLOP = 2;
    private static final int REQUEST_PICK_FILE = 1;
    private Profile beforeProfile;
    private Profile bindProfile;
    private ProfileActivityBinding binding;
    private Uri cacheUri;
    private Uri newProfileImage;
    private UserAccount userAccount;
}
