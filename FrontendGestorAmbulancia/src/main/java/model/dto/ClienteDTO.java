package model.dto;

import java.util.List;

/**
 * Clase que representa los datos de un cliente.
 */
public class ClienteDTO {

    private Long id;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String correo;
    private String sexo;
    private String fechaNacimiento;
    private List<String> numeroTelefonico;

    /**
     * Constructor de la clase ClienteDTO.
     */
    public ClienteDTO() {
    }

    /**
     * Constructor de la clase ClienteDTO.
     *
     * @param identificador   El ID del cliente.
     * @param cedula          La cédula del cliente.
     * @param nombres         Los nombres del cliente.
     * @param apellidos       Los apellidos del cliente.
     * @param correo          El correo electrónico del cliente.
     * @param sexo            El sexo del cliente.
     * @param fechaNacimiento La fecha de nacimiento del cliente.
     */
    public ClienteDTO(Long identificador, String cedula, String nombres, String apellidos, String correo, String sexo,
            String fechaNacimiento) {
        super();
        this.id = identificador;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene el ID del cliente.
     *
     * @return El ID del cliente.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param identificador El ID del cliente.
     */
    public void setId(Long identificador) {
        this.id = identificador;
    }

    /**
     * Obtiene la cédula del cliente.
     *
     * @return La cédula del cliente.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece la cédula del cliente.
     *
     * @param cedula La cédula del cliente.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtiene los nombres del cliente.
     *
     * @return Los nombres del cliente.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del cliente.
     *
     * @param nombres Los nombres del cliente.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene los apellidos del cliente.
     *
     * @return Los apellidos del cliente.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del cliente.
     *
     * @param apellidos Los apellidos del cliente.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     *
     * @return El correo electrónico del cliente.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del cliente.
     *
     * @param correo El correo electrónico del cliente.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el sexo del cliente.
     *
     * @return El sexo del cliente.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del cliente.
     *
     * @param sexo El sexo del cliente.
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtiene la fecha de nacimiento del cliente.
     *
     * @return La fecha de nacimiento del cliente.
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del cliente.
     *
     * @param fechaNacimiento La fecha de nacimiento del cliente.
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene la lista de números telefónicos del cliente.
     *
     * @return La lista de números telefónicos del cliente.
     */
    public List<String> getNumeroTelefonico() {
        return numeroTelefonico;
    }

    /**
     * Establece la lista de números telefónicos del cliente.
     *
     * @param numeroTelefonico La lista de números telefónicos del cliente.
     */
    public void setNumeroTelefonico(List<String> numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

}
