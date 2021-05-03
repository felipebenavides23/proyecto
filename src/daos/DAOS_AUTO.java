package daos;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class DAOS_AUTO {
    File bd_auto = new File("bd/autos.txt");

    public boolean verificar_bd_auto() {
        try {
            if (bd_auto.exists()) {
            } else {
                bd_auto.createNewFile();
                verificar_bd_auto();
                return true;
            }
        } catch (Exception ex) {
        }
        return true;
    }

    public boolean verificar_placas(TextField placa) throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/autos.txt"));
        Scanner entrada = new Scanner(new File("bd/autos.txt"));
        String linea = "";
        try {
            while ((linea = leer.readLine()) != null) {
                String id_auto = entrada.next();
                String dueño_auto = entrada.next();
                String placa_auto = entrada.next();
                String modelo_auto = entrada.next();

                if (dueño_auto.equals(placa.getText())) {
                    return true;
                }
            }
        } catch (Exception ex) {
        }
        return false;
    }

    public void validar_id_auto(Choice dueño, Choice tipo, TextField placas, TextField modelo) throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/autos.txt"));
        String linea = "";
        try {
            int conteo = 1;
            while ((linea = leer.readLine()) != null) {
                conteo = conteo + 1;
            }
            crear_auto(conteo, dueño, tipo, placas, modelo);
        } catch (Exception ex) {
        }
    }

    public void crear_auto(int id, Choice dueño, Choice tipo, TextField placas, TextField modelo) {
        try {
            BufferedWriter escribir_usuario = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bd_auto, true)));
            escribir_usuario.write(id + "    " + dueño.getItem(dueño.getSelectedIndex()) + "    " + tipo.getItem(tipo.getSelectedIndex()) + "    " + placas.getText() + "     " + modelo.getText());
            escribir_usuario.write("\n");
            escribir_usuario.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public String[] listar_placas() throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/autos.txt"));
        Scanner entrada = new Scanner(new File("bd/autos.txt"));
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
        String[] lista_placas = new String[conteo];
        lista_placas[0] = "";
        try {
            for (i = 1; i <= conteo; i++) {
                String id_auto = entrada.next();
                String dueño_auto = entrada.next();
                String tipo_auto = entrada.next();
                String placa_auto = entrada.next();
                String modelo_auto = entrada.next();
                lista_placas[i] = placa_auto;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return lista_placas;
    }

    public String[] listar_autos() throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/autos.txt"));
        Scanner entrada = new Scanner(new File("bd/autos.txt"));
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
        String[] lista_autos = new String[conteo];
        lista_autos[0] = "id              doc_dueño              tipo auto              placa              modelo";

        try {
            for (i = 1; i <= conteo; i++) {
                String id_auto = entrada.next();
                String dueño_auto = entrada.next();
                String tipo_auto = entrada.next();
                String placa_auto = entrada.next();
                String modelo_auto = entrada.next();
                lista_autos[i] = id_auto + "              " + dueño_auto + "              " + tipo_auto + "              " + placa_auto + "              " + modelo_auto;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return lista_autos;
    }
}
