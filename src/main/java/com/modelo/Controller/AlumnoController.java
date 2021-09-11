package com.modelo.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.modelo.controller.CommonControllers;
import com.modelo.entity.Alumno;
import com.modelo.services.AlumnoService;

@RestController
public class AlumnoController extends CommonControllers<Alumno, AlumnoService>{
    
	@GetMapping("/alumnos-por-curso")
	public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> Ids){
		return ResponseEntity.ok(service.findAllById(Ids));
	}
	
	
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id)
	{
		Optional<Alumno> o = service.findById(id);
		if(o.isEmpty() || o.get().getFoto() == null ) {
			return ResponseEntity.notFound().build();
		}

		Resource imagen = new ByteArrayResource(o.get().getFoto());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
		
	}
		
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable long id)
	{
		if(result.hasErrors())
		{
			return this.validar(result);
		}
		
		
		Optional<Alumno> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnodb = o.get();
		
		alumnodb.setNombre(alumno.getNombre());
		alumnodb.setApellido(alumno.getApellido());
		alumnodb.setEmail(alumno.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnodb));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> filtrar( @PathVariable String term)
	{
		return ResponseEntity.ok(service.BuscarporNombreApellido(term));
	}

	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearconfoto(@Valid Alumno alumno, BindingResult result,
			@RequestParam MultipartFile archivo) throws IOException {
		if(!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return super.crear(alumno, result);
	}
	
	
	
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarconfoto(@Valid  Alumno alumno, BindingResult result, @PathVariable long id, 
			@RequestParam MultipartFile archivo) throws IOException{
		if(result.hasErrors())
		{
			return this.validar(result);
		}
		
		
		Optional<Alumno> o = service.findById(id);
		if(o.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Alumno alumnodb = o.get();
		
		alumnodb.setNombre(alumno.getNombre());
		alumnodb.setApellido(alumno.getApellido());
		alumnodb.setEmail(alumno.getEmail());
		
		if(!archivo.isEmpty()) {
			alumnodb.setFoto(archivo.getBytes());
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(alumnodb));
	}	

}
