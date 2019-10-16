package com.hellohasan.android_firebase_notification.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellohasan.android_firebase_notification.R;
import com.squareup.picasso.Picasso;

public class MessageShowActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleTextView;
    private TextView timeStampTextView;
    private TextView articleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_show);
        viewInitialization();

        //receive data from MyFirebaseMessagingService class
        String title = getIntent().getStringExtra("title");
        String timeStamp = getIntent().getStringExtra("timestamp");
        String article = getIntent().getStringExtra("article_data");
        String imageUrl = getIntent().getStringExtra("image");

        //Set data on UI
        titleTextView.setText(title);
        timeStampTextView.setText(timeStamp);
        articleTextView.setText(article);
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.default_image)
                .into(imageView);
    }

    private void viewInitialization() {
        imageView = findViewById(R.id.featureGraphics);
        titleTextView = findViewById(R.id.header);
        timeStampTextView = findViewById(R.id.timeStamp);
        articleTextView = findViewById(R.id.article);
    }
}
