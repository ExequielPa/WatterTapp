package com.example.wattertapp.Model;

public class EmergenciaL {

    private String id_emergencia,descripcion_emer,fecha_inicio,hora_inicio,fecha_termino,hora_termino
            ,latitud,longuitud,direccion,id_estado_eme,id_responsable,id_categoria;

    public EmergenciaL(){};

    public EmergenciaL(String id_emergencia, String descripcion_emer, String fecha_inicio, String hora_inicio,
                       String fecha_termino, String hora_termino, String latitud, String longuitud, String direccion,
                       String id_estado_eme, String id_responsable, String id_categoria) {
        this.id_emergencia = id_emergencia;
        this.descripcion_emer = descripcion_emer;
        this.fecha_inicio = fecha_inicio;
        this.hora_inicio = hora_inicio;
        this.fecha_termino = fecha_termino;
        this.hora_termino = hora_termino;
        this.latitud = latitud;
        this.longuitud = longuitud;
        this.direccion = direccion;
        this.id_estado_eme = id_estado_eme;
        this.id_responsable = id_responsable;
        this.id_categoria = id_categoria;
    }

    public String getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(String id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getId_emergencia() {
        return id_emergencia;
    }

    public void setId_emergencia(String id_emergencia) {
        this.id_emergencia = id_emergencia;
    }

    public String getDescripcion_emer() {
        return descripcion_emer;
    }

    public void setDescripcion_emer(String descripcion_emer) {
        this.descripcion_emer = descripcion_emer;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(String fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    public String getHora_termino() {
        return hora_termino;
    }

    public void setHora_termino(String hora_termino) {
        this.hora_termino = hora_termino;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLonguitud() {
        return longuitud;
    }

    public void setLonguitud(String longuitud) {
        this.longuitud = longuitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId_estado_eme() {
        return id_estado_eme;
    }

    public void setId_estado_eme(String id_estado_eme) {
        this.id_estado_eme = id_estado_eme;
    }

    public String getId_responsable() {
        return id_responsable;
    }

    public void setId_responsable(String id_responsable) {
        this.id_responsable = id_responsable;
    }
}
