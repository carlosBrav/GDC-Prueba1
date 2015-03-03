package pe.edu.sistemas.siscadweb.entities;

import java.util.ArrayList;
import java.util.List;

public class Ciclo {
	private String cicloNombre;
	private List<CursoPeriodo> cursos = new ArrayList<CursoPeriodo>();

	public Ciclo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ciclo(String cicloNombre, List<CursoPeriodo> cursos) {
		super();
		this.cicloNombre = cicloNombre;
		this.cursos = cursos;
	}

	public String getCicloNombre() {
		return cicloNombre;
	}

	public void setCicloNombre(String cicloNombre) {
		this.cicloNombre = cicloNombre;
	}

	public List<CursoPeriodo> getCursos() {
		return cursos;
	}

	public void setCursos(List<CursoPeriodo> cursos) {
		this.cursos = cursos;
	}
}
