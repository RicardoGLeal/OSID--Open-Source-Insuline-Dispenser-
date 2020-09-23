package com.example.osid.POJOs;

import java.util.Date;

public class Glucose {
    int Id_Glucose;
    Date fecha;
    int glucose;

    public Glucose(Date fecha, int glucose) {
        this.fecha = fecha;
        this.glucose = glucose;
    }

    public Glucose(int id_Glucose, Date fecha, int glucose) {
        Id_Glucose = id_Glucose;
        this.fecha = fecha;
        this.glucose = glucose;
    }

    public int getId_Glucose() {
        return Id_Glucose;
    }

    public void setId_Glucose(int id_Glucose) {
        Id_Glucose = id_Glucose;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getGlucose() {
        return glucose;
    }

    public void setGlucose(int glucose) {
        this.glucose = glucose;
    }
}
