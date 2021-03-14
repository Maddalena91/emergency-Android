package it.fitstic.emergency.model;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
public class Provincia implements Parcelable {
    public String code;
    public List<Comune> comune = new ArrayList<>();;
    public String nome;

    public Provincia() {
        code = code;
        nome = nome;
    }

    public Provincia(Parcel in) {
        code = in.readString();
        in.readList(comune, Comune.class.getClassLoader());
        nome = in.readString();
    }

    public static final Creator<Provincia> CREATOR = new Creator<Provincia>() {
        @Override
        public Provincia createFromParcel(Parcel in) {
            return new Provincia(in);
        }

        @Override
        public Provincia[] newArray(int size) {
            return new Provincia[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeList(comune);
        dest.writeString(nome);
    }
}
