package com.vv.code.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.vv.code.model.dto.ConductorDTO;
import com.vv.code.model.entity.Conductor;

@Component
public class ConductorMapper implements Function<Conductor, ConductorDTO> {

	@Override
	public ConductorDTO apply(Conductor t) {
		return new ConductorDTO(t.getId(), t.getCedula(), t.getNombre(), t.getApellidos(), t.getCorreo(),
				String.valueOf(t.getFechaNacimiento()), String.valueOf(t.getFechaContrato()), t.getSexo(),
				t.isEstado());
	}

}
