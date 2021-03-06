package Estructuras;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import Mapa_SuperHeroes.Sala;

/**
 * PROYECTO DP 17/18
 * Clase Grafo
 * @author
 * <b> Blanco Mangut, Alvaro </b><br>
 * Ablancoze@alumnos.unex.es <br>
 */
public class Grafo implements Cloneable {
	public static final int MAXVERT=115;
	public static final int INFINITO = 9999;
	public static final int NOVALOR = -1;
	
	/** Numero de nodos del grafo */
    private int numNodos;        

    /** Vector que almacena los nodos del grafo */
    private int[] nodos = new int[MAXVERT];           

    /** Matriz de adyacencia, para almacenar los arcos del grafo */
    private int[][] arcos = new int[MAXVERT][MAXVERT];    

	/** Matriz de Camino (Warshall - Path) */
    private boolean [][] warshallC = new boolean[MAXVERT][MAXVERT];    

    /** Matriz de Costes (Floyd - Cost) */
    private int[][] floydC = new int[MAXVERT][MAXVERT];    
	
    /** Matriz de Camino (Floyd - Path) */
    private int[][] floydP = new int[MAXVERT][MAXVERT];	

    /**
    * Metodo constructor por defecto de la clase grafo
    */
    public Grafo() {
        int x,y;
        numNodos=0;

        for (x=0;x<MAXVERT;x++)
            nodos[x]= NOVALOR;

        for (x=0;x<MAXVERT;x++)
            for (y=0;y<MAXVERT;y++){
                arcos[x][y]=INFINITO;
                warshallC[x][y]=false;
                floydC[x][y]=INFINITO;
                floydP[x][y]=NOVALOR;
            }
      
        // Diagonales
        for (x=0;x<MAXVERT;x++){
            arcos[x][x]=0;
            warshallC[x][x]=false;
            floydC[x][x]=0;
            //floydP[x][x]=NO_VALOR;
        }
    }
    
    public Grafo(Grafo g){
    	numNodos=g.numNodos;
    	for (int i = 0; i < g.numNodos; i++) {
    		nodos[i]=g.nodos[i];
			for (int j = 0; j < g.numNodos; j++) {
				arcos[i][j]=g.arcos[i][j];
				warshallC[i][j]=g.warshallC[i][j];
				floydC[i][j]=g.floydC[i][j];
				floydP[i][j]=g.floydP[i][j];
			}
		}
    }
    
    public Grafo clone(){
    	Grafo clon;
		try {
			clon = (Grafo) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
    	return clon;
    }

    
    /**
    * Metodo que comprueba si el grafo esta vacio
    * @return Retorna un valor booleano que indica si el grafo esta o no vacio
    */
    public boolean esVacio () {
        return (numNodos==0);
    }

    /**
    * Metodo que inserta un nuevo arco en el grafo
    * @param origen es el nodo de origen del arco nuevo
    * @param destino es el nodo de destino del arco nuevo
    * @param valor es el peso del arco nuevo 
    * @return true si se pudo insertar
    */
    public boolean nuevoArco(int origen, int destino, int valor) {
        boolean resultado= false;
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos))	{
            arcos[origen][destino]=valor; 
            resultado=true;
        }
        return resultado;
    }

    /**
    * Metodo que borra un arco del grafo
    * @param origen es el nodo de origen del arco nuevo
    * @param destino es el nodo de destino del arco nuevo
    * @return true si se pudo borrar
    */
    public boolean borraArco(int origen, int destino) {
        boolean resultado = false;
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) {
        	arcos[origen][destino]=INFINITO;	
            resultado=true;
        }
        return resultado;
    }

    /**
    * Metodo que comprueba si dos nodos son adyacentes
    * @param origen es el primer nodo
    * @param destino es el segundo nodo
    * @return Retorna un valor booleano que indica si los dos nodos son adyacentes
    */
    public boolean adyacente (int origen, int destino) {
        boolean resultado= false;
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos))      
    		resultado = (arcos[origen][destino]!=INFINITO); 
        return resultado;
    }

    /**
    * Metodo que retorna el peso de un arco
    * @param origen es el primer nodo del arco
    * @param destino es el segundo nodo del arco
    * @return Retorna un valor entero que contiene el peso del arco
    */
    public int getArco (int origen, int destino) {
        int arco=NOVALOR;
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) 	
    		arco=arcos[origen][destino];				     
        return arco;
    }

    /**
    * Metodo que inserta un nuevo nodo en el grafo
    * @param n es el nodo que se desea insertar
    * @return true si se pudo insertar
    */
    public boolean nuevoNodo(int n) {
        boolean resultado=false;

        if (numNodos<MAXVERT){
            nodos[numNodos]=n;
            numNodos++;
            resultado=true;
        }
        return resultado;
    }

    /**
    * Metodo que elimina un nodo del grafo
    * @param nodo nodo que se desea eliminar
    * @return true si se pudo borrar
    */
    public boolean borraNodo(int nodo) {
        boolean resultado=false;
    	int pos = nodo; 

        if ((numNodos>0) && (pos <= numNodos)) {
            int x,y;
            // Borrar el nodo
            for (x=pos; x<numNodos-1; x++){		
                nodos[x]=nodos[x+1];
    			System.out.println(nodos[x+1]);
    		}
            // Borrar en la Matriz de Adyacencia
            // Borra la fila
            for (x=pos; x<numNodos-1; x++)		
                for (y=0;y<numNodos; y++)
                    arcos[x][y]=arcos[x+1][y];
            // Borra la columna
            for (x=0; x<numNodos; x++)
                for (y=pos;y<numNodos-1; y++)	
                    arcos[x][y]=arcos[x][y+1];
            // Decrementa el numero de nodos
            numNodos--;
            resultado=true;
        }
        return resultado;
    }

    /**
    * Metodo que muestra el vector de nodos del grafo
    */
    public void mostrarNodos() {
        System.out.println("NODOS:");
        for (int x=0;x<numNodos;x++)
            System.out.print(nodos[x] + " ");
        System.out.println();
    }

    /**
    * Metodo que muestra los arcos del grafo (la matriz de adyacencia)
    */
    public void mostrarArcos(){
        int x,y;

        System.out.println("ARCOS:");
        for (x=0;x<numNodos;x++) {
            for (y=0;y<numNodos;y++) {
                //cout.width(3);
                if (arcos[x][y]!=INFINITO)
                    System.out.format("%4d",arcos[x][y]);
                else
                    System.out.format("%4s","#");
            }
            System.out.println();
        }
    }


    /**
    * Metodo que devuelve el conjunto de nodos adyacentes al nodo actual
    * @param origen es el nodo actual
    * @param ady En este conjunto se almacenaran los nodos adyacentes al nodo origen
    */
    public void adyacentes(int origen, Set<Integer> ady){//si le pasas como origen la sala 6 y esta esta conectada con la 0 la 7 y la 12
														// el conjunto ady resultante vendra por orden de indentificacion de las salas.
       if ((origen >= 0) && (origen < numNodos)) {
    		for (int i=0;i<numNodos;i++) {
           	 	if ((arcos[origen][i]!=INFINITO) && (arcos[origen][i]!=0))	
              		ady.add(i);	
          	}
    	}
    }

    
    /**
     * Metodo que muestra la matriz de Warshall
     */
     public void mostrarPW()
     {
         int x,y;

         System.out.println("warshallC:");
         for (x=0;x<numNodos;x++) {
             for (y=0;y<numNodos;y++)
                 System.out.format("%6b",warshallC[x][y]);
             System.out.println();
         }
     }

     /**
     * Metodo que muestra las matrices de coste y camino de Floyd
     */
     public void mostrarFloydC(){
         int x,y;
         System.out.println("floydC:");
         for (y=0;y<numNodos;y++) {
             for (x=0;x<numNodos;x++) {                 //cout.width(3);
                 System.out.format("%4d",floydC[x][y]);
             }
             System.out.println();
         }

         System.out.println("floydP:");
         for (x=0;x<numNodos;x++) {
             for (y=0;y<numNodos;y++) {
                 //cout.width(2);
                 System.out.format("%4d",floydP[x][y]);
             }
             System.out.println();
         }
     }

     /**
     * Metodo que realiza el algoritmo de Warshall sobre el grafo
     */
     public void warshall() {
         int i,j,k;

         // Obtener la matriz de adyacencia en P
         for (i=0;i<numNodos;i++)
             for (j=0;j<numNodos;j++)
                 warshallC[i][j]=(arcos[i][j]!=INFINITO);

         // Iterar
         for (k=0;k<numNodos;k++)
             for (i=0;i<numNodos;i++)
                 for (j=0;j<numNodos;j++)
                     warshallC[i][j]=(warshallC[i][j] || (warshallC[i][k] && warshallC[k][j]));
     }

     /**
     * Metodo que realiza el algoritmo de Floyd sobre el grafo
     */
     public void floyd (){
         int i,j,k;

         // Obtener la matriz de adyacencia en P
         for (i=0;i<numNodos;i++)
             for (j=0;j<numNodos;j++){
                 floydC[i][j]=arcos[i][j];
     			floydP[i][j]=NOVALOR; 	
     		}

         // Iterar
         for (k=0;k<numNodos;k++)
             for (i=0;i<numNodos;i++)
                 for (j=0;j<numNodos;j++)
                     if (i!=j)
                         if ((floydC[i][k]+floydC[k][j] < floydC[i][j])) {
                             floydC[i][j]=floydC[i][k]+floydC[k][j];		
                             floydP[i][j]=k;
                         }
     }

     /**
      * Metodo que devuelve el siguiente nodo en la ruta entre un origen y un destino
      * @param origen es el primer nodo
      * @param destino es el segundo nodo
      * @return el siguiente nodo a visitar
      */
      public int siguiente(int origen, int destino){
      	int sig=-1; // Si no hay camino posible
          if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) {
      		if (warshallC[origen][destino]){ // Para comprobar que haya camino
      	    	if (floydP[origen][destino]!=NOVALOR)
      				sig = siguiente(origen, floydP[origen][destino]);	
          		else
          			sig=destino;
      		}
      	}
          return sig;
      }
      
      public int costeCamino(int origen, int destino){
    	  return floydC[origen][destino];
      }
      
      public LinkedList<Integer> caminoMinimo(int origen,int destino){
    	  
    	  return null;
    	  
      }
      
	public void todosCaminos(Integer origen, Integer destino, LinkedList<Integer>visitados,LinkedList<LinkedList<Integer>>todosLosCaminos){
		
		Integer vertice=0;//Nodo vertice al nodo en el que estamos
		
		TreeSet<Integer> ady= new TreeSet<Integer>();//creamos un treeSet para guardar los adyacentes del nodo con el que estamos trabajando
		
		visitados.add(origen);
		
		if(origen!=destino){//Si el origen es igual al destino el camino que se ha llevado a cabo es valido, si no lo es seguimos explorando caminos

			visitados.add(origen);//A�adimos el nodo con el que estamos trabajando al visitados

			this.adyacentes(origen, ady);//Cogemos los adyacentes del nodo que estamos tratando

			while(!ady.isEmpty()){//Mientras haya nodos por explorar y no hayamos llegado al final

				vertice=ady.first();//Igualo el nodo axuliar al primer adyancente

				ady.remove(vertice);//Elimino el adyacente que vamos a tratar
				
				if(!visitados.contains(vertice)){
					todosCaminos(vertice, destino,visitados,todosLosCaminos);
				}
			}
			visitados.remove(origen);
			
		}else{
			LinkedList<Integer>recorridoValido=new LinkedList<Integer>(visitados);
			visitados.remove(origen);
			todosLosCaminos.add(recorridoValido);//Cuando llegue a la sala objetivo entonces el camino sera correcto, por lo tanto lo guardo.
		}
	}
	
	
	
	/**
	 * 
	 * @param vertice
	 * @return
	 */
	public int excentricidad(int vertice){
		int maximo=0;
		int sala=0;
		for (int i = 0; i < numNodos; i++) {
			if(maximo<floydC[vertice][i]){
				maximo=floydC[vertice][i];
				sala=i;
			}
		}
		return maximo;
	}

	
	public int nodoCentral(){
		int execentricidadMinima=excentricidad(0);
		int salaCentral=0;
		for (int i = 1; i < numNodos; i++) {
			if (execentricidadMinima>excentricidad(i)){
				execentricidadMinima=excentricidad(i);
				salaCentral=i;
			}
		}
		return salaCentral;
	}
	
	public boolean existeCiclo(int vertice){
		boolean esCiclico=false;
		TreeSet<Integer>ady=new TreeSet<Integer>();
		adyacentes(vertice, ady);
		Integer [] adyA= new Integer[ady.size()];
		ady.toArray(adyA);
		for (int i = 0; i < adyA.length; i++) {
			borraArco(adyA[i], vertice);
		}
		floyd();
		warshall();
		for (int i = 0; i < adyA.length; i++) {
			for (int j = 0; j < adyA.length; j++) {
				if(adyA[i]!=adyA[j]){
					esCiclico=warshallC[adyA[i]][adyA[j]];
					if(esCiclico==true)
						return esCiclico;
				}
			}
		}
		return esCiclico;		
	}
    
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    //Datos ejemplo para creación grafo
		Grafo g = new Grafo();
	    int [] nodos = new int[10];
	    int [][] arcos = {{0,97,Grafo.INFINITO,Grafo.INFINITO,119,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO},
	                      {97,0,10,Grafo.INFINITO,Grafo.INFINITO,57,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO},
	                      {Grafo.INFINITO,10,0,17,Grafo.INFINITO,Grafo.INFINITO,67,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO},
	                      {Grafo.INFINITO,Grafo.INFINITO,17,0,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,122},
	                      {119,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,0,Grafo.INFINITO,Grafo.INFINITO,37,Grafo.INFINITO,Grafo.INFINITO},
	                      {Grafo.INFINITO,57,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,0,63,Grafo.INFINITO,50,Grafo.INFINITO},
	                      {Grafo.INFINITO,Grafo.INFINITO,67,Grafo.INFINITO,Grafo.INFINITO,63,0,Grafo.INFINITO,61,Grafo.INFINITO},
	                      {Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,37,Grafo.INFINITO,Grafo.INFINITO,0,72,Grafo.INFINITO},
	                      {Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,50,61,72,0,153},
	                      {Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,122,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,Grafo.INFINITO,153,0}};

	    for(int i=0;i<10;i++){
			nodos[i]=i;
	        if (!g.nuevoNodo(nodos[i]))
				System.out.println("Error en la insercion del nodo " + i);
		}

	    for(int i=0;i<10;i++)
	        for(int j=0;j<10;j++)
	            if (!g.nuevoArco(nodos[i],nodos[j],arcos[i][j]))
	            	System.out.println("Error en la insercion del arco("+i+","+j+")");

	    g.mostrarNodos();
	    g.mostrarArcos();
	    
	    System.out.println("-------------------------------------");

	    g.warshall();
	    g.mostrarPW();

	    g.floyd();
	    g.mostrarFloydC();

	    System.out.println("-------------------------------------");

	   // ------------------------------------------------------------------------------
   	   // Probando la función adyacentes:
	   int nodo = nodos[8];
	   Set<Integer> ady = new LinkedHashSet<Integer>();
	   System.out.print("Nodos adyacentes al nodo : " + nodo + ":");
 	   g.adyacentes(nodo,ady);
 	   for (Integer n : ady){
		      System.out.print (" " + n);
	   }
	   // ------------------------------------------------------------------------------
	}
}

