package pe.edu.sistemas.siscadweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.siscadweb.entities.Escuela;
import pe.edu.sistemas.siscadweb.repositories.EscuelaRepository;

@Service
@Transactional
public class EscuelaService {
	@Autowired
	private EscuelaRepository escuelaRepository;
	
	public List<Escuela> findAllEscuelas(){
		return escuelaRepository.findAllEscuelas();
	}
}
