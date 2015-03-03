package pe.edu.sistemas.siscadweb.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pe.edu.sistemas.siscadweb.entities.User;
import pe.edu.sistemas.siscadweb.mappers.UserMapper;

@Repository
public class UserRepository {
	@Autowired
	private UserMapper userMapper;
	
	public List<User> obtenerUsuarioxCodSistema(String cod){
		return userMapper.obtenerUsuarioxCodSistema(cod);
	}
}
