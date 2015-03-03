package pe.edu.sistemas.siscadweb.entities;

public class DetalleAsistencia {
	private String fecha;
	private String tipo;
	private String inicio;
	private String salida;
	private String tipoMarca;
	private boolean detalle;
	private String recuperacion;
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipoMarca() {
		return tipoMarca;
	}
	
	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}
	
	public void setTipoMarca(String tipoMarca) {
		this.tipoMarca = tipoMarca;
	}
	
	public boolean isDetalle() {
		return detalle;
	}
	
	public void setDetalle(boolean detalle) {
		this.detalle = detalle;
	}
	
	public String getRecuperacion() {
		return recuperacion;
	}
	
	public void setRecuperacion(String recuperacion) {
		this.recuperacion = recuperacion;
	}
}