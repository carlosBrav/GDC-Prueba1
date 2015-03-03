package pe.edu.sistemas.siscadweb.entities;

import java.sql.Date;

public class RegistroManual{
	private String codigo;
	private String nomDocente;
	private String nomCurso;
	private String tipo;
	private String entrada;
	private String salida;
	private String horaEntrada;
	private String horaSalida;
	private Date fecha;
	private String tipoMarca;
	private int idHorario;
	
	private int idAsistencia;
	private int idDocente;
	private int grupo;
	
	public RegistroManual(){
	}
	
	public RegistroManual(String codigo, String nomDocente, String nomCurso, String tipo,
						  String entrada, String salida, int idHorario,
						  String horaEntrada, String horaSalida, int grupo) {
		this.codigo = codigo;
		this.nomDocente = nomDocente;
		this.nomCurso = nomCurso;
		this.tipo = tipo;
		this.entrada = entrada;
		this.salida = salida;
		this.idHorario = idHorario;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.grupo = grupo;
	}
	
	public RegistroManual(String codigo, String nomDocente, String nomCurso, String tipo,
			  String entrada, String salida, int idHorario,
			  String horaEntrada, String horaSalida, int grupo,String tipoMarca,Date fecha) {
		this.codigo = codigo;
		this.nomDocente = nomDocente;
		this.nomCurso = nomCurso;
		this.tipo = tipo;
		this.entrada = entrada;
		this.salida = salida;
		this.idHorario = idHorario;
		this.horaEntrada = horaEntrada;
		this.horaSalida = horaSalida;
		this.grupo = grupo;
		this.tipoMarca=tipoMarca;
		this.fecha=fecha;
	}
	

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNomDocente() {
		return nomDocente;
	}

	public void setNomDocente(String nomDocente) {
		this.nomDocente = nomDocente;
	}

	public String getNomCurso() {
		return nomCurso;
	}

	public void setNomCurso(String nomCurso) {
		this.nomCurso = nomCurso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public String getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTipoMarca() {
		return tipoMarca;
	}

	public void setTipoMarca(String tipoMarca) {
		this.tipoMarca = tipoMarca;
	}

	public int getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public int getIdAsistencia() {
		return idAsistencia;
	}

	public void setIdAsistencia(int idAsistencia) {
		this.idAsistencia = idAsistencia;
	}

	public int getIdDocente() {
		return idDocente;
	}

	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	
	public String toString(){
		return  codigo + " " +
				nomDocente + " " +
				nomCurso + " " +
				tipo + " " +
				entrada + " " +
				salida + " " +
				horaEntrada + " " +
				horaSalida + " " +
				fecha + " " +
				tipoMarca + " " +
				idHorario + " " +
				idAsistencia + " " +
				idDocente + " " +
				grupo;
	}
	
}