package pe.edu.sistemas.siscadweb.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import pe.edu.sistemas.siscadweb.entities.CursoPeriodo;

public interface CursoPeriodoMapper {
	@Select("select cp.idcurso_periodo as idCurso,"
			+ "cp.curso_periodo_nombre as nombre,"
			+ "cp.periodo_idperiodo as periodo,"
			+ "g.idgrupo as idGrupo,"
			+ "g.grupo_numero as numGrupo "
			+ "from CURSO_PERIODO cp, GRUPO g "
			+ "where cp.idcurso_periodo = g.curso_idcurso")
	List<CursoPeriodo> obtenerCursos();
	
	@Select("select cp.idcurso_periodo as idCurso,"
			+ "cp.curso_periodo_nombre as nombre,"
			+ "cp.periodo_idperiodo as periodo,"
			+ "g.idgrupo as idGrupo,"
			+ "g.grupo_numero as numGrupo "
			+ "from CURSO_PERIODO cp, GRUPO g "
			+ "where cp.periodo_idperiodo = #{idPeriodo} "
			+ "and cp.idcurso_periodo = g.curso_idcurso ")
	List<CursoPeriodo> obtenerCursosxPeriodo(int idPeriodo);
	
	@Select("select cp.idcurso_periodo as idCurso,"
			+ "cp.curso_periodo_nombre as nombre,"
			+ "cp.periodo_idperiodo as periodo,"
			+ "g.idgrupo as idGrupo,"
			+ "g.grupo_numero as numGrupo "
			+ "from CURSO_PERIODO cp, GRUPO g "
			+ "where cp.idcurso_periodo = #{idCurso} "
			+ "and cp.idcurso_periodo = g.curso_idcurso ")
	List<CursoPeriodo> obtenerCursosxIdCurso(int idCurso);
	
	@Select("select cp.idcurso_periodo as idCurso,"
			+ "cp.curso_periodo_nombre as nombre,"
			+ "cp.periodo_idperiodo as periodo,"
			+ "g.idgrupo as idGrupo,"
			+ "g.grupo_numero as numGrupo "
			+ "from CURSO_PERIODO cp, GRUPO g "
			+ "where g.idgrupo = #{idGrupo} "
			+ "and cp.idcurso_periodo = g.curso_idcurso ")
	CursoPeriodo obtenerCursosxIdGrupo(int idGrupo);
	
	@Select("select cp.idcurso_periodo as idCurso,"
			+ "cp.curso_periodo_nombre as nombre,"
			+ "cp.periodo_idperiodo as periodo "
			+ "from CURSO_PERIODO cp, CURSO_CONJUNTO cc, CURSO_BASE cb "
			+ "where cp.periodo_idperiodo = #{0} "
			+ "and cp.curso_periodo_cursoc_codcomun = cc.cursoc_codcomun "
			+ "and cc.cursoc_idcurso_general = cb.idcurso_general "
			+ "and cb.plan_id_plan = #{2} "
			+ "and cb.cursob_ciclo = #{1} ")
	List<CursoPeriodo> obtenerCursosxPeriodoxCicloxPlan(int idPeriodo, int ciclo, int idPlan);
	
	@Select("select distinct cp.idcurso_periodo as idCurso,"
			+ "cp.curso_periodo_nombre as nombre,"
			+ "cp.periodo_idperiodo as periodo,"
			+ "g.idgrupo as idGrupo,"
			+ "g.grupo_numero as numGrupo "
			+ "from HORARIO_CLASE hc join DOCENTE d join GRUPO g join CURSO_PERIODO cp "
			+ "where d.iddocente = #{idDocente} "
			+ "and hc.horario_clase_periodo = #{ciclo} "
			+ "and cp.idcurso_periodo = g.curso_idcurso "
			+ "and d.iddocente = hc.docente_iddocente "
			+ "and hc.grupo_idgrupo = g.idgrupo "
			+ "and cp.idcurso_periodo = g.curso_idcurso "
			+ "order by cp.idcurso_periodo ")
	List<CursoPeriodo> obtenerCursosxDocentexCiclo(@Param("idDocente") int idDocente, @Param("ciclo") String ciclo);
	
	@Select("select cp.idcurso_periodo as idCurso,"
			+ "cp.curso_periodo_nombre as nombre,"
			+ "cp.periodo_idperiodo as periodo,"
			+ "cb.idcurso_general as idCursoGeneral,"
			+ "e.idescuela as idEscuela,"
			+ "cb.cursob_codigo as codigo,"
			+ "e.escuela_nombre as escuela "
			+ "from CURSO_PERIODO cp join CURSO_CONJUNTO cc join CURSO_BASE cb "
			+ "join PLAN p join ESCUELA e "
			+ "where cp.idcurso_periodo = #{idCurso} "
			+ "and cc.cursoc_codcomun = cp.curso_periodo_cursoc_codcomun "
			+ "and cb.idcurso_general = cc.cursoc_idcurso_general "
			+ "and p.idplan = cb.plan_id_plan "
			+ "and e.idescuela = p.escuela_idescuela "
			+ "order by p.idplan desc limit 1 ")
	CursoPeriodo obtenerCursoCodigoEscuelaxIdCurso(int idCurso);
	
	
	@Select("select cp.idcurso_periodo as idCurso,"
			+ "cp.curso_periodo_nombre as nombre,"
			+ "cp.periodo_idperiodo as periodo,"
			+ "hc.grupo_idgrupo as idGrupo,"
			+ "hc.idhorario_clase as idHorarioClase,"
			+ "g.curso_idcurso, g.idgrupo as idGrupo"
			+ "from HORARIO_CLASE hc,GRUPO g,CURSO_PERIODO cp"
			+ "where hc.idhorario_clase = #{idHorarioClase}"
			+ "and g.idgrupo = hc.grupo_idgrupo"
			+ "and cp.idcurso_periodo = g.curso_idcurso limit 1")
		
	CursoPeriodo obtenerCursoxHorarioClase(@Param("idHorarioClase") int idHorarioClase);
	
	@Insert ("insert into CURSO_PERIODO "
			+ "(IDCURSO_PERIODO, CURSO_PERIODO_NOMBRE, PERIODO_IDPERIODO, CURSO_PERIODO_CURSOC_CODCOMUN ) "
			+ "VALUES "
			+ "( NULL, #{nombreCurso}, #{idPeriodo}, #{codComun} )")
	void registrarCursoPeriodo(@Param("nombreCurso") String nombreCurso, @Param("codComun") int codComun, 
							   @Param("idPeriodo") int idPeriodo);
}