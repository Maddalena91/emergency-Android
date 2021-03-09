package it.fitstic.emergency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TutorialNotificationActivity extends AppCompatActivity {

    private Button btnActiveNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_notification_activity);

        btnActiveNotification = findViewById(R.id.btnActiveNotification);

        btnActiveNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManagerCompat.from(TutorialNotificationActivity.this).areNotificationsEnabled();
            }
        });
    }
}