package Pruebas;
import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;

import Estructuras.Arbol;
import Mapa_SuperHeroes.Arma;
import Puerta.HombrePuerta;

public class PruebasArbol {
	
	static Arbol<String> arbol;

	@BeforeClass
	public static void inicio() throws IOException{
		arbol=new Arbol<String>();
		arbol.insertar("F");
		arbol.insertar("B");
		arbol.insertar("G");
		arbol.insertar("A");
		arbol.insertar("D");
		arbol.insertar("C");
		arbol.insertar("E");
		arbol.insertar("I");
		arbol.insertar("H");	
	}

	@Test
	public void getNumNodos() {
		assertTrue(arbol.numNodos()==9);
		arbol.borrar("A");
		assertTrue(arbol.numNodos()==8);
		assertFalse(arbol.numNodos()==9);
	}
	
	@Test
	public void datoPadre() {
		assertTrue(arbol.datoPadre("C")=="D");
		assertTrue(arbol.datoPadre("B")=="F");
		assertTrue(arbol.datoPadre("A")=="B");
		assertTrue(arbol.datoPadre("E")=="D");
	}
	
	
}
