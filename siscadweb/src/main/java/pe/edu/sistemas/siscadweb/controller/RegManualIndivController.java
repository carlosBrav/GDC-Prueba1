package pe.edu.sistemas.siscadweb.controller;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.siscadweb.entities.Docente;
import pe.edu.sistemas.siscadweb.entities.Periodo;
import pe.edu.sistemas.siscadweb.entities.RegistroManual;
import pe.edu.sistemas.siscadweb.repositories.PeriodoRepository;
import pe.edu.sistemas.siscadweb.services.DocenteService;
import pe.edu.sistemas.siscadweb.services.RegistroIndividualService;
import pe.edu.sistemas.siscadweb.util.RegistroManualDataModel;

@ManagedBean
@SessionScoped
@Component
public class RegManualIndivController {
	private RegistroManualDataModel regManualDataModel;
	private List<RegistroManual> regManuales;
	
	private Date fechaInicial;
	private java.util.Date fechaSeleccionadaInicial;
	private Date fechaFinal;
	private java.util.Date fechaSeleccionadaFinal;
	
	private String ciclo;
	private List<Docente> docentes;
	private Docente docSeleccionado;
	private List<Docente> alumnosFiltrados;
	private String docActual = "Seleccione un DOCENTE";
	
	private RegistroManual regSeleccionado;
	
	public RegistroManual getRegSeleccionado() {
		return regSeleccionado;
	}

	public void setRegSeleccionado(RegistroManual regSeleccionado) {
		System.out.println("ya se seteo el registro seleccionado");
		this.regSeleccionado = regSeleccionado;
	}

	@Autowired
	public DocenteService docenteService;
	@Autowired
	private PeriodoRepository periodoRepository;
	@Autowired
	private RegistroIndividualService registroIndividualService;

	
	public RegManualIndivController() { }

	@PostConstruct
	public void init() {
		//docentes = new ArrayList<Docente>();
		//docentes.add(new Docente(123, "asdffffffff", "jeje", "jojo", "nini"));
		docentes = docenteService.obtenerDocentes();
		System.out.println("cant docentes: " + docentes.size());
	}
	
	public void verificaSeleccionado(){
		if(docSeleccionado != null){
			RequestContext contextoDiag = RequestContext.getCurrentInstance();
			contextoDiag.execute("dialogoDocentes.hide();");
			
			docActual = docSeleccionado.getNombre()+" "+docSeleccionado.getapPaterno()+" "+docSeleccionado.getapMaterno();
			
			FacesContext context = FacesContext.getCurrentInstance();  
            context.addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_INFO, "Docente " + docSeleccionado.getNombre() + " seleccionado", null));
		}
		else{
			FacesContext context = FacesContext.getCurrentInstance();  
            context.addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccione un docente", null));
		}
	}
	
	public void obtenerAsistencia(){
		System.out.println("ttttttttttttttttttttttttttttt");
		System.out.println(fechaSeleccionadaInicial);
		System.out.println(fechaSeleccionadaFinal);
		try{
			SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
			
			fechaInicial = Date.valueOf(fechaFormato.format(fechaSeleccionadaInicial));
			fechaFinal = Date.valueOf(fechaFormato.format(fechaSeleccionadaFinal));
			validarFecha(fechaInicial, fechaSeleccionadaInicial);
			validarFecha(fechaFinal, fechaSeleccionadaFinal);
			ciclo = periodoRepository.obtenerUltimoPeriodo().getNombre();
			System.out.println(docSeleccionado.getNombre());
			System.out.println(docSeleccionado.getidDocente());
			regManuales = registroIndividualService.obtenerAsistencias(fechaInicial, fechaFinal, ciclo , docSeleccionado.getidDocente());
			System.out.println(regManuales.size());
			System.out.println(fechaInicial);
			System.out.println(fechaFinal);
			//regManualDataModel = new RegistroManualDataModel(regManuales);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void validarFecha(Date fecha, java.util.Date fechaSeleccionada){
		try{
			Periodo periodo;
			Date diaInicio;
			Date diaFinal;
			FacesContext context;
			
			periodo = periodoRepository.obtenerUltimoPeriodo();
			diaInicio = Date.valueOf(periodo.getFechaInicio());
			diaFinal = Date.valueOf(periodo.getFechaFin());

			if(fecha.before(diaInicio)){
				fecha = diaInicio;
				fechaSeleccionada = new java.util.Date(fecha.getTime());
				context = FacesContext.getCurrentInstance();  
	            context.addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_WARN,
	            		"Fecha seleccionada fuera de rango, el resultado corresponde al inicio de ciclo.", null));
			}else if(fecha.after(diaFinal)){
				fecha = diaFinal;
				fechaSeleccionada = new java.util.Date(fecha.getTime());
				context = FacesContext.getCurrentInstance();  
	            context.addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_WARN,
	            		"Fecha seleccionada fuera de rango, el resultado corresponde al final de ciclo.", null));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void marcarAsistencia(){/*
		try{
			if(regSeleccionados.length == 0){
				return;
			}
			for(int i = 0; i < regSeleccionados.length; i++){
				if(regSeleccionados[i].getEntrada().equals("NO ASIS")){
					registroManualService.marcarAsistencia(regSeleccionados[i].getHoraEntrada(),
														   regSeleccionados[i].getHoraSalida(),
														   "Mi tema",
														   fecha,
														   descripcion,
														   regSeleccionados[i].getIdHorario(),
														   motivoSeleccionado
					);
				}
			}
			regManuales = registroManualService.obtenerAsistencias(fecha, inicioSeleccionado, salidaSeleccionada, ciclo);
			regManualDataModel = new RegistroManualDataModel(regManuales);
			showDialog = false;
			regSeleccionados = new RegistroManual[0];
			FacesContext context = FacesContext.getCurrentInstance();  
            context.addMessage("mensajes", new FacesMessage(FacesMessage.SEVERITY_INFO,
            												"Asistencias registradas con éxito", null));
		}catch(Exception e){
			e.printStackTrace();
		}
	
		*/
	}
		
	public RegistroManualDataModel getRegManualDataModel() {
		return regManualDataModel;
	}

	public void setRegManualDataModel(RegistroManualDataModel regManualDataModel) {
		this.regManualDataModel = regManualDataModel;
	}

	public List<RegistroManual> getRegManuales() {
		return regManuales;
	}

	public void setRegManuales(List<RegistroManual> regManuales) {
		this.regManuales = regManuales;
	}


	public java.util.Date getFechaSeleccionadaInicial() {
		return fechaSeleccionadaInicial;
	}

	public void setFechaSeleccionadaInicial(java.util.Date fechaSeleccionadaInicial) {
		this.fechaSeleccionadaInicial = fechaSeleccionadaInicial;
	}


	public java.util.Date getFechaSeleccionadaFinal() {
		return fechaSeleccionadaFinal;
	}

	public void setFechaSeleccionadaFinal(java.util.Date fechaSeleccionadaFinal) {
		this.fechaSeleccionadaFinal = fechaSeleccionadaFinal;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public PeriodoRepository getPeriodoRepository() {
		return periodoRepository;
	}

	public void setPeriodoRepository(PeriodoRepository periodoRepository) {
		this.periodoRepository = periodoRepository;
	}

	public RegistroIndividualService getRegistroIndividualService() {
		return registroIndividualService;
	}

	public void setRegistroIndividualService(
			RegistroIndividualService registroIndividualService) {
		this.registroIndividualService = registroIndividualService;
	}

	public List<Docente> getDocentes() {
		return docentes;
	}

	public void setDocentes(List<Docente> docentes) {
		this.docentes = docentes;
	}

	public Docente getDocSeleccionado() {
		return docSeleccionado;
	}

	public void setDocSeleccionado(Docente docSeleccionado) {
		this.docSeleccionado = docSeleccionado;
	}

	public List<Docente> getAlumnosFiltrados() {
		return alumnosFiltrados;
	}

	public void setAlumnosFiltrados(List<Docente> alumnosFiltrados) {
		this.alumnosFiltrados = alumnosFiltrados;
	}

	public String getDocActual() {
		return docActual;
	}

	public void setDocActual(String docActual) {
		this.docActual = docActual;
	}
}
