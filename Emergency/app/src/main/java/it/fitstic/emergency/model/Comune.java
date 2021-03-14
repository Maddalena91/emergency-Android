package it.fitstic.emergency.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Comune implements Parcelable {
    public String code;
    public String cap;
    public String nome;

    public Comune() {
        code = code;
        cap = cap;
        nome = nome;
    }

    public Comune(Parcel in) {
        code = in.readString();
        cap = in.readString();
        nome = in.readString();
    }

    public static final Creator<Comune> CREATOR = new Creator<Comune>() {
        @Override
        public Comune createFromParcel(Parcel in) {
            return new Comune(in);
        }

        @Override
        public Comune[] newArray(int size) {
            return new Comune[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(cap);
        dest.writeString(nome);
    }
}
