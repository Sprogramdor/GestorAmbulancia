package model.dto;

/**
 *
 * @author NELSON ZAMORA 
 * Clase que representa los datos de una ambulancia.
 */
public class AmbulanciaDTO {
    String Placa;
    String Modelo;
    String Tipo;
    boolean Estado;
    String Observacion;

     /**
     * Constructor de la clase AmbulanciaDTO.
     *
     * @param Placa       La placa de la ambulancia.
     * @param Modelo      El modelo de la ambulancia.
     * @param Tipo        El tipo de la ambulancia.
     * @param Estado      El estado de la ambulancia.
     * @param Observacion La observación de la ambulancia.
     */
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
