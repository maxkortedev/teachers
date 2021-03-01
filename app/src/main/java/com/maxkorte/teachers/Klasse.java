package com.maxkorte.teachers;

import java.util.ArrayList;
import java.util.Objects;

public class Klasse extends Lerngruppe {
    private final Klassenbuchstabe klassenbuchstabe;
    private final boolean hauptfach;

    public Klasse(Klassenbuchstabe klassenbuchstabe, boolean hauptfach, Stufe stufe, Fach fach, ArrayList<Schueler> schuelerList){
        super(stufe, fach, schuelerList);

        if(klassenbuchstabe == null){
            throw new Error("klassenbuchstabe darf nicht null sein");
        }

        if(stufe.ordinal() >= Kurs.MIN_STUFE_KURS){
            throw new Error("Es sollte ein Kursa anstatt einer Klasse erstellt werden");
        }

        this.klassenbuchstabe = klassenbuchstabe;
        this.hauptfach = hauptfach;
    }

    public Klasse(Klassenbuchstabe klassenbuchstabe, boolean hauptfach, Stufe stufe, Fach fach){
        this(klassenbuchstabe, hauptfach, stufe, fach, new ArrayList<>());
    }

    public Klasse(Klassenbuchstabe klassenbuchstabe, Stufe stufe, Fach fach, ArrayList<Schueler> schuelerList){
        this(klassenbuchstabe, false, stufe, fach, schuelerList);
    }

    public Klasse(Klassenbuchstabe klassenbuchstabe, Stufe stufe, Fach fach){
        this(klassenbuchstabe, false, stufe, fach, new ArrayList<>());
    }

    public Klassenbuchstabe getKlassenbuchstabe() {
        return klassenbuchstabe;
    }

    public boolean isHauptfach() {
        return hauptfach;
    }

    @Override
    public String toString() {
        return (getStufe().ordinal()) + 1 + "" + getKlassenbuchstabe() + ": " + getFach()
                + ((isHauptfach()) ? ", Hauptfach" : ", Nebenfach");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klasse klasse = (Klasse) o;
        return isHauptfach() == klasse.isHauptfach() && getKlassenbuchstabe() == klasse.getKlassenbuchstabe() && getStufe()
                == klasse.getStufe() && getFach() == klasse.getFach();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKlassenbuchstabe(), isHauptfach());
    }
}
