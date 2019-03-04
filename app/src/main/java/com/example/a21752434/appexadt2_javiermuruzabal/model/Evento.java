package com.example.a21752434.appexadt2_javiermuruzabal.model;

import java.io.Serializable;

public class Evento implements Serializable {

    private long id;
    private String nEvento;
    private String nMunicipio;
    private String descripcion;
    private String fecha;
    private String hora;

    /*--------------------------------         CONSTRUCTORES          ----------------------------*/

    public Evento(long id, String nEvento, String nMunicipio, String descripcion, String fecha, String hora) {
        this.id = id;
        this.nEvento = nEvento;
        this.nMunicipio = nMunicipio;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Evento(String nEvento, String nMunicipio, String descripcion, String fecha, String hora) {
        this.nEvento = nEvento;
        this.nMunicipio = nMunicipio;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
    }

    /*--------------------------------            GETTERS             ----------------------------*/

    public long getId() {
        return id;
    }

    public String getnEvento() {
        return nEvento;
    }

    public String getnMunicipio() {
        return nMunicipio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    /*--------------------------------            SETTERS             ----------------------------*/

}
