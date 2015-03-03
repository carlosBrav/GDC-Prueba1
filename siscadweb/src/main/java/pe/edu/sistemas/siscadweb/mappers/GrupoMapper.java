package pe.edu.sistemas.siscadweb.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import pe.edu.sistemas.siscadweb.entities.Grupo;

public interface GrupoMapper {

	@Select("select g.idgrupo as idgrupo, "
			+ "g.grupo_numero as grupoNumero "
			+ "from grupo g "
			+ "where g.CURSO_IDCURSO = #{idCursoPeriodo} ")
	List<Grupo> obtenerGruposxCodCursoPeriodo(@Param("idCursoPeriodo") int idCursoPeriodo);
	
	@Select("select hc.idhorario_clase as idHorarioClase,"
			+ "hc.grupo_idgrupo,"
			+ "g.idgrupo as idGrupo,"
			+ "g.grupo_numero as grupoNumero"
			+ "from HORARIO_CLASE hc, GRUPO g"
			+ "where hc.idhorario_clase=#{idHorarioClase}"
			+ "and g.idgrupo=hc.grupo_idgrupo")
	public Grupo ObtenergrupoxIdHorarioClase(@Param("idHorarioClase") int idHorarioClase);

	@Insert ("insert into GRUPO "
			+ "(IDGRUPO, GRUPO_NUMERO, CURSO_IDCURSO ) "
			+ "VALUES "
			+ "( NULL, #{nroGrupo}, #{idCurso} )")
	void crearGrupo(@Param("idCurso") int idCurso, @Param("nroGrupo") int nroGrupo);
	
}
