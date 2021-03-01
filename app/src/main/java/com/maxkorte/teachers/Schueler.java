package com.maxkorte.teachers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

public class Schueler {
    // instance variables
    private final String firstName;
    private String lastName;
    private final Date birthday;
    private static int count;
    private final HashMap<Byte, Byte> grades = new HashMap<>();

    // constructors
    public Schueler(String firstName, String lastName, Date birthday){
        if(firstName == null || lastName == null){
            throw new Error("parameters should not be null");
        }
        this.firstName = firstName;
        this.birthday = birthday;
        setLastName(lastName);
        count++;
    }

    public Schueler(String firstName, String lastName){
        this(firstName, lastName, null);
    }

    // standard methods
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public static int getCount(){
        return count;
    }

    public HashMap<Byte, Byte> getGrades(){
        return grades;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", Geburtstag: " + getBirthday().getDay() + "." + getBirthday().getMonth()
                + "." + getBirthday().getYear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schueler)) return false;
        Schueler person = (Schueler) o;
        return getFirstName().equals(person.getFirstName()) && getLastName().equals(person.getLastName())
                && Objects.equals(getBirthday(), person.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getBirthday());
    }

    // custom methods
    public int getAge(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN);
        int birthday = this.birthday.getYear() * 365 + this.birthday.getMonth() * 12 + this.birthday.getDay();
        int today = (new Date().getYear() + 1900) * 365 + (new Date().getMonth()) * 12 + (new Date().getDay());

        return (today - birthday - 1) / 365;
    }

    public void addGrade(byte quartal, byte points) throws Exception{
        if(quartal < 1 || quartal > 4){
            throw new QuartalException();
        } else if(points < 0 || points > 15){
            throw new PointsException();
        }
        grades.put(quartal, points);
    }

    public int getH1grade(){
        return (grades.get(1) + grades.get(2)) / 2;
    }

    public int getH2grade(){
        return (grades.get(3) + grades.get(4)) / 2;
    }
}
