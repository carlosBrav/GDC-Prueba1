package pe.edu.sistemas.siscadweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.siscadweb.entities.DepAcademico;
import pe.edu.sistemas.siscadweb.repositories.DepAcademicoRepository;

@Service
@Transactional
public class DepAcademicoService {
	@Autowired
	private DepAcademicoRepository depAcademicoRepository;
	
	public List<DepAcademico> findAllDepartamentos(){
		return depAcademicoRepository.findAllDepartamentos();
	}
}
