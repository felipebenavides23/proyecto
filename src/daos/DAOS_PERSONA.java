package daos;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import modelos.modelo_persona;
import controladores.controlador_persona;

public class DAOS_PERSONA {
    modelo_persona per;
    controlador_persona con_per;
    File bd_persona = new File("bd/usuarios.txt");


    public DAOS_PERSONA() throws FileNotFoundException {
    }

    public boolean verificar_bd() {
        try {
            if (bd_persona.exists()) {
                con_per=new controlador_persona();
            } else {
                bd_persona.createNewFile();
                verificar_bd();
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }
    public void iniiciarpersonas() throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/usuarios.txt"));
        String linea = "";
            try {
                while ((linea = leer.readLine()) != null){
                    per = new modelo_persona();
                    String[]data= linea.split("-");
                    per.setId(Integer.parseInt(data[0]));
                    per.setNombre(data[1]);
                    per.setApellido(data[2]);
                    per.setCorreo(data[3]);

                    con_per.agregarpersona(per);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public modelo_persona gestionar_persona(TextField nombre, TextField apellido, TextField correo, TextField contra){
            int id=con_per.conteo() + 1;
            per = new modelo_persona();
            per.setId(id);
            per.setNombre(nombre.getText());
            per.setApellido(apellido.getText());
            per.setCorreo(correo.getText());
            per.setContrasena(contra.getText());
            return per;
        }
    public void crear_usuario(modelo_persona per) {

        try {
            BufferedWriter escribir_usuario = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bd_persona, true)));
            escribir_usuario.write(per.getId() + "-" + per.getNombre() + "-" + per.getApellido() + "-" + per.getCorreo() + "-" + per.getContrasena());
            escribir_usuario.write("\n");
            escribir_usuario.close();
        } catch (Exception ex) {
        }

    }

    public boolean verificar_correo(TextField correo) throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/usuarios.txt"));
        String linea = "";
        Scanner entrada = new Scanner(new File("bd/usuarios.txt"));
        try {
            while ((linea = leer.readLine()) != null) {
               String data []= linea.split("-");
               per = new modelo_persona();
               per.setCorreo(data[3]);
                if (per.getCorreo().equals(correo.getText())) {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    public boolean vericar_datos_corre(TextField correo, TextField contra) throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/usuarios.txt"));
        String linea = "";
        Scanner entrada = new Scanner(new File("bd/usuarios.txt"));
        try {
            while ((linea = leer.readLine()) != null) {
                per = new modelo_persona();
                String[] data = linea.split("-");
                per.setCorreo(data[3]);
                per.setContrasena(data[4]);
                if (per.getCorreo().equals(correo.getText()) && per.getContrasena().equals(contra.getText())) {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }


}

