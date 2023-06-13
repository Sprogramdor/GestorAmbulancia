/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.dto;

/**
 *
 * @author nelso
 */
public class AmbulanciaDTO {
    String Placa;
    String Modelo;
    String Tipo;
    boolean Estado;
    String Observacion;

    public AmbulanciaDTO(String Placa, String Modelo, String Tipo, boolean Estado, String Observacion) {
        this.Placa = Placa;
        this.Modelo = Modelo;
        this.Tipo = Tipo;
        this.Estado = Estado;
        this.Observacion = Observacion;
    }

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }
    
    
}
