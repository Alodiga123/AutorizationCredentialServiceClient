/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alodiga.autorization.credential.response;

import java.io.Serializable;

/**
 *
 * @author ltoro
 */
public class DispertionResponse implements Serializable {

    private String codigoError;
    private String mensajeError;
    private String codigoRespuesta;
    private String mensajeRespuesta;
    private String codigoAutorizacion;
   

    public DispertionResponse() {
    }

    public DispertionResponse(String codigoError, String mensajeError, String codigoRespuesta, String mensajeRespuesta) {
        this.codigoError = codigoError;
        this.mensajeError = mensajeError;
        this.codigoRespuesta = codigoRespuesta;
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public DispertionResponse(String codigoError, String mensajeError, String codigoRespuesta, String mensajeRespuesta, String codigoAutorizacion) {
        this.codigoError = codigoError;
        this.mensajeError = mensajeError;
        this.codigoRespuesta = codigoRespuesta;
        this.mensajeRespuesta = mensajeRespuesta;
        this.codigoAutorizacion = codigoAutorizacion;

    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(String codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public String getCodigoAutorizacion() {
        return codigoAutorizacion;
    }

    public void setCodigoAutorizacion(String codigoAutorizacion) {
        this.codigoAutorizacion = codigoAutorizacion;
    }
   
}
