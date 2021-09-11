package com.modelo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servicio-cursos")
public interface CursoFeigntClient {
	
	@DeleteMapping("/eliminar-alumno/{id}")
	public void eliminarCursoAlumnoPorId(@PathVariable Long id);
}

