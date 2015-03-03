package pe.edu.sistemas.siscadweb.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import pe.edu.sistemas.siscadweb.entities.CursoBase;

public interface CursoBaseMapper {

	@Select("select cb.cursob_codigo as cursob_codigo, "
			+ "cb.cursob_nombre as cursob_nombre, "
			+ "cb.plan_id_plan as plan_id_plan, "
			+ "cb.cursob_ciclo as cursob_ciclo, "
			+ "cb.cursob_creditos as cursob_creditos "
			+ "FROM CURSO_CONJUNTO cc, CURSO_BASE cb "
			+ "WHERE cc.CURSOC_IDCURSO_GENERAL = cb.IDCURSO_GENERAL "
			+ "AND cb.PLAN_ID_PLAN = #{idPlan} "
			+ "AND cc.cursoc_codcomun NOT IN ( "
			+ "SELECT curso_periodo_cursoc_codcomun "
			+ "FROM CURSO_PERIODO "
			+ "WHERE periodo_idperiodo = #{idPeriodo} ) ")
	List<CursoBase> obtenerCursosNOAperturadosxPeriodoxPlan(@Param("idPeriodo") int idPeriodo, @Param("idPlan") int idPlan);
	
	@Select("select cb.cursob_codigo as cursob_codigo, "
			+ "cb.cursob_nombre as cursob_nombre, "
			+ "cb.plan_id_plan as plan_id_plan, "
			+ "cb.cursob_ciclo as cursob_ciclo, "
			+ "cb.cursob_creditos as cursob_creditos "
			+ "FROM CURSO_CONJUNTO cc, CURSO_BASE cb "
			+ "WHERE cc.CURSOC_IDCURSO_GENERAL = cb.IDCURSO_GENERAL "
			+ "AND cb.PLAN_ID_PLAN = #{idPlan} "
			+ "AND cc.cursoc_codcomun IN ( "
			+ "SELECT curso_periodo_cursoc_codcomun "
			+ "FROM CURSO_PERIODO "
			+ "WHERE periodo_idperiodo = #{idPeriodo} ) ")
	List<CursoBase> obtenerCursosAperturadosxPeriodoxPlan(@Param("idPeriodo") int idPeriodo, @Param("idPlan") int idPlan);
	
	@Select("select cb.cursob_codigo as cursob_codigo, "
			+ "cb.cursob_nombre as cursob_nombre, "
			+ "cb.plan_id_plan as plan_id_plan, "
			+ "cb.cursob_ciclo as cursob_ciclo, "
			+ "cb.cursob_creditos as cursob_creditos "
			+ "FROM CURSO_CONJUNTO cc, CURSO_BASE cb "
			+ "WHERE cc.CURSOC_IDCURSO_GENERAL = cb.IDCURSO_GENERAL "
			+ "AND cb.PLAN_ID_PLAN = #{idPlan} ")
	List<CursoBase> obtenerCursosxPlan(@Param("idPlan") int idPlan);
	
	@Select("select cb.cursob_codigo as cursob_codigo, "
			+ "cb.cursob_nombre as cursob_nombre, "
			+ "cb.plan_id_plan as plan_id_plan, "
			+ "cb.cursob_ciclo as cursob_ciclo, "
			+ "cb.cursob_creditos as cursob_creditos, "
			+ "cc.CURSOC_CODCOMUN as codComun "
			+ "FROM CURSO_CONJUNTO cc, CURSO_BASE cb "
			+ "WHERE cc.CURSOC_IDCURSO_GENERAL = cb.IDCURSO_GENERAL "
			+ "AND cb.PLAN_ID_PLAN = #{idPlan} "
			+ "AND cb.CURSOB_NOMBRE = #{nombre}")
	CursoBase obtenerCursoxPlanxNombre(@Param("idPlan") int idPlan, @Param("nombre") String nombre);
}
