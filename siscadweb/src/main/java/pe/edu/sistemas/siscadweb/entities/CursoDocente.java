package pe.edu.sistemas.siscadweb.entities;

import java.util.LinkedList;

public class CursoDocente {
	public class GrupoCurso{
		private int idGrupo;
		private int numGrupo;
		
		public GrupoCurso(int idGrupo, int numGrupo){
			this.idGrupo = idGrupo;
			this.numGrupo = numGrupo;
		}
		
		public int getIdGrupo() {
			return idGrupo;
		}
		
		public void setIdGrupo(int idGrupo) {
			this.idGrupo = idGrupo;
		}
		
		public int getNumGrupo() {
			return numGrupo;
		}
		
		public void setNumGrupo(int numGrupo) {
			this.numGrupo = numGrupo;
		}
	}
	
	private int idCurso;
	private String codigoCurso;
	private String nombre;
	private LinkedList<GrupoCurso> grupos;
	private String escuela;

	private int grupoSeleccionado;
	private int numGrupoSeleccionado;
	
	public CursoDocente(){
		grupos = new LinkedList<GrupoCurso>();
	}
	
	public void addGrupo(int idGrupo, int numGrupo){
		grupos.add(new GrupoCurso(idGrupo, numGrupo));
	}
	
	public int getIdCurso() {
		return idCurso;
	}
	
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	
	public String getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public LinkedList<GrupoCurso> getGrupos() {
		return grupos;
	}
	
	public void setGrupos(LinkedList<GrupoCurso> grupos) {
		this.grupos = grupos;
	}
	
	public String getEscuela() {
		return escuela;
	}
	
	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}
	
	public int getGrupoSeleccionado() {
		return grupoSeleccionado;
	}

	public void setGrupoSeleccionado(int grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}

	public int getNumGrupoSeleccionado() {
		return numGrupoSeleccionado;
	}

	public void setNumGrupoSeleccionado(int numGrupoSeleccionado) {
		this.numGrupoSeleccionado = numGrupoSeleccionado;
	}
}
