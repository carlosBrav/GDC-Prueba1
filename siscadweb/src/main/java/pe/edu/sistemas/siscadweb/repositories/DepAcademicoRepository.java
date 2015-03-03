package pe.edu.sistemas.siscadweb.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.siscadweb.entities.DepAcademico;
import pe.edu.sistemas.siscadweb.mappers.DepAcademicoMapper;

@Repository
public class DepAcademicoRepository {
	@Autowired
	private DepAcademicoMapper depAcademicoMapper;
	
	public List<DepAcademico> findAllDepartamentos(){
		return depAcademicoMapper.findAllDepartamentos();
	}
}
