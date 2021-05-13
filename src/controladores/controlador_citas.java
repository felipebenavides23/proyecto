package controladores;

import  modelos.modelo_citas;
import java.awt.*;
import java.util.ArrayList;

public class controlador_citas {

    modelo_citas citas;

    ArrayList<modelo_citas> citas_p;

    public ArrayList<modelo_citas> getCitas_p() {
        return citas_p;
    }

    public controlador_citas(){citas_p = new ArrayList<>();}
    public void  eliminar(){citas_p.clear();}
    public void agregar_citas(modelo_citas citas) {citas_p.add(citas);}

    public int conteo () {
        return citas_p.size();
    }
}
