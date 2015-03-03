package pe.edu.sistemas.siscadweb.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.siscadweb.entities.HorarioClase;
import pe.edu.sistemas.siscadweb.mappers.HorarioClaseMapper;

@Repository
public class HorarioClaseRepository {
	@Autowired
	private HorarioClaseMapper horarioClaseMapper;
	
	public List<HorarioClase> obtenerHorariosClase(){
		return horarioClaseMapper.obtenerHorariosClase();
	}
	
	public List<HorarioClase> obtenerHorariosClasexPeriodoyDia(String periodo, int dia){
		return horarioClaseMapper.obtenerHorariosClasexPeriodoyDia(periodo, dia);
	}
	
	public List<HorarioClase> obtenerHorariosClasexPeriodoxDiayHora(String periodo, int dia, String hInicio, String hFin){
		return horarioClaseMapper.obtenerHorariosClasexPeriodoxDiayHora(periodo, dia, hInicio, hFin);
	}

	
	public List<HorarioClase> obtenerHorariosClasexPeriodoxDiaxIdDocente(String periodo,int diaInicial, int diaFinal, int idDocente){
		return horarioClaseMapper.obtenerHorariosClasexPeriodoxDiaxIdDocente(periodo, diaInicial, diaFinal, idDocente);
	}
	
	public List<HorarioClase> obtenerHorariosClasexPeriodoxIdDocente(String periodo, int idDocente){
		return horarioClaseMapper.obtenerHorariosClasexPeriodoxIdDocente(periodo, idDocente);
	}

	
	public List<HorarioClase> obtenerHorariosxDiaxDocentexPeriodo(int dia,int idDocente,String periodo){
		return horarioClaseMapper.obtenerHorariosxDiaxDocentexPeriodo(dia,idDocente,periodo);
	}
	
	public List<HorarioClase> obtenerHorariosxGrupo(int grupoId){
		return horarioClaseMapper.obtenerHorariosxGrupo(grupoId);
	}
	
	public List<HorarioClase> obtenerHorariosxGrupoxDiaxPeriodo(int idGrupo, String periodo){
		return horarioClaseMapper.obtenerHorariosxGrupoxDiaxPeriodo(idGrupo, periodo);
	}
	
	public HorarioClase obtenerHorarioCasexId(int idHorarioClase){
		return horarioClaseMapper.obtenerHorarioCasexId(idHorarioClase);
	}


	public void crearNuevoHorario(int idGrupo, String tipoClase, int dia,
			int idDocente, String horaInicio, String horaFin, String periodoNombre) {
		horarioClaseMapper.crearNuevoHorario(idGrupo, tipoClase, dia, idDocente, horaInicio, horaFin, periodoNombre);
	}
}