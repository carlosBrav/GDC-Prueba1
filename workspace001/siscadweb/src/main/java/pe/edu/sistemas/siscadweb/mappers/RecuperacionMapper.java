package pe.edu.sistemas.siscadweb.mappers;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import pe.edu.sistemas.siscadweb.entities.Recuperacion;

public interface RecuperacionMapper {
	/* No olvidar colocar --> NOMBRE_COLUMNA_TABLA as NOMBRE_CAMPO_ENTITY */
	@Select("select " 
			+"idrecuperacion as idRecuperacion, "
			+"horario_clase_idhorario_clase as idHorarioClase, "
			+"motivo_idmotivo as idmotivo, "
			+"recuperacion_fecha_recuperacion as fechaRecuperacion, "
			+"recuperacion_fecha_ausente as fechaAusente, "			
			+"recuperacion_aula as aula, "
			+"recuperacion_hora_inicio as horaInicio, "
			+"recuperacion_hora_fin as horaFin, "
			+"recuperacion_observacion as observacion, "
			+"recuperacion_hora_inicio_real as horaInicioReal, "
			+"recuperacion_hora_fin_real as horaFinReal, "
			+"estado as estado "
			+"from RECUPERACION "
			+"where horario_clase_idhorario_clase=#{idHorario} "
			+"AND recuperacion_fecha_ausente=#{fecha} ")
	List<Recuperacion> obtenerRecuperacionesxFechaxHorario(@Param("idHorario")int idHorario,@Param("fecha")Date fecha); 
	
	
	@Insert ("insert into RECUPERACION "
			+ "(horario_clase_idHorario_clase, recuperacion_fecha_recuperacion, recuperacion_fecha_ausente, recuperacion_aula, "
			+ " recuperacion_hora_inicio, recuperacion_hora_fin, recuperacion_observacion,"
			+ "motivo_idmotivo , estado ) "
			+ "value "
			+ "(#{idHorarioClase}, #{fechaRec}, #{fechaAusente}, #{aula},"
			+ "#{horaInicio}, #{horaFin},#{obs},"
			+ "#{idMotivo}, #{estado})")
	void insertarRecuperacion(@Param("idHorarioClase") int idHorarioClase,
			@Param("idMotivo")int idMotivo,@Param("fechaRec") Date fechaRec,
			 @Param("fechaAusente") Date fechaAusente,@Param("aula") String aula,
			 @Param("horaInicio") Time horaInicio, @Param("horaFin") Time horaFin,
			 @Param("obs") String obs,@Param("estado") int estado);
	
}
