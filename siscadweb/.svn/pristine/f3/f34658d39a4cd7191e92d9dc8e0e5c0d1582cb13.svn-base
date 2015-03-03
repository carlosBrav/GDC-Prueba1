package pe.edu.sistemas.siscadweb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.siscadweb.entities.Ciclo;
import pe.edu.sistemas.siscadweb.entities.CursoBase;
import pe.edu.sistemas.siscadweb.entities.Docente;
import pe.edu.sistemas.siscadweb.entities.Grupo;
import pe.edu.sistemas.siscadweb.entities.HorarioClase;
import pe.edu.sistemas.siscadweb.entities.Periodo;
import pe.edu.sistemas.siscadweb.repositories.CursoBaseRepository;
import pe.edu.sistemas.siscadweb.repositories.CursoPeriodoRepository;
import pe.edu.sistemas.siscadweb.repositories.DocenteRepository;
import pe.edu.sistemas.siscadweb.repositories.GrupoRepository;
import pe.edu.sistemas.siscadweb.repositories.HorarioClaseRepository;
import pe.edu.sistemas.siscadweb.repositories.PeriodoRepository;

@Service
@Transactional
public class CargaCursosService {
	
	@Autowired
	private PeriodoRepository periodoRepository;
	
	@Autowired
	private CursoPeriodoRepository cursoPeriodoRepository;
	
	@Autowired
	private CursoBaseRepository cursoBaseRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private HorarioClaseRepository horarioClaseRepository;
	
	@Autowired
	private DocenteRepository docenteRepository;
	
	public Ciclo obtenerCursosxCicloActualxPlan(int ciclo, int idPlan){
		System.out.println("entro obtenerCursosxCicloActualxPlan");
		Ciclo c= new Ciclo();
		c.setCicloNombre("Ciclo " + ciclo);
		Periodo periodo = periodoRepository.obtenerUltimoPeriodo();
		System.out.println("Periodo " + periodo.getId());
		System.out.println("Ciclo " + ciclo);
		c.setCursos(cursoPeriodoRepository.obtenerCursosxPeriodoxCicloxPlan(periodo.getId(), ciclo, idPlan));
		for(int i=0; i<c.getCursos().size();i++){
			c.getCursos().get(i).setNumGrupo(cursoPeriodoRepository.obtenerCursosxIdCurso(c.getCursos().get(i).getIdCurso()).size());		
		}
		System.out.println("numero Cursos " + c.getCursos().size());
		System.out.println("salio obtenerCursosxCicloActualxPlan");
		return c;
	}
	
	public List<Ciclo> obtenerCursosActualxPlan(int idPlan){
		System.out.println("entro obtenerCursosActualxPlan");
		List<Ciclo> result = new ArrayList<Ciclo>();
		for(int i=1; i<=10; i++){
			result.add(obtenerCursosxCicloActualxPlan(i, idPlan));
		}
		result.add(obtenerCursosxCicloActualxPlan(0, idPlan));
		System.out.println("salio obtenerCursosActualxPlan");
		return result;
	}
	
	public List<CursoBase> obtenerCursosNOAperturadosxPlan(int idPlan){
		Periodo periodo = periodoRepository.obtenerUltimoPeriodo();
		List<CursoBase> result= cursoBaseRepository.obtenerCursosNOAperturadosxPeriodoxPlan(periodo.getId(), idPlan);
		return result;
	}
	
	public List<CursoBase> obtenerCursosAperturadosxPlan(int idPlan){
		Periodo periodo = periodoRepository.obtenerUltimoPeriodo();
		List<CursoBase> result= cursoBaseRepository.obtenerCursosAperturadosxPeriodoxPlan(periodo.getId(), idPlan);
		return result;
	}
	
	public List<CursoBase> obtenerCursosxPlan(int idPlan){
		List<CursoBase> result= cursoBaseRepository.obtenerCursosxPlan(idPlan);
		return result;
	}
	
	public CursoBase obtenerCursoxPlan(int idPlan, String nombre){
		CursoBase result= cursoBaseRepository.obtenerCursoxPlanxNombre(idPlan, nombre);
		return result;
	}

	public void AperturarCursosPeriodo(List<String> target) {
		Periodo periodo = periodoRepository.obtenerUltimoPeriodo();
		for(String nombre: target){
			CursoBase cb = cursoBaseRepository.obtenerCursoxPlanxNombre(4, nombre);
			cursoPeriodoRepository.registrarCursoPeriodo(nombre, cb.getCodComun(), periodo.getId());
			System.out.println("******");
			System.out.println(cb.getCursob_nombre());
			System.out.println(cb.getCodComun());
		}
	}

	public List<Grupo> obtenerGruposxCodCursoPeriodo(int idCursoPeriodo) {
		return grupoRepository.obtenerGruposxCodCursoPeriodo(idCursoPeriodo);
	}

	public void crearGrupo(int idCurso, int nroGrupo) {	
		grupoRepository.crearGrupo(idCurso, nroGrupo);
	}

	public Grupo obtenerNroGrupoACrear(int idCurso) {
		List<Grupo> grupos = obtenerGruposxCodCursoPeriodo(idCurso);
		int nroGrupo = 0;
		for(Grupo g: grupos){
			if(g.getGrupoNumero()>=nroGrupo){
				nroGrupo = g.getGrupoNumero();
			}
		}
		nroGrupo ++;
		Grupo result = new Grupo();
		result.setGrupoNumero(nroGrupo);
		return result;
	}

	public List<HorarioClase> obtenerHorariosxGrupo(int idGrupo) {
		List<HorarioClase> result = horarioClaseRepository.obtenerHorariosxGrupo(idGrupo);
		for(HorarioClase hc: result){
			hc.setDocente(docenteRepository.obtenerDocentexID(hc.getIdDocente()));
			hc.setDiaNombre(obtenerDia(hc.getDia()));
		}
		return result;
	}

	private String obtenerDia(int dia) {
		switch(dia){
		case 1: return "LUNES";
		case 2: return "MARTES";
		case 3: return "MIERCOLES";
		case 4: return "JUEVES";
		case 5: return "VIERNES";
		case 6: return "SABADO";
		case 7: return "DOMINGO";
		default: return "NN";
		}
	}

	public List<Docente> obtenerProfesores() {
		return docenteRepository.findAllDocentes();
	}

	public void crearNuevoHorario(int idGrupo, String tipoClase,
			int dia, int idDocente, String horaInicio, String horaFin) {
		horarioClaseRepository.crearNuevoHorario(idGrupo, tipoClase, dia, idDocente, horaInicio, horaFin, periodoRepository.obtenerUltimoPeriodo().getNombre());	
	}

}
