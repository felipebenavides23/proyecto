package daos;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class DAOS_CITAS {

    File bd_citas = new File("bd/citas.txt");


    public DAOS_CITAS() throws FileNotFoundException {
    }

    public boolean verificar_bd_citas() {
        try {
            if (bd_citas.exists()) {
                return true;
            } else {
                bd_citas.createNewFile();
                verificar_bd_citas();

            }
        } catch (Exception ex) {
        }
        return false;
    }

    public void validar_id_cita(JDateChooser fecha, Choice placa, TextArea motivo) throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/citas.txt"));
        String linea = "";
        try {
            int conteo = 1;
            while ((linea = leer.readLine()) != null) {
                conteo = conteo + 1;
            }
            crear_cita(conteo, fecha, placa, motivo);
        } catch (Exception ex) {
        }
    }

    public void crear_cita(int id, JDateChooser fecha, Choice placa, TextArea motivo) {

        Date fecha_m = fecha.getDate();
        long d = fecha_m.getTime();
        java.sql.Date fecha_b = new java.sql.Date(d);

        try {
            BufferedWriter escribir_usuario = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bd_citas, true)));
            escribir_usuario.write(id + "    " + fecha_b + "    " + placa.getItem(placa.getSelectedIndex()) + "    " + motivo.getText());
            escribir_usuario.write("\n");
            escribir_usuario.close();
        } catch (Exception ex) {
        }
    }

    public String[] lista_citas() throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/citas.txt"));
        Scanner entrada = new Scanner(new File("bd/citas.txt"));
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
        String[] listar_citas = new String[conteo];
        listar_citas[0] = "";
        try {
            for (i = 1; i <= conteo; i++) {
                String id_citas = entrada.next();
                String fecha_cita = entrada.next();
                String placa_cita = entrada.next();
                String motivo_cita = entrada.next();

                listar_citas[i] = "hay un cita para " + fecha_cita + " para el auto con placas " + placa_cita + " por el motivo " + motivo_cita;

            }

        } catch (Exception ex) {
            System.out.println(ex);

        }


        return listar_citas;
    }
}
