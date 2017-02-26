/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos.geneticos.java;

/**
 *
 * @author demg
 */
public class Flor {

    private double adaptacion;
    private int x;

    /**
     * 0 altura 
     * 1 color 
     * 2 color del tallo 
     * 3 ancho del tallo 
     * 4 tamaño de la flor
     * 5 tamaño del centro de la flor
     */
    private int[] cromosoma;

    public Flor() {
        cromosoma = new int[6];
        for (int i = 0; i < cromosoma.length; i++) {
            cromosoma[i] = 0;
        }
    }

    public double getAdaptacion() {
        return adaptacion;
    }

    public void setAdaptacion(double adaptacion) {
        this.adaptacion = adaptacion;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int[] getCromosoma() {
        return cromosoma;
    }

    public void setCromosoma(int[] cromosoma) {
        this.cromosoma = cromosoma;
    }
}
