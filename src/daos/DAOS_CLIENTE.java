package daos;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import modelos.modelos_clientes;
import controladores.controlador_cliente;

public class DAOS_CLIENTE {
    modelos_clientes cli;
    controlador_cliente con_cli;
    File bd_clientes = new File("bd/clientes.txt");


    public DAOS_CLIENTE() throws FileNotFoundException {
    }

    public boolean verificar_bd_clientes() {
        try {
            if (bd_clientes.exists()) {
                con_cli = new controlador_cliente();
                return true;
            } else {
                bd_clientes.createNewFile();
                verificar_bd_clientes();
            }
        } catch (Exception ex) {
        }
        return false;
    }

    public void iniciarcliente() throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/clientes.txt"));
        String linea = "";
        if(con_cli != null)con_cli.elminar();

        try{
            while ((linea = leer.readLine()) != null){
                 cli = new modelos_clientes();
                String [] data = linea.split("-");
                cli.setId(Integer.parseInt(data[0]));
                cli.setNombre(data[1]);
                cli.setApellido(data[2]);
                cli.setDocumemto(data[3]);
                cli.setCorreo(data[4]);

                con_cli.agregarclientes(cli);
            }
        }catch (Exception ex){}
    }

    public boolean verificar_documento(TextField documento) throws FileNotFoundException {
        return con_cli.confirmar_correo(documento);
    }

    public modelos_clientes gestionar_persona(TextField nombre, TextField apellido, TextField documento, TextField corre){

        int id = con_cli.conteo() + 1;
        cli = new modelos_clientes();
        cli.setId(id);
        cli.setNombre(nombre.getText());
        cli.setApellido(apellido.getText());
        cli.setDocumemto(documento.getText());
        cli.setCorreo(documento.getText());

        return cli;

    }

    public void crear_cliente(modelos_clientes cli) {

        try {
            BufferedWriter escribir_usuario = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bd_clientes, true)));
            escribir_usuario.write(cli.getId() + "-" + cli.getNombre() + "-" + cli.getApellido() + "-" + cli.getDocumemto() + "-" + cli.getCorreo());
            escribir_usuario.write("\n");
            escribir_usuario.close();
        } catch (Exception ex) {
        }
    }

    public String[] lista_documento() throws FileNotFoundException {
        verificar_bd_clientes();
        iniciarcliente();
        int conteo = con_cli.conteo() + 1;
        String[] lista_doc = new String[conteo];
        int i;
        lista_doc [0]= "";
        try {
            for (i = 0; i <= conteo; i++) {
                String documento_cli = con_cli.getClientes().get(i).getDocumemto();
                lista_doc[i+1] = documento_cli;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return lista_doc;
    }

    public String[] lista_clientes() throws FileNotFoundException {
        verificar_bd_clientes();
        iniciarcliente();
        int conteo = con_cli.conteo() + 1;
        int i;
        String[] lista_cliente = new String[conteo];
        lista_cliente[0] = "id-nombre-apellido-documento-correo";
        try {
            for (i = 0; i <= conteo; i++) {
                int id_cli = con_cli.getClientes().get(i).getId();
                String nombre_cli = con_cli.getClientes().get(i).getNombre();
                String apellido_cli = con_cli.getClientes().get(i).getApellido();
                String documento_cli = con_cli.getClientes().get(i).getDocumemto();
                String correo_cli = con_cli.getClientes().get(i).getCorreo();
                lista_cliente[i+1] = id_cli + " - " + nombre_cli + " - " + apellido_cli + " - " + documento_cli + " - " + correo_cli;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return lista_cliente;
    }
}
