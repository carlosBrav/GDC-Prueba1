package pe.edu.sistemas.siscadweb.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.siscadweb.entities.Grupo;
import pe.edu.sistemas.siscadweb.mappers.GrupoMapper;

@Repository
public class GrupoRepository {
	
	@Autowired
	private GrupoMapper grupoMapper;
	
	public List<Grupo> obtenerGruposxCodCursoPeriodo(int idCursoPeriodo){
		return grupoMapper.obtenerGruposxCodCursoPeriodo(idCursoPeriodo);
	}
	public Grupo ObtenergrupoxIdHorarioClase(int idHorarioClase){
		return grupoMapper.ObtenergrupoxIdHorarioClase(idHorarioClase);
	}

	public void crearGrupo(int idCurso, int nroGrupo) {		
		grupoMapper.crearGrupo(idCurso, nroGrupo);
	}
	
}
