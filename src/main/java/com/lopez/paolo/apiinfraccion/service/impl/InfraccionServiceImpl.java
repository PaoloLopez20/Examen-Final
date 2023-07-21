package com.lopez.paolo.apiinfraccion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lopez.paolo.apiinfraccion.entity.Infraccion;
import com.lopez.paolo.apiinfraccion.exceptions.GeneralServiceException;
import com.lopez.paolo.apiinfraccion.exceptions.NoDataFoundException;
import com.lopez.paolo.apiinfraccion.exceptions.ValidateServiceException;
import com.lopez.paolo.apiinfraccion.repository.InfraccionRepository;
import com.lopez.paolo.apiinfraccion.service.InfraccionService;
import com.lopez.paolo.apiinfraccion.validator.InfraccionValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InfraccionServiceImpl implements InfraccionService {
	
	@Autowired
	private InfraccionRepository repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Infraccion> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		} catch (NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Infraccion> findByDni(String dni, Pageable page) {
		try {
			return repository.findByDniContaining(dni, page);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Infraccion findById(int id) {
		try {
			Infraccion registro= repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID."));
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Infraccion save(Infraccion infraccion) {
		try {
			InfraccionValidator.save(infraccion);
			if (repository.findByDni(infraccion.getDni())!=null) {
				throw new ValidateServiceException("Ya existe un registro con el DNI "+infraccion.getDni());
			}
			infraccion.setActivo(true);
			Infraccion registro= repository.save(infraccion);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	public Infraccion update(Infraccion infraccion) {
		try {
			InfraccionValidator.save(infraccion);
			Infraccion registro= repository.findById(infraccion.getId()).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID."));
			Infraccion registroD = repository.findByDni(infraccion.getDni());
			if (registroD!=null && registroD.getId()!= infraccion.getId()) {
				throw new ValidateServiceException("Ya existe un registro con el DNI "+infraccion.getDni());
			}
			registro.setDni(infraccion.getDni());
			registro.setFalta(infraccion.getFalta());
			repository.save(registro);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	public void delete(int id) {
		try {
			Infraccion registro= repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe un registro con ese ID."));
			repository.delete(registro);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}
