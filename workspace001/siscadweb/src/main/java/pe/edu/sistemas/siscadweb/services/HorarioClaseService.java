package pe.edu.sistemas.siscadweb.services;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.siscadweb.entities.HorarioClase;
import pe.edu.sistemas.siscadweb.repositories.HorarioClaseRepository;

@Service
@Transactional
public class HorarioClaseService {
	@Autowired
	private HorarioClaseRepository horarioClaseRepository;
	
	public List<HorarioClase> obtenerHorariosClase(){
		return horarioClaseRepository.obtenerHorariosClase();
	}
	
	public List<HorarioClase> obtenerHorariosClasexPeriodoyDia(String periodo, int dia){
		return horarioClaseRepository.obtenerHorariosClasexPeriodoyDia(periodo, dia);
	}
	
	public List<HorarioClase> obtenerHorariosClasexPeriodoxDiayHora(String periodo, int dia, String hInicio, String hFin){
		return horarioClaseRepository.obtenerHorariosClasexPeriodoxDiayHora(periodo, dia, hInicio, hFin);
	}
	
	public List<HorarioClase> obtenerHorariosxDiaxDocentexPeriodo(int dia,int idDocente,String periodo){
		return horarioClaseRepository.obtenerHorariosxDiaxDocentexPeriodo(dia,idDocente,periodo);
	}
	
	public List<HorarioClase> obtenerHorariosxGrupo(int grupoId){
		return horarioClaseRepository.obtenerHorariosxGrupo(grupoId);
	}
	
	public HashMap<Integer, List<HorarioClase>> obtenerHorariosOrdenadosxDia(int idGrupo, String periodo){
		HashMap<Integer, List<HorarioClase>> horarioMap;
		List<HorarioClase> horarios;
		List<HorarioClase> mapeado;
		HorarioClase horarioClase;
		
		horarioMap = new HashMap<Integer, List<HorarioClase>>();
		horarios = horarioClaseRepository.obtenerHorariosxGrupoxDiaxPeriodo(idGrupo, periodo);
		
		for(int i = 0; i < horarios.size(); i++){
			horarioClase = horarios.get(i);
			//-> 1 : Lunes ... 6 : Sabado (0 : Domingo)
			if(horarioMap.containsKey(horarioClase.getDia())){
				mapeado = horarioMap.get(horarioClase.getDia());
			}else{
				mapeado = new LinkedList<HorarioClase>();
			}
			mapeado.add(horarioClase);
			horarioMap.put(horarioClase.getDia(), mapeado);
		}		
		return horarioMap;
	}
}
