package Pruebas;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import Estructuras.Arbol;
import Mapa_SuperHeroes.Arma;
import Puerta.HombrePuerta;

public class ArbolTest {
	
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
	
	// Despues de las anteriores inserciones el arbol queda de la siguiente manera:
	
	//					---------F---------
	//		     -------B-------		   G---------
	//			A			----D----		     -----I
	//					    C		 E			H

	@Test
	public void getNumNodos() {
		assertTrue(arbol.numNodos()==9);
	}
	
	@Test
	public void datoPadre() {
		assertTrue(arbol.datoPadre("C")=="D");
		assertTrue(arbol.datoPadre("B")=="F");
		assertTrue(arbol.datoPadre("A")=="B");
		assertTrue(arbol.datoPadre("E")=="D");
	}
	
	@Test
	public void nivelNodo() {
		assertTrue(arbol.nivelNodo("C")==3);
		assertTrue(arbol.nivelNodo("B")==1);
		assertTrue(arbol.nivelNodo("A")==2);
		assertTrue(arbol.nivelNodo("I")==2);
		assertTrue(arbol.nivelNodo("G")==1);
		assertTrue(arbol.nivelNodo("F")==0);
		assertFalse(arbol.nivelNodo("F")==1);
	}
	
	@Test
	public void datosNivel(){
		LinkedList<String> datosNivel=arbol.datosNivel("C");
		assertTrue(datosNivel.get(0)=="E");
		assertTrue(datosNivel.get(1)=="H");
	}
	
}
