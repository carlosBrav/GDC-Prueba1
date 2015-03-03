package pe.edu.sistemas.siscadweb.entities;

public class DepAcademico {
	private int idDepartamento;
	private String nombre;
	
	public DepAcademico(){
		
	}

	public DepAcademico(int idDepartamento, String nombre) {
		super();
		this.idDepartamento = idDepartamento;
		this.nombre = nombre;
	}

	public int getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(int idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
