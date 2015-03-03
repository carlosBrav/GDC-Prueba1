package pe.edu.sistemas.siscadweb.entities;


public class RegistroRecuperacion{

	private static final long serialVersionUID = 1L;
	//private DateTimeFormat df = DateTimeFormat.getFormat("MM/dd/y");
	private String code;
	private String name;
	private String curso;
	private String tipo;
	private int idHorario;
	private int minutosDeuda;
	private String tipoDeuda;
	private String fecha;
	private String estado;
	private String fechaProgramada;
	private String aula;
	private String horaInicio;
	private String horaFin;
	private String fechaLimite;
	
	public RegistroRecuperacion(){
		
	}
	
	public RegistroRecuperacion(String code, String name, String curso, String tipo, int idHorario, int minutosDeuda, String tipoDeuda, String fecha) {
		this.code = code;
		this.name = name;
		this.curso = curso;
		this.tipo = tipo;
		this.idHorario = idHorario;
		this.minutosDeuda = minutosDeuda;
		this.tipoDeuda = tipoDeuda;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getIdHorario() {
		return idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public int getMinutosDeuda() {
		return minutosDeuda;
	}

	public void setMinutosDeuda(int minutosDeuda) {
		this.minutosDeuda = minutosDeuda;
	}

	public String getTipoDeuda() {
		return tipoDeuda;
	}

	public void setTipoDeuda(String tipoDeuda) {
		this.tipoDeuda = tipoDeuda;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaProgramada() {
		return fechaProgramada;
	}

	public void setFechaProgramada(String fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	
	public String getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
}
