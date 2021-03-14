package com.maxkorte.teachers;

import java.util.HashMap;
import java.util.Objects;

public class Schueler {
    // instance variables
    private final String firstName;
    private String lastName;
    private static int count;
    private final HashMap<Integer, Integer> grades = new HashMap<>();

    // constructors
    public Schueler(String firstName, String lastName){
        if(firstName == null || lastName == null){
            throw new IllegalArgumentException("parameters should not be null");
        }
        this.firstName = firstName;
        setLastName(lastName);
        count++;
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

    public static int getCount(){
        return count;
    }

    public HashMap<Integer, Integer> getGrades(){
        return grades;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schueler)) return false;
        Schueler person = (Schueler) o;
        return getFirstName().equals(person.getFirstName()) && getLastName().equals(person.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }

    public void addGrade(int quartal, int points) throws Exception{
        if(quartal < 1 || quartal > 4) throw new QuartalException();
        if(points < 0 || points > 15) throw new PointsException();

        grades.put(quartal, points);
    }

    public Double getH1grade(){
        try {
            return (grades.get(1) + grades.get(2)) / 2.0;
        } catch (Exception e){
            return null;
        }
    }

    public Double getH2grade(){
        try {
            return (grades.get(3) + grades.get(4)) / 2.0;
        } catch (Exception e){
            return null;
        }
    }
}
