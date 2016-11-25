<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.geneticos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author demg
 */
public class Ventana extends JFrame implements ActionListener {

    private Flor[] flores;
    private Grafico graf;
    private JComboBox cbmTamano;
    private JComboBox cbmColor;
    private JComboBox cbmAltura;
    private JComboBox cbmTallo;
    private JButton btnIniciar;
    private JButton btnInicializar;
    private JButton btnDetener;
    private JLabel lblGeneraciones;
    private JLabel lblMutaciones;

    // Algoritmo Genetico
    private int generaciones, mutaciones;
    private int padreA, padreB;
    private Timer timer;

    public Ventana() {
        setTitle("Algoritmos Geneticos");
        setSize(820, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        add(panel());
        initFlores();
        graf = new Grafico(flores);
        graf.setBounds(0, 120, 850, 600);
        add(graf);
        timer = new Timer(500, this);
        // Timer.start();
    }

    private JPanel panel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 850, 120);

        cbmAltura = new JComboBox();
        cbmAltura.addItem("Alto");
        cbmAltura.addItem("Medio");
        cbmAltura.addItem("Bajo");

        cbmColor = new JComboBox();
        cbmColor.addItem("Rojo");
        cbmColor.addItem("Azul");
        cbmColor.addItem("Amarillo");

        cbmTamano = new JComboBox();
        cbmTamano.addItem("Pequeño");
        cbmTamano.addItem("Normal");
        cbmTamano.addItem("Grande");

        cbmTallo = new JComboBox();
        cbmTallo.addItem("Pequeño");
        cbmTallo.addItem("Normal");
        cbmTallo.addItem("Grande");

        cbmAltura.setBounds(65, 20, 100, 20);
        cbmColor.setBounds(240, 20, 100, 20);
        cbmTamano.setBounds(455, 20, 100, 20);
        cbmTallo.setBounds(635, 20, 100, 20);

        JLabel lblAltura = new JLabel("Altura:");
        lblAltura.setBounds(20, 20, 50, 20);

        JLabel lblColor = new JLabel("Color:");
        lblColor.setBounds(200, 20, 50, 20);

        JLabel lblTamano = new JLabel("Tamaño:");
        lblTamano.setBounds(400, 20, 50, 20);

        JLabel lblTallo = new JLabel("Tallo:");
        lblTallo.setBounds(600, 20, 50, 20);

        btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(100, 80, 90, 22);
        btnIniciar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
                btnIniciar.setText("Iniciar");
                btnDetener.setEnabled(true);
            }
        });

        btnInicializar = new JButton("Reiniciar");
        btnInicializar.setBounds(200, 80, 90, 22);
        btnInicializar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnDetener = new JButton("Detener");
        btnDetener.setBounds(300, 80, 90, 22);
        btnDetener.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                btnIniciar.setText("Continuar");
                btnDetener.setEnabled(false);
            }
        });

        lblGeneraciones = new JLabel("Generaciones:");
        lblGeneraciones.setBounds(400, 80, 200, 20);

        lblMutaciones = new JLabel("Mutaciones:");
        lblMutaciones.setBounds(550, 80, 200, 20);

        panel.add(lblGeneraciones);
        panel.add(lblMutaciones);

        panel.add(cbmAltura);
        panel.add(cbmColor);
        panel.add(cbmTamano);
        panel.add(cbmTallo);

        panel.add(lblAltura);
        panel.add(lblColor);
        panel.add(lblTamano);
        panel.add(lblTallo);

        panel.add(btnIniciar);
        panel.add(btnInicializar);
        panel.add(btnDetener);

        return panel;
    }

    private void initFlores() {
        flores = new Flor[10];
        Random ran = new Random();
        for (int i = 0; i < flores.length; i++) {
            Flor f = new Flor();
            f.setX(i * 80 + 40);
            int[] crom = f.getCromosoma();
            // altura
            crom[0] = ran.nextInt(276) + 25;
            // color
            crom[1] = ran.nextInt(3);
            // color del tallo
            crom[2] = ran.nextInt(3);
            // ancho del tallo
            crom[3] = ran.nextInt(11) + 5;
            // tamaño de la flor
            crom[4] = ran.nextInt(61) + 20;
            // tamaño del centro de la flor
            crom[5] = ran.nextInt(11) + 5;

            flores[i] = f;
        }
    }

    private void calcularAdaptacion() {
        double altura, color, tamano, ancho;
        altura = color = tamano = ancho = 0;

        for (int i = 0; i < flores.length; i++) {
            int[] crom = flores[i].getCromosoma();

            // comparamos la altura
            if (cbmAltura.getSelectedIndex() == 0) {
                altura = crom[0] / 100;
            } else if (cbmAltura.getSelectedIndex() == 1) {
                altura = crom[0] / 200;
            } else if (cbmAltura.getSelectedIndex() == 2) {
                altura = crom[0] / 300;
            }

            if (altura > 1.0) {
                altura = 1 / altura;
            }

            // color
            if (cbmColor.getSelectedIndex() == crom[1]) {
                color = 1.0;
            } else {
                color = 0.0;
            }

            // tamano
            if (cbmTamano.getSelectedIndex() == 0) { // grande
                tamano = crom[4] / 40;
            } else if (cbmTamano.getSelectedIndex() == 1) { // mediano
                tamano = crom[4] / 60;
            } else if (cbmTamano.getSelectedIndex() == 2) { // pequeño
                tamano = crom[4] / 80;
            }

            if (tamano > 1.0) {
                tamano = 1 / tamano;
            }

            // tallo
            if (cbmTallo.getSelectedIndex() == 0) {//grande
                ancho = crom[3] / 15;
            } else if (cbmTallo.getSelectedIndex() == 1) {// normal
                ancho = crom[3] / 20;
            } else if (cbmTallo.getSelectedIndex() == 2) {//pequeño
                ancho = crom[3] / 25;
            }

            if (ancho > 1.0) {
                ancho = 1 / ancho;
            }

            double adaptacion = (altura + color + tamano + ancho) / 4;
            flores[i].setAdaptacion(adaptacion);
        }
    }

    private void seleccionarPadres() {
        padreA = 0;
        padreB = 0;

        for (int i = 0; i < flores.length; i++) {
            if (flores[i].getAdaptacion() > flores[padreA].getAdaptacion()) {
                padreA = i;
            }
        }

        for (int i = 0; i < flores.length; i++) {
            if (flores[i].getAdaptacion() > flores[padreB].getAdaptacion() && padreA != i) {
                padreB = i;
            }
        }
    }

    private void crossover() {
        Flor pa = new Flor();
        Flor pb = new Flor();

        int[] cromA = new int[6];
        int[] cromB = new int[6];

        for (int i = 0; i < 6; i++) {
            cromA[i] = flores[padreA].getCromosoma()[i];
            cromB[i] = flores[padreB].getCromosoma()[i];
        }

        pa.setCromosoma(cromA);
        pb.setCromosoma(cromB);
        Random r = new Random();

        int desde = r.nextInt(3);
        int hasta = r.nextInt(5) + desde;

        for (int i = 0; i < flores.length; i++) {
            for (int j = desde; j < hasta; j++) {
                if (r.nextInt(2) == 0) {
                    flores[i].getCromosoma()[j] = pa.getCromosoma()[j];
                } else {
                    flores[i].getCromosoma()[j] = pb.getCromosoma()[j];
                }
                if (r.nextInt(100) == 50) {
                    mutaciones++;
                    switch (j) {
                        case 0:
                            flores[i].getCromosoma()[0] = r.nextInt(276) + 25;
                            break;
                        case 1:
                            flores[i].getCromosoma()[1] = r.nextInt(3);
                            break;
                        case 3:
                            flores[i].getCromosoma()[3] = r.nextInt(61) + 20;
                            break;
                        case 4:
                            flores[i].getCromosoma()[4] = r.nextInt(61) + 20;
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        generaciones++;
        lblGeneraciones.setText("Generaciones: " + generaciones);
        lblMutaciones.setText("Mutaciones: " + mutaciones);
        calcularAdaptacion();
        seleccionarPadres();
        crossover();
        graf.repaint();
    }
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.geneticos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author demg
 */
public class Ventana extends JFrame implements ActionListener {

    private Flor[] flores;
    private Grafico graf;
    private JComboBox cbmTamano;
    private JComboBox cbmColor;
    private JComboBox cbmAltura;
    private JButton btnIniciar;
    private JButton btnInicializar;
    private JButton btnDetener;
    private JLabel lblGeneraciones;
    private JLabel lblMutaciones;

    // Algoritmo Genetico
    private int generaciones, mutaciones;
    private int padreA, padreB;
    private Timer timer;

    public Ventana() {
        setTitle("Algoritmos Geneticos");
        setSize(820, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        add(panel());
        initFlores();
        graf = new Grafico(flores);
        graf.setBounds(0, 120, 850, 600);
        add(graf);
        timer = new Timer(500, this);
        // Timer.start();
    }

    private JPanel panel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 850, 120);

        cbmAltura = new JComboBox();
        cbmAltura.addItem("Alto");
        cbmAltura.addItem("Medio");
        cbmAltura.addItem("Bajo");

        cbmColor = new JComboBox();
        cbmColor.addItem("Rojo");
        cbmColor.addItem("Azul");
        cbmColor.addItem("Amarillo");

        cbmTamano = new JComboBox();
        cbmTamano.addItem("Pequeño");
        cbmTamano.addItem("Normal");
        cbmTamano.addItem("Grande");

        cbmAltura.setBounds(150, 20, 100, 20);
        cbmColor.setBounds(350, 20, 100, 20);
        cbmTamano.setBounds(555, 20, 100, 20);

        JLabel lblAltura = new JLabel("Altura:");
        lblAltura.setBounds(100, 20, 50, 20);

        JLabel lblColor = new JLabel("Color:");
        lblColor.setBounds(300, 20, 50, 20);

        JLabel lblTamano = new JLabel("Tamaño:");
        lblTamano.setBounds(500, 20, 50, 20);

        btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(100, 80, 90, 22);
        btnIniciar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });

        btnInicializar = new JButton("Reiniciar");
        btnInicializar.setBounds(200, 80, 90, 22);

        btnDetener = new JButton("Detener");
        btnDetener.setBounds(300, 80, 90, 22);

        lblGeneraciones = new JLabel("Generaciones:");
        lblGeneraciones.setBounds(400, 80, 120, 20);

        lblMutaciones = new JLabel("Mutaciones:");
        lblMutaciones.setBounds(520, 80, 100, 20);

        panel.add(lblGeneraciones);
        panel.add(lblMutaciones);

        panel.add(cbmAltura);
        panel.add(cbmColor);
        panel.add(cbmTamano);

        panel.add(lblAltura);
        panel.add(lblColor);
        panel.add(lblTamano);

        panel.add(btnIniciar);
        panel.add(btnInicializar);
        panel.add(btnDetener);

        return panel;
    }

    private void initFlores() {
        flores = new Flor[10];
        Random ran = new Random();
        for (int i = 0; i < flores.length; i++) {
            Flor f = new Flor();
            f.setX(i * 80 + 40);
            int[] crom = f.getCromosoma();
            // altura
            crom[0] = ran.nextInt(276) + 25;
            // color
            crom[1] = ran.nextInt(3);
            // color del tallo
            crom[2] = ran.nextInt(3);
            // ancho del tallo
            crom[3] = ran.nextInt(11) + 5;
            // tamaño de la flor
            crom[4] = ran.nextInt(61) + 20;
            // tamaño del centro de la flor
            crom[5] = ran.nextInt(11) + 5;

            flores[i] = f;
        }
    }

    private void calcularAdaptacion() {
        double altura, color, tamano;
        altura = color = tamano = 0;

        for (int i = 0; i < flores.length; i++) {
            int[] crom = flores[i].getCromosoma();

            // comparamos la altura
            if (cbmAltura.getSelectedIndex() == 0) {
                altura = crom[0] / 100;
            } else if (cbmAltura.getSelectedIndex() == 1) {
                altura = crom[0] / 200;
            } else if (cbmAltura.getSelectedIndex() == 2) {
                altura = crom[0] / 300;
            }

            if (altura > 1.0) {
                altura = 1 / altura;
            }

            // color
            if (cbmColor.getSelectedIndex() == crom[1]) {
                color = 1.0;
            } else {
                color = 0.0;
            }

            // tamano
            if (cbmTamano.getSelectedIndex() == 0) { // grande
                tamano = crom[4] / 40;
            } else if (cbmTamano.getSelectedIndex() == 1) { // mediano
                tamano = crom[4] / 60;
            } else if (cbmTamano.getSelectedIndex() == 2) { // pequeño
                tamano = crom[4] / 80;
            }

            if (tamano > 1.0) {
                tamano = 1 / tamano;
            }

            double adaptacion = (altura + color + tamano) / 3;
            flores[i].setAdaptacion(adaptacion);
        }
    }

    private void seleccionarPadres() {
        padreA = 0;
        padreB = 0;

        for (int i = 0; i < flores.length; i++) {
            if (flores[i].getAdaptacion() > flores[padreA].getAdaptacion()) {
                padreA = i;
            }
        }

        for (int i = 0; i < flores.length; i++) {
            if (flores[i].getAdaptacion() > flores[padreB].getAdaptacion() && padreA != i) {
                padreB = i;
            }
        }
    }

    private void crossover() {
        Flor pa = new Flor();
        Flor pb = new Flor();

        int[] cromA = new int[6];
        int[] cromB = new int[6];

        for (int i = 0; i < 6; i++) {
            cromA[i] = flores[padreA].getCromosoma()[i];
            cromB[i] = flores[padreB].getCromosoma()[i];
        }

        pa.setCromosoma(cromA);
        pb.setCromosoma(cromB);
        
        Random r = new Random();
        int desde = r.nextInt(3);
        int hasta = r.nextInt(5) + desde;

        for (int i = 0; i < flores.length; i++) {
            for (int j = desde; j < hasta; j++) {
                if (r.nextInt(2) == 0) {
                    flores[i].getCromosoma()[j] = pa.getCromosoma()[j];
                } else {
                    flores[i].getCromosoma()[j] = pb.getCromosoma()[j];
                }
                if (r.nextInt(100) == 50) {
                    mutaciones++;
                    switch (j) {
                        case 0:
                            flores[i].getCromosoma()[0] = r.nextInt(276) + 25;
                            break;
                        case 1:
                            flores[i].getCromosoma()[1] = r.nextInt(3);
                            break;
                        case 4:
                            flores[i].getCromosoma()[4] = r.nextInt(61) + 20;
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        generaciones++;
        lblGeneraciones.setText("Generaciones: " + generaciones);
        lblMutaciones.setText("Mutaciones: " + mutaciones);
        calcularAdaptacion();
        seleccionarPadres();
        crossover();
        graf.repaint();
    }
}
>>>>>>> Stable
