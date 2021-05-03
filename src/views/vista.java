package views;

import com.toedter.calendar.JDateChooser;
import daos.DAOS_AUTO;
import daos.DAOS_CITAS;
import daos.DAOS_CLIENTE;
import daos.DAOS_PERSONA;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class vista {
    public vista() throws FileNotFoundException {
    }

    public static void main(String args[]) throws FileNotFoundException {
        new vista().run();
    }
    String[] emptyData = {""};
    String[] datos = {""};
    VISTA_PRINCIPAL vistaPrincipal = new VISTA_PRINCIPAL();
    DAOS_PERSONA daos_persona = new DAOS_PERSONA();
    DAOS_CLIENTE daos_cliente = new DAOS_CLIENTE();
    DAOS_AUTO daos_auto = new DAOS_AUTO();
    DAOS_CITAS daos_citas = new DAOS_CITAS();
    Frame mf = new Frame("proyecto jordi");
    Panel panel1 = new Panel();
    GridBagLayout gridbag = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    public void run() {
        render("login", datos, emptyData);
    }

    public void render(String estado, String[] datos, String[] data) {
        limpiarvista();
        switch (estado) {
            case "login":
                vista_p();
                break;
            case "listar citas":
                vista_listar_citas(data);
                break;
            case "crear_sesion":
                vista_crear_sesion();
                break;
            case "ingresar auto":
                vista_regristrar_auto(data, datos);
                break;
            case "crear cita":
                vista_crear_cita(data);
                break;
            case "crear cliente":
                vista_crear_cliente(data);
                break;
        }
        llenarcampo();
    }

    public void vista_p() {
        vistaPrincipal.VISTAP();
    }


    public void vista_crear_sesion() {

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Label lab_crear_sesion = new Label("CREAR SESION", Label.CENTER);
        gridbag.setConstraints(lab_crear_sesion, gbc);
        panel1.add(lab_crear_sesion);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_nombre = new Label("Nombre");
        gridbag.setConstraints(lab_nombre, gbc);
        panel1.add(lab_nombre);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        TextField textbnox_nombre = new TextField();
        gridbag.setConstraints(textbnox_nombre, gbc);
        panel1.add(textbnox_nombre);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_apellido = new Label("Apellido");
        gridbag.setConstraints(lab_apellido, gbc);
        panel1.add(lab_apellido);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        TextField textbnox_apellido = new TextField();
        gridbag.setConstraints(textbnox_apellido, gbc);
        panel1.add(textbnox_apellido);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_correo = new Label("Correo");
        gridbag.setConstraints(lab_correo, gbc);
        panel1.add(lab_correo);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        TextField textbnox_correo = new TextField();
        gridbag.setConstraints(textbnox_correo, gbc);
        panel1.add(textbnox_correo);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_contra = new Label("Contraseña");
        gridbag.setConstraints(lab_contra, gbc);
        panel1.add(lab_contra);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        TextField textbnox_contra = new TextField();
        gridbag.setConstraints(textbnox_contra, gbc);
        panel1.add(textbnox_contra);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        Label lab_contra_confi = new Label("Confirmar Contraseña");
        gridbag.setConstraints(lab_contra_confi, gbc);
        panel1.add(lab_contra_confi);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        TextField textbnox_contra_confi = new TextField();
        gridbag.setConstraints(textbnox_contra_confi, gbc);
        panel1.add(textbnox_contra_confi);


        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.BOTH;
        Button bo_confirmar = new Button("Confirmar");
        gridbag.setConstraints(bo_confirmar, gbc);

        bo_confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (daos_persona.verificar_bd()) {
                    verificacion_usuario(textbnox_nombre, textbnox_apellido, textbnox_correo, textbnox_contra, textbnox_contra_confi);
                } else {
                }
            }
        });

        panel1.add(bo_confirmar);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Button bo_atras = new Button("Atras");
        gridbag.setConstraints(bo_atras, gbc);

        bo_atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                render("login", datos, emptyData);
            }
        });

        panel1.add(bo_atras);
    }

    public void vista_listar_citas(String[] data) {
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Panel panel_i1 = new Panel();
        Panel panel_i2 = new Panel();
        panel_i1.setLayout(gridbag);
        panel_i2.setLayout(gridbag);
        gridbag.setConstraints(panel_i1, gbc);
        gridbag.setConstraints(panel_i2, gbc);
        panel1.add(panel_i1);

        panel1.add(panel_i2);

        // panel 1
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1.0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Label labinicio = new Label("INICIO", Label.CENTER);
        gridbag.setConstraints(labinicio, gbc);
        panel_i1.add(labinicio);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Button bo_crear_citas = new Button("Crear Citas ");
        gridbag.setConstraints(bo_crear_citas, gbc);

        bo_crear_citas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] data = daos_auto.listar_placas();
                    render("crear cita", datos, data);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

            }
        });
        panel_i1.add(bo_crear_citas);

        Button bo_ingresar_cliente = new Button("Insertar Cliente ");
        gridbag.setConstraints(bo_ingresar_cliente, gbc);

        bo_ingresar_cliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] data = daos_cliente.lista_clientes();
                    render("crear cliente", datos, data);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }

            }
        });
        panel_i1.add(bo_ingresar_cliente);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Button bo_ingresar_auto = new Button("Insertar Autos ");
        gridbag.setConstraints(bo_ingresar_auto, gbc);

        bo_ingresar_auto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] datos = daos_cliente.lista_documento();
                    String[] data = daos_auto.listar_autos();
                    render("ingresar auto", datos, data);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        panel_i1.add(bo_ingresar_auto);

        // panel 2
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        JList lista = new JList(data);
        gridbag.setConstraints(lista, gbc);
        panel_i2.add(lista);

    }

    public void vista_crear_cita(String[] data) {
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Label lab_citas = new Label("CREAR CITAS", Label.CENTER);
        gridbag.setConstraints(lab_citas, gbc);
        panel1.add(lab_citas);

        gbc.gridwidth = GridBagConstraints.RELATIVE;
        Label lab_fecha = new Label("Fecha De La Cita");
        gridbag.setConstraints(lab_fecha, gbc);
        panel1.add(lab_fecha);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        JDateChooser calendario = new JDateChooser();
        //java.util.Date Fecha_actual = new Date();
        //calendario.setDate(Fecha_actual);
        gridbag.setConstraints(calendario, gbc);
        panel1.add(calendario);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_placa_cita = new Label("Placa");
        gridbag.setConstraints(lab_placa_cita, gbc);
        panel1.add(lab_placa_cita);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Choice lista_placas = new Choice();
        int n = data.length;
        int i;
        for (i = 0; i < n; i++) {
            lista_placas.add(String.valueOf(data[i]));
        }

        gridbag.setConstraints(lista_placas, gbc);
        panel1.add(lista_placas);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_motivo = new Label("Motivo");
        gridbag.setConstraints(lab_motivo, gbc);
        panel1.add(lab_motivo);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        TextArea text_motivo = new TextArea();
        gridbag.setConstraints(text_motivo, gbc);
        panel1.add(text_motivo);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.BOTH;
        Button bo_crear_cita = new Button("Crear Cita");
        gridbag.setConstraints(bo_crear_cita, gbc);
        bo_crear_cita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (daos_citas.verificar_bd_citas()) {
                    try {
                        verificar_citas(calendario, lista_placas, text_motivo);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
            }
        });
        panel1.add(bo_crear_cita);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Button bo_atras = new Button("Atras");
        gridbag.setConstraints(bo_atras, gbc);
        bo_atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                render("listar citas", datos, emptyData);
            }
        });
        panel1.add(bo_atras);


    }

    public void vista_crear_cliente(String[] data) {

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Panel panel_i1 = new Panel();
        Panel panel_i2 = new Panel();
        panel_i1.setLayout(gridbag);
        panel_i2.setLayout(gridbag);
        gridbag.setConstraints(panel_i1, gbc);
        gridbag.setConstraints(panel_i2, gbc);
        panel1.add(panel_i1);
        panel1.add(panel_i2);
        //panel1
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Label lab_cliente = new Label("REGISTRO DE CLIENTES", Label.CENTER);
        gridbag.setConstraints(lab_cliente, gbc);
        panel_i1.add(lab_cliente);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_nombre_cli = new Label("Nombre");
        gridbag.setConstraints(lab_nombre_cli, gbc);
        panel_i1.add(lab_nombre_cli);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        TextField text_nombre_cli = new TextField();
        gridbag.setConstraints(text_nombre_cli, gbc);
        panel_i1.add(text_nombre_cli);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_apellido_cli = new Label("Apelldios");
        gridbag.setConstraints(lab_apellido_cli, gbc);
        panel_i1.add(lab_apellido_cli);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        TextField text_apellido_cli = new TextField();
        gridbag.setConstraints(text_apellido_cli, gbc);
        panel_i1.add(text_apellido_cli);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_documento_cli = new Label("Documento");
        gridbag.setConstraints(lab_documento_cli, gbc);
        panel_i1.add(lab_documento_cli);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        TextField text_documento_cli = new TextField();
        gridbag.setConstraints(text_documento_cli, gbc);
        panel_i1.add(text_documento_cli);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_correo_cli = new Label("Correo");
        gridbag.setConstraints(lab_correo_cli, gbc);
        panel_i1.add(lab_correo_cli);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        TextField text_correo_cli = new TextField();
        gridbag.setConstraints(text_correo_cli, gbc);
        panel_i1.add(text_correo_cli);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.BOTH;
        Button bo_crear_cliente = new Button("Regristar");
        gridbag.setConstraints(bo_crear_cliente, gbc);
        bo_crear_cliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (daos_cliente.verificar_bd_clientes()) {
                    try {
                        verificar_cliente(text_nombre_cli, text_apellido_cli, text_documento_cli, text_correo_cli);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }

                } else {
                }
            }
        });
        panel_i1.add(bo_crear_cliente);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Button bo_atras = new Button("Atras");
        gridbag.setConstraints(bo_atras, gbc);

        bo_atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                render("listar citas", datos, emptyData);
            }
        });
        panel_i1.add(bo_atras);

        // panel 2
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        JList lista = new JList(data);
        gridbag.setConstraints(lista, gbc);
        panel_i2.add(lista);
    }

    public void vista_regristrar_auto(String[] datos_l, String[] data) {

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Panel panel_i1 = new Panel();
        Panel panel_i2 = new Panel();
        panel_i1.setLayout(gridbag);
        panel_i2.setLayout(gridbag);
        gridbag.setConstraints(panel_i1, gbc);
        gridbag.setConstraints(panel_i2, gbc);
        panel1.add(panel_i1);

        panel1.add(panel_i2);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Label lab_autos = new Label("REGISTRO DE CARROS", Label.CENTER);
        gridbag.setConstraints(lab_autos, gbc);
        panel_i1.add(lab_autos);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_dueños = new Label("Dueños");
        gridbag.setConstraints(lab_dueños, gbc);
        panel_i1.add(lab_dueños);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Choice lista_dueños = new Choice();
        int n = data.length;
        int i;
        for (i = 0; i < n; i++) {
            lista_dueños.add(String.valueOf(data[i]));
        }
        gridbag.setConstraints(lista_dueños, gbc);
        panel_i1.add(lista_dueños);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_tipo = new Label("Tipo De Vehiculo");
        gridbag.setConstraints(lab_tipo, gbc);
        panel_i1.add(lab_tipo);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Choice lista_tipo = new Choice();
        lista_tipo.add("");
        lista_tipo.add("Ciclomotor");
        lista_tipo.add("Motocicleta");
        lista_tipo.add("Motocarro");
        lista_tipo.add("Automóvil de tres ruedas");
        lista_tipo.add("Autobús");
        lista_tipo.add("Camión");
        lista_tipo.add("Motocultor");
        lista_tipo.add("Tractocarro");
        gridbag.setConstraints(lista_tipo, gbc);
        panel_i1.add(lista_tipo);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_placas = new Label("Placas");
        gridbag.setConstraints(lab_placas, gbc);
        panel_i1.add(lab_placas);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        TextField text_placas = new TextField();
        gridbag.setConstraints(text_placas, gbc);
        panel_i1.add(text_placas);

        gbc.gridwidth = GridBagConstraints.BOTH;
        Label lab_modelo = new Label("Modelo");
        gridbag.setConstraints(lab_modelo, gbc);
        panel_i1.add(lab_modelo);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        TextField text_modelo = new TextField();
        gridbag.setConstraints(text_modelo, gbc);
        panel_i1.add(text_modelo);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = GridBagConstraints.BOTH;
        Button bo_regristrauto = new Button("Registrar");
        gridbag.setConstraints(bo_regristrauto, gbc);

        bo_regristrauto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (daos_auto.verificar_bd_auto()) {
                    try {
                        verificar_auto(lista_dueños, lista_tipo, text_placas, text_modelo);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
            }
        });

        panel_i1.add(bo_regristrauto);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        Button bo_atras = new Button("Atras");
        gridbag.setConstraints(bo_atras, gbc);

        bo_atras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                render("listar citas", datos, emptyData);
            }
        });

        panel_i1.add(bo_atras);

        // panel 2
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        JList lista = new JList(datos_l);
        gridbag.setConstraints(lista, gbc);
        panel_i2.add(lista);
    }

    public void limpiarvista() {
        panel1.removeAll();
        mf.repaint();

    }

    public void llenarcampo() {
        panel1.setLayout(gridbag);
        gbc.fill = GridBagConstraints.BOTH;
        panel1.setBackground(Color.LIGHT_GRAY);
        gbc.weightx = 1.0;
        gridbag.setConstraints(panel1, gbc);
        mf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        mf.setVisible(true);
        mf.setSize(500, 700);
        mf.setLayout(gridbag);
        mf.setLocationRelativeTo(null);
    }

    public void avisos(String te) {
        JOptionPane.showMessageDialog(null, te);
    }


    public void verificacion_usuario(TextField nombre, TextField apellido, TextField correo, TextField contra, TextField confi) {
        String vacio = "";
        if (vacio.equals(nombre.getText())) {
            avisos(" falta  llenar nombre");
        } else {
            if (vacio.equals(apellido.getText())) {
                avisos(" falta llenar apellido");
            } else {
                if (vacio.equals(correo.getText())) {
                    avisos("falta llenar correo");
                } else {
                    try {
                        if (daos_persona.verificar_correo(correo)) {
                            avisos("ya existe el correo");
                        } else {
                            if (vacio.equals(contra.getText())) {
                                avisos("falta llenar contraseña");
                            } else {
                                if (contra.getText().equals(confi.getText())) {
                                    daos_persona.validar_id(nombre, apellido, correo, contra);
                                    render("crear_sesion", datos, emptyData);
                                    avisos("se creo con exito el usuario");
                                } else {
                                    avisos("las contraseñas no son iguales");
                                }
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public void verificar_cliente(TextField nombre, TextField apelldio, TextField documento, TextField correo) throws FileNotFoundException {
        String vacio = "";
        if (vacio.equals(nombre.getText())) {
            avisos("nombre del cliente esta sin llenar");
        } else {
            if (vacio.equals(apelldio.getText())) {
                avisos("el apellido del cliente esta sin llenar");
            } else {
                if (vacio.equals(documento.getText())) {
                    avisos("el documento esta sin llenar");
                } else {
                    try {
                        if (daos_cliente.verificar_documento(documento)) {
                            avisos("el documento ya esta registrado");
                        } else {
                            if (vacio.equals(correo.getText())) {
                                avisos("el correo del cliente esta sin llenar");
                            } else {
                                daos_cliente.validar_id_cliente(nombre, apelldio, documento, correo);
                                render("listar citas", datos, emptyData);
                                avisos("se registro el cliente de forma exitosa ");
                            }
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public void verificar_auto(Choice dueño, Choice tipo, TextField placa, TextField modelo) throws FileNotFoundException {
        String vacio = "";
        if (vacio.equals(dueño.getItem(dueño.getSelectedIndex()))) {
            avisos("no selecciono dueño");
        } else {
            if (vacio.equals(tipo.getItem(tipo.getSelectedIndex()))) {
                avisos("no seleciono tipo de vehivulo");

            } else {
                if (vacio.equals(placa.getText())) {
                    avisos("la clase esta sin llenar");
                } else {
                    if (daos_auto.verificar_placas(placa)) {
                        avisos("la placa ya existe");
                    } else {
                        if (vacio.equals(modelo.getText())) {
                            avisos("el modelo esta sin llenar ");
                        } else {
                            daos_auto.validar_id_auto(dueño, tipo, placa, modelo);
                            render("listar citas", datos, emptyData);
                            avisos("se registro exitosamente el vehiculo");
                        }
                    }
                }
            }
        }
    }

    public void verificar_citas(JDateChooser fecha_c, Choice placa, TextArea motivo) throws FileNotFoundException {
        Date fecha_m = fecha_c.getDate();
        java.util.Date Fecha_actual = new Date();
        String vacio = "";
        if (fecha_c.getDate() == null) {
            avisos("seleccione fecha para la cita");
        } else {
            if (Fecha_actual.getTime() > fecha_m.getTime()) {
                avisos("la fecha no es correcta");
            } else {
                if (vacio.equals(placa.getItem(placa.getSelectedIndex()))) {
                    avisos("seleccione una placa");
                } else {
                    if (vacio.equals(motivo.getText())) {
                        avisos("falta lleanr el motivo");
                    } else {
                        daos_citas.validar_id_cita(fecha_c, placa, motivo);
                        avisos("la cita se registro con exito");
                        render("listar citas", datos, emptyData);
                    }
                }
            }
        }

    }


}

