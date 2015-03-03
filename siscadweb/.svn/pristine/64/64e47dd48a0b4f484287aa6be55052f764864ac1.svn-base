package pe.edu.sistemas.siscadweb.services;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.siscadweb.entities.Asistencia;
import pe.edu.sistemas.siscadweb.entities.CursoPeriodo;
import pe.edu.sistemas.siscadweb.entities.Docente;
import pe.edu.sistemas.siscadweb.entities.HorarioClase;
import pe.edu.sistemas.siscadweb.entities.Motivo;
import pe.edu.sistemas.siscadweb.entities.Periodo;
import pe.edu.sistemas.siscadweb.entities.Recuperacion;
import pe.edu.sistemas.siscadweb.entities.RegistroRecuperacion;
import pe.edu.sistemas.siscadweb.repositories.AsistenciaRepository;
import pe.edu.sistemas.siscadweb.repositories.CursoPeriodoRepository;
import pe.edu.sistemas.siscadweb.repositories.DocenteRepository;
import pe.edu.sistemas.siscadweb.repositories.HorarioClaseRepository;
import pe.edu.sistemas.siscadweb.repositories.MotivoRepository;
import pe.edu.sistemas.siscadweb.repositories.PeriodoRepository;
import pe.edu.sistemas.siscadweb.repositories.RecuperacionRepository;

@Service
@Transactional
public class RecuperacionService {

	@Autowired
	private AsistenciaRepository asistenciaRepository;
	@Autowired
	private HorarioClaseRepository horarioClaseRepository;
	@Autowired
	private DocenteRepository docenteRepository;
	@Autowired
	private PeriodoRepository periodoRepository;
	@Autowired
	private CursoPeriodoRepository cursoPeriodoRepository;
	
	@Autowired
	private RecuperacionRepository recuperacionRepository;	
	@Autowired
	private MotivoRepository motivoRepository;
	
	public ArrayList<RegistroRecuperacion> ObtenerFaltasxDocente(int idDocente) {
		ArrayList<RegistroRecuperacion> registros = new ArrayList<RegistroRecuperacion>();

		int encontroAsistencia = 0;
		// 0 no hay asistencia
		// 1 tardanza
		// 2 salio antes
		// 3 asistencia dentro de lo permitido
		Calendar fechaActual = Calendar.getInstance();
		Calendar fechaModif = Calendar.getInstance();
		Periodo p = periodoRepository.obtenerUltimoPeriodo();
		Docente docente=docenteRepository.obtenerDocentexID(idDocente);
		CursoPeriodo curso;
		// DATOS
		ArrayList<Asistencia> auxAsistencias = new ArrayList<Asistencia>();
		ArrayList<HorarioClase> horarios = new ArrayList<HorarioClase>();
		ArrayList<Recuperacion> recuperaciones =new ArrayList<Recuperacion>();
		fechaModif.setTime(fechaInicio(fechaActual, p));
		System.out.println("FECHA INICIO + " + fechaInicio(fechaActual, p));
		// ITERACIONES DE FECHAS
		int dia;
		while (fechaModif.before(fechaActual)) {
			Date fechaLim = fechaLimite(fechaModif);
			auxAsistencias = (ArrayList<Asistencia>) asistenciaRepository
					.obtenerAsistenciasxFechaxDocentexPeriodo(
							retornarSQLDate(fechaModif), idDocente, p.getNombre());
			dia = fechaModif.get(Calendar.DAY_OF_WEEK) - 1;
			horarios = (ArrayList<HorarioClase>) horarioClaseRepository
					.obtenerHorariosxDiaxDocentexPeriodo(dia, idDocente,
							p.getNombre());
			System.out.println("FECHA INICIO + " + fechaLim.toString());
			if (horarios.size() > 0) {
				if (auxAsistencias.size() > 0) {
					for (int j = 0; j < horarios.size(); j++) {
						curso = cursoPeriodoRepository.obtenerCursosxIdGrupo(horarios.get(j).getIdGrupo());
						int minutosDeudaTardanza = 0;
						int minutosDeudaSalidaAntes = 0;
						int minutosDeudaFinal = 0;
						encontroAsistencia = 0;
						int k = 0;
						// for (int k = 0; k < auxAsistencias.size(); k++) {
						while ((encontroAsistencia == 0) && k < auxAsistencias.size()) {
							if ((horarios.get(j).getIdHorarioClase() == auxAsistencias
									.get(k).getIdHorarioClase())) { // encontro
																	// asistencia
								if (!auxAsistencias.get(k).getTipoMarca()
										.equals("RECUPERACION")) {
									 System.out.println(" ");
                                     HorarioClase horario = horarios.get(j);
                                     Time horaInicioMarcada = auxAsistencias.get(k).getHoraIngreso();
                                     Time horaFinMarcada = auxAsistencias.get(k).getHoraSalida();
                                     Time horaInicioReal = horario.getHoraInicio();
                                     Time horaFinReal = horario.getHoraFin();
                                    
                                     int minutosHoraInicioMarcada = horaInicioMarcada.getHours() * 60 + horaInicioMarcada.getMinutes();
                                     int minutosHoraInicioReal = horaInicioReal.getHours() * 60 + horaInicioReal.getMinutes();
                                     int minutosHoraFinMarcada = horaFinMarcada.getHours() * 60 + horaFinMarcada.getMinutes();
                                     int minutosHoraFinReal = horaFinReal.getHours() * 60 + horaFinReal.getMinutes();
                                     int difMinutosTardanza = minutosHoraInicioMarcada - minutosHoraInicioReal;
                                     int difMinutosSalidaAntes = minutosHoraFinReal - minutosHoraFinMarcada;
                                     
                                     if (horaInicioMarcada.compareTo(horaFinMarcada) == 0) {//Si tiene misma hora de entrada y salida se considera solo su tardanza
                                         System.out.println("Hora inicio igual hora fin "+ retornarSQLDate(fechaModif));
                                         if (difMinutosTardanza > 15) {//Si la diferencia de minutos es mayor a 15 minutos se considera tardanza y guardamos los minutos tardes
                                             minutosDeudaTardanza = difMinutosTardanza - 15;
                                             minutosDeudaFinal = minutosDeudaTardanza;
                                             encontroAsistencia = 1;
                                         } else {//Al lo considerar salida, no debe nada
                                             encontroAsistencia = 3;
                                         }
                                     } else {//Si hay marcacion de salida veremos la tardanza y si salio antes
                                         if (difMinutosTardanza > 15) {//Si la diferencia de minutos es mayor a 15 minutos se considera tardanza y guardamos los minutos tardes
                                             minutosDeudaTardanza = difMinutosTardanza - 15;
                                             encontroAsistencia = 1;
                                         }
                                         if (difMinutosSalidaAntes > 5) {//Si es mayor a lo minimo que pudo marcar entonces salio antes
                                             //guardamos los minutos deuda
                                             minutosDeudaSalidaAntes = difMinutosSalidaAntes - 5;
                                             encontroAsistencia = 2;
                                         }
                                         if (encontroAsistencia == 0) {
                                             encontroAsistencia = 3;//no debe nada
                                         }
                                         minutosDeudaFinal = minutosDeudaTardanza + minutosDeudaSalidaAntes;
                                     }
								} else {
									// Si es de recuperacion se considera como
									// asistido
									encontroAsistencia = 3;
								}
								if (encontroAsistencia != 3) {
									System.out.println("DIFERENTE DE  3  --->"+ encontroAsistencia);
									Date fa = this.retornarSQLDate(fechaModif);
									RegistroRecuperacion recup = new RegistroRecuperacion();
	                                recup.setCode(""+docente.getCodigo());
	                                recup.setFecha(fa.toString());
	                                recup.setIdHorario(horarios.get(j).getIdHorarioClase());
	                                recup.setName(""+docente.getapPaterno()+" "+docente.getapMaterno()+" , "+docente.getNombre());	                                
	                                recup.setCurso(curso.getNombre());
	                                recup.setTipo(horarios.get(j).getHorarioClaseTipo());
	                                if (encontroAsistencia == 0) {
	                                	recup.setTipoDeuda("INASISTENCIA");
	                                	recuperaciones=(ArrayList<Recuperacion>) recuperacionRepository.obtenerRecuperacionesxFechaxHorario(horarios.get(j).getIdHorarioClase(),fa);
	                                	if (recuperaciones.size() > 0) {
	                                		System.out.println("size: " + recuperaciones.size());
	                                        if (recuperaciones.size() > 1) {
	                                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

	                                            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

	                                            if (recuperaciones.get(0).getEstado() == 1 || recuperaciones.get(1).getEstado() == 1) {
	                                                recup.setEstado("EN CURSO");
	                                                System.out.println("en curso");
	                                            } else {
	                                                if (recuperaciones.get(0).getEstado() == 2 && recuperaciones.get(1).getEstado() == 2) {
	                                                    recup.setEstado("RECUPERADO");
	                                                    System.out.println("recuperado");
	                                                } else {
	                                                    if (recuperaciones.get(0).getEstado() == 0 && recuperaciones.get(1).getEstado() == 0) {
	                                                        recup.setEstado("PROGRAMADO");
	                                                        System.out.println("programado");
	                                                    }
	                                                }
	                                            }


	                                            recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getFechaRecuperacion()) + "/" + sdf1.format(recuperaciones.get(1).getFechaRecuperacion()));
	                                            recup.setAula(recuperaciones.get(0).getAula() + "/" + recuperaciones.get(1).getAula());
	                                            recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getHoraInicio()) + "/" + sdf2.format(recuperaciones.get(1).getHoraInicio()));
	                                            recup.setHoraFin(sdf2.format(recuperaciones.get(0).getHoraFin()) + "/" + sdf2.format(recuperaciones.get(1).getHoraFin()));
	                                        } else {
	                                            if (recuperaciones.get(0).getEstado() == 0) {
	                                                recup.setEstado("PROGRAMADO");
	                                            }
	                                            if (recuperaciones.get(0).getEstado() == 1) {
	                                                recup.setEstado("EN CURSO");
	                                            }
	                                            if (recuperaciones.get(0).getEstado() == 2) {
	                                                recup.setEstado("RECUPERADO");
	                                            }

	                                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

	                                            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");


	                                            recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getFechaRecuperacion()));
	                                            recup.setAula(recuperaciones.get(0).getAula());
	                                            recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getHoraInicio()));
	                                            recup.setHoraFin(sdf2.format(recuperaciones.get(0).getHoraFin()));
	                                        }
	                                	}else {
	                                        recup.setEstado("NO PROGRAMADO");
	                                        System.out.println("no programado");
	                                    }
	                                	HorarioClase horario = horarios.get(j);
	                                    Time horaInicioReal = horario.getHoraInicio();
	                                    Time horaFinReal = horario.getHoraFin();
	                                    int minutosHoraInicioReal = horaInicioReal.getHours() * 60 + horaInicioReal.getMinutes();
	                                    int minutosHoraFinReal = horaFinReal.getHours() * 60 + horaFinReal.getMinutes();
	                                    int difMinutosReal = minutosHoraFinReal - minutosHoraInicioReal;

	                                    recup.setMinutosDeuda(difMinutosReal);

	                                    recup.setFechaLimite(fechaLim.toString());

	                                    if (fechaLim.before(fechaActual.getTime()) && !recup.getEstado().equals("RECUPERADO")) {
	                                        System.out.println("fecha limite antes de fecha actual");
	                                        System.out.println("fecha limite: " + fechaLim.toString());
	                                        System.out.println("fecha actual: " + fechaActual.toString());

	                                        recup.setEstado("NO RECUPERADO");
	                                    }


	                                   registros.add(recup);

	                                    System.out.println("inasistencia");
	                                }
	                                else {
	                                	if (encontroAsistencia == 1) {//tardanza
	                                        recup.setTipoDeuda("TARDANZA");
	                                        recup.setMinutosDeuda(minutosDeudaFinal);
	                                        recuperaciones=(ArrayList<Recuperacion>) recuperacionRepository.obtenerRecuperacionesxFechaxHorario(horarios.get(j).getIdHorarioClase(),fa);
	                                        if (recuperaciones.size() > 0) {
	                                            if (recuperaciones.size() > 1) {
	                                                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

	                                                SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

	                                                boolean encontro = false;
	                                                if (recuperaciones.get(0).getEstado() == 1 || recuperaciones.get(1).getEstado() == 1) {
	                                                    recup.setEstado("EN CURSO");
	                                                } else {
	                                                    if (recuperaciones.get(0).getEstado() == 2 && recuperaciones.get(1).getEstado() == 2) {
	                                                        recup.setEstado("RECUPERADO");
	                                                    } else {
	                                                        if (recuperaciones.get(0).getEstado() == 0 && recuperaciones.get(1).getEstado() == 0) {
	                                                            recup.setEstado("PROGRAMADO");
	                                                        }
	                                                    }
	                                                }
	                                                recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getFechaRecuperacion()) + "/" + sdf1.format(recuperaciones.get(1).getFechaRecuperacion()));
	                                                recup.setAula(recuperaciones.get(0).getAula() + "/" + recuperaciones.get(1).getAula());
	                                                recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getHoraInicio()) + "/" + sdf2.format(recuperaciones.get(1).getHoraInicio()));
	                                                recup.setHoraFin(sdf2.format(recuperaciones.get(0).getHoraFin()) + "/" + sdf2.format(recuperaciones.get(1).getHoraFin()));
	                                            } else {
	                                                if (recuperaciones.get(0).getEstado() == 0) {
	                                                    recup.setEstado("PROGRAMADO");
	                                                }
	                                                if (recuperaciones.get(0).getEstado() == 1) {
	                                                    recup.setEstado("EN CURSO");
	                                                }
	                                                if (recuperaciones.get(0).getEstado() == 2) {
	                                                    recup.setEstado("RECUPERADO");
	                                                }

	                                                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

	                                                SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

	                                                recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getFechaRecuperacion()));
	                                                recup.setAula(recuperaciones.get(0).getAula());
	                                                recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getHoraInicio()));
	                                                recup.setHoraFin(sdf2.format(recuperaciones.get(0).getHoraFin()));
	                                            }
	                                        } else {
	                                            recup.setEstado("NO PROGRAMADO");
	                                        }

	                                        recup.setFechaLimite(fechaLim.toString());

	                                        if (fechaLim.before(fechaActual.getTime()) && !recup.getEstado().equals("RECUPERADO")) {
	                                            System.out.println("fecha limite antes de fecha actual");
	                                            System.out.println("fecha limite: " + fechaLim.toString());
	                                            System.out.println("fecha actual: " + fechaActual.toString());

	                                            recup.setEstado("NO RECUPERADO");
	                                        }

	                                        registros.add(recup);
	                                        System.out.println("tardanza");
	                                    }else{
	                                    	if (encontroAsistencia == 2) {//salio antes
	                                            recup.setTipoDeuda("INCOMPLETO");
	                                            recup.setMinutosDeuda(minutosDeudaFinal);

	                                            recuperaciones=(ArrayList<Recuperacion>) recuperacionRepository.obtenerRecuperacionesxFechaxHorario(horarios.get(j).getIdHorarioClase(),fa);
	    	                                	if (recuperaciones.size() > 0) {
	                                                if (recuperaciones.size() > 1) {
	                                                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

	                                                    SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");


	                                                    boolean encontro = false;
	                                                    if (recuperaciones.get(0).getEstado() == 1 || recuperaciones.get(1).getEstado() == 1) {
	                                                        recup.setEstado("EN CURSO");
	                                                    } else {
	                                                        if (recuperaciones.get(0).getEstado() == 2 && recuperaciones.get(1).getEstado() == 2) {
	                                                            recup.setEstado("RECUPERADO");
	                                                        } else {
	                                                            if (recuperaciones.get(0).getEstado() == 0 && recuperaciones.get(1).getEstado() == 0) {
	                                                                recup.setEstado("PROGRAMADO");
	                                                            }
	                                                        }
	                                                    }
	                                                    recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getFechaRecuperacion()) + "/" + sdf1.format(recuperaciones.get(1).getFechaRecuperacion()));
	                                                    recup.setAula(recuperaciones.get(0).getAula() + "/" + recuperaciones.get(1).getAula());
	                                                    recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getHoraInicio()) + "/" + sdf2.format(recuperaciones.get(1).getHoraInicio()));
	                                                    recup.setHoraFin(sdf2.format(recuperaciones.get(0).getHoraFin()) + "/" + sdf2.format(recuperaciones.get(1).getHoraFin()));
	                                                } else {
	                                                    if (recuperaciones.get(0).getEstado() == 0) {
	                                                        recup.setEstado("PROGRAMADO");
	                                                    }
	                                                    if (recuperaciones.get(0).getEstado() == 1) {
	                                                        recup.setEstado("EN CURSO");
	                                                    }
	                                                    if (recuperaciones.get(0).getEstado() == 2) {
	                                                        recup.setEstado("RECUPERADO");
	                                                    }

	                                                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

	                                                    SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");


	                                                    recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getFechaRecuperacion()));
	                                                    recup.setAula(recuperaciones.get(0).getAula());
	                                                    recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getHoraInicio()));
	                                                    recup.setHoraFin(sdf2.format(recuperaciones.get(0).getHoraFin()));
	                                                }
	                                            } else {
	                                                recup.setEstado("NO PROGRAMADO");
	                                            }

	                                            recup.setFechaLimite(fechaLim.toString());

	                                            if (fechaLim.before(fechaActual.getTime()) && !recup.getEstado().equals("RECUPERADO")) {
	                                                System.out.println("fecha limite antes de fecha actual");
	                                                System.out.println("fecha limite: " + fechaLim.toString());
	                                                System.out.println("fecha actual: " + fechaActual.toString());

	                                                recup.setEstado("NO RECUPERADO");
	                                            }

	                                            registros.add(recup);
	                                            System.out.println("incompleto");
	                                        }
	                                    }
	                                	
	                                }
								}
							}
							k++;
						}
					}
				} else {// no hay asistencias para este dia
					for (int j = 0; j < horarios.size(); j++) {
						curso = cursoPeriodoRepository.obtenerCursosxIdGrupo(horarios.get(j).getIdGrupo());
                        System.out.println("Existen Inasistencias");
                        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date fa = this.retornarSQLDate(fechaModif);
                        System.out.println("Existen Inasistencias");
                        RegistroRecuperacion recup = new RegistroRecuperacion();
                        recup.setCode(""+docente.getCodigo());
                        recup.setFecha(fa.toString());
                        recup.setIdHorario(horarios.get(j).getIdHorarioClase());
                        recup.setName(docente.getapPaterno()+" "+docente.getapMaterno()+" "+docente.getNombre());
                        recup.setCurso(curso.getNombre());
                        recup.setTipo(horarios.get(j).getHorarioClaseTipo());
                        recup.setTipoDeuda("INASISTENCIA");

                        HorarioClase horario = horarios.get(j);
                        Time horaInicioReal = horario.getHoraInicio();
                        Time horaFinReal = horario.getHoraFin();
                        int minutosHoraInicioReal = horaInicioReal.getHours() * 60 + horaInicioReal.getMinutes();
                        int minutosHoraFinReal = horaFinReal.getHours() * 60 + horaFinReal.getMinutes();
                        int difMinutosReal = minutosHoraFinReal - minutosHoraInicioReal;

                        recup.setMinutosDeuda(difMinutosReal);

                        recuperaciones=(ArrayList<Recuperacion>) recuperacionRepository.obtenerRecuperacionesxFechaxHorario(horarios.get(j).getIdHorarioClase(),fa);
                        if (recuperaciones.size() > 0) {
                            System.out.println("size: " + recuperaciones.size());
                            if (recuperaciones.size() > 1) {
                                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

                                SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

                                boolean encontro = false;
                                if (recuperaciones.get(0).getEstado() == 1 || recuperaciones.get(1).getEstado() == 1) {
                                    recup.setEstado("EN CURSO");
                                    System.out.println("en curso");
                                    recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getFechaRecuperacion()) + "/" + sdf1.format(recuperaciones.get(1).getFechaRecuperacion()));
                                    recup.setAula(recuperaciones.get(0).getAula() + "/" + recuperaciones.get(1).getAula());
                                    recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getHoraInicio()) + "/" + sdf2.format(recuperaciones.get(1).getHoraInicio()));
                                    recup.setHoraFin(sdf2.format(recuperaciones.get(0).getHoraFin()) + "/" + sdf2.format(recuperaciones.get(1).getHoraFin()));
                                    //recup.setFechaProgramada(""+recuperaciones.get(0).getRecuperacionFechaRecuperacion() + "-" + recuperaciones.get(1).getRecuperacionFechaRecuperacion());
                                } else {
                                    if (recuperaciones.get(0).getEstado() == 2 && recuperaciones.get(1).getEstado() == 2) {
                                        recup.setEstado("RECUPERADO");
                                        System.out.println("recuperado");

                                    } else {
                                        if (recuperaciones.get(0).getEstado() == 0 && recuperaciones.get(1).getEstado() == 0) {
                                            recup.setEstado("PROGRAMADO");
                                            System.out.println("programado");

                                        }
                                    }
                                }
                                recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getFechaRecuperacion()) + "/" + sdf1.format(recuperaciones.get(1).getFechaRecuperacion()));
                                recup.setAula(recuperaciones.get(0).getAula() + "/" + recuperaciones.get(1).getAula());
                                System.out.println("holalalalala: "+sdf2.format(recuperaciones.get(0).getHoraInicio()));
                                System.out.println("holalalalala123: "+sdf2.format(recuperaciones.get(1).getHoraInicio()));
                                System.out.println(sdf2.format(recuperaciones.get(0).getHoraInicio()) + "/" + sdf2.format(recuperaciones.get(1).getHoraInicio()));
                                recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getHoraInicio()) + "/" + sdf2.format(recuperaciones.get(1).getHoraInicio()));
                                System.out.println("recuperacionasdflkjasdñfkjasdlñfjkaslñdfjkasdfkl: "+recup.getHoraInicio());
                                recup.setHoraFin(sdf2.format(recuperaciones.get(0).getHoraFin()) + "/" + sdf2.format(recuperaciones.get(1).getHoraFin()));
                            } else {
                            	System.out.println("estado: " + recuperaciones.get(0).getEstado());
                                if (recuperaciones.get(0).getEstado() == 0) {
                                    recup.setEstado("PROGRAMADO");
                                }
                                if (recuperaciones.get(0).getEstado() == 1) {
                                    recup.setEstado("EN CURSO");
                                }
                                if (recuperaciones.get(0).getEstado() == 2) {
                                    recup.setEstado("RECUPERADO");
                                }
                                System.out.println("estado: " + recuperaciones.get(0).getEstado());
                                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

                                SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

                                System.out.println("AULA : " + recuperaciones.get(0).getAula());
                                System.out.println("HORA INICIO : " + recuperaciones.get(0).getHoraInicio());
                                System.out.println("FECHA DE RECUPERACION : " + recuperaciones.get(0).getFechaRecuperacion());
                                System.out.println("FECHA DE RECUPERACION : "+ recuperaciones.get(0).getHoraFin());
                                
                                recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getFechaRecuperacion()));
                                recup.setAula(recuperaciones.get(0).getAula());
                                recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getHoraInicio()));
                                recup.setHoraFin(sdf2.format(recuperaciones.get(0).getHoraFin()));
                               
                            }
                        } else {
                            recup.setEstado("NO PROGRAMADO");
                            System.out.println("no programado");
                        }

                        recup.setFechaLimite(fechaLim.toString());
                        System.out.println("FECHA LIMITE : " + fechaLim.toString());	
                        if (fechaLim.before(fechaActual.getTime()) && !recup.getEstado().equals("RECUPERADO")) {
                            System.out.println("fecha limite antes de fecha actual");
                            System.out.println("fecha limite: " + fechaLim.toString());
                            System.out.println("fecha actual: " + fechaActual.toString());

                            recup.setEstado("NO RECUPERADO");
                        }

                        registros.add(recup);

                        System.out.println("inasistencia en else");
                    }
				}
			}
			fechaModif.add(Calendar.DAY_OF_MONTH, 1);
		}
		return registros;
	}

	Date fechaInicio(Calendar f, Periodo p) {
		int mes = f.get(Calendar.MONTH);// 0-11
		int annio = f.get(Calendar.YEAR);
		int dia = f.get(Calendar.DAY_OF_MONTH);
		Date inicioCalculado;
		if (mes <= 0) {
			mes = 12;
			annio--;
		}
		if (dia > 8 && dia < 24) {
			inicioCalculado = Date
					.valueOf(retornaCadenaFecha(annio, mes, "24"));
		} else {
			inicioCalculado = Date
					.valueOf(retornaCadenaFecha(annio, mes, "09"));
		}

		Date inicioCiclo = Date.valueOf(p.getFechaInicio());
		System.out.println("FECHA INICIO CICLO + " + inicioCiclo);
		if (inicioCalculado.before(inicioCiclo))
			return inicioCiclo;
		return inicioCalculado;
	}

	Date fechaLimite(Calendar c) {
		int mes = c.get(Calendar.MONTH);// 0-11
		mes++;
		int annio = c.get(Calendar.YEAR);
		int dia = c.get(Calendar.DAY_OF_MONTH);
		Date fecha;
		if (dia > 8 && dia < 24) {
			mes = mes + 1;
			fecha = Date.valueOf(retornaCadenaFecha(annio, mes, "08"));
		} else {
			if (dia >= 24) {
				mes = mes + 1;
			}
			fecha = Date.valueOf(retornaCadenaFecha(annio, mes, "23"));
		}
		return fecha;

	}

	// funcion extra 
	//a partir de un Calendar
	public Date retornarSQLDate(Calendar c) {
		int mes = c.get(Calendar.MONTH);// 0-11
		mes++;
		int annio = c.get(Calendar.YEAR);
		int dia = c.get(Calendar.DAY_OF_MONTH);
		if (dia < 10) {
			return Date.valueOf(retornaCadenaFecha(annio, mes, "0" + dia));
		} else {
			return Date.valueOf(retornaCadenaFecha(annio, mes, "" + dia));
		}

	}
	
	//a partir de un util.date
	public Date retornarSQLDate(java.util.Date f){
		SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha = Date.valueOf(fechaFormato.format(f));
		return fecha;
	}
	
	//a partir de un String  yyyy-MM-dd
	public Date retornarSQLDate(String c){
		Date fecha = Date.valueOf(c);
		return fecha;
	}

	String retornaCadenaFecha(int annio, int mes, String dia) {
		if (mes < 10) {
			return "" + annio + "-0" + mes + "-" + dia;
		} else {
			return "" + annio + "-" + mes + "-" + dia;
		}
	}
	
	public ArrayList<Motivo> obtenerMotivos (){
		//tipo 1 para recuperacion
		return (ArrayList<Motivo>) motivoRepository.obtenerMotivos(1);
	}
	
	public void insertarRecuperacion(int idHorarioClase,int idMotivo,Date fechaRec,
			Date fechaAusente,String Aula,Time horaInicio,Time horaFin,String obs,int estado){
		recuperacionRepository.insertarRecuperacion(idHorarioClase,
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
