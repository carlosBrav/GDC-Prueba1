package pe.edu.sistemas.siscadweb.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.siscadweb.entities.Ciclo;
import pe.edu.sistemas.siscadweb.entities.CursoBase;
import pe.edu.sistemas.siscadweb.entities.CursoPeriodo;
import pe.edu.sistemas.siscadweb.entities.Docente;
import pe.edu.sistemas.siscadweb.entities.Grupo;
import pe.edu.sistemas.siscadweb.entities.HorarioClase;
import pe.edu.sistemas.siscadweb.services.CargaCursosService;

@ManagedBean
@SessionScoped
@Component
public class CargaHorariosController {
	private boolean opcionCursos = true;
	private boolean opcionHorarios = false;
	private boolean opcionReportes = false;
	private boolean opcionAperturar = false;
	private boolean opcionEditarCursob = false;
	private boolean opcionEditarGrupo = false;
	private boolean opcionNuevoHorario = false;
	private boolean horarioValidado = false;

	private List<Ciclo> cursos_ciclo = new ArrayList<Ciclo>();
	private CursoPeriodo cursoSelected;

	private DualListModel<String> dualCursosBase;
	private List<CursoBase> cursosAperturados = new ArrayList<CursoBase>();
	private List<CursoBase> cursosNoAperturados = new ArrayList<CursoBase>();
	private List<String> cursosAperturadosS = new ArrayList<String>();
	private List<String> cursosNoAperturadosS = new ArrayList<String>();

	private List<Grupo> grupos_curso = new ArrayList<Grupo>();
	private Grupo grupoToCreate = new Grupo();
	private Grupo grupoSelected;

	private List<HorarioClase> horarios_clase = new ArrayList<HorarioClase>();

	private String diaSelected = "LUNES";
	private List<String> dias = new ArrayList<String>();
	private String tipoClaseSelected = "TEORIA";
	private List<String> tipoClases = new ArrayList<String>();
	private Date horaInicio = null;
	private Date horaFin = null;

	private List<Docente> docentes = new ArrayList<Docente>();
	private Docente docenteSelected = null;

	private List<String> error = new ArrayList<String>();

	@Autowired
	private CargaCursosService cargaCursosService;
	


	@PostConstruct
	public void init() {
		cursos_ciclo = cargaCursosService.obtenerCursosActualxPlan(4);
		cursosNoAperturados = cargaCursosService
				.obtenerCursosNOAperturadosxPlan(4);
		cursosAperturadosS = new ArrayList<String>();
		cursosNoAperturadosS = new ArrayList<String>();
		for (CursoBase cb : cursosNoAperturados) {
			cursosNoAperturadosS.add(cb.getCursob_nombre());
		}
		this.dualCursosBase = new DualListModel<String>(cursosNoAperturadosS,
				cursosAperturadosS);
		grupos_curso = new ArrayList<Grupo>();
		grupoToCreate = new Grupo();
		horarios_clase = new ArrayList<HorarioClase>();

		dias = new ArrayList<String>();
		dias.add("LUNES");
		dias.add("MARTES");
		dias.add("MIERCOLES");
		dias.add("JUEVES");
		dias.add("VIERNES");
		dias.add("SABADO");
		dias.add("DOMINGO");
		tipoClases = new ArrayList<String>();
		tipoClases.add("TEORIA");
		tipoClases.add("PRACTICA");
		tipoClases.add("LABORATORIO");

		horaInicio = null;
		horaFin = null;

		docentes = new ArrayList<Docente>();
		docenteSelected = null;
		tipoClaseSelected = "TEORIA";
		diaSelected = "LUNES";

		horarioValidado = false;
	}

	public void limpia() {
		opcionCursos = false;
		opcionHorarios = false;
		opcionReportes = false;
		opcionAperturar = false;
		opcionEditarCursob = false;
		opcionEditarGrupo = false;
		opcionNuevoHorario = false;
		horarioValidado = false;
	}

	public boolean isOpcionAperturar() {
		return opcionAperturar;
	}

	public boolean isOpcionEditarGrupo() {
		return opcionEditarGrupo;
	}

	public List<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

	public Docente getDocenteSelected() {
		return docenteSelected;
	}

	public void setDocenteSelected(Docente docenteSelected) {
		this.docenteSelected = docenteSelected;
	}

	public void setOpcionEditarGrupo(boolean opcionEditarGrupo) {
		this.opcionEditarGrupo = opcionEditarGrupo;
	}

	public List<Grupo> getGrupos_curso() {
		return grupos_curso;
	}

	public String getDiaSelected() {
		return diaSelected;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	public void setDiaSelected(String diaSelected) {
		this.diaSelected = diaSelected;
	}

	public List<String> getDias() {
		return dias;
	}

	public void setDias(List<String> dias) {
		this.dias = dias;
	}

	public String getTipoClaseSelected() {
		return tipoClaseSelected;
	}

	public void setTipoClaseSelected(String tipoClaseSelected) {
		this.tipoClaseSelected = tipoClaseSelected;
	}

	public List<String> getTipoClases() {
		return tipoClases;
	}

	public void setTipoClases(List<String> tipoClases) {
		this.tipoClases = tipoClases;
	}

	public void setGrupos_curso(List<Grupo> grupos_curso) {
		this.grupos_curso = grupos_curso;
	}

	public boolean isOpcionEditarCursob() {
		return opcionEditarCursob;
	}

	public void setOpcionEditarCursob(boolean opcionEditarCursob) {
		this.opcionEditarCursob = opcionEditarCursob;
	}

	public List<CursoBase> getCursosAperturados() {
		return cursosAperturados;
	}

	public void setCursosAperturados(List<CursoBase> cursosAperturados) {
		this.cursosAperturados = cursosAperturados;
	}

	public List<CursoBase> getCursosNoAperturados() {
		return cursosNoAperturados;
	}

	public void setCursosNoAperturados(List<CursoBase> cursosNoAperturados) {
		this.cursosNoAperturados = cursosNoAperturados;
	}

	public void setOpcionAperturar(boolean opcionAperturar) {
		this.opcionAperturar = opcionAperturar;
	}

	public CursoPeriodo getCursoSelected() {
		return cursoSelected;
	}

	public void setCursoSelected(CursoPeriodo cursoSelected) {
		this.cursoSelected = cursoSelected;
		clickCurso();
	}

	public List<Ciclo> getCursos_ciclo() {
		return cursos_ciclo;
	}

	public void setCursos_ciclo(List<Ciclo> cursos_ciclo) {
		this.cursos_ciclo = cursos_ciclo;
	}

	public boolean isOpcionCursos() {
		return opcionCursos;
	}

	public DualListModel<String> getDualCursosBase() {
		return dualCursosBase;
	}

	public void setDualCursosBase(DualListModel<String> dualCursosBase) {
		this.dualCursosBase = dualCursosBase;
	}

	public void setOpcionCursos(boolean opcionCursos) {
		this.opcionCursos = opcionCursos;
	}

	public Grupo getGrupoSelected() {
		return grupoSelected;
	}

	public void setGrupoSelected(Grupo grupoSelected) {
		this.grupoSelected = grupoSelected;
		clickGrupo();
	}

	public Grupo getGrupoToCreate() {
		return grupoToCreate;
	}

	public void setGrupoToCreate(Grupo grupoToCreate) {
		this.grupoToCreate = grupoToCreate;
	}

	public boolean isOpcionNuevoHorario() {
		return opcionNuevoHorario;
	}

	public void setOpcionNuevoHorario(boolean opcionNuevoHorario) {
		this.opcionNuevoHorario = opcionNuevoHorario;
	}

	public boolean isOpcionHorarios() {
		return opcionHorarios;
	}

	public void setOpcionHorarios(boolean opcionHorarios) {
		this.opcionHorarios = opcionHorarios;
	}

	public boolean isOpcionReportes() {
		return opcionReportes;
	}

	public void setOpcionReportes(boolean opcionReportes) {
		this.opcionReportes = opcionReportes;
	}

	public void cargaOpcionCursos() {
		limpia();
		opcionCursos = true;
	}

	public void cargaOpcionHorarios() {
		limpia();
		opcionHorarios = true;
	}

	public void cargaOpcionReportes() {
		limpia();
		opcionReportes = true;
	}

	public void cargaOpcionEditarCursob() {
		limpia();
		opcionEditarCursob = true;
	}

	public void cargaOpcionAperturar() {
		limpia();
		opcionAperturar = true;
	}

	public void cargaOpcionEditarGrupo() {
		limpia();
		opcionEditarGrupo = true;
	}

	public void cargaOpcionNuevoHorario() {
		limpia();
		opcionNuevoHorario = true;
		opcionEditarGrupo = true;
	}

	public void clickCurso() {
		System.out.println("Entra clickCurso");
		System.out.println(cursoSelected.getNombre());
		grupos_curso = cargaCursosService
				.obtenerGruposxCodCursoPeriodo(cursoSelected.getIdCurso());
		grupoToCreate = cargaCursosService.obtenerNroGrupoACrear(cursoSelected
				.getIdCurso());
		cargaOpcionEditarCursob();
	}

	public void clickAperturar() {
		cargaOpcionAperturar();
		System.out.println("Entra clickAperturar");
	}

	public void guardarCursosBase() {
		cargaCursosService.AperturarCursosPeriodo(dualCursosBase.getTarget());
		init();
	}

	public void crearGrupo() {
		System.out.println("Entra CrearGrupo");
		CursoPeriodo cpaux = cursoSelected;
		cargaCursosService.crearGrupo(cursoSelected.getIdCurso(),
				grupoToCreate.getGrupoNumero());
		init();
		cursoSelected = cpaux;
		cursoSelected.setNumGrupo(cursoSelected.getNumGrupo() + 1);
		clickCurso();
	}

	private void clickGrupo() {
		cargaOpcionEditarGrupo();
		System.out.println("Entra clickGrupo");
		System.out.println(grupoSelected.getIdGrupo() + " "
				+ grupoSelected.getGrupoNumero());
		horarios_clase = cargaCursosService.obtenerHorariosxGrupo(grupoSelected
				.getIdGrupo());
	}

	public List<HorarioClase> getHorarios_clase() {
		return horarios_clase;
	}

	public void setHorarios_clase(List<HorarioClase> horarios_clase) {
		this.horarios_clase = horarios_clase;
	}

	public boolean isHorarioValidado() {
		return horarioValidado;
	}

	public List<String> getError() {
		return error;
	}

	public void setError(List<String> error) {
		this.error = error;
	}

	public void setHorarioValidado(boolean horarioValidado) {
		this.horarioValidado = horarioValidado;
	}

	public void cargarProfesores() {
		docentes = cargaCursosService.obtenerProfesores();
	}

	public void validarNuevoHorario() {
		System.out.println("Entra validarNuevoHorario");
		horarioValidado = false;
		error = new ArrayList<String>();
		if (this.horaInicio != null && this.horaFin != null
				&& this.docenteSelected != null) {
			horarioValidado = true;
			if (horaInicio.after(horaFin)) {
				System.out.println("Error de Horas");
				error.add("La hora de fin debe ser mayor a la hora de inicio");
				horarioValidado = false;
			}
		} else {
			if (this.docenteSelected == null) {
				System.out.println("Error Docente");
				error.add("No se ha definido Profesor");
			}
			if (this.horaInicio == null) {
				System.out.println("Error HoraInicio");
				error.add("No se ha definido Hora de Inicio");
			}
			if (this.horaFin == null) {
				System.out.println("Error HoraFin");
				error.add("No se ha definido Hora de Fin");
			}
		}
	}

	public void crearNuevoHorario() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Grupo gaux = grupoSelected;
		Docente daux = docenteSelected;
		System.out.println("**********************");
		System.out.println("Creando nuevo Horario");
		System.out.println("Grupo: " + grupoSelected.getGrupoNumero()
				+ " - id: " + grupoSelected.getIdGrupo());
		System.out.println("Tipo: " + tipoClaseSelected);
		System.out.println("Dia: " + diaSelected + " "
				+ obtenerDia(this.diaSelected));
		System.out.println("Docente: " + docenteSelected.getapPaterno()
				+ " -id: " + docenteSelected.getidDocente());
		System.out.println("Inicio: " + format.format(horaInicio));
		System.out.println("Inicio: " + format.format(horaFin));
		cargaCursosService.crearNuevoHorario(this.grupoSelected.getIdGrupo(),
				this.tipoClaseSelected, obtenerDia(this.diaSelected),
				this.docenteSelected.getidDocente(), format.format(horaInicio),
				format.format(horaFin));
		System.out.println("Creo el Horario n.n");
		init();
		grupoSelected = gaux;
		docenteSelected = daux;
		this.horarios_clase = cargaCursosService
				.obtenerHorariosxGrupo(grupoSelected.getIdGrupo());
		this.opcionNuevoHorario = false;
	}

	public int obtenerDia(String diax) {
		if (diax.equalsIgnoreCase("LUNES")) {
			return 1;
		} else if (diax.equalsIgnoreCase("MARTES")) {
			return 2;
		} else if (diax.equalsIgnoreCase("MIERCOLES")) {
			return 3;
		} else if (diax.equalsIgnoreCase("JUEVES")) {
			return 4;
		} else if (diax.equalsIgnoreCase("VIERNES")) {
			return 5;
		} else if (diax.equalsIgnoreCase("SABADO")) {
			return 6;
		} else if (diax.equalsIgnoreCase("DOMINGO")) {
			return 7;
		}
		return 0;
	}

}
