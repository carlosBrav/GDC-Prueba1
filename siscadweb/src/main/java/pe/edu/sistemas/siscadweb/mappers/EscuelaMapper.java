package pe.edu.sistemas.siscadweb.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import pe.edu.sistemas.siscadweb.entities.Escuela;

public interface EscuelaMapper {
	/* No olvidar colocar --> NOMBRE_COLUMNA_TABLA as NOMBRE_CAMPO_ENTITY */
	@Select("select " + "idescuela as idEscuela,"
			+ "escuela_nombre as nombre,"
			+ "facultad_idfacultad as idFacultad "
			+ "from ESCUELA")
	List<Escuela> findAllEscuelas();
}
