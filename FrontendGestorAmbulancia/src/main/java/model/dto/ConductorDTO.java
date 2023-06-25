package model.dto;

/**
 * Clase que representa los datos de un conductor.
 */
public class ConductorDTO {

    private Long id;
    private String cedula;
    private String nombre;
    private String apellidos;
    private String correo;
    private String fechaNacimiento;
    private String fechaContrato;
    private String sexo;
    private boolean estado;

    /**
     * Constructor de la clase ConductorDTO.
     */
    public ConductorDTO() {
        super();
    }

    /**
     * Constructor de la clase ConductorDTO.
     *
     * @param id El ID del conductor.
     * @param cedula La cédula del conductor.
     * @param nombre El nombre del conductor.
     * @param apellidos Los apellidos del conductor.
     * @param correo El correo electrónico del conductor.
     * @param fechaNacimiento La fecha de nacimiento del conductor.
     * @param fechaContrato La fecha de contrato del conductor.
     * @param sexo El sexo del conductor.
     * @param estado El estado del conductor.
     */
    public ConductorDTO(Long id, String cedula, String nombre, String apellidos, String correo, String fechaNacimiento,
            String fechaContrato, String sexo, boolean estado) {
        super();
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContrato = fechaContrato;
        this.sexo = sexo;
        this.estado = estado;
    }

    /**
     * Verifica si alguna de las propiedades en el objeto ConductorDTO tiene
     * valores nulos o valores por defecto.
     *
     * @return true si alguna propiedad está vacía o con valor por defecto,
     * false de lo contrario.
     */
    public boolean PropiedadesVacias() {
        return cedula == null || cedula.isEmpty()
                || nombre == null || nombre.isEmpty()
                || apellidos == null || apellidos.isEmpty()
                || correo == null || correo.isEmpty()
                || fechaNacimiento == null || fechaNacimiento.isEmpty()
                || fechaContrato == null || fechaContrato.isEmpty()
                || sexo == null || sexo.isEmpty();
    }

    /**
     * Obtiene el ID del conductor.
     *
     * @return El ID del conductor.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del conductor.
     *
     * @param id El ID del conductor.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del conductor.
     *
     * @return El nombre del conductor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del conductor.
     *
     * @param nombre El nombre del conductor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del conductor.
     *
     * @return Los apellidos del conductor.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del conductor.
     *
     * @param apellidos Los apellidos del conductor.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el correo electrónico del conductor.
     *
     * @return El correo electrónico del conductor.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del conductor.
     *
     * @param correo El correo electrónico del conductor.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la fecha de nacimiento del conductor.
     *
     * @return La fecha de nacimiento del conductor.
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del conductor.
     *
     * @param fechaNacimiento La fecha de nacimiento del conductor.
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene la fecha de contrato del conductor.
     *
     * @return La fecha de contrato del conductor.
     */
    public String getFechaContrato() {
        return fechaContrato;
    }

    /**
     * Establece la fecha de contrato del conductor.
     *
     * @param fechaContrato La fecha de contrato del conductor.
     */
    public void setFechaContrato(String fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    /**
     * Verifica el estado del conductor.
     *
     * @return true si el conductor está activo, false si está inactivo.
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Establece el estado del conductor.
     *
     * @param estado true si el conductor está activo, false si está inactivo.
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la cédula del conductor.
     *
     * @return La cédula del conductor.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece la cédula del conductor.
     *
     * @param cedula La cédula del conductor.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtiene el sexo del conductor.
     *
     * @return El sexo del conductor.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del conductor.
     *
     * @param sexo El sexo del conductor.
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}
