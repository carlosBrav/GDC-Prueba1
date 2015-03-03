package pe.edu.sistemas.siscadweb.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import pe.edu.sistemas.siscadweb.entities.Motivo;

public interface MotivoMapper {
	@Select("select idmotivo as idMotivo," 
			+ "motivo_nombre as motivoNombre,"
			+ "motivo_tipo as motivoTipo "
			+ "from MOTIVO " 
			+ "where motivo_tipo = #{tipo}")
	List<Motivo> obtenerMotivos(int tipo);
}