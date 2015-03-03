package pe.edu.sistemas.siscadweb.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.siscadweb.entities.CursoBase;
import pe.edu.sistemas.siscadweb.mappers.CursoBaseMapper;

@Repository
public class CursoBaseRepository {
	@Autowired
	private CursoBaseMapper cursoBaseMapper;

	
	public List<CursoBase> obtenerCursosNOAperturadosxPeriodoxPlan(int idPeriodo, int idPlan){
		return cursoBaseMapper.obtenerCursosNOAperturadosxPeriodoxPlan(idPeriodo, idPlan);
	}
	
	public List<CursoBase> obtenerCursosAperturadosxPeriodoxPlan(int idPeriodo, int idPlan){
		return cursoBaseMapper.obtenerCursosAperturadosxPeriodoxPlan(idPeriodo, idPlan);
	}
	
	public List<CursoBase> obtenerCursosxPlan(int idPlan){
		return cursoBaseMapper.obtenerCursosxPlan(idPlan);
	}
	
	public CursoBase obtenerCursoxPlanxNombre(int idPlan, String nombre){
		return cursoBaseMapper.obtenerCursoxPlanxNombre(idPlan, nombre);
	}

}
