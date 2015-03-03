package pe.edu.sistemas.siscadweb.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.siscadweb.entities.Docente;
import pe.edu.sistemas.siscadweb.mappers.DocenteMapper;

@Repository
public class DocenteRepository {
	@Autowired
	private DocenteMapper docenteMapper;
	
	public List<Docente> findAllDocentes(){
		return docenteMapper.findAllDocentes();
	}
	
	public Docente obtenerDocentexID(int id){
		return docenteMapper.obtenerDocentexID(id);
	}
	
	/* YA IMPLEMENTE ESTOS METODOS Y LOS TESTEE!
	public List<Docente> ReadCodigo(String id){
		return docenteMapper.ReadCodigo(id);
	}
 	public List<Docente> SelectDocentesxOtros(String nombre, String apPaterno, String apMaterno){
 		return docenteMapper.SelectDocentesxOtros(nombre, apPaterno, apMaterno);
 	}
 	*/
	
	public Docente obtenerDocentexCodigo(String codigo){
		return docenteMapper.obtenerDocentexCodigo(codigo);
	}
	
	public List<Docente> obtenerDocentexOtro(String nombre, String apPaterno,String apMaterno){
		nombre = "%"+nombre+"%";
		apPaterno = "%"+apPaterno+"%";
		apMaterno = "%"+apMaterno+"%";
		
		return docenteMapper.obtenerDocentexOtro(nombre, apPaterno, apMaterno);
	}
	
}
