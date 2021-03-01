package com.maxkorte.teachers;

public enum Kurstyp {
    ZK(3), LK(1), GK(2);

    private final int rang;

    Kurstyp(int rang){
        this.rang = rang;
    }
}
