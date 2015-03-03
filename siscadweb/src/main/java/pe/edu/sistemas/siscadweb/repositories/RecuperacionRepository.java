package pe.edu.sistemas.siscadweb.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import pe.edu.sistemas.siscadweb.entities.Recuperacion;
import pe.edu.sistemas.siscadweb.mappers.RecuperacionMapper;

@Repository
public class RecuperacionRepository {
	@Autowired
	private RecuperacionMapper recuperacionMapper;
	
	public List<Recuperacion> obtenerRecuperacionesxFechaxHorario(int idHorario,Date fecha){
		return recuperacionMapper.obtenerRecuperacionesxFechaxHorario(idHorario, fecha);
	}
	
	public void insertarRecuperacion(int idHorarioClase,int idMotivo,Date fechaRec,
			Date fechaAusente,String Aula,Time horaInicio,Time horaFin,String obs,int estado){
		recuperacionMapper.insertarRecuperacion(idHorarioClase,
				idMotivo, 
				fechaRec,
				fechaAusente,
				Aula,
				horaInicio,
				horaFin,
				obs,
				estado);
	}
	
}
