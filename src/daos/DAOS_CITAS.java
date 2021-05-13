package daos;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import modelos.modelo_citas;
import controladores.controlador_citas;

public class DAOS_CITAS {

    modelo_citas citas;
    controlador_citas con_citas;
    File bd_citas = new File("bd/citas.txt");


    public DAOS_CITAS() throws FileNotFoundException {
    }

    public boolean verificar_bd_citas() {
        try {
            if (bd_citas.exists()) {
                con_citas = new controlador_citas();
                return true;
            } else {
                bd_citas.createNewFile();
                verificar_bd_citas();

            }
        } catch (Exception ex) {
        }
        return false;
    }

    public void iniciarcita() throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/citas.txt"));
        String linea = "";

        SimpleDateFormat formatea = new SimpleDateFormat("dd/MM/yyyy");
        if (con_citas != null) con_citas.eliminar();

        try {
            while ((linea = leer.readLine()) != null) {
                citas = new modelo_citas();
                String[] data = linea.split(";");
                java.sql.Date feche_b = java.sql.Date.valueOf(data[1]);
                citas.setId(Integer.parseInt(data[0]));
                citas.setFecha_cita(feche_b);
                citas.setPlaca(data[2]);
                citas.setMotivo(data[3]);

                con_citas.agregar_citas(citas);
            }
        } catch (Exception ex) {
        }
    }


    public modelo_citas gestionar_citas(JDateChooser fecha, Choice placa, TextArea motivo) {
        int id = con_citas.conteo() + 1;

        Date fecha_m = fecha.getDate();
        long d = fecha_m.getTime();
        java.sql.Date fecha_b = new java.sql.Date(d);
        citas.setId(id);
        citas.setFecha_cita(fecha_b);
        citas.setPlaca(placa.getItem(placa.getSelectedIndex()));
        citas.setMotivo(motivo.getText());
            return citas;
    }

    public void crear_cita(modelo_citas citas) {


        try {
            BufferedWriter escribir_usuario = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bd_citas, true)));
            escribir_usuario.write(citas.getId() + ";" + citas.getFecha_cita() + ";" + citas.getPlaca() + ";" + citas.getMotivo());
            escribir_usuario.write("\n");
            escribir_usuario.close();
        } catch (Exception ex) {
        }
    }

    public String[] lista_citas() throws FileNotFoundException {
        verificar_bd_citas();
        iniciarcita();
        int conteo = con_citas.conteo() ;
        int i;

        String[] listar_citas = new String[conteo];

        try {
            for (i = 0; i <= conteo; i++) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

                Date fecha_cita = con_citas.getCitas_p().get(i).getFecha_cita();
                String placa_cita =con_citas.getCitas_p().get(i).getPlaca();
                String motivo_cita  = con_citas.getCitas_p().get(i).getMotivo();

                listar_citas[i] = "hay un cita para " + fecha_cita + " para el auto con placas " + placa_cita + " por el motivo " + motivo_cita;

            }

        } catch (Exception ex) {
            System.out.println(ex);

        }


        return listar_citas;
    }
}
