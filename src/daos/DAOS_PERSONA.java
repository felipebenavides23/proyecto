package daos;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class DAOS_PERSONA {

    File bd_persona = new File("bd/usuarios.txt");


    public DAOS_PERSONA() throws FileNotFoundException {
    }

    public boolean verificar_bd() {
        try {
            if (bd_persona.exists()) {
            } else {
                bd_persona.createNewFile();
                verificar_bd();
                return true;
            }
        } catch (Exception ex) {
        }
        return true;
    }


    public void crear_usuario(int id, TextField nombre, TextField apellido, TextField correo, TextField contra) {
        try {
            BufferedWriter escribir_usuario = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bd_persona, true)));
            escribir_usuario.write(id + "    " + nombre.getText() + "    " + apellido.getText() + "    " + correo.getText() + "     " + contra.getText());
            escribir_usuario.write("\n");
            escribir_usuario.close();
        } catch (Exception ex) {
        }
    }

    public void validar_id(TextField nombre, TextField apellido, TextField correo, TextField contra) throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/usuarios.txt"));
        String linea = "";
        try {
            int conteo = 1;
            while ((linea = leer.readLine()) != null) {
                conteo = conteo + 1;
            }
            crear_usuario(conteo, nombre, apellido, correo, contra);
        } catch (Exception ex) {
        }
    }


    public boolean verificar_correo(TextField correo) throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/usuarios.txt"));
        String linea = "";
        Scanner entrada = new Scanner(new File("bd/usuarios.txt"));
        try {

            while ((linea = leer.readLine()) != null) {
                String id_u = entrada.next();
                String nombres_u = entrada.next();
                String apellido_u = entrada.next();
                String correo_u = entrada.next();
                String contraseña_u = entrada.next();
                if (correo_u.equals(correo.getText())) {
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
        int conte = 0;
        try {
            while ((linea = leer.readLine()) != null) {
                String id_u = entrada.next();
                String nombres_u = entrada.next();
                String apellido_u = entrada.next();
                String correo_u = entrada.next();
                String contraseña_u = entrada.next();
                conte = conte + 1;
                if (correo_u.equals(correo.getText()) && contraseña_u.equals(contra.getText())) {
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }


}

