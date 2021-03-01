package com.maxkorte.teachers;

import java.util.ArrayList;

public abstract class Lerngruppe {
    private Stufe stufe;
    private final Fach fach;
    private ArrayList<Schueler> schuelerList;
    private static short count;

    public Lerngruppe(Stufe stufe, Fach fach, ArrayList<Schueler> schuelerList){
        if(stufe == null || fach == null || schuelerList == null){
            throw new Error("Argumente dürfen nicht null sein!");
        }

        this.fach = fach;
        setStufe(stufe);
        setSchuelerList(schuelerList);
        count++;
    }

    public Lerngruppe(Stufe stufe, Fach fach){
        this(stufe, fach, new ArrayList<>());
    }

    public Stufe getStufe() {
        return stufe;
    }

    public void setStufe(Stufe stufe) {
        this.stufe = stufe;
    }

    public Fach getFach() {
        return fach;
    }

    public ArrayList<Schueler> getSchuelerList() {
        return schuelerList;
    }

    public void setSchuelerList(ArrayList<Schueler> schuelerList) {
        this.schuelerList = schuelerList;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public abstract String toString();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    public void listSchueler(){
        if(getSchuelerList().size() == 0){
            System.out.println("keine Schüler vorhanden!");
        } else{
            for (int i = 0; i < getSchuelerList().size(); i++){
                Schueler s = getSchuelerList().get(i);
                System.out.println( "  " + (i+1) + ": " + s.getFirstName() + " " + s.getLastName() + ", Alter: " + s.getAge());
                if(s.getGrades().containsKey(1) || s.getGrades().containsKey(2)){
                    System.out.println("    1. HJ: " + s.getH1grade() + " Punkte");
                }
                if(s.getGrades().containsKey(3) || s.getGrades().containsKey(4)){
                    System.out.println("    2. HJ: " + s.getH2grade() + " Punkte");
                }
            }
        }
    }
}
