package model.dto;

/**
 * Clase que representa los datos de un hospital.
 */
public class HospitalDTO {

    private Long id;
    private String nombre;
    private boolean estado;

    /**
     * Constructor de la clase HospitalDTO.
     *
     * @param id     El ID del hospital.
     * @param nombre El nombre del hospital.
     * @param estado El estado del hospital.
     */
    public HospitalDTO(Long id, String nombre, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    /**
     * Constructor de la clase HospitalDTO.
     */
    public HospitalDTO() {
    }

    /**
     * Obtiene el ID del hospital.
     *
     * @return El ID del hospital.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del hospital.
     *
     * @param id El ID del hospital.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del hospital.
     *
     * @return El nombre del hospital.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del hospital.
     *
     * @param nombre El nombre del hospital.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Verifica el estado del hospital.
     *
     * @return true si el hospital está activo, false si está inactivo.
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece el estado del hospital.
     *
     * @param estado true si el hospital está activo, false si está inactivo.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
