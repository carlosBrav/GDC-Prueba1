package pe.edu.sistemas.siscadweb.mappers;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pe.edu.sistemas.siscadweb.entities.Periodo;
import pe.edu.sistemas.siscadweb.entities.RangoMes;
import pe.edu.sistemas.siscadweb.entities.RangoSemana;

public interface PeriodoMapper {
	/* No olvidar colocar --> NOMBRE_COLUMNA_TABLA as NOMBRE_CAMPO_ENTITY */
	@Select("select " + "idperiodo as id,"
			+ "periodo_nombre as nombre,"
			+ "periodo_fecha_inicio as fechaInicio,"
			+ "periodo_fecha_fin as fechaFin "
			+ "from PERIODO")
	List<Periodo> findAllPeriodos(); // Trae todo el listado de personas

	
	//SELECT MONTH(p.periodo_fecha_inicio), MONTH(p.periodo_fecha_fin) FROM PERIODO p WHERE p.periodo_nombre = '" + ciclo +"'"
	@Select("select " + "MONTH(p.periodo_fecha_inicio) as mesInicio,"
	        + "MONTH(p.periodo_fecha_fin) as mesFin "
	        + "FROM PERIODO p "
	        + "WHERE p.periodo_nombre =  #{ciclo}")
	RangoMes obtenerRangoMes(String ciclo);
	
	@Select("select " + "ADDDATE(DATE( #{fecha}),12-WEEKDAY(#{fecha})) as r1,"
				+"ADDDATE(DATE(#{fecha}),14-WEEKDAY(#{fecha})) as r2,"
			    +"ADDDATE(DATE(#{fecha}),19-WEEKDAY(#{fecha})) as r3,"
			    +"ADDDATE(DATE(#{fecha}),21-WEEKDAY(#{fecha})) as r4,"
			    +"ADDDATE(DATE(#{fecha}),26-WEEKDAY(#{fecha})) as r5,"
			    +"ADDDATE(DATE(#{fecha}),28-WEEKDAY(#{fecha})) as r6,"
    			+"DATE_ADD(DATE_SUB(DATE(#{fecha}), INTERVAL 1 DAY), INTERVAL 1 MONTH) as r7")
	RangoSemana obtenerRangoSemanas(String fecha);
	
	@Select("select idperiodo as id,"
			+ "periodo_nombre as nombre,"
			+ "periodo_fecha_inicio as fechaInicio,"
	        + "periodo_fecha_fin as fechaFin "
	        + "from PERIODO "
	        + "where periodo_fecha_inicio < #{fecha} "
	        + "and #{fecha} < periodo_fecha_fin ")
	Periodo obtenerPeriodoxFecha(Date fecha);
	
	@Select("select idperiodo as id,"
			+ "periodo_nombre as nombre,"
			+ "periodo_fecha_inicio as fechaInicio,"
	        + "periodo_fecha_fin as fechaFin "
	        + "from PERIODO "
	        + "order by idperiodo desc limit 1")
	Periodo obtenerUltimoPeriodo();
}
