package pe.edu.sistemas.siscadweb.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import pe.edu.sistemas.siscadweb.entities.DepAcademico;
import pe.edu.sistemas.siscadweb.entities.Periodo;

public interface DepAcademicoMapper {
	/* No olvidar colocar --> NOMBRE_COLUMNA_TABLA as NOMBRE_CAMPO_ENTITY */
	@Select("select " + "iddepartamento_academico as idDepartamento,"
			+ "departamento_academico_nombre as nombre "
			+ "from DEPARTAMENTO_ACADEMICO")
	List<DepAcademico> findAllDepartamentos();
}
