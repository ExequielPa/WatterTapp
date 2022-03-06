package com.example.wattertapp.Model;

import java.io.Serializable;

public class EstadoEmerg implements Serializable {

    private String id_estado_eme,estado_emergencia,descricpicion_emergencia;

    public EstadoEmerg(){    }

    public EstadoEmerg(String id_estado_eme,String estado_emergencia,String descricpicion_emergencia){
        this.id_estado_eme = id_estado_eme;
        this.estado_emergencia = estado_emergencia;
        this.descricpicion_emergencia = descricpicion_emergencia;
    }

    public String getId_estado_eme() {
        return id_estado_eme;
    }

    public void setId_estado_eme(String id_estado_eme) {
        this.id_estado_eme = id_estado_eme;
    }

    public String getEstado_emergencia() {
        return estado_emergencia;
    }

    public void setEstado_emergencia(String estado_emergencia) {
        this.estado_emergencia = estado_emergencia;
    }

    public String getDescricpicion_emergencia() {
        return descricpicion_emergencia;
    }

    public void setDescricpicion_emergencia(String descricpicion_emergencia) {
        this.descricpicion_emergencia = descricpicion_emergencia;
    }
}
