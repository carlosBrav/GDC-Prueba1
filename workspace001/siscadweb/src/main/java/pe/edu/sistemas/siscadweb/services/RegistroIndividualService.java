package pe.edu.sistemas.siscadweb.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.siscadweb.entities.Asistencia;
import pe.edu.sistemas.siscadweb.entities.CursoPeriodo;
import pe.edu.sistemas.siscadweb.entities.Docente;
import pe.edu.sistemas.siscadweb.entities.HorarioClase;
import pe.edu.sistemas.siscadweb.entities.RegistroManual;
import pe.edu.sistemas.siscadweb.repositories.AsistenciaRepository;
import pe.edu.sistemas.siscadweb.repositories.CursoPeriodoRepository;
import pe.edu.sistemas.siscadweb.repositories.DocenteRepository;
import pe.edu.sistemas.siscadweb.repositories.GrupoRepository;
import pe.edu.sistemas.siscadweb.repositories.HorarioClaseRepository;

@Service
@Transactional
public class RegistroIndividualService {
	@Autowired
	private AsistenciaRepository asistenciaRepository;
	@Autowired
	private HorarioClaseRepository horarioClaseRepository;
	@Autowired
	private DocenteRepository docenteRepository;
	@Autowired
	private CursoPeriodoRepository cursoPeriodoRepository;
	@Autowired
	private GrupoRepository grupoRepository;
	
	public List<RegistroManual> obtenerAsistencias(Date fechaInicial, Date fechaFinal,  String ciclo , int idDocente){
		Asistencia asistencia;
		CursoPeriodo curso;
		Docente docente;
		HorarioClase horarioClase;
		String entrada;
		String salida;
		String horaEntrada;
		String horaSalida;
		String tipoMarca="";
		Date fecha=null;
		
		
		ArrayList<RegistroManual> registro = new ArrayList<RegistroManual>();
		List<HorarioClase> horarios;
		/*
		if(fechaInicial.getDay() == 0 && fechaFinal.getDay()==0){
			return registro;
		}else*/
		horarios = horarioClaseRepository.obtenerHorariosClasexPeriodoxDiaxIdDocente(ciclo, fechaInicial.getDay(), fechaFinal.getDay(), idDocente);
		/*System.out.println(ciclo +  "----" + idDocente);
		System.out.println(fechaInicial.getDay() + "-----" + fechaFinal.getDay());*/
		System.out.println("aqui se muestran el tamaño : " +horarios.size());
		for(int i = 0; i < horarios.size(); i++){
			horarioClase =  horarios.get(i);
			docente = docenteRepository.obtenerDocentexID(horarioClase.getIdDocente());
			asistencia = asistenciaRepository.obtenerAsistenciasxFechaInxFechaFinyIdHorario(fechaInicial, fechaFinal,horarioClase.getIdHorarioClase());
			curso = cursoPeriodoRepository.obtenerCursosxIdGrupo(horarioClase.getIdGrupo());
			if(asistencia == null){
				entrada = "NO ASIS";
				salida = "NO ASIS";
				tipoMarca="AUTOMATICO";
				horaEntrada = horarioClase.getHoraInicio().toString().substring(0, 5); 
				horaSalida = horarioClase.getHoraFin().toString().substring(0, 5);
			}else{
				fecha=asistencia.getFecha();
				tipoMarca=asistencia.getTipoMarca();
				entrada = asistencia.getHoraIngreso().toString().substring(0, 5);
				salida = asistencia.getHoraSalida().toString().substring(0, 5);
				horaEntrada = ""; 
				horaSalida = "";
			}
			/*System.out.println("anteeeeeeeeeeeeeeeeeeeees");
			System.out.println(docente.getCodigo());
			System.out.println(docente.getNombre() + " " + docente.getapPaterno() + " " + docente.getapMaterno());
			System.out.println(curso.getNombre());
			System.out.println(horarioClase.getHorarioClaseTipo());
			System.out.println(entrada);
			System.out.println(salida);
			System.out.println(horarioClase.getIdHorarioClase());
			System.out.println(horaEntrada);
			System.out.println(horaSalida);
			System.out.println(curso.getNumGrupo());*/
			registro.add(new RegistroManual(docente.getCodigo(),
								docente.getNombre() + " " + docente.getapPaterno() + " " + docente.getapMaterno(),
								curso.getNombre(),
								horarioClase.getHorarioClaseTipo(),
								entrada,
								salida,
								horarioClase.getIdHorarioClase(),
								horaEntrada,
								horaSalida,
								curso.getNumGrupo(),
								tipoMarca,
								fecha
								
						)
			);
			//System.out.println("despuesssssssssssssssss");
		}
		return registro;
	}
	
	/*
	public ArrayList<RegistroManual> obtenerAsistenciasxDocenteyFecha(int id, Date fechaInicio, Date fechaFin, String ciclo) {
		
		//fecha = new Date(112,8,1); //1 de setiembre del 2012
		ArrayList<RegistroManual> registros = new ArrayList<RegistroManual>();
		String curso;
		
		int dia;
		long diferenciaDias;
		boolean encontroAsistencia=false;
		
		Calendar fechaInicial = Calendar.getInstance();
		Calendar fechaFinal = Calendar.getInstance();
		
		fechaInicial.setTime(fechaInicio);
		fechaFinal.setTime(fechaFin);
		
		//HorarioClaseDao horarioDao = new HorarioClaseDaoImpl();
		ArrayList<HorarioClase> horarios = new ArrayList<HorarioClase>();
		
		//AsistenciaDao asistenciaDao = new AsistenciaDaoImpl();
		List<Asistencia> asis = new ArrayList<Asistencia>();
		
		diferenciaDias=(fechaFinal.get(Calendar.DAY_OF_YEAR) - fechaInicial.get(Calendar.DAY_OF_YEAR));
		
		System.out.println("dif dias: " + diferenciaDias);
		
		//if(diferenciaDias == 0) diferenciaDias = 1;
		diferenciaDias = diferenciaDias + 1;
		
		int idHorario = 0;
		String tipo = "", tEntrada="", tSalida="", fecha="", tipoMarca="", thorEntrada = "", thorSalida = "";
		
		for(int i=0;i<diferenciaDias;i++){
			//asis=asistenciaDao.SelectAsistenciaxFechaxDocente(id, fechaInicial.getTime());
			asis=asistenciaRepository.obtenerAsistenciasxFechaxDocente(id, fechaInicial.getTime());
			dia=fechaInicial.getTime().getDay();//equivalente a Calendar.DAY_OF_WEEK
			//horarios = (ArrayList<HorarioClase>) horarioDao.getHorarioClasexIdDocentexDiaxPeriodo(id, dia, ciclo);
			horarios= (ArrayList<HorarioClase>) horarioClaseRepository.obtenerHorariosxDiaxDocentexPeriodo(dia, id, ciclo);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			fecha = sdf.format(fechaInicial.getTime());
			if(horarios!=null){
				System.out.println("Existen horarios");
				if(asis!=null){
					System.out.println("Existen asistencias");
					for(int j=0;j<horarios.size();j++){
						encontroAsistencia=false;
						for(int k=0;k<asis.size();k++){
							//idHorario = horarios.get(j).getIdhorarioClase();
							idHorario=horarios.get(j).getIdHorarioClase();
							
							if((encontroAsistencia==false) && (horarios.get(j).getIdHorarioClase()==asis.get(k).getIdHorarioClase()) ){
								encontroAsistencia=true;
								
								System.out.println("Encontro asistencia =) id: " + asis.get(k).getIdAsistencia() + " del horario: " + idHorario);
								
								//curso = asis.get(k).getHorarioClase().getGrupo().getCursoPeriodo().getCursoPeriodoNombre();
								
								curso = cursoPeriodoRepository.obtenerCursoxHorarioClase(asis.get(k).getIdHorarioClase()).getNombre();
								//tipo = asis.get(k).getHorarioClase().getHorarioClaseTipo();
								tipo= horarioClaseRepository.obtenerHorarioCasexId(asis.get(k).getIdHorarioClase()).getHorarioClaseTipo();
								
								Formatter fmt = new Formatter();
								Formatter fmt2 = new Formatter();
								
								int hEntrada = asis.get(k).getHoraIngreso().getHours();
								fmt.format("%02d",hEntrada);
								int minEntrada = asis.get(k).getHoraIngreso().getMinutes();
								fmt2.format("%02d",minEntrada);
								tEntrada = fmt.toString()+":"+fmt2.toString();

								Formatter fmt3 = new Formatter();
								Formatter fmt4 = new Formatter();

								int hSalida = asis.get(k).getHoraSalida().getHours();
								fmt3.format("%02d",hSalida);
								int minSalida = asis.get(k).getHoraSalida().getMinutes();
								fmt4.format("%02d",minSalida);
								tSalida = fmt3.toString()+":"+fmt4.toString();
								
								Formatter fmt5 = new Formatter();
								Formatter fmt6 = new Formatter();
								
								int horEntrada = horarios.get(j).getHoraInicio().getHours();
								fmt5.format("%02d",horEntrada);
								int minhorEntrada = horarios.get(j).getHoraInicio().getMinutes();
								fmt6.format("%02d",minhorEntrada);
								thorEntrada = fmt5.toString()+":"+fmt6.toString();

								Formatter fmt7 = new Formatter();
								Formatter fmt8 = new Formatter();

								int horSalida = horarios.get(j).getHoraFin().getHours();
								fmt7.format("%02d",horSalida);
								int minhorSalida = horarios.get(j).getHoraFin().getMinutes();
								fmt8.format("%02d",minhorSalida);
								thorSalida = fmt7.toString()+":"+fmt8.toString();

								tipoMarca = asis.get(k).getTipoMarca();
								
								RegistroManual reg = new RegistroManual();
								reg.setNomCurso(curso);
								reg.setTipo(tipo);
								reg.setEntrada(tEntrada);
								reg.setSalida(tSalida);
								reg.setFecha(fecha);
								reg.setIdHorario(idHorario);
								reg.setTipoMarca(tipoMarca);
								reg.setHoraEntrada(thorEntrada);
								reg.setHoraSalida(thorSalida);
								
								// reg.setGrupo(horarioService.obtenernumerogrupoxHorario(horarios.get(j).getidHorario))	
								//reg.setGrupo(horarios.get(j).getGrupo().getGrupoNumero());
								reg.setGrupo( grupoRepository.ObtenergrupoxIdHorarioClase(horarios.get(j).getIdHorarioClase()).getGrupoNumero());
								
								registros.add(reg);
							}							
						}
						if(!encontroAsistencia){
							System.out.println("No Encontro asistencia =( del docente: " + id);
							//curso=horarioService.obtenernombreCursoxhorario(horarios.get(j).getidHorario);
							//curso = horarios.get(j).getGrupo().getCursoPeriodo().getCursoPeriodoNombre();
							curso=horarioClaseService.obtenernombreCursoxhorario(horarios.get(j).getIdHorarioClase());
							tipo = horarios.get(j).getHorarioClaseTipo();
							tipoMarca = "SIN MARCA";
							
							RegistroManual reg = new RegistroManual();
							reg.setNomCurso(curso);
							reg.setTipo(tipo);
							reg.setEntrada("NO ASIS");
							reg.setSalida("NO ASIS");
							reg.setFecha(fecha);
							reg.setIdHorario(idHorario);
							reg.setTipoMarca(tipoMarca);
							
							Formatter fmt = new Formatter();
							Formatter fmt2 = new Formatter();
							
							int hEntrada = horarios.get(j).getHoraInicio().getHours();
							fmt.format("%02d",hEntrada);
							int minEntrada = horarios.get(j).getHoraInicio().getMinutes();
							fmt2.format("%02d",minEntrada);
							tEntrada = fmt.toString()+":"+fmt2.toString();

							Formatter fmt3 = new Formatter();
							Formatter fmt4 = new Formatter();

							int hSalida = horarios.get(j).getHoraFin().getHours();
							fmt3.format("%02d",hSalida);
							int minSalida = horarios.get(j).getHoraFin().getMinutes();
							fmt4.format("%02d",minSalida);
							tSalida = fmt3.toString()+":"+fmt4.toString();
							
							reg.setHoraEntrada(tEntrada);
							reg.setHoraSalida(tSalida);
							
							//reg.setGrupo(horarios.get(j).getGrupo().getGrupoNumero());
							reg.setGrupo(horarios.get(j).getIdGrupo());
							registros.add(reg);
						}
					}
				}
				else{
					System.out.println("No existen asistencias pero si horarios...");
					for(int j=0;j<horarios.size();j++){
						idHorario = horarios.get(j).getIdHorarioClase();
						curso = horarios.get(j).getGrupo().getCursoPeriodo().getCursoPeriodoNombre();
						tipo = horarios.get(j).getHorarioClaseTipo();
						tipoMarca = "SIN MARCA";
						
						RegistroManual reg = new RegistroManual();
						reg.setNomCurso(curso);
						reg.setTipo(tipo);
						reg.setEntrada("NO ASIS");
						reg.setSalida("NO ASIS");
						reg.setFecha(fecha);
						reg.setIdHorario(idHorario);
						reg.setTipoMarca(tipoMarca);
						
						Formatter fmt = new Formatter();
						Formatter fmt2 = new Formatter();
						
						int hEntrada = horarios.get(j).getHoraInicio().getHours();
						fmt.format("%02d",hEntrada);
						int minEntrada = horarios.get(j).getHoraInicio().getMinutes();
						fmt2.format("%02d",minEntrada);
						tEntrada = fmt.toString()+":"+fmt2.toString();

						Formatter fmt3 = new Formatter();
						Formatter fmt4 = new Formatter();

						int hSalida = horarios.get(j).getHoraFin().getHours();
						fmt3.format("%02d",hSalida);
						int minSalida = horarios.get(j).getHoraFin().getMinutes();
						fmt4.format("%02d",minSalida);
						tSalida = fmt3.toString()+":"+fmt4.toString();
						
						reg.setHoraEntrada(tEntrada);
						reg.setHoraSalida(tSalida);
						
						//reg.setGrupo(horarios.get(j).getGrupo().getGrupoNumero());
						reg.setGrupo(horarios.get(j).getIdGrupo());
						registros.add(reg);
					}
				}
			}
			fechaInicial.add(Calendar.DAY_OF_MONTH,1);
		}
		
		return registros;
	}*/


}
