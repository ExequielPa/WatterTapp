package com.example.wattertapp.Model;

public class Camion {

    String id_camion,patente,anio,chasis,id_tipo_camion,id_marca;

    public Camion() {   }

    public Camion(String id_camion, String patente, String anio, String chasis, String id_tipo_camion, String id_marca) {
        this.id_camion = id_camion;
        this.patente = patente;
        this.anio = anio;
        this.chasis = chasis;
        this.id_tipo_camion = id_tipo_camion;
        this.id_marca = id_marca;
    }

    public String getId_camion() {
        return id_camion;
    }

    public void setId_camion(String id_camion) {
        this.id_camion = id_camion;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getId_tipo_camion() {
        return id_tipo_camion;
    }

    public void setId_tipo_camion(String id_tipo_camion) {
        this.id_tipo_camion = id_tipo_camion;
    }

    public String getId_marca() {
        return id_marca;
    }

    public void setId_marca(String id_marca) {
        this.id_marca = id_marca;
    }
}
