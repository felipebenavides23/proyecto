package controladores;

import  modelos.modelo_informacion_vehiculo;

import java.awt.*;
import java.util.ArrayList;

public class controlador_auto    {

    modelo_informacion_vehiculo auto;

    ArrayList<modelo_informacion_vehiculo> vehiculos;

    public ArrayList<modelo_informacion_vehiculo> getVehiculos() {
        return vehiculos;
    }

    public controlador_auto(){vehiculos = new ArrayList<>();}
    public  void  eliminar (){vehiculos.clear();}
    public void agregar_vehiculo(modelo_informacion_vehiculo auto){ vehiculos.add(auto); }

    public boolean confirmar_placas(TextField placas){
        int i ;
        for (i=0;i<vehiculos.size();i++){
            System.out.println(vehiculos.get(i).getPlaca());
            if(vehiculos.get(i).getPlaca().equals(placas.getText())) return true;
        }
        return false;
    }

    public int conteo(){return vehiculos.size();}
}
