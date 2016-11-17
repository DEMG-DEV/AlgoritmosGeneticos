/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.geneticos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author demg
 */
public class Grafico extends JPanel {
    
    private Flor[] flores;
    private final int PISO = 500;
    
    public Grafico(Flor[] flores) {
        this.flores = flores;
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2d = (Graphics2D) g;

        // PISO
        g2d.setColor(Color.GREEN);
        g2d.drawLine(0, PISO, 850, PISO);
        
        for (Flor f : flores) {
            int[] crom = f.getCromosoma();

            // selecciona el color del tallo de la flor
            switch (crom[2]) {
                case 0:
                    g2d.setColor(new Color(0, 100, 0));
                    break;
                case 1:
                    g2d.setColor(new Color(0, 175, 0));
                    break;
                case 2:
                    g2d.setColor(new Color(0, 255, 0));
                    break;
            }
            g2d.fillRect(f.getX(), PISO - crom[0], crom[3], crom[0]);

            // color de la flor
            switch (crom[1]) {
                case 0:
                    g2d.setColor(Color.RED);
                    break;
                case 1:
                    g2d.setColor(Color.BLUE);
                    break;
                case 2:
                    g2d.setColor(Color.YELLOW);
            }
            // grafica la flor
            int x = f.getX() + crom[3] / 2 - crom[4] / 2;
            int y = PISO - crom[0] - crom[4] / 2;
            g2d.fillOval(x, y, crom[4], crom[4]);

            // grafica el centro de la flor
            g2d.setColor(new Color(255, 200, 15));
            x = f.getX() + crom[3] / 2 - crom[5] / 2;
            y = PISO - crom[0] - crom[5] / 2;
            g2d.fillOval(x, y, crom[5], crom[5]);
        }
    }
}
