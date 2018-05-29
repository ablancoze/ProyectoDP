package Personajes;
import Mapa_SuperHeroes.Mapa;
import Rutas.MovimientoMasCorto;

/**
 * PROYECTO DP 17/18
 * Subclase ShFlight que hereda de SuperHeroe
 * @author
 * <b> Blanco Mangut, Alvaro </b><br>
 * Ablancoze@alumnos.unex.es <br>
 */
public class ShFlight extends SuperHeroe {
	
/**Constructores de la clase ShFlight */
	
	
	/**
	 * Constructor por defecto de la clase ShFlight que hereda de SuperHeroe
	 * Este constructor utiliza el constructor de su clase padre que es SuperHeroe
	 */
	public ShFlight() {
		super();
	}
	
	
	/**
	 * Constructor parametrizado de la clase ShFlight
	 * @param nombre nombre del ShFlight
	 * @param identificador caracter del ShFlight
	 * @param turno turno en el que inicia su movimiento el ShFlight
	 */
	public ShFlight(String nombre, char identificador, int turno) {
		super(nombre,identificador,turno,turno,Mapa.getMapa().getCornerSurOeste());
		moveBehavior=new MovimientoMasCorto();
		ruta=moveBehavior.move(Mapa.getMapa().getCornerSurOeste(), Mapa.getMapa().getSalaHombrePuerta());
	}
}
