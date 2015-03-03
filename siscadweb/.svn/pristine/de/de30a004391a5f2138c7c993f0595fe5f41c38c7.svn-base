package pe.edu.sistemas.siscadweb.controller;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.siscadweb.entities.CursoDocente;
import pe.edu.sistemas.siscadweb.entities.DetalleAsistencia;
import pe.edu.sistemas.siscadweb.entities.Periodo;
import pe.edu.sistemas.siscadweb.repositories.CursoPeriodoRepository;
import pe.edu.sistemas.siscadweb.repositories.PeriodoRepository;
import pe.edu.sistemas.siscadweb.services.ConsultAsistService;
import pe.edu.sistemas.siscadweb.util.MenuBean;

@ManagedBean
@SessionScoped
@Component
public class ConsultAsistController {
	private List<DetalleAsistencia> asistencias;
	private List<CursoDocente> cursosDocente;
	private Periodo periodoActual;
	private Date inicio;
	private Date salida;
	private CursoDocente cursoSeleccionado;
	
	@Autowired
	private ConsultAsistService consultAsistService;
	@Autowired
	private CursoPeriodoRepository cursoPeriodoRepository;
	@Autowired
	private PeriodoRepository periodoRepository;
	
	public ConsultAsistController(){
	}
	
	@PostConstruct
	public void init(){
		try{
			int idDocente;
			
			periodoActual = periodoRepository.obtenerUltimoPeriodo();
			//idDocente = metodoParaObtenerAlDocenteActual().getIdDocente();
			idDocente = 921;
			cursosDocente = consultAsistService.obtenerCursos(idDocente, periodoActual.getNombre());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void verCurso(MenuBean mb, CursoDocente curso){
		try{
			inicio = Date.valueOf(periodoActual.getFechaInicio());
			salida = Date.valueOf(periodoActual.getFechaFin());
			cursoSeleccionado = curso;
			cursoSeleccionado.setNumGrupoSeleccionado(
					cursoPeriodoRepository.obtenerCursosxIdGrupo(curso.getGrupoSeleccionado()).getNumGrupo()
			);
			asistencias = consultAsistService.obtenerDetalleAsistencias(curso.getGrupoSeleccionado(),
															 periodoActual.getNombre(), inicio, salida);
			mb.setSelectedMenu(mb.getOPT2());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public CursoDocente getCursoSeleccionado() {
		return cursoSeleccionado;
	}

	public void setCursoSeleccionado(CursoDocente cursoSeleccionado) {
		this.cursoSeleccionado = cursoSeleccionado;
	}
	
	public Periodo getPeriodoActual() {
		return periodoActual;
	}

	public void setPeriodoActual(Periodo periodoActual) {
		this.periodoActual = periodoActual;
	}

	public List<CursoDocente> getCursosDocente() {
		return cursosDocente;
	}

	public void setCursosDocente(List<CursoDocente> cursosDocente) {
		this.cursosDocente = cursosDocente;
	}
	
	public List<DetalleAsistencia> getAsistencias() {
		return asistencias;
	}

	public void setAsistencias(List<DetalleAsistencia> asistencias) {
		this.asistencias = asistencias;
	}
}
