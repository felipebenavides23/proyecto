package daos;

import java.awt.*;
import java.io.*;

import modelos.modelo_informacion_vehiculo;

import controladores.controlador_auto;

public class DAOS_AUTO {

    modelo_informacion_vehiculo info_auto;
    controlador_auto con_aut;
    File bd_auto = new File("bd/autos.txt");



    public boolean verificar_bd_auto() {
        try {
            if (bd_auto.exists()) {
                con_aut = new controlador_auto();
                return true;
            } else {
                bd_auto.createNewFile();
                verificar_bd_auto();
            }
        } catch (Exception ex) {
        }
        return false;
    }

    public void  iniciarautos() throws FileNotFoundException {
        BufferedReader leer = new BufferedReader(new FileReader("bd/autos.txt"));
        String linea = "";
        if (con_aut != null) con_aut.eliminar();

        try {
            while((linea = leer . readLine()) != null){
                info_auto = new modelo_informacion_vehiculo();
                String [] data = linea.split(";");
                info_auto.setId(Integer.parseInt(data[0]));
                info_auto.setTipo_vehiculo(data[1]);
                info_auto.setMarca(data[2]);
                info_auto.setModelo(data[3]);
                info_auto.setColor(data[4]);
                info_auto.setCilindraje(data[5]);
                info_auto.setPlaca(data[6]);
                info_auto.setDueno(data[7]);

                con_aut.agregar_vehiculo(info_auto);
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public boolean verificar_placas(TextField placa) throws FileNotFoundException {
       return con_aut.confirmar_placas(placa);
    }

    public modelo_informacion_vehiculo gestionar_auto (Choice tipo,TextField modelo,TextField ruedas,TextField puertas, TextField cilin, TextField placas, Choice dueño){

        int id = con_aut.conteo() +1;
        info_auto = new modelo_informacion_vehiculo();
        info_auto.setId(id);
        info_auto.setTipo_vehiculo(tipo.getItem(tipo.getSelectedIndex()));
        info_auto.setMarca(modelo.getText());
        info_auto.setModelo(ruedas.getText());
        info_auto.setColor(puertas.getText());
        info_auto.setCilindraje(cilin.getText());
        info_auto.setPlaca(placas.getText());
        info_auto.setDueno(dueño.getItem(dueño.getSelectedIndex()));

        return  info_auto;

    }

    public void crear_auto(modelo_informacion_vehiculo info_auto) {
        try {
            BufferedWriter escribir_usuario = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(bd_auto, true)));
            escribir_usuario.write(info_auto.getId() + ";"
                    + info_auto.getTipo_vehiculo() + ";"
                    + info_auto.getMarca() + ";"
                    + info_auto.getModelo() + ";"
                    + info_auto.getColor() + ";"
                    + info_auto.getCilindraje() + ";"
                    + info_auto.getPlaca() +  ";"
                    + info_auto.getDueno());
            escribir_usuario.write("\n");
            escribir_usuario.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public String[] listar_placas() throws FileNotFoundException {
        verificar_bd_auto();
        iniciarautos();
        int conteo = con_aut.conteo() + 1;
        int i;
        String[] lista_placas = new String[conteo];
        lista_placas[0] = "";
        try {
            for (i = 0; i <= conteo; i++) {
                String placa_auto = con_aut.getVehiculos().get(i).getPlaca();
                lista_placas[i+1] = placa_auto;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return lista_placas;
    }

    public String[] listar_autos() throws FileNotFoundException {
        verificar_bd_auto();
        iniciarautos();
        int conteo = con_aut.conteo() + 1;
        int i;
        String[] lista_autos = new String[conteo];
        lista_autos[0] = "tipo vehiculo - marca - modelo - color - cilindraje - placa - dueño";
        try {
            for (i = 0; i <= conteo; i++) {
                String tipo_vehiculo = con_aut.getVehiculos().get(i).getTipo_vehiculo();
                String marca = con_aut.getVehiculos().get(i).getMarca();
                String modelo = con_aut.getVehiculos().get(i).getModelo();
                String color = con_aut.getVehiculos().get(i).getColor();
                String cilin = con_aut.getVehiculos().get(i).getCilindraje();
                String placa = con_aut.getVehiculos().get(i).getPlaca();
                String dueno = con_aut.getVehiculos().get(i).getDueno();
                lista_autos[i + 1] = tipo_vehiculo + " - " + marca + " - " + modelo + " - " + color + " - " + cilin + " - " + placa + " - " + dueno;
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return lista_autos;
    }
}
