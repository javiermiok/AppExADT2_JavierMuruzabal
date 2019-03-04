package com.example.a21752434.appexadt2_javiermuruzabal.model;

import java.io.Serializable;

public class Municipio implements Serializable {

    private long id; // no se usa
    private String nombre;

    public Municipio(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
