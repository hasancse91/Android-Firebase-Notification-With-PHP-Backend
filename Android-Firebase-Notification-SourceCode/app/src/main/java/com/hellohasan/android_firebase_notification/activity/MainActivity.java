package com.hellohasan.android_firebase_notification.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hellohasan.android_firebase_notification.util.Preferences;
import com.hellohasan.android_firebase_notification.notification.Configuration;
import com.hellohasan.android_firebase_notification.notification.DeleteTokenService;
import com.hellohasan.android_firebase_notification.notification.NotificationUtils;
import com.hellohasan.android_firebase_notification.R;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private TextView firebaseIdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Preferences preferences = Preferences.getInstance(this);
        Activity activity = this;
        firebaseIdTextView = findViewById(R.id.firebaseId);

//        try {
//            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
//            int versionCode = packageInfo.versionCode;
//
//            if(preferences.getVersionCode()!=versionCode){
//
//                System.out.println("Version code changed");
//
//                Intent intentService = new Intent(activity, DeleteTokenService.class);
//                activity.startService(intentService);
//
//                preferences.setVersionCode(versionCode);
//            }
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }

//        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                // checking for type intent filter
//                if (intent.getAction().equals(Configuration.REGISTRATION_COMPLETE)) {
//                    // fcm successfully registered
//                    // now subscribe to `global` topic to receive app wide notifications
//                    FirebaseMessaging.getInstance().subscribeToTopic(Configuration.TOPIC_GLOBAL);
//                }
//            }
//        };

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                firebaseIdTextView.append(newToken);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Configuration.REGISTRATION_COMPLETE));

        // register new push postUrlFromNotification receiver
        // by doing this, the activity will be notified each time a new postUrlFromNotification arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Configuration.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }
}
