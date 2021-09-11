package com.modelo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.modelo.client.CursoFeigntClient;
import com.modelo.entity.Alumno;
import com.modelo.repository.AlumnoRepository;

@Service

public class AlumnoServiceimp extends CommonServiceimp<Alumno, AlumnoRepository> implements AlumnoService {

	@Autowired
	private CursoFeigntClient client;
	
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> BuscarporNombreApellido(String term) {
		
		return repository.BuscarporNombreApellido(term);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return repository.findAllById(ids);
	}

	@Override
	public void eliminarCursoAlumnoPorId(Long id) {
		client.eliminarCursoAlumnoPorId(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public void delateById(Long Id) {
		// TODO Auto-generated method stub
		super.delateById(Id);
		this.client.eliminarCursoAlumnoPorId(Id);
		//this.eliminarCursoAlumnoPorId(Id);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll() {
	
		return repository.findAllByOrderByIdAsc();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Alumno> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return repository.findAllByOrderByIdAsc(pageable);
	}


	
	
	

}
