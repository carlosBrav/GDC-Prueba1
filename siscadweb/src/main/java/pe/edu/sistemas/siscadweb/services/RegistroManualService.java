package pe.edu.sistemas.siscadweb.services;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.siscadweb.entities.Asistencia;
import pe.edu.sistemas.siscadweb.entities.CursoPeriodo;
import pe.edu.sistemas.siscadweb.entities.Docente;
import pe.edu.sistemas.siscadweb.entities.HorarioClase;
import pe.edu.sistemas.siscadweb.entities.RegistroManual;
import pe.edu.sistemas.siscadweb.repositories.AsistenciaRepository;
import pe.edu.sistemas.siscadweb.repositories.CursoPeriodoRepository;
import pe.edu.sistemas.siscadweb.repositories.DocenteRepository;
import pe.edu.sistemas.siscadweb.repositories.HorarioClaseRepository;

@Service
@Transactional
public class RegistroManualService {
	@Autowired
	private AsistenciaRepository asistenciaRepository;
	@Autowired
	private HorarioClaseRepository horarioClaseRepository;
	@Autowired
	private DocenteRepository docenteRepository;
	@Autowired
	private CursoPeriodoRepository cursoPeriodoRepository;
	
	public List<RegistroManual> obtenerAsistencias(Date fecha, String hInicio, String hFin, String ciclo){
		Asistencia asistencia;
		CursoPeriodo curso;
		Docente docente;
		HorarioClase horarioClase;
		String entrada;
		String salida;
		String horaEntrada;
		String horaSalida;
	
		
		ArrayList<RegistroManual> registro = new ArrayList<RegistroManual>();
		List<HorarioClase> horarios;
		
		if(fecha.getDay() == 0){
			return registro;
		}
		horarios = horarioClaseRepository.obtenerHorariosClasexPeriodoxDiayHora(ciclo, fecha.getDay(), hInicio, hFin);
		for(int i = 0; i < horarios.size(); i++){
			horarioClase =  horarios.get(i);
			docente = docenteRepository.obtenerDocentexID(horarioClase.getIdDocente());
			asistencia = asistenciaRepository.obtenerAsistenciaxFechayIdHorario(fecha, horarioClase.getIdHorarioClase());
			curso = cursoPeriodoRepository.obtenerCursosxIdGrupo(horarioClase.getIdGrupo());

			if(asistencia == null){
				entrada = "NO ASIS";
				salida = "NO ASIS";
				horaEntrada = horarioClase.getHoraInicio().toString().substring(0, 5); 
				horaSalida = horarioClase.getHoraFin().toString().substring(0, 5);
			}else{
				entrada = asistencia.getHoraIngreso().toString().substring(0, 5);
				salida = asistencia.getHoraSalida().toString().substring(0, 5);
				horaEntrada = ""; 
				horaSalida = "";
			}
			registro.add(new RegistroManual(docente.getCodigo(),
								docente.getNombre() + " " + docente.getapPaterno() + " " + docente.getapMaterno(),
								curso.getNombre(),
								horarioClase.getHorarioClaseTipo(),
								entrada,
								salida,
								horarioClase.getIdHorarioClase(),
								horaEntrada,
								horaSalida,
								curso.getNumGrupo()
						)
			);
		}
		return registro;
	}
	
	public void marcarAsistencia(String hEntrada, String hSalida, String tema, Date fecha,  String observacion,
								 int idHorarioClase, int idMotivo){
		Time horaEntrada = Time.valueOf(hEntrada + ":00");
		Time horaSalida = Time.valueOf(hSalida + ":00");
		asistenciaRepository.registrarAsistenciaxMotivo(horaEntrada, horaSalida, tema, fecha, observacion, "MANUAL",
														idHorarioClase, idMotivo);
	}
}
