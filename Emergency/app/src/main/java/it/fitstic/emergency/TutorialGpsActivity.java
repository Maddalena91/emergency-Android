package it.fitstic.emergency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TutorialGpsActivity extends AppCompatActivity {

    private Button btnActiveGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_gps_activity);

        btnActiveGPS = findViewById(R.id.btnActiveNotification);

        Intent intent = new Intent(this, RegionActivity.class);

        btnActiveGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckGPSPermission(intent);
            }
        });
    }

    private static final int ID_RICHIESTA_PERMISSION = 102;

    /**
     * Verifica se i permessi del GPS sono già stati abilitati per passare alla prossima Activity
     * altrimenti avvia l'Activity di richiesta dei permessi GPS
     * Una delle due scelte richiama il metodo onRequestPermissionResult
     * @param intent
     */
    public void CheckGPSPermission(Intent intent) {
        int state = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(state == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ID_RICHIESTA_PERMISSION);
        }
    }

    public void CheckGPSPermission() {
        int state = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(state == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ID_RICHIESTA_PERMISSION);
        }
    }

    /**
     * metodo richiamato dall'Activity di richiesta di attivazione del servizio GPS
     * permette di abilitare o negare i permessi da parte dell'utente
     * entrambe le scelte avviano l'Activity di TutorialNotificationActivity
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ProvaRefactor(requestCode, grantResults, RegionActivity.class);
    }

    private void ProvaRefactor(int requestCode, int[] grantResults, Class activity) {
        Intent intent = new Intent(this, activity);
        switch (requestCode) {
            case ID_RICHIESTA_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startActivity(intent);
                } else {
                    startActivity(intent);
                }
            }
        }
    }
}