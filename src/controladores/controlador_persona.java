package controladores;
import modelos.modelo_persona;

import java.util.ArrayList;

public class controlador_persona {
    modelo_persona per;
    ArrayList<modelo_persona> personas;

    public controlador_persona(){
        personas = new ArrayList<>();
    }

    public void agregarpersona(modelo_persona per){
        personas.add(per);
    }

    public int conteo(){
        return personas.size();
    }
}
