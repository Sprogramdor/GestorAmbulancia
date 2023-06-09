package com.vv.code.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vv.code.mapper.AmbulanciaDTOMapper;
import com.vv.code.model.dto.AmbulanciaDTO;
import com.vv.code.model.entity.Ambulancia;
import com.vv.code.repository.AmbulanciaRepository;

@Service
/**
 * @author Natanael Muñoz
 * @version 1.0 Date: 09/06/2023
 */
public class AmbulanciaService {

	private final AmbulanciaRepository ambulanciaRepository;
	private final AmbulanciaDTOMapper ambulanciaDTOMapper;

	public AmbulanciaService(AmbulanciaRepository ambulanciaRepository, AmbulanciaDTOMapper ambulanciaDTOMapper) {
		super();
		this.ambulanciaRepository = ambulanciaRepository;
		this.ambulanciaDTOMapper = ambulanciaDTOMapper;
	}

	public ResponseEntity<List<AmbulanciaDTO>> listarAmbulancias() {
		List<AmbulanciaDTO> listaAmbulancias = ambulanciaRepository.findAll().stream().map(ambulanciaDTOMapper)
				.collect(Collectors.toList());

		return new ResponseEntity<>(listaAmbulancias, HttpStatus.OK);
	}

	public ResponseEntity<String> registrarAmbulancia(AmbulanciaDTO ambulanciaDTO) {
		if (ambulanciaDTO == null) {
			// throw new BadRequestException("SGAR-1", "Petición mal creada");
			return new ResponseEntity<String>("FALLIDO", HttpStatus.BAD_REQUEST);
		}

		Ambulancia ambulancia = new Ambulancia();
		ambulancia.setNumeroPlaca(ambulanciaDTO.getNumeroPlaca());
		ambulancia.setModelo(ambulanciaDTO.getModelo());
		ambulancia.setEstado(ambulanciaDTO.isEstado());
		ambulancia.setObservaciones(ambulancia.getObservaciones());
		ambulancia.setTipo(ambulanciaDTO.getTipo());

		ambulanciaRepository.save(ambulancia);
		return new ResponseEntity<String>("EXITOSO", HttpStatus.CREATED);
	}

	public ResponseEntity<String> modificarAmbulancia(Long id, Boolean estado, String observaciones) {
		if (id.equals("") || estado == null || observaciones.isEmpty()) {
			return new ResponseEntity<String>("FALLIDO", HttpStatus.BAD_REQUEST);
		}

		Optional<Ambulancia> ambulancia = ambulanciaRepository.findById(id);

		if (!ambulancia.isPresent()) {
			return new ResponseEntity<String>("NO SE ENCUENTRA ESE RECURSO", HttpStatus.NOT_FOUND);
		}

		ambulancia.get().setEstado(estado);
		ambulancia.get().setObservaciones(observaciones);

		ambulanciaRepository.save(ambulancia.get());

		return new ResponseEntity<String>("EXITOSO", HttpStatus.ACCEPTED);
	}

	public ResponseEntity<String> deshabilitarAmbulancia(Long id) {
		if (id.equals("") || id == null) {
			return new ResponseEntity<String>("FALLIDO", HttpStatus.BAD_REQUEST);
		}

		ambulanciaRepository.deleteById(id);

		return new ResponseEntity<String>("EXITOSO", HttpStatus.ACCEPTED);
	}

}
