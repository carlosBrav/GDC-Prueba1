package pe.edu.sistemas.siscadweb.entities;

public class Periodo {
	private int id;
	private String nombre;
	private String fechaInicio;
	private String fechaFin;
	
	public Periodo(){
		
	}

	public Periodo(int id, String nombre, String fechaInicio, String fechaFin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/*@Override
	public String toString(){
		return nombre;
	}*/
	
}
