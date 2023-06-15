package model.dto;

public class PeticionDTO {

	private Long id;
	private String puntoOrigen;
	private String puntoDestino;
	private HospitalDTO hospital;
	private ClienteDTO cliente;
	private AmbulanciaDTO ambulancia;
	private ConductorDTO conductor;
	private boolean estado;

    public PeticionDTO(Long id, String puntoOrigen, String puntoDestino, HospitalDTO hospital, ClienteDTO cliente, AmbulanciaDTO ambulancia, ConductorDTO conductor, boolean estado) {
        this.id = id;
        this.puntoOrigen = puntoOrigen;
        this.puntoDestino = puntoDestino;
        this.hospital = hospital;
        this.cliente = cliente;
        this.ambulancia = ambulancia;
        this.conductor = conductor;
        this.estado = estado;
    }
        

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPuntoOrigen() {
		return puntoOrigen;
	}

	public void setPuntoOrigen(String puntoOrigen) {
		this.puntoOrigen = puntoOrigen;
	}

	public String getPuntoDestino() {
		return puntoDestino;
	}

	public void setPuntoDestino(String puntoDestino) {
		this.puntoDestino = puntoDestino;
	}

	public HospitalDTO getHospital() {
		return hospital;
	}

	public void setHospital(HospitalDTO hospital) {
		this.hospital = hospital;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public AmbulanciaDTO getAmbulancia() {
		return ambulancia;
	}

	public void setAmbulancia(AmbulanciaDTO ambulancia) {
		this.ambulancia = ambulancia;
	}

	public ConductorDTO getConductor() {
		return conductor;
	}

	public void setConductor(ConductorDTO conductor) {
		this.conductor = conductor;
	}

}
