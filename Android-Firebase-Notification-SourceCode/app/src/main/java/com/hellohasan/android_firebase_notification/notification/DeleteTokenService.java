package com.hellohasan.android_firebase_notification.notification;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.IOException;


public class DeleteTokenService extends IntentService
{
    public static final String TAG = DeleteTokenService.class.getSimpleName();

    public DeleteTokenService()
    {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        try
        {
            // Resets Instance ID and revokes all tokens.
            FirebaseInstanceId.getInstance().deleteInstanceId();

            // Now manually call onTokenRefresh()
            Log.d(TAG, "Getting new token");
            FirebaseInstanceId.getInstance().getToken();

            System.out.println("TOKEN DELETED. NEW TOKEN FROM SERVICE: "+FirebaseInstanceId.getInstance().getToken());

        }
        catch (IOException e)
        {
            System.out.println("INTENT SERVICE IOException");

            e.printStackTrace();
        }
    }



}