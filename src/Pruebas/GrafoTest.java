package Pruebas;
import static org.junit.Assert.*;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import Estructuras.Arbol;
import Estructuras.Grafo;
import Mapa_SuperHeroes.Arma;
import Mapa_SuperHeroes.Mapa;
import Puerta.HombrePuerta;

public class GrafoTest {
	
	static Mapa m;

	@BeforeClass
	public static void inicio() throws IOException{
		m =Mapa.getMapa(15, 4, 4, 3);
	}
	

//	  _ _ _ _
//	 |_ _  | |
//	 | |_    |
//	 |_  | | |
//	 |_ _ _ _|
	
//	floydC:
//0		   0   1   2   5   9   4   3   4   8   7   4   5   7   6   5   6
//1		   1   0   1   4   8   3   2   3   7   6   3   4   6   5   4   5
//2		   2   1   0   3   7   2   1   2   6   5   2   3   5   4   3   4
//3		   5   4   3   0   8   3   2   1   7   6   3   2   6   5   4   3
//4		   9   8   7   8   0   7   6   7   1   2   5   8   4   3   4   9
//5		   4   3   2   3   7   0   1   2   6   5   2   3   5   4   3   4
//6		   3   2   1   2   6   1   0   1   5   4   1   2   4   3   2   3
//7		   4   3   2   1   7   2   1   0   6   5   2   1   5   4   3   2
//8		   8   7   6   7   1   6   5   6   0   1   4   7   3   2   3   8
//9		   7   6   5   6   2   5   4   5   1   0   3   6   2   1   2   7
//10	   4   3   2   3   5   2   1   2   4   3   0   3   3   2   1   4
//11	   5   4   3   2   8   3   2   1   7   6   3   0   6   5   4   1
//12	   7   6   5   6   4   5   4   5   3   2   3   6   0   1   2   7
//13	   6   5   4   5   3   4   3   4   2   1   2   5   1   0   1   6
//14 	   5   4   3   4   4   3   2   3   3   2   1   4   2   1   0   5
//15	   6   5   4   3   9   4   3   2   8   7   4   1   7   6   5   0
	
	@Test
	public void excentricidad() {
		assertTrue(m.excentricidad(1)==8);
		
	}
	
	@Test
	public void salaCentral() {
		assertTrue(m.salaCentral()==10);
		
	}
	
	@Test
	public void esCiclico(){
		m.tirarPared(15, 14);
		assertTrue(m.esCiclico(6)==true);
		assertTrue(m.esCiclico(7)==true);
		assertFalse(m.esCiclico(9)==true);
		assertTrue(m.esCiclico(10)==true);
		assertTrue(m.esCiclico(11)==true);
		assertTrue(m.esCiclico(14)==true);
		assertTrue(m.esCiclico(15)==true);
		
	}
	
	
	
}
