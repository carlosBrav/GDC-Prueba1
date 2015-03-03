package pe.edu.sistemas.siscadweb.mappers;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

import pe.edu.sistemas.siscadweb.entities.Asistencia;
import pe.edu.sistemas.siscadweb.entities.CursoPeriodo;

public interface AsistenciaMapper {
	@Select ("select idasistencia as idAsistencia,"
			+ "asistencia_hora_ingreso as horaIngreso,"
			+ "asistencia_hora_salida as horaSalida,"
			+ "asistencia_tema as tema,"
			+ "asistencia_fecha as fecha,"
			+ "asistencia_observacion as observacion,"
			+ "asistencia_tipo_marca as tipoMarca,"
			+ "horario_clase_idhorario_clase as idHorarioClase,"
			+ "motivo_idmotivo as idMotivo "
			+ "from ASISTENCIA")
	List<Asistencia> obtenerAsistencias();
	
	@Select ("select idasistencia as idAsistencia,"
			+ "asistencia_hora_ingreso as horaIngreso,"
			+ "asistencia_hora_salida as horaSalida,"
			+ "asistencia_tema as tema,"
			+ "asistencia_fecha as fecha,"
			+ "asistencia_observacion as observacion,"
			+ "asistencia_tipo_marca as tipoMarca,"
			+ "horario_clase_idhorario_clase as idHorarioClase,"
			+ "motivo_idmotivo as idMotivo "
			+ "from ASISTENCIA "
			+ "where asistencia_fecha = #{fecha}")
	List<Asistencia> obtenerAsistenciasxFecha(Date fecha);
	
	@Select ("select idasistencia as idAsistencia,"
			+ "asistencia_hora_ingreso as horaIngreso,"
			+ "asistencia_hora_salida as horaSalida,"
			+ "asistencia_tema as tema,"
			+ "asistencia_fecha as fecha,"
			+ "asistencia_observacion as observacion,"
			+ "asistencia_tipo_marca as tipoMarca,"
			+ "horario_clase_idhorario_clase as idHorarioClase,"
			+ "motivo_idmotivo as idMotivo "
			+ "from ASISTENCIA "
			+ "where asistencia_fecha = #{fecha} "
			+ "and #{hInicio} <= asistencia_hora_ingreso "
			+ "and asistencia_hora_salida <= #{hFin} ")
	List<Asistencia> obtenerAsistenciasxFechayHora(@Param("fecha") Date fecha, @Param("hInicio") String hInicio,
												   @Param("hFin") String hFin);
	
	@Select ("select idasistencia as idAsistencia,"
			+ "asistencia_hora_ingreso as horaIngreso,"
			+ "asistencia_hora_salida as horaSalida,"
			+ "asistencia_tema as tema,"
			+ "asistencia_fecha as fecha,"
			+ "asistencia_observacion as observacion,"
			+ "asistencia_tipo_marca as tipoMarca,"
			+ "horario_clase_idhorario_clase as idHorarioClase,"
			+ "motivo_idmotivo as idMotivo "
			+ "from ASISTENCIA "
			+ "where asistencia_fecha = #{fecha} "
			+ "and horario_clase_idhorario_clase = #{idHorario} limit 1")
	Asistencia obtenerAsistenciaxFechayIdHorario(@Param("fecha") Date fecha, @Param("idHorario") int idHorario);
	
	@Insert ("insert into ASISTENCIA "
			+ "(asistencia_hora_ingreso, asistencia_hora_salida, asistencia_tema, asistencia_fecha, "
			+ " asistencia_observacion, asistencia_tipo_marca, horario_clase_idhorario_clase, motivo_idmotivo) "
			+ "value "
			+ "(#{horaEntrada}, #{horaSalida}, #{tema}, #{fecha}, #{observacion}, "
			+ "#{tipoMarca}, #{idHorarioClase}, #{idMotivo})")
	 void registrarAsistenciaxMotivo(@Param("horaEntrada") Time horaEntrada, @Param("horaSalida") Time horaSalida,
			 						 @Param("tema") String tema, @Param("fecha") Date fecha,
			 						 @Param("observacion") String observacion, @Param("tipoMarca") String tipoMarca,
			 						 @Param("idHorarioClase") int idHorarioClase, @Param("idMotivo") int idMotivo);
	
	@Insert ("insert into ASISTENCIA "
			+ "(asistencia_hora_ingreso, asistencia_hora_salida, asistencia_tema, asistencia_fecha, "
			+ " asistencia_observacion, asistencia_tipo_marca, horario_clase_idhorario_clase) "
			+ "value "
			+ "(#{horaEntrada}, #{horaSalida}, #{tema}, #{fecha}, #{observacion}, "
			+ "#{tipoMarca}, #{idHorarioClase})")
	void registrarAsistencia(@Param("horaEntrada") Time horaEntrada, @Param("horaSalida") Time horaSalida,
			 				 @Param("tema") String tema, @Param("fecha") Date fecha,
			 				 @Param("observacion") String observacion, @Param("tipoMarca") String tipoMarca,
			 				 @Param("idHorarioClase") int idHorarioClase);

	
	@Select ("select idasistencia as idAsistencia,"
			+ "asistencia_hora_ingreso as horaIngreso,"
			+ "asistencia_hora_salida as horaSalida,"
			+ "asistencia_tema as tema,"
			+ "asistencia_fecha as fecha,"
			+ "asistencia_observacion as observacion,"
			+ "asistencia_tipo_marca as tipoMarca,"
			+ "horario_clase_idhorario_clase as idHorarioClase,"
			+ "motivo_idmotivo as idMotivo "
			+ "from ASISTENCIA "
			+ "where asistencia_fecha >= #{fechaInicial} "
			+ "and asistencia_fecha <= #{fechaFinal} "
			+ "and horario_clase_idhorario_clase = #{idHorario} limit 1")
	Asistencia obtenerAsistenciasxFechaInxFechaFinyIdHorario(@Param("fechaInicial") Date fechaInicial,@Param("fechaFinal") Date fechaFinal,
																@Param("idHorario") int idHorario);


	@Select ("select idasistencia as idAsistencia,"
			+ "a.asistencia_hora_ingreso as horaIngreso,"
			+ "a.asistencia_hora_salida as horaSalida,"
			+ "a.asistencia_tema as tema,"
			+ "a.asistencia_fecha as fecha,"
			+ "a.asistencia_observacion as observacion,"
			+ "a.asistencia_tipo_marca as tipoMarca,"
			+ "a.horario_clase_idhorario_clase as idHorarioClase,"
			+ "a.motivo_idmotivo as idMotivo "
			+ "from ASISTENCIA a , HORARIO_CLASE h "
			+ "where h.idhorario_clase=a.horario_clase_idhorario_clase "
			+ "AND h.docente_iddocente= #{idDocente} "
			+ "AND h.horario_clase_periodo = #{periodo} "
			+ "AND a.asistencia_fecha= #{fecha}")
	List<Asistencia> obtenerAsistenciasxFechaxDocentexPeriodo(@Param("fecha") Date fecha,@Param("idDocente")int idDocente,@Param("periodo") String periodo);


	@Select ("select idasistencia as idAsistencia,"
			+ "a.asistencia_hora_ingreso as horaIngreso,"
			+ "a.asistencia_hora_salida as horaSalida,"
			+ "a.asistencia_tema as tema,"
			+ "a.asistencia_fecha as fecha,"
			+ "a.asistencia_observacion as observacion,"
			+ "a.asistencia_tipo_marca as tipoMarca,"
			+ "a.horario_clase_idhorario_clase as idHorarioClase,"
			+ "a.motivo_idmotivo as idMotivo "
			+ "from ASISTENCIA a , HORARIO_CLASE h "
			+ "where h.idhorario_clase=a.horario_clase_idhorario_clase "
			+ "AND h.docente_iddocente= #{idDocente} "
			+ "AND a.asistencia_fecha= #{fecha}")
	List<Asistencia> obtenerAsistenciasxFechaxDocente(@Param("idDocente")int idDocente,@Param("fecha") java.util.Date date);
}

