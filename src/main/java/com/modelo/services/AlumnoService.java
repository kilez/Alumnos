package com.modelo.services;



import java.util.List;



import com.modelo.entity.Alumno;

public interface AlumnoService extends CommonService<Alumno> {
	
	 public List<Alumno> BuscarporNombreApellido (String term);
	 
	 public Iterable <Alumno> findAllById(Iterable <Long> ids);
	 
	 public void eliminarCursoAlumnoPorId(Long id);
	 
	 
}
