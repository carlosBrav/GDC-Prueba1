package pe.edu.sistemas.siscadweb.util;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JRPrintPage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.edu.sistemas.siscadweb.entities.Reporte;

public class ReportManaged {
	private static Log log = LogFactory.getLog(ReportManaged.class);
	private static ReportManaged reportManaged;
	private static Reporte reportParam;
	private Connection conexion;
	private int tipoFormato = 0;
	private static final String URL = "/WEB-INF/reportes/";
	private static final String URLImages = "/WEB-INF/reportes/imagenes/";
	
	public static ReportManaged getInstance(String reporte) {
		if (reportManaged == null) {
			log.info("creando instancia del reporte");
			reportManaged = new ReportManaged();
		}
		reportManaged.setReportParam(ReportManaged.getInstanceReporte(reporte));
		return reportManaged;
	}

	private void setReportParam(Reporte instanceReporte) {
		ReportManaged.reportParam = instanceReporte;
	}

	private static Reporte getInstanceReporte(String reporte) {
		if (reportParam == null) {
			log.info("creando un nuevo reporte");
			reportParam = new Reporte();
		}
		reportParam.setQueryParams(new HashMap<String, Object>());
		reportParam.setNombreReport(reporte);
		return reportParam;
	}

	public void addMapParam(Map<String, Object> h) {
		reportParam.setQueryParams(h);
	}

	public boolean ejecutarReporte(FacesContext context, ServletContext servletContext) {
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		JasperPrint jasperPrint = execReport(context, servletContext);
		if (jasperPrint != null) {
			List<JRPrintPage> lista = jasperPrint.getPages();
			jasperPrint.setLocaleCode("en_US");
			if (lista != null && lista.size() > 0) {
				log.info("numero de hojas en reporte " + lista.size());
				if (tipoFormato == 0) {
					exportarReporteaPDF(jasperPrint, context, response, request);
				} else {
					log.info("no se cuenta con soporte para excel");
				}				
				return true;
			}
			log.info("no contiene hojas el reporte");
		}
		return false;
	}

	private void exportarReporteaPDF(JasperPrint jasperPrint,
			FacesContext context, HttpServletResponse response,
			HttpServletRequest request) {
		byte[] pdf;
		try {
			pdf = JasperExportManager.exportReportToPdf(jasperPrint);
			
			log.info("nombre del reporte : " + reportParam.getNombreReport());
			//Para descargarlo
			response.addHeader("Content-disposition", "attachment;filename="+ reportParam.getNombreReport() + ".pdf");
			//Para mostrarlo
			//response.addHeader("Content-disposition", "filename="+ reportParam.getNombreReport() + ".pdf");
			response.setContentLength(pdf.length);
			response.getOutputStream().write(pdf);
			response.setContentType("application/pdf");
			context.responseComplete();

		} catch (Exception e) {
			log.info(" Mensaje de la excepcion " + e.getMessage());
		}

	}

	private JasperPrint execReport(FacesContext context, ServletContext servletContext) {
		try {
			JasperReport report = null;
			String archivo;
			archivo = servletContext.getRealPath(URL + reportParam.getNombreReport() + ".jrxml");
			//obtengo los parametros para agregar uno ultimo para obtener la ruta de la imagen del reporte
			reportParam.getQueryParams().put("contexto", servletContext.getRealPath(URLImages + "unmsm" + ".gif"));
			//archivo = new File(servletContext.getRealPath("/")).getAbsolutePath();
			log.info("archivo : " + archivo);

			if (!archivo.equals("")) {
				report = JasperCompileManager.compileReport(archivo);

			} else {
				log.info("no existe el archivo");
			}

			JasperPrint jasperPrint = JasperFillManager.fillReport(report,
					reportParam.getQueryParams(), conexion);
			log.info("pasado");
			return jasperPrint;

		} catch (JRException e) {
			// log.error(e);
			e.printStackTrace();
		} catch (Exception e) {
			// log.error(e);
			e.printStackTrace();
		}
		return new JasperPrint();
	}

	public void setTipoFormato(int tipoFormato) {
		this.tipoFormato = tipoFormato;
	}

	public int getTipoFormato() {
		return tipoFormato;
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
}
