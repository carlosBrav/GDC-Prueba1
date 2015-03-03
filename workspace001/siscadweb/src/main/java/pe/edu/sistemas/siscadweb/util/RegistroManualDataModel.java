package pe.edu.sistemas.siscadweb.util;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import pe.edu.sistemas.siscadweb.entities.RegistroManual;

public class RegistroManualDataModel extends ListDataModel<RegistroManual> implements SelectableDataModel<RegistroManual>{
	public RegistroManualDataModel() {  
    }  
  
    public RegistroManualDataModel(List<RegistroManual> data) {  
        super(data);  
    }  
    @Override  
    public RegistroManual getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
        String id;  
        List<RegistroManual> registros = (List<RegistroManual>) getWrappedData();  
        
        for(RegistroManual registro : registros) {
        	id = registro.getIdHorario()+"";
            if(id.equals(rowKey)){
                return registro;  
            }
        }  
        return null;  
    }  
    @Override  
    public Object getRowKey(RegistroManual registroManual) {  
        return registroManual.getIdHorario();
    }  
}
