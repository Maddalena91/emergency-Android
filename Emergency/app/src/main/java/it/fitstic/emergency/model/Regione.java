package it.fitstic.emergency.model;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Regione implements Parcelable {
    public List<Provincia> provincia = new ArrayList<>();;
    public String nome;

    public Regione() {
        nome = nome;
    }

    public Regione(Parcel in) {
        in.readList(provincia, Provincia.class.getClassLoader());
        nome = in.readString();
    }

    public static final Creator<Regione> CREATOR = new Creator<Regione>() {
        @Override
        public Regione createFromParcel(Parcel in) {
            return new Regione(in);
        }

        @Override
        public Regione[] newArray(int size) {
            return new Regione[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(provincia);
        dest.writeString(nome);
    }
}