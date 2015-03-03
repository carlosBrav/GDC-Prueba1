package pe.edu.sistemas.siscadweb.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.siscadweb.entities.Docente;
import pe.edu.sistemas.siscadweb.entities.HorarioClase;
import pe.edu.sistemas.siscadweb.entities.RegistroRecuperacion;
import pe.edu.sistemas.siscadweb.repositories.DocenteRepository;


@Service
@Transactional
public class DocenteService {
	
	@Autowired
	private DocenteRepository docenteRepository;
	
	public List<Docente> obtenerDocente(String codigo,String nombre,String apPaterno,String apMaterno){
		List<Docente> docentes= new ArrayList<Docente>();
		Docente doc = null;
		if(codigo.compareTo("") != 0){
			System.out.println("Codigo diferente de vacio");
			
			doc= docenteRepository.obtenerDocentexCodigo(codigo);
			if(doc != null) docentes.add(doc);
			
		}else{
			docentes=docenteRepository.obtenerDocentexOtro(nombre,apPaterno,apMaterno);
		}
		return docentes;
	}
	
	public List<Docente> obtenerDocentes(){
		return docenteRepository.findAllDocentes();
	}
	
	/*
	public ArrayList<RegistroRecuperacion> ObtenerFaltasxDocente(int idDocente, String ciclo, Date diaInicio, Date diaFin) {
        // TODO Auto-generated method stub
        int dia;
        int encontroAsistencia = 0;
        //0 no hay asistencia
        //1 tardanza
        //2 salio antes
        //3 asistencia dentro de lo permitido

        Calendar fechaActual = Calendar.getInstance();
        Calendar fechaModif = Calendar.getInstance();
        //Calendar prueba = Calendar.getInstance();
        //prueba.set(2013,1,8);

        //System.out.println("fecha Prueba: "+prueba);
///////////ya estaba comentado////////////////////////
        /*long tiempoActual = fechaActual.getTimeInMillis();
         long dias = diasRecp * 24 * 60 * 60 * 1000;
         Date fecha = new Date(tiempoActual - dias);*/
	/*
        int mes = fechaActual.get(Calendar.MONTH);
        int annio = fechaActual.get(Calendar.YEAR);
        int ddia = fechaActual.get(Calendar.DAY_OF_MONTH);
///////////////////ya estaba comentado/////////////////////////////
        /*if(ddia>4){//Si estamos mas de la fecha 4 del mes actual no se puede recuperar faltas del mes anterior
         mes = mes + 1;
         }*/
	/*
        System.out.println("mes actual: " + mes + " anio: " + annio + " dia: " + ddia);
        String fechaInicioMesAnterior = "";

        if (ddia > 8 && ddia < 24) {
            if (mes < 10) {
                fechaInicioMesAnterior = "" + annio + "/0" + mes + "/24";
            } else {
                fechaInicioMesAnterior = "" + annio + "/" + mes + "/24";
            }
        } else {
            if (mes < 10) {
                fechaInicioMesAnterior = "" + annio + "/0" + mes + "/09";
            } else {
                fechaInicioMesAnterior = "" + annio + "/" + mes + "/09";
            }
        }


        //System.out.println("mes actual: " + mes + " anio: " + annio + " dia: " + ddia);
        //String fechaPrimerMesAnterior = ""+annio+"/"+mes+"/01";
        System.out.println("fecha primer dia mes anterior: " + fechaInicioMesAnterior);
        Date fecha = new Date(fechaInicioMesAnterior);
        fechaModif.setTime(fecha);

        ArrayList<RegistroRecuperacion> inasistencias = new ArrayList<RegistroRecuperacion>();
        AsistenciaDao asistenciaDao = new AsistenciaDaoImpl();
        ArrayList<Asistencia> aux = new ArrayList<Asistencia>();

        HorarioClaseDao horarioDao = new HorarioClaseDaoImpl();
        ArrayList<HorarioClase> horarios = new ArrayList<HorarioClase>();

        RecuperacionDao recDao = new RecuperacionDaoImpl();
        ArrayList<Recuperacion> recuperaciones = null;
        //ArrayList<Recuperacion> recuperaciones=recDao.selectRecuperaciones();
        //diferenciaDias=(fechaActual.get(Calendar.DAY_OF_YEAR) - fechaModif.get(Calendar.DAY_OF_YEAR));
        //System.out.println("diferencia dias: " + diferenciaDias);

        //int i = 0;
        while (fechaModif.before(fechaActual)) {//Mientras sea diferente a la fecha actual				
            String fechaLimite = this.obtenerFechaLimite(fechaModif.get(Calendar.DAY_OF_MONTH), fechaModif.get(Calendar.MONTH) + 1, fechaModif.get(Calendar.YEAR));
            System.out.println("fecha limite: " + fechaLimite);
            Date fechaLim = new Date(fechaLimite);

            aux = asistenciaDao.SelectAsistenciaxFechaxDocente(idDocente, fechaModif.getTime());
            System.out.println("fecha Modificada: " + fechaModif.getTime());
            dia = fechaModif.getTime().getDay();//equivalente a Calendar.DAY_OF_WEEK
            System.out.println("fecha Modificada dia: " + dia);
            horarios = (ArrayList<HorarioClase>) horarioDao.getHorarioClasexIdDocentexDiaxPeriodo(idDocente, dia, ciclo);

            if ((fechaModif.getTimeInMillis() > diaInicio.getTime()) && (fechaModif.getTimeInMillis() < diaFin.getTime())) {//Si esta dentro del ciclo
                if (horarios != null) {
                    System.out.println("Horarios tama�o: " + horarios.size());
                    System.out.println("Existen horarios");
                    if (aux != null) {
                        System.out.println("Existen asistencias");
                        for (int j = 0; j < horarios.size(); j++) {
                            int minutosDeudaTardanza = 0;
                            int minutosDeudaSalidaAntes = 0;
                            int minutosDeudaFinal = 0;
                            encontroAsistencia = 0;
                            for (int k = 0; k < aux.size(); k++) {
                                if ((encontroAsistencia == 0) && (horarios.getIdhorarioClase() == aux.get(k).getHorarioClase().getIdhorarioClase())) {
                                    //Existe una tupla en la tabla asistencia
                                    System.out.println("Existe una tupla en la tabla asistencia del horario: " + horarios.get(j).getIdhorarioClase());
                                    if (!aux.get(k).getAsistenciaTipoMarca().equals("RECUPERACION")) {
                                        System.out.println("Asistencia diferente de RECUPERACION");
                                        HorarioClase horario = horarios.get(j);
                                        Date horaInicioMarcada = aux.get(k).getAsistenciaHoraIngreso();
                                        Date horaFinMarcada = aux.get(k).getAsistenciaHoraSalida();
                                        Date horaInicioReal = horario.getHoraInicio();
                                        Date horaFinReal = horario.getHoraFin();
                                        int minutosHoraInicioMarcada = horaInicioMarcada.getHours() * 60 + horaInicioMarcada.getMinutes();
                                        int minutosHoraInicioReal = horaInicioReal.getHours() * 60 + horaInicioReal.getMinutes();
                                        int minutosHoraFinMarcada = horaFinMarcada.getHours() * 60 + horaFinMarcada.getMinutes();
                                        int minutosHoraFinReal = horaFinReal.getHours() * 60 + horaFinReal.getMinutes();
                                        int difMinutosTardanza = minutosHoraInicioMarcada - minutosHoraInicioReal;
                                        int difMinutosSalidaAntes = minutosHoraFinReal - minutosHoraFinMarcada;

                                        if (horaInicioMarcada.compareTo(horaFinMarcada) == 0) {//Si tiene misma hora de entrada y salida se considera solo su tardanza
                                            System.out.println("Hora inicio igual hora fin");
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
                                        //Si es de recuperacion se considera como asistido
                                        encontroAsistencia = 3;
                                    }
                                }
                            }
                            if (encontroAsistencia != 3) {
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                String fechaAs = sdf.format(fechaModif.getTime());
                                System.out.println("Existen Inasistencias");
                                RegistroRecuperacion recup = new RegistroRecuperacion();
                                recup.setCode(horarios.get(j).getDocente().getPersona().getPersonaCodigo());
                                recup.setFecha(fechaAs);
                                recup.setIdHorario(horarios.get(j).getIdhorarioClase());
                                recup.setName(horarios.get(j).getDocente().getPersona().getPersonaNombre() + " " + horarios.get(j).getDocente().getPersona().getPersonaAppaterno() + " " + horarios.get(j).getDocente().getPersona().getPersonaApmaterno());
                                recup.setCurso(horarios.get(j).getGrupo().getCursoPeriodo().getCursoPeriodoNombre());
                                recup.setTipo(horarios.get(j).getHorarioClaseTipo());
                                if (encontroAsistencia == 0) {//No encontro asistencia
                                    recup.setTipoDeuda("INASISTENCIA");

                                    recuperaciones = recDao.selectRecuperacionesxHorarioxFecha(horarios.get(j).getIdhorarioClase(), fechaAs);

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


                                            recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getRecuperacionFechaRecuperacion()) + "/" + sdf1.format(recuperaciones.get(1).getRecuperacionFechaRecuperacion()));
                                            recup.setAula(recuperaciones.get(0).getRecuperacionAula() + "/" + recuperaciones.get(1).getRecuperacionAula());
                                            recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getRecuperacionHoraInicio()) + "/" + sdf2.format(recuperaciones.get(1).getRecuperacionHoraInicio().toString()));
                                            recup.setHoraFin(sdf2.format(recuperaciones.get(0).getRecuperacionHoraFin()) + "/" + sdf2.format(recuperaciones.get(1).getRecuperacionHoraFin().toString()));
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


                                            recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getRecuperacionFechaRecuperacion()));
                                            recup.setAula(recuperaciones.get(0).getRecuperacionAula());
                                            recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getRecuperacionHoraInicio()));
                                            recup.setHoraFin(sdf2.format(recuperaciones.get(0).getRecuperacionHoraFin()));
                                        }
                                    } else {
                                        recup.setEstado("NO PROGRAMADO");
                                        System.out.println("no programado");
                                    }

                                    HorarioClase horario = horarios.get(j);
                                    Date horaInicioReal = horario.getHoraInicio();
                                    Date horaFinReal = horario.getHoraFin();
                                    int minutosHoraInicioReal = horaInicioReal.getHours() * 60 + horaInicioReal.getMinutes();
                                    int minutosHoraFinReal = horaFinReal.getHours() * 60 + horaFinReal.getMinutes();
                                    int difMinutosReal = minutosHoraFinReal - minutosHoraInicioReal;

                                    recup.setMinutosDeuda(difMinutosReal);

                                    recup.setFechaLimite(fechaLimite);

                                    if (fechaLim.before(fechaActual.getTime()) && !recup.getEstado().equals("RECUPERADO")) {
                                        System.out.println("fecha limite antes de fecha actual");
                                        System.out.println("fecha limite: " + fechaLim.toString());
                                        System.out.println("fecha actual: " + fechaActual.toString());

                                        recup.setEstado("NO RECUPERADO");
                                    }


                                    inasistencias.add(recup);

                                    System.out.println("inasistencia");
                                } else {
                                    if (encontroAsistencia == 1) {//tardanza
                                        recup.setTipoDeuda("TARDANZA");
                                        recup.setMinutosDeuda(minutosDeudaFinal);
                                        recuperaciones = recDao.selectRecuperacionesxHorarioxFecha(horarios.get(j).getIdhorarioClase(), fechaAs);
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
                                                recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getRecuperacionFechaRecuperacion()) + "/" + sdf1.format(recuperaciones.get(1).getRecuperacionFechaRecuperacion()));
                                                recup.setAula(recuperaciones.get(0).getRecuperacionAula() + "/" + recuperaciones.get(1).getRecuperacionAula());
                                                recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getRecuperacionHoraInicio()) + "/" + sdf2.format(recuperaciones.get(1).getRecuperacionHoraInicio().toString()));
                                                recup.setHoraFin(sdf2.format(recuperaciones.get(0).getRecuperacionHoraFin()) + "/" + sdf2.format(recuperaciones.get(1).getRecuperacionHoraFin().toString()));
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

                                                recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getRecuperacionFechaRecuperacion()));
                                                recup.setAula(recuperaciones.get(0).getRecuperacionAula());
                                                recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getRecuperacionHoraInicio()));
                                                recup.setHoraFin(sdf2.format(recuperaciones.get(0).getRecuperacionHoraFin()));
                                            }
                                        } else {
                                            recup.setEstado("NO PROGRAMADO");
                                        }

                                        recup.setFechaLimite(fechaLimite);

                                        if (fechaLim.before(fechaActual.getTime()) && !recup.getEstado().equals("RECUPERADO")) {
                                            System.out.println("fecha limite antes de fecha actual");
                                            System.out.println("fecha limite: " + fechaLim.toString());
                                            System.out.println("fecha actual: " + fechaActual.toString());

                                            recup.setEstado("NO RECUPERADO");
                                        }

                                        inasistencias.add(recup);
                                        System.out.println("tardanza");

                                    } else {
                                        if (encontroAsistencia == 2) {//salio antes
                                            recup.setTipoDeuda("INCOMPLETO");
                                            recup.setMinutosDeuda(minutosDeudaFinal);

                                            recuperaciones = recDao.selectRecuperacionesxHorarioxFecha(horarios.get(j).getIdhorarioClase(), fechaAs);
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
                                                    recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getRecuperacionFechaRecuperacion()) + "/" + sdf1.format(recuperaciones.get(1).getRecuperacionFechaRecuperacion()));
                                                    recup.setAula(recuperaciones.get(0).getRecuperacionAula() + "/" + recuperaciones.get(1).getRecuperacionAula());
                                                    recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getRecuperacionHoraInicio()) + "/" + sdf2.format(recuperaciones.get(1).getRecuperacionHoraInicio().toString()));
                                                    recup.setHoraFin(sdf2.format(recuperaciones.get(0).getRecuperacionHoraFin()) + "/" + sdf2.format(recuperaciones.get(1).getRecuperacionHoraFin().toString()));
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


                                                    recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getRecuperacionFechaRecuperacion()));
                                                    recup.setAula(recuperaciones.get(0).getRecuperacionAula());
                                                    recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getRecuperacionHoraInicio()));
                                                    recup.setHoraFin(sdf2.format(recuperaciones.get(0).getRecuperacionHoraFin()));
                                                }
                                            } else {
                                                recup.setEstado("NO PROGRAMADO");
                                            }

                                            recup.setFechaLimite(fechaLimite);

                                            if (fechaLim.before(fechaActual.getTime()) && !recup.getEstado().equals("RECUPERADO")) {
                                                System.out.println("fecha limite antes de fecha actual");
                                                System.out.println("fecha limite: " + fechaLim.toString());
                                                System.out.println("fecha actual: " + fechaActual.toString());

                                                recup.setEstado("NO RECUPERADO");
                                            }

                                            inasistencias.add(recup);
                                            System.out.println("incompleto");

                                        }
                                    }
                                }
                            }
                        }
                    } else {//No existen asistencias
                        for (int j = 0; j < horarios.size(); j++) {
                            System.out.println("Existen Inasistencias");
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String fechaAs = sdf.format(fechaModif.getTime());
                            System.out.println("Existen Inasistencias");
                            RegistroRecuperacion recup = new RegistroRecuperacion();
                            recup.setCode(horarios.get(j).getDocente().getPersona().getPersonaCodigo());
                            recup.setFecha(fechaAs);
                            recup.setIdHorario(horarios.get(j).getIdhorarioClase());
                            recup.setName(horarios.get(j).getDocente().getPersona().getPersonaNombre() + " " + horarios.get(j).getDocente().getPersona().getPersonaAppaterno() + " " + horarios.get(j).getDocente().getPersona().getPersonaApmaterno());
                            recup.setCurso(horarios.get(j).getGrupo().getCursoPeriodo().getCursoPeriodoNombre());
                            recup.setTipo(horarios.get(j).getHorarioClaseTipo());
                            recup.setTipoDeuda("INASISTENCIA");

                            HorarioClase horario = horarios.get(j);
                            Date horaInicioReal = horario.getHoraInicio();
                            Date horaFinReal = horario.getHoraFin();
                            int minutosHoraInicioReal = horaInicioReal.getHours() * 60 + horaInicioReal.getMinutes();
                            int minutosHoraFinReal = horaFinReal.getHours() * 60 + horaFinReal.getMinutes();
                            int difMinutosReal = minutosHoraFinReal - minutosHoraInicioReal;

                            recup.setMinutosDeuda(difMinutosReal);

                            recuperaciones = recDao.selectRecuperacionesxHorarioxFecha(horarios.get(j).getIdhorarioClase(), fechaAs);

                            if (recuperaciones.size() > 0) {
                                System.out.println("size: " + recuperaciones.size());
                                if (recuperaciones.size() > 1) {
                                    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

                                    SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

                                    boolean encontro = false;
                                    if (recuperaciones.get(0).getEstado() == 1 || recuperaciones.get(1).getEstado() == 1) {
                                        recup.setEstado("EN CURSO");
                                        System.out.println("en curso");
                                        recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getRecuperacionFechaRecuperacion()) + "/" + sdf1.format(recuperaciones.get(1).getRecuperacionFechaRecuperacion()));
                                        recup.setAula(recuperaciones.get(0).getRecuperacionAula() + "/" + recuperaciones.get(1).getRecuperacionAula());
                                        recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getRecuperacionHoraInicio()) + "/" + sdf2.format(recuperaciones.get(1).getRecuperacionHoraInicio().toString()));
                                        recup.setHoraFin(sdf2.format(recuperaciones.get(0).getRecuperacionHoraFin()) + "/" + sdf2.format(recuperaciones.get(1).getRecuperacionHoraFin().toString()));
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
                                    recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getRecuperacionFechaRecuperacion()) + "/" + sdf1.format(recuperaciones.get(1).getRecuperacionFechaRecuperacion()));
                                    recup.setAula(recuperaciones.get(0).getRecuperacionAula() + "/" + recuperaciones.get(1).getRecuperacionAula());
                                    System.out.println(sdf2.format(recuperaciones.get(0).getRecuperacionHoraInicio()) + "/" + sdf2.format(recuperaciones.get(1).getRecuperacionHoraInicio().toString()));
                                    recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getRecuperacionHoraInicio()) + "/" + sdf2.format(recuperaciones.get(1).getRecuperacionHoraInicio().toString()));
                                    System.out.println("recuperacionasdflkjasdñfkjasdlñfjkaslñdfjkasdfkl: "+recup.getHoraInicio());
                                    recup.setHoraFin(sdf2.format(recuperaciones.get(0).getRecuperacionHoraFin()) + "/" + sdf2.format(recuperaciones.get(1).getRecuperacionHoraFin().toString()));
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


                                    recup.setFechaProgramada(sdf1.format(recuperaciones.get(0).getRecuperacionFechaRecuperacion()));
                                    recup.setAula(recuperaciones.get(0).getRecuperacionAula());
                                    recup.setHoraInicio(sdf2.format(recuperaciones.get(0).getRecuperacionHoraInicio()));
                                    recup.setHoraFin(sdf2.format(recuperaciones.get(0).getRecuperacionHoraFin()));
                                }
                            } else {
                                recup.setEstado("NO PROGRAMADO");
                                System.out.println("no programado");
                            }

                            recup.setFechaLimite(fechaLimite);

                            if (fechaLim.before(fechaActual.getTime()) && !recup.getEstado().equals("RECUPERADO")) {
                                System.out.println("fecha limite antes de fecha actual");
                                System.out.println("fecha limite: " + fechaLim.toString());
                                System.out.println("fecha actual: " + fechaActual.toString());

                                recup.setEstado("NO RECUPERADO");
                            }

                            inasistencias.add(recup);

                            System.out.println("inasistencia en else");
                        }
                    }
                }

            }
            fechaModif.add(Calendar.DAY_OF_MONTH, 1);
        }
        System.out.println("inasistencias size: " + inasistencias.size());

        if (inasistencias.size() > 0) {
            System.out.println("inasistencia 1 val: " + inasistencias.get(0).getMinutosDeuda());
        }
        
        return inasistencias;
    }
	*/
	
}