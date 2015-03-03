package pe.edu.sistemas.siscadweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.stereotype.Component;

import pe.edu.sistemas.siscadweb.entities.User;
import pe.edu.sistemas.siscadweb.services.LoginService;
import pe.edu.sistemas.siscadweb.services.UserService;

@ManagedBean
@SessionScoped
@Component
public class LoginController {
	private User usuario = null;
	private String codigo = null;
	private String password = null;
	private String rememberMe = null;
	private List<String> roles = null;
	private boolean administrador = false;
	private boolean docente = false;
	private boolean atencionDocente = false;
	private boolean soporteTecnico = false;
	private boolean escuelaSistemas = false;
	private boolean escuelaSoftware = false;

	@Autowired
	private LoginService loginService;
	@Autowired
	private UserService userService;
	private Log log = LogFactory.getLog(LoginController.class);

	public String login() {
		String res = "error";
		try {
			if (loginService.login(codigo, password, rememberMe)) {
				System.out.println("usuario dif nul : " + codigo);
				List<User> roles_user = userService
						.obtenerUsuarioxCodSistema(codigo);
				if (roles_user.size() == 1) {
					usuario = roles_user.get(0);
				} else {
					roles = new ArrayList<String>();
					for (User usu : roles_user) {
						roles.add(usu.getAuthority());
					}
					usuario = roles_user.get(0);
					res = loginService.irSeleccionRoles();
					validarRoles();
					return res;
				}
				if (usuario.getAuthority().equals("ADMINISTRADOR")) {
					res = loginService.irAtencion();
				} else {
					res = loginService.irDocente();
				}
			}
		} catch (DisabledException e) {
			e.printStackTrace();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("mensajes", new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Usuario deshabilitado", null));
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("mensajes", new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"Usuario o password incorrecto", null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	private void validarRoles() {
		administrador = false;
		docente = false;
		atencionDocente = false;
		soporteTecnico = false;
		escuelaSistemas = false;
		escuelaSoftware = false;
		
		for (String rol : roles) {
			if (rol.equalsIgnoreCase("ADMINISTRADOR")) {
				setAdministrador(true);
			} else if (rol.equalsIgnoreCase("OPERADOR")) {
				setDocente(true);
			} else if (rol.equalsIgnoreCase("ATENCION DOCENTE")) {
				setAtencionDocente(true);
			} else if (rol.equalsIgnoreCase("ESCUELA DE SISTEMAS")) {
				setEscuelaSistemas(true);
			} else if (rol.equalsIgnoreCase("ESCUELA DE SOFTWARE")) {
				setEscuelaSoftware(true);
			} else if (rol.equalsIgnoreCase("SOPORTE TECNICO")) {
				setSoporteTecnico(true);
			}
		}
	}

	public String logout() {
		String res = "error";
		try {
			loginService.logout();
			res = loginService.irLogin();
			codigo = null;
		} catch (Exception e) {
			log.info("loginService = " + res);
		}
		return res;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}

	public boolean isDocente() {
		return docente;
	}

	public void setDocente(boolean docente) {
		this.docente = docente;
	}

	public boolean isAtencionDocente() {
		return atencionDocente;
	}

	public void setAtencionDocente(boolean atencionDocente) {
		this.atencionDocente = atencionDocente;
	}

	public boolean isSoporteTecnico() {
		return soporteTecnico;
	}

	public void setSoporteTecnico(boolean soporteTecnico) {
		this.soporteTecnico = soporteTecnico;
	}

	public boolean isEscuelaSistemas() {
		return escuelaSistemas;
	}

	public void setEscuelaSistemas(boolean escuelaSistemas) {
		this.escuelaSistemas = escuelaSistemas;
	}

	public boolean isEscuelaSoftware() {
		return escuelaSoftware;
	}

	public void setEscuelaSoftware(boolean escuelaSoftware) {
		this.escuelaSoftware = escuelaSoftware;
	}

	public String irAdministrador() {
		System.out.println("irAdministrador");
		try {
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(					
							"/siscadweb/atencion_docente/index_atencion.jsf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "irAdministrador";
	}

	public String irDocente() {
		System.out.println("irDocente");
		try {
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(					
							"/siscadweb/docente/index_docente.jsf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "irDocente";
	}

	public String irAtencionDocente() {
		System.out.println("irAtencionDocente");
		try {
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(					
							"/siscadweb/atencion_docente/index_atencion.jsf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "irAtencionDocente";
	}

	public String irEscuelaSistemas() {
		System.out.println("irEscuelaSistemas");
		try {
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(					
							"/siscadweb/escuela/sistemas/index_sistemas.jsf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "irEscuelaSistemas";
	}

	public String irEscuelaSoftware() {
		System.out.println("irEscuelaSoftware");
		try {
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(					
							"/siscadweb/escuela/software/index_software.jsf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "irEscuelaSoftware";
	}

	public String irSoporte() {
		System.out.println("irSoporte");
		try {
			FacesContext
					.getCurrentInstance()
					.getExternalContext()
					.redirect(					
							"/siscadweb/soporte/index_soporte.jsf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "irSoporte";
	}
}
