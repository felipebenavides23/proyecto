package daos;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class DAOS_CLIENTE {
    File bd_clientes = new File("bd/clientes.txt");


    public DAOS_CLIENTE() throws FileNotFoundException {
    }

    public boolean verificar_bd_clientes() {
        try {
            if (bd_clientes.exists()) {
                return true;
            } else {
                bd_clientes.createNewFile();
                verificar_bd_clientes();

            }
        } catch (Exception ex) {
        }
        return false;
    }

    public boolean verificar_documento(TextField documento) throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/clientes.txt"));
        Scanner entrada = new Scanner(new File("bd/clientes.txt"));
        String linea = "";
        try {
            while ((linea = leer.readLine()) != null) {
                String id_cli = entrada.next();
                String nombre_cli = entrada.next();
                String apellido_cli = entrada.next();
                String documento_cli = entrada.next();
                String correo_cli = entrada.next();
                String v_documento = documento.getText();
                if (documento_cli.equals(v_documento)) {
                    return true;
                }
            }
        } catch (Exception ex) {

        }
        return false;
    }

    public void validar_id_cliente(TextField nombre, TextField apellido, TextField documento, TextField correo) throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/clientes.txt"));
        String linea = "";
        try {
            int conteo = 1;
            while ((linea = leer.readLine()) != null) {
                conteo = conteo + 1;
            }
            crear_cliente(conteo, nombre, apellido, documento, correo);
        } catch (Exception ex) {
        }
    }

    public void crear_cliente(int id, TextField nombre, TextField apellido, TextField documento, TextField corre) {
        try {
            BufferedWriter escribir_usuario = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bd_clientes, true)));
            escribir_usuario.write(id + "    " + nombre.getText() + "    " + apellido.getText() + "    " + documento.getText() + "     " + corre.getText());
            escribir_usuario.write("\n");
            escribir_usuario.close();
        } catch (Exception ex) {
        }
    }

    public String[] lista_documento() throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/clientes.txt"));
        Scanner entrada = new Scanner(new File("bd/clientes.txt"));
        String linea = "";
        int conteo = 1;
        int i;

        try {
            while ((linea = leer.readLine()) != null) {
                conteo = conteo + 1;
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        String[] lista_doc = new String[conteo];
        lista_doc[0] = "";
        try {
            for (i = 1; i <= conteo; i++) {
                String id_cli = entrada.next();
                String nombre_cli = entrada.next();
                String apellido_cli = entrada.next();
                String documento_cli = entrada.next();
                String correo_cli = entrada.next();

                lista_doc[i] = documento_cli;

            }

        } catch (Exception ex) {
            System.out.println(ex);

        }


        return lista_doc;
    }

    public String[] lista_clientes() throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/clientes.txt"));
        Scanner entrada = new Scanner(new File("bd/clientes.txt"));
        String linea = "";
        int conteo = 1;
        int i;

        try {
            while ((linea = leer.readLine()) != null) {
                conteo = conteo + 1;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        String[] lista_cliente = new String[conteo];
        lista_cliente[0] = "id              nombre              apellido              docuemnto              correo";
        try {
            for (i = 1; i <= conteo; i++) {
                String id_cli = entrada.next();
                String nombre_cli = entrada.next();
                String apellido_cli = entrada.next();
                String documento_cli = entrada.next();
                String correo_cli = entrada.next();
                lista_cliente[i] = id_cli + "              " + nombre_cli + "              " + apellido_cli + "              " + documento_cli + "              " + correo_cli;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return lista_cliente;
    }
}
