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

import it.fitstic.emergency.model.Comune;
import it.fitstic.emergency.model.Provincia;

public class CityActivity extends AppCompatActivity {

    private Provincia selectedProvincia;

    private List<Comune> listaComuni = new ArrayList<>();
    private List<String> nomeComuni = new ArrayList<>();
    private Comune selectedComune;
    private String selectedItem;
    private List<String> answers = new ArrayList<>();

    private ListView lvComuni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_activity);
        lvComuni = findViewById(R.id.lvComuni);

        Intent intent = getIntent();
        selectedProvincia = intent.getParcelableExtra("selectedProvincia");
        answers = intent.getStringArrayListExtra("answersList");

        // Estrazione lista di oggetti Provincia
        listaComuni = selectedProvincia.comune.stream().filter(name -> name.nome.startsWith("")).collect(Collectors.toList());

        // Recupero il nome di ogni province
        listaComuni.forEach(p -> nomeComuni.add(p.nome));

        // Stampa della lista delle province nella ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_listview_props, nomeComuni);
        lvComuni.setAdapter(adapter);

        lvComuni.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = String.valueOf(nomeComuni.get(position));
                selectedComune = selectedProvincia.comune.stream().filter(name -> name.nome.startsWith(selectedItem)).collect(Collectors.toList()).get(0);
                answers.add(selectedItem);

                Intent intent = new Intent(CityActivity.this, AddPhonenumberActivity.class);
                intent.putExtra("selectedComune", selectedComune);
                intent.putStringArrayListExtra("answersList", (ArrayList<String>) answers);
                startActivity(intent);
            }
        });
    }
}