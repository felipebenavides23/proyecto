package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


public class VISTA_PRINCIPAL extends  vista{


    public VISTA_PRINCIPAL() throws FileNotFoundException {
    }

    public void VISTAP() {

        mf.add(panel1);

        //tittulo
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Label labtitulo = new Label("LOGIN", Label.CENTER);
        gridbag.setConstraints(labtitulo, gbc);
        panel1.add(labtitulo);


        // formulario de inicio de sesion
        //relleno en X
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        // crecion de caja de texto
        TextField t1, t2;
        // caja de texto 1 "ususario"
        Label lab_usuario = new Label("Usuario");
        gbc.gridwidth = GridBagConstraints.BOTH;
        gridbag.setConstraints(lab_usuario, gbc);
        panel1.add(lab_usuario);

        t1 = new TextField();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(t1, gbc);
        panel1.add(t1);

        // caja de texto 2 "ususario"
        Label lab_contraseña = new Label("Contraseña");
        gbc.gridwidth = GridBagConstraints.BOTH;
        gridbag.setConstraints(lab_contraseña, gbc);
        panel1.add(lab_contraseña);

        t2 = new TextField();
        t2.isVisible();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(t2, gbc);
        panel1.add(t2);

        Button bo_inicio_sesion = new Button("Iniciar Sesion");
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.BOTH;
        gridbag.setConstraints(bo_inicio_sesion, gbc);

        bo_inicio_sesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (daos_persona.vericar_datos_corre(t1, t2)) {
                        String[] data = daos_citas.lista_citas();
                        render("listar citas", datos, data);
                    } else {
                        avisos("los datos estan mal ");
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

            }
        });

        panel1.add(bo_inicio_sesion);

        Button bo_crear_cuenta = new Button("Crear Cuenta");
        gbc.fill = GridBagConstraints.NONE;
        //gbc.gridwidth = GridBagConstraints.RELATIVE;
        gridbag.setConstraints(bo_crear_cuenta, gbc);

        bo_crear_cuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                render("crear_sesion", datos, emptyData);
            }
        });
        panel1.add(bo_crear_cuenta);
    }
}
