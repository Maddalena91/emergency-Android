package it.fitstic.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.fitstic.emergency.model.Provincia;
import it.fitstic.emergency.model.Regione;

public class ProvinceActivity extends AppCompatActivity {

    private Regione selectedRegione;

    private List<Provincia> listaProvince = new ArrayList<>();
    private List<String> nomeProvince = new ArrayList<>();
    private Provincia selectedProvincia;
    private String selectedItem;
    private List<String> answers = new ArrayList<>();

    private ListView lvProvince;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.province_activity);
        lvProvince = findViewById(R.id.lvProvince);

        Intent intent = getIntent();
        selectedRegione = intent.getParcelableExtra("selectedRegione");
        answers = intent.getStringArrayListExtra("answersList");

        // Estrazione lista di oggetti Provincia
        listaProvince = selectedRegione.provincia.stream().filter(name -> name.nome.startsWith("")).collect(Collectors.toList());

        // Recupero il nome di ogni province
        listaProvince.forEach(p -> nomeProvince.add(p.nome));

        // Stampa della lista delle province nella ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_listview_props, nomeProvince);
        lvProvince.setAdapter(adapter);

        lvProvince.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = String.valueOf(nomeProvince.get(position));
                selectedProvincia = selectedRegione.provincia.stream().filter(name -> name.nome.startsWith(selectedItem)).collect(Collectors.toList()).get(0);
                answers.add(selectedItem);

                Intent intent = new Intent(ProvinceActivity.this, CityActivity.class);
                intent.putExtra("selectedProvincia", selectedProvincia);
                intent.putStringArrayListExtra("answersList", (ArrayList<String>) answers);
                startActivity(intent);
            }
        });
    }
}