package pe.edu.sistemas.siscadweb.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.siscadweb.entities.Docente;
import pe.edu.sistemas.siscadweb.entities.Motivo;
import pe.edu.sistemas.siscadweb.entities.RegistroRecuperacion;
import pe.edu.sistemas.siscadweb.services.DocenteService;
import pe.edu.sistemas.siscadweb.services.RecuperacionService;

@ManagedBean
@SessionScoped
@Component
public class RecuperacionController {
	// Dialog Docentes
	private List<Docente> docentes;
	private Docente docSeleccionado;
	private List<Docente> docentesFiltrados;
	private String docActual = "Seleccione un DOCENTE";

	// Tabla Recuperacion
	private List<RegistroRecuperacion> registrosRecuperacion;
	private List<RegistroRecuperacion> registrosFiltrados;
	private RegistroRecuperacion registroRecuperacionSeleccionado;

	// Dialog Registrar Recuperacion
	private String inicioSeleccionado;
	private String salidaSeleccionada;
	private Motivo motivoSeleccionado;
	private int idMotivoSeleccionado;

	private Date fechaRec;	
	private String observacion="";
	private java.util.Date fechaSeleccionada;
	private List<String> horasInicio;
	private List<String> horasSalida;
	private List<Motivo> motivos;
	private final static String[] tipos;
	private SelectItem[] tipoDeudaOpciones;
	static {
		tipos = new String[3];
		tipos[0] = "TARDANZA";
		tipos[1] = "INCOMPLETO";
		tipos[2] = "INASISTENCIA";
	}

	// SERVICIOS
	@Autowired
	public DocenteService docenteService;
	@Autowired
	public RecuperacionService recuperacionService;

	public RecuperacionController() {

	}

	@PostConstruct
	public void init() {
		// docentes = new ArrayList<Docente>();
		// docentes.add(new Docente(123, "asdffffffff", "jeje", "jojo",
		// "nini"));
		motivos = recuperacionService.obtenerMotivos();
		tipoDeudaOpciones = creaOpcionesDeFiltro(tipos);
		docentes = docenteService.obtenerDocentes();
		// llenaTabla(22);
		llenarHoras();
		// establecerHorasSalida();
	}

	public void llenarHoras() {
		horasInicio = new ArrayList<String>();
		horasInicio.add("08:00");
		horasInicio.add("09:00");
		for (int i = 10; i < 21; i++) {
			horasInicio.add(i + ":00");
		}
	}

	public void establecerHorasSalida() {
		
		StringTokenizer st = new StringTokenizer(inicioSeleccionado.toString(), ":");
		int horasIni = Integer.parseInt(st.nextToken());
        int minutosIni = Integer.parseInt(st.nextToken());
        
        int minutos = registroRecuperacionSeleccionado.getMinutosDeuda();
        
        int horasFin=horasIni+minutos/60;
        int minutosFin=minutosIni+minutos%60;
        
        String smin,shora;
        
        if(horasFin<10){
        	shora="0"+horasFin;
        }else{shora=""+horasFin;}
        
        if(minutosFin<10){
        	smin="0"+minutosFin;
        }else{smin=""+minutosFin;}
        horasSalida = new ArrayList<String>();
        horasSalida.add(shora+":"+smin);
        
        
        /*
		try {
			int i = horasInicio.indexOf(inicioSeleccionado) + 2;

			horasSalida = new ArrayList<String>();
			while (i < horasInicio.size()) {
				horasSalida.add(horasInicio.get(i));
				i++;
			}
			horasSalida.add("21:00");
			horasSalida.add("22:00");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	public void llenaTabla(int id) {
		System.out.println("cant RECUPERACIOn docentes: " + docentes.size());
		registrosRecuperacion = recuperacionService.ObtenerFaltasxDocente(id);
	}

	public void verificaSeleccionado() {
		if (docSeleccionado != null) {
			RequestContext contextoDiag = RequestContext.getCurrentInstance();
			contextoDiag.execute("dialogoRec.hide();");
			docActual = docSeleccionado.getNombre() + " "
					+ docSeleccionado.getapPaterno() + " "
					+ docSeleccionado.getapMaterno();
			System.out.println(" " + docActual);
			int idd = docSeleccionado.getidDocente();
			llenaTabla(idd);
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("mensajes", new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Docente " + docActual
							+ " seleccionado", null));
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("mensajes", new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Seleccione un docente", null));
		}
	}

	public void validarRegistrar() {
		
		//VALIDACIONES NECESARIAS PARA EL INSERT 	
		
		//Fecha : antes de la fecha limite  y despues de Hoy  -- Cruce con otro horario del profe
		//Inicio-Salida  > a 2 horas
		//INICIO Y SALIDA SELECCIONADAS
		//Motivo =Otros --> Que exista Observacion
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		System.out.println("-------------------VALIDAR REGISTRAR:------------------- ");
		
		System.out.println("VALIDAR INICIO: " + this.getInicioSeleccionado());
		System.out.println("VALIDAR SALIDA: " + this.getSalidaSeleccionada());
		System.out.println("VALIDAR FECHA: " + fechaRec);
		System.out.println("VALIDAR MOTIVO: " + this.getIdMotivoSeleccionado());
		System.out.println("VALIDAR MOTIVO: " + this.getObservacion());
		
		Date fechaLimite = recuperacionService.retornarSQLDate(registroRecuperacionSeleccionado.getFechaLimite());
		fechaRec = recuperacionService.retornarSQLDate(getFechaSeleccionada());
		Date hoy= recuperacionService.retornarSQLDate(Calendar.getInstance());
		
		boolean validaObs=true;
		if(getObservacion().trim().equals("") && this.getIdMotivoSeleccionado()!=1){
			validaObs=false;
		}
		if(fechaRec.after(hoy) 
				&& fechaRec.before(fechaLimite) 
				&& validaObs
				&& registroRecuperacionSeleccionado.getMinutosDeuda()>=120){
			System.out.println("INSERTAR RECUPERACION  "+ registroRecuperacionSeleccionado.getMinutosDeuda());
			
			int idHorarioClase =  registroRecuperacionSeleccionado.getIdHorario();
			int idMotivo=this.getIdMotivoSeleccionado();
			//fecha de recuperacion es fechaRec
			Date fechaAusente=Date.valueOf(registroRecuperacionSeleccionado.getFecha());
			String Aula="211";
			Time horaInicio =Time.valueOf(inicioSeleccionado+":"+"00");
			Time horaFin =Time.valueOf(salidaSeleccionada+":"+"00");
			String obs =this.getObservacion();
			int estado =0; //Programado
			System.out.println("" + horaInicio);
			System.out.println("" + horaFin);
			recuperacionService.insertarRecuperacion(idHorarioClase,
					idMotivo, 
					fechaRec,
					fechaAusente,
					Aula,
					horaInicio,
					horaFin,
					obs,
					estado);
			this.llenaTabla(this.docSeleccionado.getidDocente());
			
			/*
			private Integer idRecuperacion;
			private int idHorarioClase;-----
			private int idmotivo;**
			private Date fechaRecuperacion;**
			private Date fechaAusente;---
			private String aula;**
			private Time horaInicio;**
			private Time horaFin;**
			private String observacion;**
			private Time horaInicioReal;--
			private Time horaFinReal;--
			private int estado;*/
			/*
			 * private String code;
				private String name;
				private String curso;
				private String tipo;
				private int idHorario;------
				private int minutosDeuda;
				private String tipoDeuda;
				private String fecha;---
				private String estado;
				private String fechaProgramada;
				private String aula;
				private String horaInicio;
				private String horaFin;
				private String fechaLimite;*/
						
			
			RequestContext contextoDiag = RequestContext.getCurrentInstance();
			contextoDiag.execute("dialogprogrec.hide();");
		}else{
			context.addMessage("mensajes", new FacesMessage(
					FacesMessage.SEVERITY_INFO, "ERROR", null));
		} 
		if(fechaRec.after(hoy)){
			System.out.println("FECHA DESPUES A HOY");
		}
		if(fechaRec.before(fechaLimite) ){
			System.out.println("ANTES DE LA FECHA LIMITE");
		}
		
		System.out.println("FECHAS LIMITE : " + fechaLimite);
		System.out.println("FECHAS RECUPERACIONM : " + fechaRec);
		System.out.println("FECHAS HOY : " + hoy);
		
		if (this.getObservacion().equals("")) {
			System.out.println("VALIDAR MOTIVO :  VACIOOOOOOO");
		}
		
		

		

	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("",
				((RegistroRecuperacion) event.getObject()).getCurso());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("",
				((RegistroRecuperacion) event.getObject()).getCurso());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private SelectItem[] creaOpcionesDeFiltro(String[] data) {
		SelectItem[] options = new SelectItem[data.length + 1];

		options[0] = new SelectItem("", "Select");
		for (int i = 0; i < data.length; i++) {
			options[i + 1] = new SelectItem(data[i], data[i]);
		}
		return options;
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

	public List<Docente> getDocentesFiltrados() {
		return docentesFiltrados;
	}

	public void setDocentesFiltrados(List<Docente> docentesFiltrados) {
		this.docentesFiltrados = docentesFiltrados;
	}

	public String getDocActual() {
		return docActual;
	}

	public void setDocActual(String docActual) {
		this.docActual = docActual;
	}

	public List<String> getHorasInicio() {
		return horasInicio;
	}

	public void setHorasInicio(List<String> horasInicio) {
		this.horasInicio = horasInicio;
	}

	public List<String> getHorasSalida() {
		return horasSalida;
	}

	public void setHorasSalida(List<String> horasSalida) {
		this.horasSalida = horasSalida;
	}

	public java.util.Date getFechaSeleccionada() {
		return fechaSeleccionada;
	}

	public void setFechaSeleccionada(java.util.Date fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}

	public String getInicioSeleccionado() {
		return inicioSeleccionado;
	}

	public void setInicioSeleccionado(String inicioSeleccionado) {
		this.inicioSeleccionado = inicioSeleccionado;
	}

	public String getSalidaSeleccionada() {
		return salidaSeleccionada;
	}

	public void setSalidaSeleccionada(String salidaSeleccionada) {
		this.salidaSeleccionada = salidaSeleccionada;
	}

	public SelectItem[] getTipoDeudaOpciones() {
		return tipoDeudaOpciones;
	}

	public void setTipoDeudaOpciones(SelectItem[] tipoDeudaOpciones) {
		this.tipoDeudaOpciones = tipoDeudaOpciones;
	}

	public List<RegistroRecuperacion> getRegistrosRecuperacion() {
		return registrosRecuperacion;
	}

	public void setRegistrosRecuperacion(
			List<RegistroRecuperacion> registrosRecuperacion) {
		this.registrosRecuperacion = registrosRecuperacion;
	}

	public List<RegistroRecuperacion> getRegistrosFiltrados() {
		return registrosFiltrados;
	}

	public void setRegistrosFiltrados(
			List<RegistroRecuperacion> registrosFiltrados) {
		this.registrosFiltrados = registrosFiltrados;
	}

	public RegistroRecuperacion getRegistroRecuperacionSeleccionado() {
		return registroRecuperacionSeleccionado;
	}

	public void setRegistroRecuperacionSeleccionado(
			RegistroRecuperacion registroRecuperacionSeleccionado) {
		this.registroRecuperacionSeleccionado = registroRecuperacionSeleccionado;
	}

	public Motivo getMotivoSeleccionado() {
		return motivoSeleccionado;
	}

	public void setMotivoSeleccionado(Motivo motivoSeleccionado) {
		this.motivoSeleccionado = motivoSeleccionado;
	}

	public int getIdMotivoSeleccionado() {
		return idMotivoSeleccionado;
	}

	public void setIdMotivoSeleccionado(int idMotivoSeleccionado) {
		this.idMotivoSeleccionado = idMotivoSeleccionado;
	}

	public Date getFechaRec() {
		return fechaRec;
	}

	public void setFechaRec(Date fechaRec) {
		this.fechaRec = fechaRec;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public List<Motivo> getMotivos() {
		return motivos;
	}

	public void setMotivos(List<Motivo> motivos) {
		this.motivos = motivos;
	}

}
