package controladores;
import modelos.modelos_clientes;

import java.awt.*;
import java.util.ArrayList;

public class controlador_cliente {

    modelos_clientes cli;
    ArrayList<modelos_clientes> clientes;

    public ArrayList<modelos_clientes> getClientes() { return clientes; }
    public  controlador_cliente(){ clientes = new ArrayList<>();}
    public void elminar(){clientes.clear();}
    public void agregarclientes(modelos_clientes cli) {clientes.add(cli);}
    public boolean confirmar_correo(TextField documento){
        int i;
        for (i=0; i < clientes.size();i++){

            if(clientes.get(i).getDocumemto().equals(documento.getText())){
                return true;
            }
        }
        return  false;
    }
    public int conteo() {
        return  clientes.size();
    }
}
