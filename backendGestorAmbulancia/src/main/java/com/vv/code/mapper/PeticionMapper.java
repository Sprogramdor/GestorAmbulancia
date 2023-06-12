package com.vv.code.mapper;

import java.text.SimpleDateFormat;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.vv.code.model.dto.AmbulanciaDTO;
import com.vv.code.model.dto.ClienteDTO;
import com.vv.code.model.dto.HospitalDTO;
import com.vv.code.model.dto.PeticionDTO;
import com.vv.code.model.entity.Ambulancia;
import com.vv.code.model.entity.Peticion;

@Component
public class PeticionMapper implements Function<Peticion, PeticionDTO> {

	@Override
	public PeticionDTO apply(Peticion t) {
		PeticionDTO peticionDTO = new PeticionDTO();
		peticionDTO.setId(t.getId());
		peticionDTO.setPuntoOrigen(t.getPuntoOrigen());
		peticionDTO.setPuntoDestino(t.getPuntoDestino());
		peticionDTO.setEstado(t.isEstado());

		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setCedula(t.getCliente().getCedula());
		clienteDTO.setNombres(t.getCliente().getNombre());
		clienteDTO.setApellidos(t.getCliente().getApellidos());

		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
		String fechaNacimiento = formato.format(t.getCliente().getFechaNacimiento());
		clienteDTO.setFechaNacimiento(fechaNacimiento);
		clienteDTO.setSexo(t.getCliente().getSexo());

		peticionDTO.setCliente(clienteDTO);

		HospitalDTO hospitalDTO = new HospitalDTO();
		hospitalDTO.setId(t.getHospital().getId());
		hospitalDTO.setNombre(t.getHospital().getNombre());

		for (Ambulancia ambulancia : t.getAmbulancia()) {
			AmbulanciaDTO ambulanciaDTO = new AmbulanciaDTO();
			ambulanciaDTO.setModelo(ambulancia.getModelo());
			ambulanciaDTO.setNumeroPlaca(ambulancia.getNumeroPlaca());
			ambulanciaDTO.setTipo(ambulancia.getTipo());
			ambulanciaDTO.setObservaciones(ambulancia.getObservaciones());
			peticionDTO.setAmbulancia(ambulanciaDTO);
		}

		peticionDTO.setHospital(hospitalDTO);

		return peticionDTO;
	}

}
