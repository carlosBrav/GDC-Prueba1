package pe.edu.sistemas.siscadweb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.siscadweb.entities.User;
import pe.edu.sistemas.siscadweb.repositories.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> obtenerUsuarioxCodSistema(String cod){
		return userRepository.obtenerUsuarioxCodSistema(cod);
	}
}
