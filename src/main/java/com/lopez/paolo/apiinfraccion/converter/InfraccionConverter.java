package com.lopez.paolo.apiinfraccion.converter;

import org.springframework.stereotype.Component;

import com.lopez.paolo.apiinfraccion.dto.InfraccionDTO;
import com.lopez.paolo.apiinfraccion.entity.Infraccion;

@Component
public class InfraccionConverter extends AbstractConverter<Infraccion, InfraccionDTO> {
	
	@Override
	public InfraccionDTO fromEntity(Infraccion entity) {
		if(entity==null) return null;
		return InfraccionDTO.builder()
				.id(entity.getId())
				.dni(entity.getDni())
				.fecha(entity.getFecha())
				.falta(entity.getFalta())
				.infraccion(entity.getInfraccion())
				.descripcion(entity.getDescripcion())
				.build();
	}
	
	@Override
	public Infraccion fromDTO(InfraccionDTO dtos) {
		if(dtos==null) return null;
		return Infraccion.builder()
				.id(dtos.getId())
				.dni(dtos.getDni())
				.fecha(dtos.getFecha())
				.falta(dtos.getFalta())
				.infraccion(dtos.getInfraccion())
				.descripcion(dtos.getDescripcion())
				.build();
	}
}
