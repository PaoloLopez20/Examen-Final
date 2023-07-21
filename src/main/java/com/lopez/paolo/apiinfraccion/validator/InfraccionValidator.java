package com.lopez.paolo.apiinfraccion.validator;

import com.lopez.paolo.apiinfraccion.entity.Infraccion;
import com.lopez.paolo.apiinfraccion.exceptions.ValidateServiceException;

public class InfraccionValidator {
	public static void save(Infraccion infraccion) {
		if (infraccion.getDni()==null || infraccion.getDni().isEmpty()) {
			throw new ValidateServiceException("El DNI es requerido");
		}
		if (infraccion.getDni().length()>8) {
			throw new ValidateServiceException("El DNI es muy largo");
		}
		if (infraccion.getFalta()==null) {
			throw new ValidateServiceException("La falta es requerida");
		}
		if (infraccion.getFalta().length()>3) {
			throw new ValidateServiceException("La falta es incorrecta");
		}
	}
}
