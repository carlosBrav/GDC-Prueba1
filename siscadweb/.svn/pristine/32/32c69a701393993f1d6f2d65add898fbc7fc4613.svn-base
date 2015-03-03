package pe.edu.sistemas.siscadweb.services;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.siscadweb.entities.Asistencia;
import pe.edu.sistemas.siscadweb.entities.CursoDocente;
import pe.edu.sistemas.siscadweb.entities.CursoPeriodo;
import pe.edu.sistemas.siscadweb.entities.DetalleAsistencia;
import pe.edu.sistemas.siscadweb.entities.HorarioClase;
import pe.edu.sistemas.siscadweb.entities.Recuperacion;
import pe.edu.sistemas.siscadweb.repositories.AsistenciaRepository;
import pe.edu.sistemas.siscadweb.repositories.CursoPeriodoRepository;
import pe.edu.sistemas.siscadweb.repositories.RecuperacionRepository;
import pe.edu.sistemas.siscadweb.services.HorarioClaseService;

@Service
@Transactional
public class ConsultAsistService {
	@Autowired
	private CursoPeriodoRepository cursoPeriodoRepository;
	@Autowired
	private AsistenciaRepository asistenciaRepository;
	@Autowired
	private RecuperacionRepository recuperacionRepository;
	@Autowired
	private HorarioClaseService horarioClaseService;
	
	public List<CursoDocente> obtenerCursos(int idDocente, String periodo){
		List<CursoPeriodo> cursosPeriodoList;
		LinkedList<CursoDocente> cursosList;
		CursoPeriodo cursoPeriodo;
		CursoDocente curso;
		int idCursoAux;
		
		cursosPeriodoList = cursoPeriodoRepository.obtenerCursosxDocentexCiclo(idDocente, periodo);
		cursosList = new LinkedList<CursoDocente>();
		while(cursosPeriodoList.size() != 0){
			cursoPeriodo = cursosPeriodoList.get(0);
			curso = new CursoDocente();
			idCursoAux = cursoPeriodo.getIdCurso();

			curso.setIdCurso(cursoPeriodo.getIdCurso());
			curso.setNombre(cursoPeriodo.getNombre());
			curso.addGrupo(cursoPeriodo.getIdGrupo(), cursoPeriodo.getNumGrupo());
			
			cursoPeriodo = cursoPeriodoRepository.obtenerCursoCodigoEscuelaxIdCurso(idCursoAux);
			
			curso.setCodigoCurso(cursoPeriodo.getCodigo());
			curso.setEscuela(cursoPeriodo.getEscuela());
			
			cursosList.add(curso);
			cursosPeriodoList.remove(0);
			while(cursosPeriodoList.size() != 0){
				cursoPeriodo = cursosPeriodoList.get(0);
				if(idCursoAux == cursoPeriodo.getIdCurso()){
					curso.addGrupo(cursoPeriodo.getIdGrupo(), cursoPeriodo.getNumGrupo());
					cursosPeriodoList.remove(0);
				}else{
					break;
				}
			}
		}
		return cursosList;
	}

	public List<DetalleAsistencia> obtenerDetalleAsistencias(int idGrupo, String periodo, Date inicio, Date fin){
		Map<Integer, List<HorarioClase>> horariosMap;
		List<DetalleAsistencia> detalleList;
		List<HorarioClase> horariosList;
		List<Recuperacion> recuperaciones;
		HorarioClase horarioClase;
		DetalleAsistencia detalle;
		Asistencia asistencia;
		
		horariosMap = horarioClaseService.obtenerHorariosOrdenadosxDia(idGrupo, periodo);
		detalleList = new LinkedList<DetalleAsistencia>();
		
		if(horariosMap.size() == 0){	//-> no hay horarios registrados
			return detalleList;
		}
		while(!fin.before(inicio)){		//-> mientras fecha sea menor o igual fin
			if(horariosMap.containsKey(inicio.getDay())){
				horariosList = horariosMap.get(inicio.getDay());
				
				for(int i = 0; i < horariosList.size(); i++){
					horarioClase = horariosList.get(i);
					detalle = new DetalleAsistencia();
					asistencia = asistenciaRepository.obtenerAsistenciaxFechayIdHorario(inicio, 
																				horarioClase.getIdHorarioClase());
					detalle.setFecha(inicio.toString());
					detalle.setTipo(horarioClase.getHorarioClaseTipo());
					if(asistencia != null){		//-> hubo asistencia
						detalle.setInicio(asistencia.getHoraIngreso().toString());
						if(asistencia.getHoraIngreso().equals(asistencia.getHoraSalida())){	//-> INCOMPLETO
							detalle.setSalida("-");
							detalle.setDetalle(false);
							detalle.setTipoMarca("INCOMPLETO");
						}else{															//-> AUTOMATICO o MANUAL
							detalle.setSalida(asistencia.getHoraSalida().toString());
							detalle.setDetalle(false);	//-> falta datos para activar en MANUAL
							detalle.setTipoMarca(asistencia.getTipoMarca());
						}
						detalle.setRecuperacion("-");
					}else{						//->no asistio
						detalle.setInicio("-");
						detalle.setSalida("-");
						detalle.setTipoMarca("NO ASIS");
						
						recuperaciones = recuperacionRepository.obtenerRecuperacionesxFechaxHorario(
																		horarioClase.getIdHorarioClase(), inicio);
						if(recuperaciones.size() == 0){	//-> no hay recuperaciones
							detalle.setDetalle(false);
							detalle.setRecuperacion("NO PROGRAMADO");
						}else{
							detalle.setDetalle(true);
							if(recuperaciones.get(recuperaciones.size()-1).getEstado() == 0){
								detalle.setRecuperacion("PROGRAMADO");
							}else{
								detalle.setRecuperacion("RECUPERADO");
							}
						}//-> falta el estado NO RECUPERADO osea sin opcion a recuperar
					}
					detalleList.add(detalle);
				}
			}
			if(inicio.getDay() != 6){
				inicio.setDate(inicio.getDate() + 1);
			}else{		
				inicio.setDate(inicio.getDate() + 2);
			}
		}
		return detalleList;
	}
}
