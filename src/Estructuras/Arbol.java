package Estructuras;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import Mapa_SuperHeroes.Mapa;

/**
 * PROYECTO DP 17/18
 * Estructura de tipo ABB (Arbol de busqueda binaria)
 * @author 
 * <b> Blanco Mangut, Alvaro </b><br>
 * Ablancoze@alumnos.unex.es <br>
 */
public class Arbol<T extends Comparable<T>> {

/**Atributos de la clase arbol */
	
	/** 
	 * Dato almacenado en cada nodo del arbol
	 */
	private T datoRaiz;
	
	/**
	 * Atributo que indica si el arbol esta vaccio
	 */
	boolean esVacio;
	
	/** 
	 * Hijo izquierdo del nodo actual
	 */
	private Arbol<T> hIzq;
	
	/**
	 * Hijo derecho del nodo actual
	 */
	private Arbol<T> hDer;
	
	
	
	
/**Constructores de la clase Arbol */
	
	
	/**
	 * Constructor por defecto de la clase. Inicializa un árbol vacío.
	 */
	public Arbol(){
	    this.esVacio=true;
	    this.hIzq = null;
	    this.hDer = null;
	}

	
	/**
	 * Constructor parametrizado
	 * Crea un nuevo árbol a partir de los datos pasados por parámetro.
	 *
	 * @param hIzq El hijo izquierdo del árbol que se está creando 
	 * @param datoRaiz Raíz del árbol que se está creando
	 * @param hDer El hijo derecho del árbol que se está creando
	 */
	public Arbol (Arbol<T> hIzq, T datoRaiz, Arbol<T> hDer){
	        this.esVacio=false;
		this.datoRaiz = datoRaiz;
		this.hIzq = hIzq;
		this.hDer=hDer;
	}
	
	
	
	
/**Metodos privados de la clase arbol */
	
	
	/**
	 * Borrar un dato. Este metodo es utilizado por el metodo borrar publico.
	 *
	 * @param dato El dato a borrar
	 * @return Devuelve el arbol resultante despues de haber realizado el borrado
	 */
	private Arbol<T> borrarOrden(T dato){
		
	    T datoaux;
	    Arbol<T> retorno=this;
	    Arbol<T> aborrar, candidato, antecesor;
	  	    
	    if (!vacio()) {
	    	
	        if (dato.compareTo(this.datoRaiz)<0){		// dato<datoRaiz
		    	       if (hIzq!=null)
	        	hIzq = hIzq.borrarOrden(dato);
	        }else
	        	
	            if (dato.compareTo(this.datoRaiz)>0){	// dato>datoRaiz
	    	        if (hDer!=null)  
	            	hDer = hDer.borrarOrden(dato);
	    	        
	            }else{
	            	
	            	if(dato.equals(this.datoRaiz)){
		                aborrar=this;
		                if ((hDer==null)&&(hIzq==null)) { /*si es hoja*/
		                    retorno=null;
		                }
		                else {
		                    if (hDer==null) { /*Solo hijo izquierdo*/
		                        aborrar=hIzq;
		                        datoaux=this.datoRaiz;
		                        datoRaiz=hIzq.getRaiz();
		                        hIzq.datoRaiz = datoaux;
		                        hIzq=hIzq.getHijoIzq();
		                        hDer=aborrar.getHijoDer();

		                        retorno=this;
		                    }
		                    else
		                        if (hIzq==null) { /*Solo hijo derecho*/
		                            aborrar=hDer;
		                            datoaux=datoRaiz;
		                            datoRaiz=hDer.getRaiz();
		                            hDer.datoRaiz = datoaux;
		                            hDer=hDer.getHijoDer();
		                            hIzq=aborrar.getHijoIzq();

		                            retorno=this;
		                        }
		                        else { /* Tiene dos hijos */
		                            candidato = this.getHijoIzq();
		                            antecesor = this;
		                            while (candidato.getHijoDer()!=null) {
		                                antecesor = candidato;
		                                candidato = candidato.getHijoDer();
		                            }

		                            /*Intercambio de datos de candidato*/
		                            datoaux = datoRaiz;
		                            datoRaiz = candidato.getRaiz();
		                            candidato.datoRaiz=datoaux;
		                            aborrar = candidato;
		                            if (antecesor==this)
		                                hIzq=candidato.getHijoIzq();
		                            else
		                                antecesor.hDer=candidato.getHijoIzq();
		                        } //Eliminar solo ese nodo, no todo el subarbol
		                    aborrar.hIzq=null;
		                    aborrar.hDer=null;
		                }
	            		
	            	}else{
		    	        if (hDer!=null)  
			            	hDer = hDer.borrarOrden(dato);
	            	}
	            	
	            	

	            }
	    }
	   
	    return retorno;
	}
	
	
	/**
	 * Metodo privado que calcula el numero de hojas del arbol
	 * @param aux: arbol de entrada.
	 * @return int que indica el numero de nodos hojas del arbol
	 */
	private int numHojas(Arbol<T> aux){
		int acumulador=0;
		if(aux!=null){
			if (aux.esHoja()){
				return 1;
			}else{
				if(aux.getHijoIzq()!=null){
					acumulador=acumulador+numHojas(aux.getHijoIzq());
				}
				if(aux.getHijoDer()!=null){
					acumulador=acumulador+numHojas(aux.getHijoDer());
				}
			}	
		}
		return acumulador;
	}
	
	
	/**
	 * Metodo que permite calcular la profundidad de un arbol
	 * @param aux: Arbol
	 * @param profundidad: int profundida que viene definido por su metodo publico
	 * @return int: altura del arbol
	 */
	private int profundidadAbb(Arbol<T> aux,int profundidad){
		int altura1=0;int altura2=0;
		if (aux!=null){
			if (aux.esHoja()){
				return profundidad;
			}
			if (aux.getHijoIzq()!=null){
				altura1=altura1+profundidadAbb(aux.getHijoIzq(),profundidad+1);
			}
			if (aux.getHijoDer()!=null){
				altura2=altura2+profundidadAbb(aux.getHijoDer(),profundidad+1);	
			}
		}
		if (altura1>altura2){
			return altura1;
		}
		return altura2;
	}
	
	
	/**
	 * Metodo que realiza una busqueda en Anchura de un dato 
	 * @param A: arbol donde se encuentras los datos
	 * @param dato: dato que queremos buscar en la estrucctura
	 * @return TRUE si el dato se encuetra en el arbol, FALSE si no se encuentra.
	 */
	private boolean busquedaAnchura (Arbol<T> A,T dato){
		Arbol<T>  b;
		Queue<Arbol<T>> c=new LinkedList();
		c.add(A);
		while (!c.isEmpty()) {
			b = c.peek();
			c.remove();
			if (! b.esVacio ) {
				if(dato.compareTo(b.datoRaiz)==0){//procesamiento de la ra�z
					return true;
				}
				c.add(b.hIzq);
				c.add(b.hDer);
			}
		}
		return false;
	}
	
	
	/**
	 * Metodo que nos permite conocer el dato padre de un nodo dado por parametro
	 * @param dato: dato del cual queremos conocer cual es su padre
	 * @param cierto: array que indica si hemos encontrado el dato en el arbol
	 * @return T nodo padre del dato indicado por parametro.
	 */
	private T datoPadre(T dato,boolean[] cierto){
	    Arbol<T> aux=null;
	    T  encontrado=null;
	    if (!vacio()) {
	        if (this.datoRaiz.equals(dato)){
	            encontrado = this.datoRaiz;
	            cierto[0]=true;
	        } else {
	            if (dato.compareTo(this.datoRaiz)<0)	//dato < datoRaiz
	                aux=getHijoIzq();
	            else									//dato > datoRaiz
	                aux=getHijoDer();
	            if (aux!=null)
	                encontrado = aux.datoPadre(dato,cierto);
	            
			    if (cierto[0]==true){
			    	encontrado=this.getRaiz();
			    	cierto[0]=false;
			    }
	        }
	        
	    }
	    return encontrado;
	}
	
	
	/**
	 * Metodo que nos permite conocer el nivel en el que se encuentra un dato indicado por parametro de entrada
	 * @param dato: dato el cual queremos conocer su nivel en el arbol
	 * @param nivel: nivel en el que nos encontramos
	 * @param aux: arbol auxiliar que nos ayuda a realizar una busqueda binaria del dato
	 * @return int que indica el nivel al que se encuentra el dato
	 */
	private int nivelNodo(T dato,int nivel,  Arbol<T> aux){
	    if (!vacio()) {
	        if (aux.datoRaiz.equals(dato))
	            return nivel;
	        else {
	            if (dato.compareTo(aux.datoRaiz)<0)	//dato < datoRaiz
	                aux=aux.hIzq;
	            else									//dato > datoRaiz
	            	aux=aux.hDer;
	            if (aux!=null)
	                return nivelNodo(dato,nivel+1,aux);
	        }
	    }
	    return nivel;
	}
	
	
	/**
	 * Metodo que nos devuelve una lista con los nodos que se encuentran al mismo nivel que un dato dado por parametro
	 * @param dato:dato del cual queremos conocer su nivel
	 * @param aux: arbol auxiliar para realizar la busqueda del resto de datos
	 * @param nivelDato: nivel del dato pasado por parametro
	 * @param nivelNodo: nivel en el que nos encontramos en la recursividad.
	 * @param datosNivel: LinkedList<T> que almacenara los datos que se encuentren al mismo nivel que dato indicado por parametro.
	 */
	private void datosNivel(T dato,Arbol<T> aux,int nivelDato,int nivelNodo,LinkedList<T>datosNivel){
		
		if (nivelNodo==nivelDato){
			if(!dato.equals(aux.datoRaiz))
				datosNivel.add(aux.datoRaiz);
			
		}else{
			
			if (aux!=null){
				if (aux.getHijoIzq()!=null)
					datosNivel(dato,aux.hIzq,nivelDato,nivelNodo+1,datosNivel);
				
				if (aux.getHijoDer()!=null)
					datosNivel(dato,aux.hDer,nivelDato,nivelNodo+1,datosNivel);
				
			}
		}
	}
	
	
	
/**Metodos publicos de la clase arbol */
	
	
	/**
	 * Comprueba si el arbol esta vacio
	 * @return verdadero si el arbol esta vacio, falso en caso contrario
	 */
	public boolean vacio(){
		return esVacio;
	}
	
	
	/**
	 * Inserta un nuevo dato en el arbol
	 *
	 * @param dato El dato a insertar
	 * @return verdadero si el dato se ha insertado correctamente, falso en caso contrario
	 */
	public boolean insertar(T dato){
	    boolean resultado=true;
	    if (vacio()) {
	        datoRaiz = dato;
			esVacio = false;
		}
	    else {
	        if (!(this.datoRaiz.equals(dato))) {
	            Arbol<T> aux;
	            if (dato.compareTo(this.datoRaiz)<0) { //dato < datoRaiz
	                if ((aux=getHijoIzq())==null)
	                    hIzq = aux = new Arbol<T>();
	            }
	            else {									//dato > datoRaiz
	                if ((aux=getHijoDer())==null)
	                    hDer = aux = new Arbol<T>();
	            }
	            resultado = aux.insertar(dato);
	        }
	        else
	            resultado=false;
	    }
	    return resultado;
	}
	
	/**
	 * Comprueba si un dato se encuentra almacenado en el arbol
	 *
	 * @param dato El dato a buscar
	 * @return verdadero si el dato se encuentra en el arbol, falso en caso contrario
	 */
	public boolean pertenece(T dato){
	    Arbol<T> aux=null;
	    boolean encontrado=false;
	    if (!vacio()) {
	        if (this.datoRaiz.equals(dato))
	            encontrado = true;
	        else {
	            if (dato.compareTo(this.datoRaiz)<0)	//dato < datoRaiz
	                aux=getHijoIzq();
	            else									//dato > datoRaiz
	                aux = getHijoDer();
	            if (aux!=null)
	                encontrado = aux.pertenece(dato);
	        }
	    }
	    return encontrado;
	}
	
	
	/**
	 * Dado un dato del mismo tipo comprueba si esta almacenado en el arbol
	 * @param dato: Dato que deseamos buscar en el arbol
	 * @return devuelve el dato si este se encuentra en el arbol, si no devolvera NULL.
	 */
	public T getDato(T dato){
		
	    Arbol<T> aux=null;
	    T  encontrado=null;
	    if (!vacio()) {
	        if (this.datoRaiz.equals(dato))
	            encontrado = this.datoRaiz;
	        else {
	            if (dato.compareTo(this.datoRaiz)<0)	//dato < datoRaiz
	                aux=getHijoIzq();
	            else									//dato > datoRaiz
	                aux = getHijoDer();
	            if (aux!=null)
	                encontrado = aux.getDato(dato);
	        }
	    }
	    return encontrado;
	}
	
	
	/**
	 * Borrar un dato del arbol.
	 *
	 * @param dato El dato que se quiere borrar
	 */
	public void borrar(T dato){
	    if (!vacio()) {
	        if (dato.compareTo(this.datoRaiz)<0){			//dato<datoRaiz
	        		if (hIzq!=null)
					hIzq = hIzq.borrarOrden(dato);
			}	
	        else
	            if (dato.compareTo(this.datoRaiz)>0) {		//dato>datoRaiz 
	            		if (hDer!=null)
	            		hDer = hDer.borrarOrden(dato);
				}
	            else { //En este caso el dato es datoRaiz
	            
	                if (hIzq==null && hDer==null){
	                
	                    esVacio=true;
	                }
	                else
	                    borrarOrden(dato);
	            }
	    }
	}
	
	
	/**
	 * Recorrido inOrden del arbol
	 * @return String con los elementos del arbol
	 */
	public String inOrden(){
	    Arbol<T> aux=null;
	    if (!vacio()) {
	        if ((aux=getHijoIzq())!=null) {
	            aux.inOrden();
	        }    
	      
	        System.out.print(datoRaiz.toString());
	        
			try {
				Mapa.getMapa().getFlujo().write(datoRaiz.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        if ((aux=getHijoDer())!=null){
	            aux.inOrden();
	        }    
	    }
	    return "";
	}
	
	
	/**
	 * Recorrido preOrden del arbol
	 */
	public void preOrden(){
	    Arbol<T> aux=null;
	    if (!vacio()) {
	    	System.out.println(this.datoRaiz);
	    	
	        if ((aux=getHijoIzq())!=null) {
	            aux.preOrden();
	        }    
	        
	        if ((aux=getHijoDer())!=null){
	            aux.preOrden();
	        }    
	    }
	}
	
	
	/**
	 * Recorrido postOrden del arbol
	 */
	public void postOrden(){
	    Arbol<T> aux=null;
	    if (!vacio()) {
	    	
	        if ((aux=getHijoIzq())!=null) {
	            aux.postOrden();
	        }    
	        
	        if ((aux=getHijoDer())!=null){
	            aux.postOrden();
	        }    
	        
	        System.out.println(this.datoRaiz + " ");
	    }
	}
	
	
	/**
	 * Metodo que nos permite saber si un nodo es hoja o no
	 * @return boolean TRUE si es hoja, FALSE si no lo es
	 */
	public boolean esHoja(){
		if (this.hDer==null && this.hIzq==null){
			return true;
		}
		
		return false;
	}
	
	
	/**
	 * Metodo publico que llama a numHojas(this)
	 * @return Devuelve el numero de Hojas del Arbol
	 */
	public int numHojas(){
		return numHojas(this);
	}
	
	
	/**
	 * Metodo publico que llama a profundidadAbb(this,1)
	 * @return devuelve la profundidad o la altura del arbol.
	 */
	
	public int profundidadAbb(){
		if (!this.vacio()){
			return profundidadAbb(this,1);
		}
		return 0;
	}
	
	
	/**
	 * Realiza una busqueda en anchura dentro del arbol
	 * @param dato: dato que queremos buscar dentro del arbol
	 * @return True si el dato se encuentra en el arbol, False si no esta
	 */
	public boolean busquedaAnchura(T dato){
		if (!this.vacio()){
			return busquedaAnchura(this,dato);
		}
		return false;
	}
	
	
	/**
	 * Metodo que nos permite conocer el numero de nodos del arbol 
	 * @return int que indica el numero de nodos que tiene el arbol
	 */
	public int numNodos(){
		Arbol<T> aux=null;
		int total=0;
	    if (!vacio()) {
	        if ((aux=getHijoIzq())!=null) {
	          total=total+aux.numNodos();
	        }    
	      
	        total=total+1;
	        
	        if ((aux=getHijoDer())!=null){
	        	total=total+aux.numNodos();
	        }    
	    }
	    return total;
	}
	
	
	/**
	 * Metodo que nos devuelve el nodo padre al dato indicado por parametro
	 * @param dato: dato que queremos buscar dentro del arbol
	 * @return T: nodo padre al dato pasado por parametro.
	 */
	public T datoPadre(T dato){
		boolean[] cierto;
		cierto=new boolean[1];
		cierto[0]=false;
		
		return datoPadre(dato,cierto);
		
	}
	
	
	/**
	 * Metodo que nos permite conocer el nivel en el que se encuentra un dato indicado por parametro de entrada
	 * @param dato: dato el cual queremos conocer su nivel en el arbol
	 * @return int que indica el nivel en el que se encuentra el dato 
	 */
	public int nivelNodo(T dato){
		return nivelNodo(dato,0,this);
	}
	
	/**
	 * Metodo que nos devuelve una lista con los nodos que se encuentran al mismo nivel que un dato dado por parametro
	 * @param dato: dato del cual queremos conocer su nivel 
	 * @return LinkedList<T> con los nodos que se encuentran en el mismo nivel que el dato indicado por parametro de entrada
	 */
	public LinkedList<T> datosNivel(T dato){
		LinkedList<T> datosNivel=new LinkedList<T>();
		int nivelDato=nivelNodo(dato,0,this);
		datosNivel(dato, this,nivelDato, 0, datosNivel);
		return datosNivel;
	}
	

	

	
	
	
/**GET'S y SET'S de la clase arbol */

	
	/**
	 * Devuelve el hijo izquierdo del árbol
	 *
	 * @return El hijo izquierdo
	 */
	public Arbol<T> getHijoIzq(){
		return hIzq;
	}
	
	
	/**
	 * Devuelve el hijo derecho del árbol
	 *
	 * @return Hijo derecho del árbol
	 */
	public Arbol<T> getHijoDer(){
		return hDer;
	}
	
	/**
	 * Devuelve la raíz del árbol
	 *
	 * @return La raíz del árbol
	 */
	public T getRaiz(){
		return datoRaiz;
	}
}
