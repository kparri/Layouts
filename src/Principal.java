import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.text.html.HTMLEditorKit;

public class Principal extends JFrame {
    JPanel panelPrincipal, Tarjetauno, TarjetaDos, TarjetaTres, TarjetaCuatro, TarjetaCinco, panelInferior, paneldibujo, panelBajo, panelEast;
    JTextPane datos;
    FlowLayout fl, f2;
    CardLayout tarjetas;
    JTextArea informacion;
    JButton siguiente, cancelar, atras, generar, validar, salir, guardar;
    JLabel nombre, email, contraseña;
    JTextField textonombre, textoemail, errorEmail, errorContr;
    JPasswordField textocontraseña;
    JComboBox provincia, pais;
    JCheckBox confirmar;
    File ruta;
    int numeropanel = 1;
    boolean comprobar = false;
    boolean comprobar2 = false;
    String almacenado, logo;

    public Principal() {
        this.initdiseño();
        this.inittarjeta1();
        this.inittarjeta2();
        this.inittarjeta3();
        this.inittarjeta4();
        this.inittarjeta5();
        this.initbotones();
        this.initcontrolbotones();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp = new GradientPaint(20, 40, Color.red, 100.0F, 150.0F, Color.white);

        g2d.setPaint(gp);



        g2d.fillOval(20, 40, 100, 100);
        g2d.setColor(Color.white);

        g2d.fillOval(37, 57, 65, 65);
        g2d.setColor(Color.black);

        g2d.drawString("KPARRI" , 46, 93);


    }

    public void initcontrolbotones() {
        this.siguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Principal.this.tarjetas.next(Principal.this.panelPrincipal);
                ++Principal.this.numeropanel;
                if (Principal.this.numeropanel != 5 && Principal.this.numeropanel != 4 && Principal.this.numeropanel != 2) {
                    Principal.this.siguiente.setEnabled(true);
                } else {
                    Principal.this.siguiente.setEnabled(false);
                }

                Principal.this.atras.setEnabled(true);
            }
        });
        this.atras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Principal.this.tarjetas.previous(Principal.this.panelPrincipal);
                --Principal.this.numeropanel;
                if (Principal.this.numeropanel == 1) {
                    Principal.this.atras.setEnabled(false);
                } else {
                    Principal.this.atras.setEnabled(true);
                }

                Principal.this.siguiente.setEnabled(true);
            }
        });
        this.cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void initbotones() {
        this.siguiente = new JButton("Siguiente");
        this.cancelar = new JButton("Salir");
        this.atras = new JButton("Atras");
        this.atras.setEnabled(false);
        this.siguiente.setEnabled(true);
        this.panelInferior.add(this.cancelar);
        this.panelInferior.add(this.atras);
        this.panelInferior.add(this.siguiente);
    }

    private void inittarjeta5() {
        this.salir = new JButton("Salir");
        this.TarjetaCinco.add(this.salir);
        this.salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.guardar = new JButton("Guardar");
        this.TarjetaCinco.add(this.guardar);
        this.guardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser elegirruta = new JFileChooser();
                elegirruta.setFileSelectionMode(1);
                int resultado = elegirruta.showOpenDialog((Component)null);
                if (resultado == 0) {
                    Principal.this.ruta = elegirruta.getSelectedFile();
                }

                File nuevofichero = new File(Principal.this.ruta, "Datos.txt");

                try {
                    FileWriter fw = new FileWriter(nuevofichero);
                    Throwable var6 = null;

                    try {
                        fw.write(Principal.this.textonombre.getText());
                        fw.write(" ");
                        fw.write(Principal.this.textocontraseña.getText());
                        fw.write(" ");
                        fw.write(Principal.this.textoemail.getText());
                        fw.write(" ");
                        fw.write(String.valueOf(Principal.this.pais.getSelectedItem()));
                        fw.write(" ");
                        fw.write(String.valueOf(Principal.this.provincia.getSelectedItem()));
                        fw.flush();
                    } catch (Throwable var16) {
                        var6 = var16;
                        throw var16;
                    } finally {
                        if (fw != null) {
                            if (var6 != null) {
                                try {
                                    fw.close();
                                } catch (Throwable var15) {
                                    var6.addSuppressed(var15);
                                }
                            } else {
                                fw.close();
                            }
                        }

                    }
                } catch (IOException var18) {
                    var18.printStackTrace();
                }

            }
        });
    }

    private void inittarjeta4() {
        this.datos = new JTextPane();
        this.TarjetaCuatro.add(this.datos);
        this.datos.setFocusable(false);
        this.datos.setBackground(Color.white);
        this.datos.setPreferredSize(new Dimension(400, 200));
        this.confirmar = new JCheckBox("Al marcar la casillas acedes a guardar tus datos");
        this.confirmar.setBackground(Color.gray);
        this.confirmar.setBounds(180,300,50,30);
        this.TarjetaCuatro.add(this.confirmar);
        this.confirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Principal.this.siguiente.setEnabled(true);
            }
        });
        this.confirmar.setBackground(Color.WHITE);
        this.confirmar.setPreferredSize(new Dimension(400, 30));
        HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
        this.datos.setEditorKit(htmlEditorKit);
        this.generar = new JButton("Generar");
        this.generar.setFont(new Font("Monospaced", 0, 16));
        this.generar.setOpaque(true);
        this.generar.setBackground(Color.WHITE);
        this.generar.setBorder(new LineBorder(Color.DARK_GRAY));
        this.generar.setForeground(Color.BLACK);
        this.TarjetaCuatro.add(this.generar);
        this.generar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Principal.this.datos.setText("Nombre: " + Principal.this.textonombre.getText() + "<br><i>Correo: </i> " + Principal.this.textoemail.getText() + "<br><i>Contraseña: </i> " + Principal.this.textocontraseña.getText() + "<br><i>Pais: </i><br>" + Principal.this.pais.getSelectedItem() + "<br><i>Provincia: </i>" + Principal.this.provincia.getSelectedItem() + "<br>");
            }
        });
        System.out.println(this.almacenado);
        this.TarjetaCuatro.add(this.datos);
    }

    private void inittarjeta3() {
        this.pais = new JComboBox();
        this.pais.addItem("Pais");
        this.pais.addItem("España");
        this.pais.addItem("Estados Unidos");
        this.pais.setPreferredSize(new Dimension(200, 30));
        this.TarjetaTres.add(this.pais);
        this.provincia = new JComboBox();
        this.provincia.setPreferredSize(new Dimension(200, 30));
        this.TarjetaTres.add(this.provincia);
        this.pais.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File lista;
                FileReader lector;
                BufferedReader BR;
                String linea;
                if (Principal.this.pais.getSelectedItem() == "Estados Unidos") {
                    Principal.this.provincia.removeAllItems();
                    Principal.this.provincia.getSelectedItem();

                    try {
                        lista = new File("C:\\Users\\aparr\\IdeaProjects\\Layouts\\Estados Unidos.txt");
                        lector = new FileReader(lista);
                        BR = new BufferedReader(lector);

                        for(linea = BR.readLine(); linea != null; linea = BR.readLine()) {
                            Principal.this.provincia.addItem(linea);
                        }
                    } catch (Exception var7) {
                    }
                }

                if (Principal.this.pais.getSelectedItem() == "España") {
                    Principal.this.provincia.removeAllItems();
                    Principal.this.provincia.setSelectedItem((Object)null);

                    try {
                        lista = new File("C:\\Users\\aparr\\IdeaProjects\\Layouts\\España.txt");
                        lector = new FileReader(lista);
                        BR = new BufferedReader(lector);

                        for(linea = BR.readLine(); linea != null; linea = BR.readLine()) {
                            Principal.this.provincia.addItem(linea);
                        }
                    } catch (Exception var6) {
                    }
                }

            }
        });
    }

    private void inittarjeta2() {
        this.nombre = new JLabel("Nombre");
        this.TarjetaDos.add(this.nombre);
        this.nombre.setBounds(40, 10, 100, 35);
        this.email = new JLabel("Correo");
        this.TarjetaDos.add(this.email);
        this.email.setBounds(40, 70, 100, 35);
        this.contraseña = new JLabel("Contraseña");
        this.TarjetaDos.add(this.contraseña);
        this.contraseña.setBounds(20, 130, 100, 30);
        this.textonombre = new JTextField("");
        this.TarjetaDos.add(this.textonombre);
        this.textonombre.setBounds(90, 10, 100, 30);
        this.textoemail = new JTextField("");
        this.TarjetaDos.add(this.textoemail);
        this.textoemail.setBounds(90, 70, 100, 30);
        this.textocontraseña = new JPasswordField("");
        this.textocontraseña.setBounds(90, 130, 100, 30);
        this.TarjetaDos.add(this.textocontraseña);
        this.errorEmail = new JTextField("El correo debe contener '@' y un '.'");
        this.TarjetaDos.add(this.errorEmail);
        this.errorEmail.setBounds(200, 70, 250, 30);
        this.errorEmail.setForeground(Color.red);
        this.errorEmail.setBorder(new LineBorder(Color.WHITE));
        this.errorEmail.setVisible(false);
        this.errorContr = new JTextField("8 y 16 caracteres, una letramayúscula, una minúscula y  un caracter especial");
        this.TarjetaDos.add(this.errorContr);
        this.errorContr.setBounds(200, 130, 250, 30);
        this.errorContr.setForeground(Color.red);
        this.errorContr.setBorder(new LineBorder(Color.WHITE));
        this.errorContr.setVisible(false);
        this.validar = new JButton("Validar");
        this.validar.setFont(new Font("Roboto", 0, 10));
        this.validar.setBounds(100, 200, 80, 30);
        this.TarjetaDos.add(this.validar);
        this.validar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; !Principal.this.comprobar; ++i) {
                    String mail = Principal.this.textoemail.getText();
                    if (mail.charAt(i) == '@') {
                        Principal.this.comprobar = true;
                        Principal.this.errorEmail.setVisible(false);
                    } else {
                        Principal.this.errorEmail.setVisible(true);
                    }

                    if (mail.charAt(i) == '.') {
                        Principal.this.comprobar = true;
                        Principal.this.errorEmail.setVisible(false);
                    } else {
                        Principal.this.errorEmail.setVisible(true);
                    }


                }

                if (!Principal.this.textocontraseña.getText().matches("")) {
                    Principal.this.errorContr.setVisible(false);
                    Principal.this.comprobar2 = true;
                } else {
                    Principal.this.errorContr.setVisible(true);
                }

                if (Principal.this.comprobar || Principal.this.comprobar2) {
                    Principal.this.siguiente.setEnabled(true);
                }

            }
        });
    }

    private void inittarjeta1() {
        this.informacion = new JTextArea("Practica de layouts. " + "\nPulse Siguiente para avanzar entre las pestañas.");
        this.Tarjetauno.add(this.informacion);
        this.informacion.setFocusable(false);
    }

    private void initdiseño() {
        this.setLocationRelativeTo((Component)null);
        this.setLayout(new BorderLayout());
        this.fl = new FlowLayout();
        this.f2 = new FlowLayout();
        this.panelBajo = new JPanel();
        this.panelBajo.setPreferredSize(new Dimension(100, 50));
        this.add(this.panelBajo, "South");
        this.panelInferior = new JPanel();
        this.panelInferior.setBackground(Color.WHITE);
        this.paneldibujo = new JPanel();
        this.paneldibujo.setBackground(Color.gray);
        this.paneldibujo.setPreferredSize(new Dimension(120, 100));
        this.panelEast = new JPanel();
        this.panelEast.setBackground(Color.gray);
        this.panelEast.setPreferredSize(new Dimension(120, 100));
        this.add(this.panelInferior, "South");
        this.add(this.paneldibujo, "West");
        this.add(this.panelEast, "East");
        this.tarjetas = new CardLayout();
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(this.tarjetas);
        this.Tarjetauno = new JPanel();
        this.TarjetaDos = new JPanel();
        this.TarjetaTres = new JPanel();
        this.TarjetaCuatro = new JPanel();
        this.TarjetaCinco = new JPanel();
        this.Tarjetauno.setBackground(Color.WHITE);
        this.TarjetaDos.setBackground(Color.WHITE);
        this.TarjetaTres.setBackground(Color.WHITE);
        this.TarjetaCuatro.setBackground(Color.WHITE);
        this.TarjetaCinco.setBackground(Color.WHITE);
        this.panelPrincipal.add(this.Tarjetauno, "1");
        this.panelPrincipal.add(this.TarjetaDos, "2");
        this.panelPrincipal.add(this.TarjetaTres, "3");
        this.panelPrincipal.add(this.TarjetaCuatro, "4");
        this.panelPrincipal.add(this.TarjetaCinco, "5");
        this.tarjetas.show(this.panelPrincipal, "1");
        this.add(this.panelPrincipal, "Center");
        this.TarjetaDos.setLayout((LayoutManager)null);
    }

    public static void main(String[] args) {
        Principal formulario = new Principal();
        formulario.setBounds(150, 150, 700, 400);
        formulario.setTitle("Practica Layouts");
        formulario.setDefaultCloseOperation(3);
        formulario.setVisible(true);
        formulario.setResizable(true);
    }
}
