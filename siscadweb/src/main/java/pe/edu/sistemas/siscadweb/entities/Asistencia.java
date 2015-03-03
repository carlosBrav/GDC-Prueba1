package pe.edu.sistemas.siscadweb.entities;

import java.sql.Time;
import java.sql.Date;

public class Asistencia {
	private int idAsistencia;
	private Time horaIngreso;
	private Time horaSalida;
	private String tema;
	private Date fecha;
	private String observacion;
	private String tipoMarca;
	private int idHorarioClase;
	private int idMotivo;
	
	public int getIdAsistencia() {
		return idAsistencia;
	}
	
	public void setIdAsistencia(int idAsistencia) {
		this.idAsistencia = idAsistencia;
	}
	
	public Time getHoraIngreso() {
		return horaIngreso;
	}
	
	public void setHoraIngreso(Time horaIngreso) {
		this.horaIngreso = horaIngreso;
	}
	
	public Time getHoraSalida() {
		return horaSalida;
	}
	
	public void setHoraSalida(Time horaSalida) {
		this.horaSalida = horaSalida;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getObservacion() {
		return observacion;
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public Integer getIdHorarioClase() {
		return idHorarioClase;
	}
	
	public void setIdHorarioClase(Integer idHorarioClase) {
		this.idHorarioClase = idHorarioClase;
	}
	
	public int getIdMotivo() {
		return idMotivo;
	}
	
	public void setIdMotivo(int idMotivo) {
		this.idMotivo = idMotivo;
	}
	
	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	public String getTipoMarca() {
		return tipoMarca;
	}

	public void setTipoMarca(String tipoMarca) {
		this.tipoMarca = tipoMarca;
	}
	
	public String toString(){
		return  idAsistencia + " " +
				horaIngreso + " " +
				horaSalida + " " +
				tema + " " +
				fecha + " " +
				observacion + " " +
				tipoMarca + " " +
				idHorarioClase + " " +
				idMotivo;
	}
}
