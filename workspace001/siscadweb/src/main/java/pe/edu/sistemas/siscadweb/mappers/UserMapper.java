package pe.edu.sistemas.siscadweb.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import pe.edu.sistemas.siscadweb.entities.User;

public interface UserMapper {
	/* No olvidar colocar --> NOMBRE_COLUMNA_TABLA as NOMBRE_CAMPO_ENTITY */
	/*@Select("select u.id_usuario as id,"
			+ "p.persona_codigo_sistema as codigo,"
			+ "p.persona_password_sistema as password,"
			+ "p.id_persona as id_persona,"
			+ "r.nombre_tipo_usuario as authority "
			+ "from USUARIO u, PERSONA p, TIPO_USUARIO r "
			+ "and u.persona_id_persona = p.id_persona " 
			+ "and u.tipo_usuario_idtipo_usuario = r.idtipo_usuario "
			+ "and u.sistema_id_sistema = 4 " 
			+ "and u.usuario_activo = 1")
	List<User> findAllUser(); // Trae todo el listado de usuarios activos del sistema siscadweb (id = 4)*/

	@Select("select u.id_usuario as id,"
			+ "p.persona_codigo_sistema as codigo,"
			+ "p.persona_password_sistema as password,"
			+ "p.id_persona as id_persona,"
			+ "r.nombre_tipo_usuario as authority "
			+ "from USUARIO u, PERSONA p, TIPO_USUARIO r "
			+ "where u.persona_id_persona = p.id_persona " 
			+ "and u.tipo_usuario_idtipo_usuario = r.idtipo_usuario "
			+ "and u.sistema_id_sistema = 4 " //sistema siscadweb
			+ "and u.usuario_activo = 1 " 
			+ "and p.persona_codigo_sistema = #{cod}")
	List<User> obtenerUsuarioxCodSistema(String cod); // Trae todo el listado de usuarios activos del sistema

}
