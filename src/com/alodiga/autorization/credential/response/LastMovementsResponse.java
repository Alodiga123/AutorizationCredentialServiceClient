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
public class LastMovementsResponse implements Serializable {

    private String codigoError;
    private String mensajeError;
    private String codigoRespuesta;
    private String mensajeRespuesta;
    private String codigoAutorizacion;
    private String disponibleConsumos;
    private String disponibleCuotas;
    private String disponibleAdelantos;
    private String disponiblePrestamos;   
    private String saldo;
    private String saldoEnDolares;
    private String pagoMinimo;
    private String fechaVencimientoUltimaLiquidacion;
    private String fecha;
    private String hora;
    private String comercio;
    private String importe;
    private String descripcionImporte;
    private String codigoAutorizacion2;
    

    public LastMovementsResponse() {
    }

    public LastMovementsResponse(String codigoError, String mensajeError, String codigoRespuesta, String mensajeRespuesta, String codigoAutorizacion, String disponibleConsumos, String disponibleCuotas, String disponibleAdelantos, String disponiblePrestamos, String saldo, String saldoEnDolares, String pagoMinimo, String fechaVencimientoUltimaLiquidacion, String fecha, String hora, String comercio, String importe, String descripcionImporte, String codigoAutorizacion2) {
        this.codigoError = codigoError;
        this.mensajeError = mensajeError;
        this.codigoRespuesta = codigoRespuesta;
        this.mensajeRespuesta = mensajeRespuesta;
        this.codigoAutorizacion = codigoAutorizacion;
        this.disponibleConsumos = disponibleConsumos;
        this.disponibleCuotas = disponibleCuotas;
        this.disponibleAdelantos = disponibleAdelantos;
        this.disponiblePrestamos = disponiblePrestamos;
        this.saldo = saldo;
        this.saldoEnDolares = saldoEnDolares;
        this.pagoMinimo = pagoMinimo;
        this.fechaVencimientoUltimaLiquidacion = fechaVencimientoUltimaLiquidacion;
        this.fecha = fecha;
        this.hora = hora;
        this.comercio = comercio;
        this.importe = importe;
        this.descripcionImporte = descripcionImporte;
        this.codigoAutorizacion2 = codigoAutorizacion2;
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

    public String getDisponibleConsumos() {
        return disponibleConsumos;
    }

    public void setDisponibleConsumos(String disponibleConsumos) {
        this.disponibleConsumos = disponibleConsumos;
    }

    public String getDisponibleCuotas() {
        return disponibleCuotas;
    }

    public void setDisponibleCuotas(String disponibleCuotas) {
        this.disponibleCuotas = disponibleCuotas;
    }

    public String getDisponibleAdelantos() {
        return disponibleAdelantos;
    }

    public void setDisponibleAdelantos(String disponibleAdelantos) {
        this.disponibleAdelantos = disponibleAdelantos;
    }

    public String getDisponiblePrestamos() {
        return disponiblePrestamos;
    }

    public void setDisponiblePrestamos(String disponiblePrestamos) {
        this.disponiblePrestamos = disponiblePrestamos;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getSaldoEnDolares() {
        return saldoEnDolares;
    }

    public void setSaldoEnDolares(String saldoEnDolares) {
        this.saldoEnDolares = saldoEnDolares;
    }

    public String getPagoMinimo() {
        return pagoMinimo;
    }

    public void setPagoMinimo(String pagoMinimo) {
        this.pagoMinimo = pagoMinimo;
    }

    public String getFechaVencimientoUltimaLiquidacion() {
        return fechaVencimientoUltimaLiquidacion;
    }

    public void setFechaVencimientoUltimaLiquidacion(String fechaVencimientoUltimaLiquidacion) {
        this.fechaVencimientoUltimaLiquidacion = fechaVencimientoUltimaLiquidacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    public String getImporte() {
        return importe;
    }

    public void setImporte(String importe) {
        this.importe = importe;
    }

    public String getDescripcionImporte() {
        return descripcionImporte;
    }

    public void setDescripcionImporte(String descripcionImporte) {
        this.descripcionImporte = descripcionImporte;
    }

    public String getCodigoAutorizacion2() {
        return codigoAutorizacion2;
    }

    public void setCodigoAutorizacion2(String codigoAutorizacion2) {
        this.codigoAutorizacion2 = codigoAutorizacion2;
    }

   
}
