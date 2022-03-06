package com.example.wattertapp.Model;

import java.io.Serializable;

public class Emergencia implements Serializable {

    private String id_emergencia,descripcion_emer,direccion,id_cod_emer,codigo_categoria,nombre_categoria
            ,rut_voluntario,nombre_voluntario,apellido_voluntario,estado_emergencia,nombre_compania,cod_camion,
        latitud,longuitud;

    public Emergencia() {
    }

    public Emergencia(String id_emergencia, String descripcion_emer, String direccion, String id_cod_emer
            , String codigo_categoria, String nombre_categoria, String rut_voluntario, String nombre_voluntario,
                      String apellido_voluntario, String estado_emergencia, String nombre_compania, String cod_camion,
                      String latitud, String longuitud) {
        this.id_emergencia = id_emergencia;
        this.descripcion_emer = descripcion_emer;
        this.direccion = direccion;
        this.id_cod_emer = id_cod_emer;
        this.codigo_categoria = codigo_categoria;
        this.nombre_categoria = nombre_categoria;
        this.rut_voluntario = rut_voluntario;
        this.nombre_voluntario = nombre_voluntario;
        this.apellido_voluntario = apellido_voluntario;
        this.estado_emergencia = estado_emergencia;
        this.nombre_compania = nombre_compania;
        this.cod_camion = cod_camion;
        this.longuitud = longuitud;
        this.latitud = latitud;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getId_cod_emer() {
        return id_cod_emer;
    }

    public void setId_cod_emer(String id_cod_emer) {
        this.id_cod_emer = id_cod_emer;
    }

    public String getCodigo_categoria() {
        return codigo_categoria;
    }

    public void setCodigo_categoria(String codigo_categoria) {
        this.codigo_categoria = codigo_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public String getRut_voluntario() {
        return rut_voluntario;
    }

    public void setRut_voluntario(String rut_voluntario) {
        this.rut_voluntario = rut_voluntario;
    }

    public String getNombre_voluntario() {
        return nombre_voluntario;
    }

    public void setNombre_voluntario(String nombre_voluntario) {
        this.nombre_voluntario = nombre_voluntario;
    }

    public String getApellido_voluntario() {
        return apellido_voluntario;
    }

    public void setApellido_voluntario(String apellido_voluntario) {
        this.apellido_voluntario = apellido_voluntario;
    }

    public String getEstado_emergencia() {
        return estado_emergencia;
    }

    public void setEstado_emergencia(String estado_emergencia) {
        this.estado_emergencia = estado_emergencia;
    }

    public String getNombre_compania() {
        return nombre_compania;
    }

    public void setNombre_compania(String nombre_compania) {
        this.nombre_compania = nombre_compania;
    }

    public String getCod_camion() {
        return cod_camion;
    }

    public void setCod_camion(String cod_camion) {
        this.cod_camion = cod_camion;
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
}
