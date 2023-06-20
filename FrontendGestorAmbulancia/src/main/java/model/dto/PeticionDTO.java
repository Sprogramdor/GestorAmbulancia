package model.dto;

/**
 * Clase que representa los datos de una petición.
 */
public class PeticionDTO {

    private Long id;
    private String puntoOrigen;
    private String puntoDestino;
    private HospitalDTO hospital;
    private ClienteDTO cliente;
    private AmbulanciaDTO ambulancia;
    private ConductorDTO conductor;
    private boolean estado;

    /**
     * Constructor de la clase PeticionDTO.
     *
     * @param puntoOrigen   El punto de origen de la petición.
     * @param puntoDestino  El punto de destino de la petición.
     * @param hospital      El hospital asociado a la petición.
     * @param cliente       El cliente asociado a la petición.
     * @param ambulancia    La ambulancia asociada a la petición.
     * @param conductor     El conductor asociado a la petición.
     * @param estado        El estado de la petición.
     */
    public PeticionDTO(String puntoOrigen, String puntoDestino, HospitalDTO hospital, ClienteDTO cliente,
                       AmbulanciaDTO ambulancia, ConductorDTO conductor, boolean estado) {
      
        this.puntoOrigen = puntoOrigen;
        this.puntoDestino = puntoDestino;
        this.hospital = hospital;
        this.cliente = cliente;
        this.ambulancia = ambulancia;
        this.conductor = conductor;
        this.estado = estado;
    }
/**
     * Constructor de la clase PeticionDTO por defecto.
     **/
    public PeticionDTO() {
    }
    

    /**
     * Obtiene el ID de la petición.
     *
     * @return El ID de la petición.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la petición.
     *
     * @param id El ID de la petición.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el punto de origen de la petición.
     *
     * @return El punto de origen de la petición.
     */
    public String getPuntoOrigen() {
        return puntoOrigen;
    }

    /**
     * Establece el punto de origen de la petición.
     *
     * @param puntoOrigen El punto de origen de la petición.
     */
    public void setPuntoOrigen(String puntoOrigen) {
        this.puntoOrigen = puntoOrigen;
    }

    /**
     * Obtiene el punto de destino de la petición.
     *
     * @return El punto de destino de la petición.
     */
    public String getPuntoDestino() {
        return puntoDestino;
    }

    /**
     * Establece el punto de destino de la petición.
     *
     * @param puntoDestino El punto de destino de la petición.
     */
    public void setPuntoDestino(String puntoDestino) {
        this.puntoDestino = puntoDestino;
    }

    /**
     * Obtiene el hospital asociado a la petición.
     *
     * @return El hospital asociado a la petición.
     */
    public HospitalDTO getHospital() {
        return hospital;
    }

    /**
     * Establece el hospital asociado a la petición.
     *
     * @param hospital El hospital asociado a la petición.
     */
    public void setHospital(HospitalDTO hospital) {
        this.hospital = hospital;
    }

    /**
     * Obtiene el cliente asociado a la petición.
     *
     * @return El cliente asociado a la petición.
     */
    public ClienteDTO getCliente() {
        return cliente;
    }

    /**
     * Establece el cliente asociado a la petición.
     *
     * @param cliente El cliente asociado a la petición.
     */
    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtiene el estado de la petición.
     *
     * @return true si la petición está en estado activo, false si está inactivo.
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece el estado de la petición.
     *
     * @param estado true si la petición está en estado activo, false si está inactivo.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la ambulancia asociada a la petición.
     *
     * @return La ambulancia asociada a la petición.
     */
    public AmbulanciaDTO getAmbulancia() {
        return ambulancia;
    }

    /**
     * Establece la ambulancia asociada a la petición.
     *
     * @param ambulancia La ambulancia asociada a la petición.
     */
    public void setAmbulancia(AmbulanciaDTO ambulancia) {
        this.ambulancia = ambulancia;
    }

    /**
     * Obtiene el conductor asociado a la petición.
     *
     * @return El conductor asociado a la petición.
     */
    public ConductorDTO getConductor() {
        return conductor;
    }

    /**
     * Establece el conductor asociado a la petición.
     *
     * @param conductor El conductor asociado a la petición.
     */
    public void setConductor(ConductorDTO conductor) {
        this.conductor = conductor;
    }
}
