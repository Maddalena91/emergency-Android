package it.fitstic.emergency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO
        // if statement per gestire:
        // WelcomeActivity se l'utente non si è registrato
        // altrimenti schermata di servizio è attivo se è già registrato

        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }
}