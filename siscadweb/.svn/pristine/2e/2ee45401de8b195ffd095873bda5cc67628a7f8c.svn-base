package pe.edu.sistemas.siscadweb.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;

import pe.edu.sistemas.siscadweb.entities.DepAcademico;
import pe.edu.sistemas.siscadweb.entities.Escuela;
import pe.edu.sistemas.siscadweb.entities.Periodo;
import pe.edu.sistemas.siscadweb.services.DepAcademicoService;
import pe.edu.sistemas.siscadweb.services.EscuelaService;
import pe.edu.sistemas.siscadweb.services.PeriodoService;
import pe.edu.sistemas.siscadweb.util.ReportManaged;

@Controller
@Scope("session")
public class ReporteController {
	private Log log = LogFactory.getLog(ReporteController.class);

	@Autowired
	private PeriodoService periodoService;

	private List<Periodo> periodos = null;
	private Periodo periodo;
	private String mes;
	private List<String> meses = null;

	@Autowired
	private DepAcademicoService depAcademicoService;

	private List<DepAcademico> departamentos = null;
	private DepAcademico departamento;

	@Autowired
	private EscuelaService escuelaService;

	private List<Escuela> escuelas = null;
	private Escuela escuela;

	private String paramCiclo = "";
	private String paramMes = "";
	private String paramDepa = "";
	private String paramEscuela = "";
	private String encabezado = "";
	private String fechaInicial = "";
	private String rango1 = "", rango2 = "", rango3 = "", rango4 = "";

	@Autowired
	@Qualifier("dataSource")
	private BasicDataSource dataSource;

	public ReporteController() {

	}

	@PostConstruct
	public void init() {
		periodos = periodoService.findAllPeriodos();
		
		System.out.println("imprimo fechaIni de primer periodo: " + periodos.get(0).getFechaInicio());
		
		periodo = new Periodo();

		departamentos = depAcademicoService.findAllDepartamentos();
		departamento = new DepAcademico();

		escuelas = escuelaService.findAllEscuelas();
		escuela = new Escuela();
	}

	public void generarReporte() {
		Map<String, Object> parameters = new HashMap<String, Object>();

		paramCiclo = periodo.getNombre();
		paramEscuela = escuela.getNombre();
		paramMes = mes;
		paramDepa = departamento.getNombre();

		System.out.println("parametros: " + paramCiclo + " " + paramEscuela
				+ " " + paramMes + " " + paramCiclo);

		int valorMesSeleccionado = periodoService.obtenerValorMes(paramMes);
		valorMesSeleccionado--;

		encabezado = "DEL 24 DE "+ periodoService.nombreDelMes(valorMesSeleccionado)+ " AL 23 DE " + paramMes;

		if (valorMesSeleccionado < 10) {
			fechaInicial = "2013-0" + valorMesSeleccionado + "-24";
		} else {
			fechaInicial = "2013-" + valorMesSeleccionado + "-24";
		}

		List<String> rangoSemanas = periodoService.obtenerRangoSemanas(fechaInicial);

		rango1 = rangoSemanas.get(0);
		rango2 = rangoSemanas.get(1);
		rango3 = rangoSemanas.get(2);
		rango4 = rangoSemanas.get(3);

		parameters.put("fecha", fechaInicial);
		parameters.put("ciclo", paramCiclo);
		parameters.put("escuela", paramEscuela);
		parameters.put("departamento", paramDepa);
		parameters.put("fechaEncabezado", encabezado);
		parameters.put("rangoFecha1", rango1);
		parameters.put("rangoFecha2", rango2);
		parameters.put("rangoFecha3", rango3);
		parameters.put("rangoFecha4", rango4);

		String reporte = "ReporteAsistencia";

		this.generarPdf(reporte, parameters);

	}

	public void generarPdf(String reporte, Map<String, Object> h) {
		FacesContext context = FacesContext.getCurrentInstance();

		ServletContext servletContext = (ServletContext) context
				.getExternalContext().getContext();
		ReportManaged report = ReportManaged.getInstance(reporte);
		try {
			report.setConexion(dataSource.getConnection());
			log.info("se creo la conexion con la base de datos");
		} catch (Exception e) {
			log.info(" No se puede traer la conexion a la bd");
			e.printStackTrace();
		}
		report.setTipoFormato(0);
		report.addMapParam(h);
		boolean rpt = false;
		rpt = report.ejecutarReporte(context, servletContext);
		if (!rpt)
			log.info("no se construyo el reporte");
	}

	public void cambiarRangoMes() {
		//buscamos el periodo seleccionado
		
		System.out.println("imprimimos id: " + periodo.getId());
		
		periodo = buscarPeriodoxId(periodo.getId());
		
		meses = periodoService.obtenerRangoMes(periodo.getNombre());
		
		/*System.out.println("imprimo valores: " + meses.get(0)+ " fecha ini: " + periodo.getFechaInicio());
		
		String fechaIni = "2013-08-18";
		
		periodoService.rangoFechasInicioCiclo(meses.get(0), fechaIni);*/
	}
	
	public Periodo buscarPeriodoxId(int id){
		
		for(int i=0; i<periodos.size(); i++){
			Periodo val = periodos.get(i);
			if(val.getId() == id) return val;
		}
		
		return null;
	}

	public BasicDataSource getBasicDataSource() {
		return dataSource;
	}

	public void setBasicDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<String> getMeses() {
		return meses;
	}

	public void setMeses(List<String> meses) {
		this.meses = meses;
	}

	public List<DepAcademico> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<DepAcademico> departamentos) {
		this.departamentos = departamentos;
	}

	public DepAcademico getDepartamento() {
		return departamento;
	}

	public void setDepartamento(DepAcademico departamento) {
		this.departamento = departamento;
	}

	public List<Escuela> getEscuelas() {
		return escuelas;
	}

	public void setEscuelas(List<Escuela> escuelas) {
		this.escuelas = escuelas;
	}

	public Escuela getEscuela() {
		return escuela;
	}

	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}

	public String getParamCiclo() {
		return paramCiclo;
	}

	public void setParamCiclo(String paramCiclo) {
		this.paramCiclo = paramCiclo;
	}

	public String getParamMes() {
		return paramMes;
	}

	public void setParamMes(String paramMes) {
		this.paramMes = paramMes;
	}

	public String getParamDepa() {
		return paramDepa;
	}

	public void setParamDepa(String paramDepa) {
		this.paramDepa = paramDepa;
	}

	public String getParamEscuela() {
		return paramEscuela;
	}

	public void setParamEscuela(String paramEscuela) {
		this.paramEscuela = paramEscuela;
	}
}
