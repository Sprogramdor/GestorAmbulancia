package model.dto;
/**
 * Clase que representa los datos de una asignación.
 */
public class AsignacionDTO {

	private Long id;

	private PeticionDTO peticion;

	private AmbulanciaDTO ambulancia;

	private ConductorDTO conductor;
/**
     * Constructor de la clase AsignacionDTO.
     */
	public AsignacionDTO() {
		super();
	}
 /**
     * Constructor de la clase AsignacionDTO.
     *
     * @param id          El ID de la asignación.
     * @param peticion    La petición asociada a la asignación.
     * @param ambulancia  La ambulancia asignada.
     * @param conductor   El conductor asignado.
     */
	public AsignacionDTO(Long id, PeticionDTO peticion, AmbulanciaDTO ambulancia, ConductorDTO conductor) {
		super();
		this.id = id;
		this.peticion = peticion;
		this.ambulancia = ambulancia;
		this.conductor = conductor;
	}

	/**
     * Obtiene el ID de la asignación.
     *
     * @return El ID de la asignación.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la asignación.
     *
     * @param id El ID de la asignación.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la petición asociada a la asignación.
     *
     * @return La petición asociada a la asignación.
     */
    public PeticionDTO getPeticion() {
        return peticion;
    }

    /**
     * Establece la petición asociada a la asignación.
     *
     * @param peticion La petición asociada a la asignación.
     */
    public void setPeticion(PeticionDTO peticion) {
        this.peticion = peticion;
    }

    /**
     * Obtiene la ambulancia asignada.
     *
     * @return La ambulancia asignada.
     */
    public AmbulanciaDTO getAmbulancia() {
        return ambulancia;
    }

    /**
     * Establece la ambulancia asignada.
     *
     * @param ambulancia La ambulancia asignada.
     */
    public void setAmbulancia(AmbulanciaDTO ambulancia) {
        this.ambulancia = ambulancia;
    }

    /**
     * Obtiene el conductor asignado.
     *
     * @return El conductor asignado.
     */
    public ConductorDTO getConductor() {
        return conductor;
    }

    /**
     * Establece el conductor asignado.
     *
     * @param conductor El conductor asignado.
     */
    public void setConductor(ConductorDTO conductor) {
        this.conductor = conductor;
    }

}
