package pe.edu.sistemas.siscadweb.repositories;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.siscadweb.entities.Asistencia;
import pe.edu.sistemas.siscadweb.mappers.AsistenciaMapper;

@Repository
public class AsistenciaRepository {
	@Autowired
	private AsistenciaMapper asistenciaMapper;
	
	public List<Asistencia> obtenerAsistencias(){
		return asistenciaMapper.obtenerAsistencias();
	}
	
	public List<Asistencia> obtenerAsistenciasxFecha(Date fecha){
		return asistenciaMapper.obtenerAsistenciasxFecha(fecha);
	}
	
	public List<Asistencia> obtenerAsistenciasxFechayHora(Date fecha, String hInicio, String hFin){
		return asistenciaMapper.obtenerAsistenciasxFechayHora(fecha, hInicio, hFin);
	}
	
	public Asistencia obtenerAsistenciaxFechayIdHorario(Date fecha, int idHorario){
		return asistenciaMapper.obtenerAsistenciaxFechayIdHorario(fecha, idHorario);
	}

	public void registrarAsistenciaxMotivo(Time horaEntrada, Time horaSalida, String tema, Date fecha, String observacion,
										   String tipoMarca, int idHorarioClase, int idMotivo){
		asistenciaMapper.registrarAsistenciaxMotivo(horaEntrada, horaSalida, tema, fecha, observacion, tipoMarca,
													idHorarioClase, idMotivo);
	}
	
	public void registrarAsistencia(Time horaEntrada, Time horaSalida, String tema, Date fecha, String observacion,
									String tipoMarca, int idHorarioClase){
		asistenciaMapper.registrarAsistencia(horaEntrada, horaSalida, tema, fecha, observacion, tipoMarca,
											 idHorarioClase);
	}

	
	public Asistencia obtenerAsistenciasxFechaInxFechaFinyIdHorario(Date fechaInicial,Date fechaFinal, int idHorario){
		return asistenciaMapper.obtenerAsistenciasxFechaInxFechaFinyIdHorario(fechaInicial, fechaFinal, idHorario);
	}

	
	public List<Asistencia> obtenerAsistenciasxFechaxDocentexPeriodo(Date date,int idDocente,String periodo){
		return asistenciaMapper.obtenerAsistenciasxFechaxDocentexPeriodo(date,idDocente, periodo);
	}
	
	public List<Asistencia> obtenerAsistenciasxFechaxDocente(int idDocente, java.util.Date date){
		return asistenciaMapper.obtenerAsistenciasxFechaxDocente(idDocente, date);
	}

}

