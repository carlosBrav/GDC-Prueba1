package pe.edu.sistemas.siscadweb.repositories;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.siscadweb.entities.Periodo;
import pe.edu.sistemas.siscadweb.entities.RangoMes;
import pe.edu.sistemas.siscadweb.entities.RangoSemana;
import pe.edu.sistemas.siscadweb.mappers.PeriodoMapper;

@Repository
public class PeriodoRepository {
	@Autowired
	private PeriodoMapper periodoMapper;
	
	public List<Periodo> findAllPeriodos(){
		return periodoMapper.findAllPeriodos();
	}
	
	public RangoMes obtenerRangoMes(String ciclo){
		return periodoMapper.obtenerRangoMes(ciclo);
	}
	
	public RangoSemana obtenerRangoSemanas(String fecha){
		return periodoMapper.obtenerRangoSemanas(fecha);
	}
	
	public Periodo obtenerPeriodoxFecha(Date fecha){
		return periodoMapper.obtenerPeriodoxFecha(fecha);
	}
	
	public Periodo obtenerUltimoPeriodo(){
		return periodoMapper.obtenerUltimoPeriodo();
	}
}
