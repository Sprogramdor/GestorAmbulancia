package model.dto;

import java.util.Date;

/**
 * Clase que representa los datos de un usuario.
 */
public class UsuarioDTO {

    private Long id;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String sexo;
    private String correo;
    private String fechaNacimiento;
    private Date fechaContrato;
    private String nombreUsuario;
    private String contrasena;
    private String tipo;

    /**
     * Constructor de la clase UsuarioDTO.
     *
     * @param cedula          La cédula del usuario.
     * @param nombres         Los nombres del usuario.
     * @param apellidos       Los apellidos del usuario.
     * @param sexo            El sexo del usuario.
     * @param correo          El correo electrónico del usuario.
     * @param fechaNacimiento La fecha de nacimiento del usuario.
     * @param fechaContrato   La fecha de contrato del usuario.
     * @param nombreUsuario   El nombre de usuario del usuario.
     * @param tipo            El tipo de usuario.
     */
    public UsuarioDTO(String cedula, String nombres, String apellidos, String sexo, String correo, String fechaNacimiento,
                      Date fechaContrato, String nombreUsuario, String tipo) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContrato = fechaContrato;
        this.nombreUsuario = nombreUsuario;
        this.tipo = tipo;
    }

    public UsuarioDTO() {
    }

    /**
     * Obtiene la cédula del usuario.
     *
     * @return La cédula del usuario.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece la cédula del usuario.
     *
     * @param cedula La cédula del usuario.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtiene los nombres del usuario.
     *
     * @return Los nombres del usuario.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Establece los nombres del usuario.
     *
     * @param nombres Los nombres del usuario.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Obtiene los apellidos del usuario.
     *
     * @return Los apellidos del usuario.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del usuario.
     *
     * @param apellidos Los apellidos del usuario.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el sexo del usuario.
     *
     * @return El sexo del usuario.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Establece el sexo del usuario.
     *
     * @param sexo El sexo del usuario.
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correo El correo electrónico del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la fecha de nacimiento del usuario.
     *
     * @return La fecha de nacimiento del usuario.
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del usuario.
     *
     * @param fechaNacimiento La fecha de nacimiento del usuario.
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Obtiene la fecha de contrato del usuario.
     *
     * @return La fecha de contrato del usuario.
     */
    public Date getFechaContrato() {
        return fechaContrato;
    }

    /**
     * Establece la fecha de contrato del usuario.
     *
     * @param fechaContrato La fecha de contrato del usuario.
     */
    public void setFechaContrato(Date fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    /**
     * Obtiene el nombre de usuario del usuario.
     *
     * @return El nombre de usuario del usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario del usuario.
     *
     * @param nombreUsuario El nombre de usuario del usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena La contraseña del usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el tipo de usuario.
     *
     * @return El tipo de usuario.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de usuario.
     *
     * @param tipo El tipo de usuario.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return El ID del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param id El ID del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

}
