package pe.edu.sistemas.siscadweb.util;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import pe.edu.sistemas.siscadweb.entities.RegistroManual;
import pe.edu.sistemas.siscadweb.entities.RegistroRecuperacion;

public class RegistroRecuperacionDataModel extends ListDataModel<RegistroRecuperacion> implements SelectableDataModel<RegistroRecuperacion> {
	public RegistroRecuperacionDataModel() {  
    }  
  
    public RegistroRecuperacionDataModel(List<RegistroRecuperacion> data) {  
        super(data);  
    }  
    @Override  
    public RegistroRecuperacion getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
        String id;  
        List<RegistroRecuperacion> registros = (List<RegistroRecuperacion>) getWrappedData();  
        
        for(RegistroRecuperacion registro : registros) {
        	id = registro.getIdHorario()+"";
            if(id.equals(rowKey)){
                return registro;  
            }
        }  
        return null;  
    }  
    @Override  
    public Object getRowKey(RegistroRecuperacion registroRecuperacion) {  
        return registroRecuperacion.getIdHorario();
    } 
}
