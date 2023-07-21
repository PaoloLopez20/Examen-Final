package com.lopez.paolo.apiinfraccion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfraccionDTO {
	private int id;
	private String dni;
	private String fecha;
	private String falta;
	private String infraccion;
	private String descripcion;
}
