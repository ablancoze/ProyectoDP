package Personajes;
import Mapa_SuperHeroes.Mapa;
import Rutas.ManoDerecha;

/**
 * PROYECTO DP 17/18
 * Subclase ShFlight que hereda de SuperHeroe
 * @author
 * <b> Blanco Mangut, Alvaro </b><br>
 * Ablancoze@alumnos.unex.es <br>
 */
public class ShExtraSensorial extends SuperHeroe {

	
/**Constructores de la clase ShExtraSensorial */
	
	
	/**
	 * Constructor por defecto de la clase ShExtraSensorial que hereda de SuperHeroe
	 * Este constructor utiliza el constructor de su clase padre que es SuperHeroe
	 */
	public ShExtraSensorial() {
		super();
	}
	
	
	/**
	 * Constructor parametrizado de la clase ShExtraSensorial
	 * @param nombre nombre del ShExtraSensorial
	 * @param identificador caracter del ShExtraSensorial
	 * @param turno turno en el que inicia su movimiento el ShExtraSensorial
	 */
	public ShExtraSensorial(String nombre, char identificador, int turno) {
		super(nombre,identificador,turno,turno,0);
		moveBehavior=new ManoDerecha();
		ruta=moveBehavior.move(0, Mapa.getMapa().getSalaHombrePuerta());
	}
}
