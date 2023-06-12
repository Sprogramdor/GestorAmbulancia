package com.vv.code.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vv.code.mapper.PeticionMapper;
import com.vv.code.model.dto.PeticionDTO;
import com.vv.code.model.entity.Ambulancia;
import com.vv.code.model.entity.Cliente;
import com.vv.code.model.entity.Hospital;
import com.vv.code.model.entity.Peticion;
import com.vv.code.repository.AmbulanciaRepository;
import com.vv.code.repository.ClienteRepository;
import com.vv.code.repository.HospitalRepository;
import com.vv.code.repository.PeticionesRepository;

@Service
public class PeticionService {

	private final PeticionesRepository peticionesRepository;
	private final HospitalRepository hospitalRepository;
	private final ClienteRepository clienteRepository;
	private final AmbulanciaRepository ambulanciaRepository;
	private final PeticionMapper mapper;

	public PeticionService(PeticionesRepository peticionesRepository, HospitalRepository hospitalRepository,
			ClienteRepository clienteRepository, AmbulanciaRepository ambulanciaRepository, PeticionMapper mapper) {
		super();
		this.peticionesRepository = peticionesRepository;
		this.hospitalRepository = hospitalRepository;
		this.clienteRepository = clienteRepository;
		this.ambulanciaRepository = ambulanciaRepository;
		this.mapper = mapper;
	}

	public ResponseEntity<List<PeticionDTO>> listarPeticiones() {
		List<Peticion> listaPeticiones = peticionesRepository.findAll();
		List<PeticionDTO> listaPeticionesDTO = listaPeticiones.stream().map(mapper).collect(Collectors.toList());

		return new ResponseEntity<List<PeticionDTO>>(listaPeticionesDTO, HttpStatus.OK);
	}

	public ResponseEntity<String> registrarPeticion(PeticionDTO peticionDTO) {
		if (peticionDTO == null) {
			return new ResponseEntity<String>("ERROR EN LA TRAMA", HttpStatus.BAD_REQUEST);
		}

		Peticion peticion = new Peticion();
		peticion.setPuntoOrigen(peticionDTO.getPuntoOrigen());
		peticion.setPuntoDestino(peticionDTO.getPuntoDestino());
		peticion.setEstado(peticionDTO.isEstado());

		Optional<Hospital> hospital = hospitalRepository.findById(peticionDTO.getHospital().getId());
		Optional<Cliente> cliente = clienteRepository.findByCedula(peticionDTO.getCliente().getCedula());
		Optional<Set<Ambulancia>> setAmbulancia = ambulanciaRepository
				.findbyNumeroPlaca(peticionDTO.getAmbulancia().getNumeroPlaca());

		if (!hospital.isPresent() || !cliente.isPresent() || !setAmbulancia.isPresent()) {
			return new ResponseEntity<String>("NO EXISTE EL RECURSO", HttpStatus.NOT_FOUND);
		}

		peticion.setHospital(hospital.get());
		peticion.setCliente(cliente.get());
		peticion.setAmbulancia(setAmbulancia.get());

		peticionesRepository.save(peticion);

		for (Ambulancia ambulancia : setAmbulancia.get()) {
			ambulancia.setPeticion(peticion);
			ambulanciaRepository.save(ambulancia);
		}

		return new ResponseEntity<String>("EXITOSO", HttpStatus.CREATED);
	}

	public ResponseEntity<String> modificarConductor(Long id, PeticionDTO peticionDTO) {
		if (id <= 0 || id == null || peticionDTO == null) {
			return new ResponseEntity<String>("ERROR EN LA TRAMA", HttpStatus.BAD_REQUEST);
		}

		Optional<Peticion> peticion = peticionesRepository.findById(id);
		peticion.get().setPuntoOrigen(peticionDTO.getPuntoOrigen());
		peticion.get().setPuntoDestino(peticionDTO.getPuntoDestino());
		peticion.get().setEstado(peticionDTO.isEstado());

		Optional<Hospital> hospital = hospitalRepository.findById(peticionDTO.getHospital().getId());
		Optional<Cliente> cliente = clienteRepository.findByCedula(peticionDTO.getCliente().getCedula());
		Optional<Set<Ambulancia>> setAmbulancia = ambulanciaRepository
				.findbyNumeroPlaca(peticionDTO.getAmbulancia().getNumeroPlaca());

		if (!hospital.isPresent() || !cliente.isPresent() || !setAmbulancia.isPresent()) {
			return new ResponseEntity<String>("NO EXISTE EL RECURSO", HttpStatus.NOT_FOUND);
		}

		peticion.get().setHospital(hospital.get());
		peticion.get().setCliente(cliente.get());
		peticion.get().setAmbulancia(setAmbulancia.get());

		peticionesRepository.save(peticion.get());

		for (Ambulancia ambulancia : setAmbulancia.get()) {
			ambulancia.setPeticion(peticion.get());
			ambulanciaRepository.save(ambulancia);
		}

		return new ResponseEntity<String>("EXITOSO", HttpStatus.OK);
	}

	public ResponseEntity<String> eliminarPeticion(Long id) {
		if (id <= 0 | id == null) {
			return new ResponseEntity<String>("ERROR DE PETICION", HttpStatus.BAD_REQUEST);
		}

		Optional<Peticion> peticion = peticionesRepository.findById(id);

		if (!peticion.isPresent()) {
			return new ResponseEntity<String>("NO EXISTE LA PETICION", HttpStatus.NOT_FOUND);
		}

		peticionesRepository.delete(peticion.get());

		return new ResponseEntity<String>("EXITOSO", HttpStatus.ACCEPTED);
	}

}
