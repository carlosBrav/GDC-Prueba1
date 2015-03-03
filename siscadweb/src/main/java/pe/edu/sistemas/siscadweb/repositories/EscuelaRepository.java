package pe.edu.sistemas.siscadweb.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.siscadweb.entities.Escuela;
import pe.edu.sistemas.siscadweb.entities.Periodo;
import pe.edu.sistemas.siscadweb.mappers.EscuelaMapper;

@Repository
public class EscuelaRepository {
	@Autowired
	private EscuelaMapper escuelaMapper;
	
	public List<Escuela> findAllEscuelas(){
		return escuelaMapper.findAllEscuelas();
	}
}
