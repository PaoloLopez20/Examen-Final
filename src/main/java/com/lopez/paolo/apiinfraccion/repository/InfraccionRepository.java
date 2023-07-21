package com.lopez.paolo.apiinfraccion.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lopez.paolo.apiinfraccion.entity.Infraccion;

@Repository
public interface InfraccionRepository extends JpaRepository<Infraccion, Integer> {
	List<Infraccion> findByDniContaining(String dni, Pageable page);
	Infraccion findByDni(String dni);
}
