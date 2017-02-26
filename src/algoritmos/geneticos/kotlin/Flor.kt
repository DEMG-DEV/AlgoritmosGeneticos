package algoritmos.geneticos.kotlin

/**
 * Created by DEMG on 18/02/2017.
 */
class Flor {
    var adaptacion: Double = 0.0
        get() = adaptacion
        set(value) {
            field = value
        }
    var x: Int = 0
        get() = x
        set(value) {
            field = value
        }

    /**
     * 0 altura
     * 1 color
     * 2 color del tallo
     * 3 ancho del tallo
     * 4 tamaño de la flor
     * 5 tamaño del centro de la flor
     */
    var cromosomas = arrayOf<Int>()
        get() = cromosomas
        set(value) {
            field = value
        }

    fun Flor() {
        for (i in 1..6) {
            cromosomas[i] = 0
        }
    }
}