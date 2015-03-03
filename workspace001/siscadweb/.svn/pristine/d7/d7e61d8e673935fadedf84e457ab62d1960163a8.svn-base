package pe.edu.sistemas.siscadweb.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.sistemas.siscadweb.entities.Periodo;
import pe.edu.sistemas.siscadweb.entities.RangoMes;
import pe.edu.sistemas.siscadweb.entities.RangoSemana;
import pe.edu.sistemas.siscadweb.repositories.PeriodoRepository;

@Service
@Transactional
public class PeriodoService {
	@Autowired
	private PeriodoRepository periodoRepository;
	
	public List<Periodo> findAllPeriodos(){
		return periodoRepository.findAllPeriodos();
	}
	
	public List<String> obtenerRangoMes(String ciclo){
		List<String> meses = new ArrayList<String>();
		RangoMes rango = periodoRepository.obtenerRangoMes(ciclo);
		
		int ini, fin;
		
		ini = rango.getMesInicio();
		fin = rango.getMesFin();
		
		for(int i=ini; i<=fin; i++){
			meses.add(nombreDelMes(i));
		}

		return meses;
	}
	
	public String nombreDelMes(int val){
        String nom = "";
        
        switch(val){
            case 1: nom = "ENERO";
                    break;
            case 2: nom = "FEBRERO";
                    break;
            case 3: nom = "MARZO";
                    break;
            case 4: nom = "ABRIL";
                    break;
            case 5: nom = "MAYO";
                    break;
            case 6: nom = "JUNIO";
                    break;
            case 7: nom = "JULIO";
                    break;
            case 8: nom = "AGOSTO";
                    break;
            case 9: nom = "SETIEMBRE";
                    break;
            case 10: nom = "OCTUBRE";
                     break;
            case 11: nom = "NOVIEMBRE";
                     break;
            case 12: nom = "DICIEMBRE";
                     break;
        }
        
        return nom;
    }
	
	public int obtenerValorMes(String mes){
        if(mes.equals("ENTERO")) return 1;
        else if(mes.equals("FEBRERO")) return 2;
        else if(mes.equals("MARZO")) return 3;
        else if(mes.equals("ABRIL")) return 4;
        else if(mes.equals("MAYO")) return 5;
        else if(mes.equals("JUNIO")) return 6;
        else if(mes.equals("JULIO")) return 7;
        else if(mes.equals("AGOSTO")) return 8;
        else if(mes.equals("SETIEMBRE")) return 9;
        else if(mes.equals("OCTUBRE")) return 10;
        else if(mes.equals("NOVIEMBRE")) return 11;
        else if(mes.equals("DICIEMBRE")) return 12;
        else return 0;
    }
	
	public List<String> obtenerRangoSemanas(String fecha){
		List<String> rangos = new ArrayList<String>();
		RangoSemana rangoObtenido = periodoRepository.obtenerRangoSemanas(fecha);
		
		rangos.add("1ERA SEM: DEL "+fecha+" AL "+rangoObtenido.getR1());
        rangos.add("2DA SEM: DEL "+rangoObtenido.getR2()+" AL "+rangoObtenido.getR3());
        rangos.add("3ERA SEM: DEL "+rangoObtenido.getR4()+" AL "+rangoObtenido.getR5());
        rangos.add("4TA SEM: DEL "+rangoObtenido.getR6()+" AL "+rangoObtenido.getR7());
        
        System.out.println("Rangos: "+fecha+" "+rangoObtenido.getR1()+ " " + rangoObtenido.getR2());
        
        return rangos;
	}
	
	public void rangoFechasInicioCiclo(String mes, String fechaInicio){
		int valorMes = obtenerValorMes(mes);
		String fechaFin = "";
		
		if(valorMes<10){
            fechaFin = "2013-0"+valorMes+"-24";
        }
        else{
            fechaFin = "2013-"+valorMes+"-24";
        }
		
		ArrayList<String> fechas = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fi = null;
		Date ff = null;
		try {
			fi = sdf.parse(fechaInicio);
			ff = sdf.parse(fechaFin);
		} catch (ParseException e) {
			System.out.println("Error>>>>>>>>>>>>>>");
			e.printStackTrace();
		}
		Calendar cali = Calendar.getInstance();
		cali.setTime(fi);
		Calendar calf = Calendar.getInstance();
		calf.setTime(ff);
		Calendar auxiliar = Calendar.getInstance();
		auxiliar.setTime(cali.getTime());
		int semanas = 0;
		
		fechas.add(sdf.format(cali.getTime()));//agrego el primer dia
		//int dia_semana = cali.get(Calendar.DAY_OF_WEEK);//dia 4 = miercoles
		int dia_semana = cali.get(Calendar.DAY_OF_WEEK);
		if(dia_semana == 1) dia_semana = 8;
		
		auxiliar.add(Calendar.DATE, 13-dia_semana);//agregamos para completar la 1era semana
		
		System.out.println("Semana: " + dia_semana);
		System.out.println("Se agrego a auxiliar quedando: " + sdf.format(auxiliar.getTime()));
		
		if(auxiliar.getTime().before(calf.getTime())){
			//pasa primera semana
			fechas.add(sdf.format(auxiliar.getTime()));
		}
		else{
			fechas.add(sdf.format(calf.getTime()));
		}
		
		semanas++; 
		
		int cont = 0;
		
		while(cont<3){
			auxiliar.add(Calendar.DATE, 2);//para ir al lunes
			
			if(auxiliar.getTime().before(calf.getTime())){
				//Al aumentarle debe ser menor, si es igual o mayor termina
				fechas.add(sdf.format(auxiliar.getTime()));
				auxiliar.add(Calendar.DATE, 5); //para ir al viernes
				
				if(auxiliar.getTime().before(calf.getTime())){
					//pasa la semana
					semanas++; 
					fechas.add(sdf.format(auxiliar.getTime()));
				}
				else{
					fechas.add(sdf.format(calf.getTime()));
				}
			}
			else{
				break;
			}
			cont++;
		}
		
		if(fechas.size()>0){
			String[] fech = fechas.get(fechas.size()-1).split("-");
			System.out.println("imprimo ultimo valor: " + fechas.get(fechas.size()-1));
			if(fech[2].equals("23"))
				fechas.set(fechas.size()-1, fech[0]+"-"+fech[1]+"-24");
		}
		
		System.out.println("Numero semanas: "+semanas);
		System.out.println("Numero fechas: "+fechas.size());
		System.out.println("Imprimimos fechas guardadas");
		
		for(int i=0;i<fechas.size();i++){
			System.out.println(fechas.get(i));
		}
	}

}
