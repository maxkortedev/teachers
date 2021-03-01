package com.maxkorte.teachers;

import java.util.ArrayList;
import java.util.Objects;

public class Kurs extends Lerngruppe {
    private final int kursNr;
    private final Kurstyp kursTyp;

    protected static final int MIN_STUFE_KURS = 10;

    public Kurs(int kursNr, Kurstyp kursTyp, Stufe stufe, Fach fach, ArrayList<Schueler> schuelerList) {
        super(stufe, fach, schuelerList);

        if(kursNr < 0 || kursTyp == null){
            throw new Error("Falsche Argumente");
        }

        if(stufe.ordinal() < MIN_STUFE_KURS){
            throw new Error("Es sollte eine Klasse anstatt eines Kurses erstellt werden");
        }

        this.kursNr = kursNr;
        this.kursTyp = kursTyp;
    }

    public Kurs(int kursNr, Kurstyp kursTyp, Stufe stufe, Fach fach) {
        this(kursNr, kursTyp, stufe, fach, new ArrayList<>());
    }

    public Kurs(int kursNr, Stufe stufe, Fach fach, ArrayList<Schueler> schuelerList) {
        this(kursNr, Kurstyp.GK, stufe, fach, schuelerList);
    }

    public Kurs(int kursNr, Stufe stufe, Fach fach) {
        this(kursNr, Kurstyp.GK, stufe, fach, new ArrayList<>());
    }

    public int getKursNr() {
        return kursNr;
    }

    public Kurstyp getKursTyp() {
        return kursTyp;
    }

    @Override
    public String toString() {
        return getFach() + "/" + getStufe() + "/" + getKursTyp() + "" + getKursNr();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kurs kurs = (Kurs) o;
        return getKursNr() == kurs.getKursNr() && getKursTyp() == kurs.getKursTyp() && getStufe() == kurs.getStufe() &&
                getFach() == kurs.getFach();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKursNr(), getKursTyp(), getStufe(), getFach());
    }
}
