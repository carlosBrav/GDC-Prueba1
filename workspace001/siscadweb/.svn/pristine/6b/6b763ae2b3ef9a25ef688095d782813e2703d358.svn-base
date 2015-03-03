package pe.edu.sistemas.siscadweb.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.siscadweb.entities.Motivo;
import pe.edu.sistemas.siscadweb.mappers.MotivoMapper;

@Repository
public class MotivoRepository {
	@Autowired
	private MotivoMapper motivoMapper;
	
	public List<Motivo> obtenerMotivos(int tipo){
		return motivoMapper.obtenerMotivos(tipo);
	}
}
