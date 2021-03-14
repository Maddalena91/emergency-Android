package it.fitstic.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AddPhonenumberActivity extends AppCompatActivity {

    private List<String> answers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_phonenumber_activity);

        Intent intent = getIntent();
        answers = intent.getStringArrayListExtra("answersList");
    }
}