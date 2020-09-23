package com.example.osid.POJOs;

import java.util.Date;

public class Insuline {
    int Id_Insuline;
    Date fecha;
    float insuline;

    public Insuline() {
    }

    public Insuline(Date fecha, float insuline) {
        this.fecha = fecha;
        this.insuline = insuline;
    }

    public Insuline(int id_Insuline, Date fecha, float insuline) {
        Id_Insuline = id_Insuline;
        this.fecha = fecha;
        this.insuline = insuline;
    }

    public int getId_Insuline() {
        return Id_Insuline;
    }

    public void setId_Insuline(int id_Insuline) {
        Id_Insuline = id_Insuline;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getInsuline() {
        return insuline;
    }

    public void setInsuline(float insuline) {
        this.insuline = insuline;
    }
}
