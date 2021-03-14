package it.fitstic.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.fitstic.emergency.model.Comune;
import it.fitstic.emergency.model.Provincia;
import it.fitstic.emergency.model.Regione;
import it.fitstic.emergency.model.Root;

public class RegionActivity extends AppCompatActivity {

    private Root map = null;

    private List<Regione> listaRegioni = new ArrayList<>();
    private List<String> nomeRegioni = new ArrayList<>();
    private Regione selectedRegione;
    private String selectedItem;
    private List<String> answers = new ArrayList<>();

    private ListView lvRegioni;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.region_activity);
        lvRegioni = findViewById(R.id.lvRegioni);

        getComuniJson();

        // Estrazione lista di oggetti Regione
        listaRegioni = map.regione.stream().filter(name -> name.nome.startsWith("")).collect(Collectors.toList());

        // Recupero il nome di ogni regione
        listaRegioni.forEach(p -> nomeRegioni.add(p.nome));

        // Stampa della lista delle regioni nella ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.custom_listview_props, nomeRegioni);
        lvRegioni.setAdapter(adapter);

        lvRegioni.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = String.valueOf(nomeRegioni.get(position));
                selectedRegione = map.regione.stream().filter(name -> name.nome.startsWith(selectedItem)).collect(Collectors.toList()).get(0);
                answers.add(selectedItem);

                Intent intent = new Intent(RegionActivity.this, ProvinceActivity.class);
                intent.putExtra("selectedRegione", selectedRegione);
                intent.putStringArrayListExtra("answersList", (ArrayList<String>) answers);
                startActivity(intent);
            }
        });
    }

    /**
     * Permette di recuperare il contenuto del file "comuni.json" per trasformarlo in oggetto ROOT
     * che conterrà gli oggetti Regioni, Province, Comuni
     */
    private void getComuniJson() {

        // Recupero del file tramite InputSteam
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("json/comuni.json");

        // Lettura del fIle e riversamento del contenuto in stringa
        InputStreamReader isReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(isReader);
        StringBuffer sb = new StringBuffer();
        String str = "";
        while(true){
            try {
                if (!((str = reader.readLine())!= null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb.append(str);
        }

        // Conversione della stringa in oggetto Json
        JSONObject jObject = null;
        try {
            jObject = new JSONObject(sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            // Conversione del JSON in lista di oggetti string
            map = mapper.readValue(sb.toString(), Root.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}