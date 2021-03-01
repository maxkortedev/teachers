package com.maxkorte.teachers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Lerngruppe> lerngruppen = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        boolean running = true;
        while (running){
            System.out.println("Du hast " + Lerngruppe.getCount() + " Lerngruppen und " + Schueler.getCount() + "Schüler");
            System.out.println();
            System.out.println("Was möchtest Du tun?");
            System.out.println("1: Lerngruppen anzeigen");
            System.out.println("2: Lerngruppe hinzufügen");
            System.out.println("3: Lerngruppe löschen");
            System.out.println("4: Schüler hinzufügen");
            System.out.println("5: Schüler löschen");
            System.out.println("6: Note hinzufügen");
            System.out.println("0: Beenden");
            System.out.print("Gib die Nummer der gewünschten Operation an: ");

            byte userSelection = sc.nextByte();
            if (userSelection == 1){
                listLerngruppenSchueler();
            }else if(userSelection == 2){
                addLerngruppe();
            }else if(userSelection == 3){
                removeLerngruppe();
            }else if(userSelection == 4){
                addSchueler();
            }else if(userSelection == 5){
                removeSchueler();
            }else if(userSelection == 6){
                addGrade();
            }else if(userSelection == 0){
                running = false;
            }else{
                System.out.println("Falsche Eingabe!");
            }

        }
    }

    private static void listLerngruppenSchueler(){
        if(lerngruppen.size() == 0){
            System.out.println("keine Lerngruppen vorhanden!");
        }else {
            for (Lerngruppe l : lerngruppen) {
                System.out.println(l);
                l.listSchueler();
            }
        }
        System.out.println();
    }

    private static void listLerngruppen(){
        if(lerngruppen.size() == 0){
            System.out.println("keine Lerngruppen vorhanden!");
        }else {
            for(int i = 0; i < lerngruppen.size(); i++){
                System.out.println((i + 1) + ": " + lerngruppen.get(i));
            }
        }
        System.out.println();
    }

    public static byte selectLerngruppe(){
        listLerngruppen();
        System.out.print("Gib die Nummer der gewünschten Lerngruppe ein: ");
        return (byte) (sc.nextByte() - 1);
    }

    private static void addLerngruppe(){
        // Fach
        System.out.println("Fach: ");
        Fach[] faecher = Fach.values();
        for(Fach fach : faecher){
            printWithOrdinal(fach.toString(), (byte) (fach.ordinal() + 1));
        }
        System.out.print("Gib die Nummer des gewünschten Fachs ein: ");
        Fach fach = faecher[(sc.nextByte() - 1)];

        // Stufe
        System.out.println("Stufe: ");
        Stufe[] stufen = Stufe.values();
        for (Stufe stufe : stufen){
            printWithOrdinal(stufe.toString(), (byte) (stufe.ordinal() + 1));
        }
        System.out.print("Gib die Nummer der gewünschten Stufe ein: ");
        Stufe stufe = stufen[(sc.nextByte() - 1)];

        if(stufe.ordinal() < Kurs.MIN_STUFE_KURS){
            addKlasse(fach, stufe);
        } else{
            addKurs(fach,stufe);
        }
    }

    private static void addKlasse(Fach fach, Stufe stufe){
        // Klassenbuchstabe
        System.out.println("Klasse: ");
        Klassenbuchstabe[] klassenbuchstaben = Klassenbuchstabe.values();
        for(Klassenbuchstabe b : klassenbuchstaben){
            printWithOrdinal(b.toString(), (byte) (b.ordinal() + 1));
        }
        System.out.print("Gib die Nummer des gewünschten Klassenbuchstaben ein: ");
        Klassenbuchstabe klassenbuchstabe = klassenbuchstaben[sc.nextByte() - 1];

        // Hauptfach
        boolean hauptfach;
        while(true){
            System.out.print("Soll ein Hauptfach oder ein Nebenfach erstellt werden? Gib ein: 0 für Nebenfach, " +
                    "1 für Hauptfach: ");
            try {
                byte userResponse = sc.nextByte();
                if(userResponse == 0){
                    hauptfach = false;
                    break;
                } else if(userResponse == 1){
                    hauptfach = true;
                    break;
                } else{
                    System.out.println("Falsche Eingabe!");
                }
            } catch (Exception e){
                System.out.println("Falsche Eingabe!");
            }
        }

        // Klasse erstellen und hinzufügen
        Klasse neueKlasse = new Klasse(klassenbuchstabe, hauptfach, stufe, fach);
        lerngruppen.add(neueKlasse);

        System.out.println("Es wurde folgende Klasse erstellt:");
        System.out.println((neueKlasse.getStufe().ordinal()) + 1 + "" + neueKlasse.getKlassenbuchstabe() + ": "
                + neueKlasse.getFach() + ((neueKlasse.isHauptfach()) ? ", Hauptfach" : ", Nebenfach"));

        proofUserInputLerngruppe();
    }

    private static void addKurs(Fach fach, Stufe stufe){
        // Kursnummer
        System.out.print("Gib die gewünschte Kursnummer ein: ");
        byte kursNr = sc.nextByte();

        // Kurstyp
        System.out.println("Kurstyp: ");
        Kurstyp[] kurstypen = Kurstyp.values();
        for(Kurstyp kurstyp : kurstypen){
            printWithOrdinal(kurstyp.toString(), (byte) (kurstyp.ordinal() + 1));
        }
        System.out.print("Gib die Nummer des gewünschten Kurstyps ein: ");
        Kurstyp kurstyp = kurstypen[sc.nextByte() - 1];

        //Kurs erstellen und hinzufügen
        Kurs neuerKurs = new Kurs(kursNr, kurstyp, stufe, fach);
        lerngruppen.add(neuerKurs);

        System.out.println("Es wird folgender Kurs erstellt:");
        System.out.println(neuerKurs.getFach() + "/" + neuerKurs.getStufe() + "/" + neuerKurs.getKursTyp()
                + "" + neuerKurs.getKursNr());

        proofUserInputLerngruppe();
    }

    private static void removeLerngruppe(){
        Lerngruppe geloeschteLerngruppe = lerngruppen.get(selectLerngruppe());
        lerngruppen.remove(geloeschteLerngruppe);

        System.out.println("Es wurde folgende Lerngruppe gelöscht: " + geloeschteLerngruppe);
        System.out.println("Rückgängig: 0, Bestätigen: 1");
        if(sc.nextByte() == 0){
            lerngruppen.add(geloeschteLerngruppe);
        }
        System.out.println();
    }

    private static void addSchueler(){
        System.out.print("Gib den Namen des Schülers ein: ");
        String firstName = sc.next();
        System.out.print("Gib den Nachnamen des Schülers ein: ");
        String lastName = sc.next();

        System.out.print("Geburtstag Jahr: ");
        short year = sc.nextShort();
        System.out.print("Geburtstag Monat: ");
        byte month = sc.nextByte();
        System.out.print("Geburtstag Tag: ");
        byte day = sc.nextByte();
        Date birthday = new Date(year, month, day);

        Lerngruppe lerngruppe = lerngruppen.get(selectLerngruppe());

        Schueler neuerSchueler = new Schueler(firstName, lastName, birthday);
        lerngruppe.getSchuelerList().add(neuerSchueler);

        System.out.println("Folgender Schüler wurde hinzugefügt: " + neuerSchueler + " in " + lerngruppe);
        System.out.println("Rückgängig: 0, Bestätigen: 1");
        if(sc.nextByte() == 0){
            lerngruppe.getSchuelerList().remove(lerngruppe.getSchuelerList().size() - 1);
        }
        System.out.println();
    }

    private static void removeSchueler(){
        Lerngruppe lerngruppe = lerngruppen.get(selectLerngruppe());
        lerngruppe.listSchueler();

        System.out.print("Gib die Nummer des zu löschenden Schülers ein: ");
        Schueler geloeschterSchueler = lerngruppe.getSchuelerList().get(sc.nextByte() - 1);
        lerngruppe.getSchuelerList().remove(geloeschterSchueler);

        System.out.println("Folgender Schueler wurde gelöscht: " + geloeschterSchueler + "aus " + lerngruppe);
        System.out.println("Rückgängig: 0, Bestätigen: 1");
        if(sc.nextByte() == 0){
            lerngruppe.getSchuelerList().add(geloeschterSchueler);
        }
        System.out.println();
    }

    private static void addGrade() throws Exception {
        Lerngruppe l = lerngruppen.get(selectLerngruppe());
        l.listSchueler();
        System.out.print("Gib die Nummer des gewünschten Schülers ein: ");
        Schueler s = l.getSchuelerList().get((sc.nextByte() - 1));

        boolean x = true;
        while (x){
            System.out.println("Gib das gewünschte Quartal ein (1 - 4: ");
            byte quartal = sc.nextByte();
            System.out.println("Gib die gewünschte Note in Punkten ein (0 - 15): ");
            try {
                s.addGrade(quartal, sc.nextByte());
                x = false;
            } catch (PointsException e){
                System.out.println("Die Anzahl der Punkte sollte einen Wert von 0 bis 15 betragen!");
            } catch (QuartalException e){
                System.out.println("Die Zahl des Quartals sollte einen Wert von 1 bis 4 betragen!");
            }
        }
    }

    private static void proofUserInputLerngruppe(){
        System.out.println("Rückgängig: 0, Bestätigen: 1");
        if(sc.nextByte() == 0){
            lerngruppen.remove(lerngruppen.size() - 1);
        }
        System.out.println();
    }

    private static void printWithOrdinal(String str, byte ordinal){
        System.out.println(ordinal + ": " + str);
    }
}

