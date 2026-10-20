package prog2;

import java.util.ArrayList;
import java.util.List;

public class AB<T extends Comparable<T>> {
  
  protected Nodo<T> raiz;
  
  class Nodo<T extends Comparable<T>> {
    protected T info;
    protected Nodo<T> izq;
    protected Nodo<T> der;
    
    public Nodo(T info) {
      super();
      this.info = info;
    }
    
    public T getInfo() {
      return this.info;
    }

    public void setIzq(Nodo<T> izq) {
      this.izq = izq;
    }

    public void setDer(Nodo<T> der) {
      this.der = der;
    }
    
    public Nodo<T> getIzq() {
      return izq;
    }

    public Nodo<T> getDer() {
      return der;
    }

    @Override
    public String toString() {
      return "Nodo [info=" + info + "]";
    }   
  }

  // Agrega el elemento como la raíz principal del árbol
  public void agregarRaiz(T elemento) {
    this.raiz = new Nodo<>(elemento);
  }
  
  // Agrega un elemento como hijo izquierdo directo de la raíz
  public void agregarIzqARaiz(T elemento) {
    this.raiz.setIzq(new Nodo<>(elemento));
  }

  // Agrega un elemento como hijo derecho directo de la raíz
  public void agregarDerARaiz(T elemento) {
    this.raiz.setDer(new Nodo<>(elemento));
  }
  
  // Busca un elemento en el árbol y retorna el nodo que lo contiene (o null si no está)
  public Nodo<T> buscar(T elemento) {
    if (this.raiz==null) {
      return null; 
    } else {
      return buscar(this.raiz,elemento);
    }
  } 
  
  // Método auxiliar recursivo para buscar un elemento recorriendo las ramas
  private Nodo<T> buscar(Nodo<T> nodo, T elemento) {
    if(nodo==null) return null;
    if(nodo.info.equals(elemento)) {
      return nodo;
    } else {
      Nodo<T> aux = buscar(nodo.izq,elemento);
      if(aux==null) 
        aux = buscar(nodo.der,elemento);
      return aux;
    }
  }
  
  // Retorna la cantidad total de nodos que tiene el árbol
  public int cantNodos(){  
    return (raiz == null) ? 0 : cantNodos(raiz); 
  } 

  // Método auxiliar recursivo para contar los nodos sumando 1 por cada subárbol
  private int cantNodos(Nodo<T> nodo){  
    int cantIzq = (nodo.izq  == null) ? 0 : cantNodos(nodo.izq) ;  
    int cantDer = (nodo.der  == null) ? 0 : cantNodos(nodo.der) ;  
    return 1 + cantIzq + cantDer; 
  } 

  // Retorna la altura máxima del árbol
  public int altura(){  
    return (raiz == null) ? 0 : altura(raiz); 
  } 

  // Método auxiliar recursivo para calcular la altura tomando el máximo entre la altura izquierda y derecha
  private int altura(Nodo<T> nodo){  
    int altIzq = (nodo.izq  == null) ? 0 : altura(nodo.izq) ;  
    int altDer = (nodo.der  == null) ? 0 : altura(nodo.der) ;  
    return 1 + Math.max(altIzq, altDer); 
  } 
  
  @Override
  public String toString() {
    return "AB [raiz=" + raiz + 
        "izq " + raiz.getIzq()+" "+
        "der " + raiz.getDer()+" "+
        "]";
  }
  
  // Busca un nodo con el valor 'aBuscar' y le cuelga un hijo izquierdo con 'aAgregar'
  public void agregarIzqA(T aBuscar, T aAgregar) {
    Nodo<T> aux = buscar(this.raiz,aBuscar);
    if(aux==null) return;
    if(aux.getIzq()!=null) return;
    aux.setIzq(new Nodo<>(aAgregar));   
  }
  
  // Busca un nodo con el valor 'aBuscar' y le cuelga un hijo derecho con 'aAgregar'
  public void agregarDerA(T aBuscar, T aAgregar) {
    Nodo<T> aux = buscar(this.raiz,aBuscar);
    if(aux==null) return;
    if(aux.getDer()!=null) return;
    aux.setDer(new Nodo<>(aAgregar));   
  }

  // Verifica si el árbol está balanceado (la diferencia de altura entre ramas no supera 1)
  public boolean balanceado(){  
    return (raiz == null) ? true : balanceado(raiz); 
  }

  // Método auxiliar recursivo que comprueba el balance de altura en cada nodo
  private boolean balanceado(Nodo<T> nodo){  
    boolean ret= true;  
    int altIzq = 0 ;  
    int altDer = 0 ;  
    if (nodo.izq != null){   
      altIzq =altura(nodo.izq);   
      ret = ret && balanceado(nodo.izq);  
    }  
    if (nodo.der != null){   
      altDer =altura(nodo.der);   
      ret = ret && balanceado(nodo.der);  
    }  
    ret=ret && Math.abs(altIzq - altDer) <= 1 ;  
    return ret; 
  }

  // Imprime por consola únicamente los nodos que se encuentren en el nivel indicado
  public void imprimirEnNivel(int nivel) {
    if(this.raiz==null) return;
    imprimirEnNivel(this.raiz,nivel,0);   
  }

  // Método auxiliar recursivo para controlar el nivel actual y comparar con el buscado
  private void imprimirEnNivel(Nodo<T> nodo, int nivel, int nivelActual) {
    if(nodo==null) return;
    if(nivel==nivelActual) {
      System.out.println(nodo);
    }
    imprimirEnNivel(nodo.izq,nivel,nivelActual+1);
    imprimirEnNivel(nodo.der,nivel,nivelActual+1);
  } 
  
  // Recorrido Pre-Orden: Raíz -> Izquierda -> Derecha
  public void preOrder() {
    preOrder(this.raiz);
  }
  
  // Método auxiliar recursivo para Pre-Orden
  private void preOrder(Nodo<T> nodo) {
    if(nodo==null) return;
    System.out.println(nodo.info);
    preOrder(nodo.izq);
    preOrder(nodo.der);
  }
  
  // Recorrido In-Orden: Izquierda -> Raíz -> Derecha
  public void inOrder() {
    inOrder(this.raiz);
  }

  // Método auxiliar recursivo para In-Orden
  private void inOrder(Nodo<T> nodo) {
    if(nodo==null) return;
    inOrder(nodo.izq);
    System.out.println(nodo.info);
    inOrder(nodo.der);
  }

  // Recorrido Post-Orden: Izquierda -> Derecha -> Raíz
  public void postOrder() {
    postOrder(this.raiz);
  }
  
  // Método auxiliar recursivo para Post-Orden
  private void postOrder(Nodo<T> nodo) {
    if(nodo==null) return;
    postOrder(nodo.izq);
    postOrder(nodo.der);
    System.out.println(nodo.info);
  }

  // Retorna una lista con todos los elementos mayores al elemento pasado por parámetro (recorrido total sin poda)
  public List<T> buscarMayoresA(T elemento) {
    List<T> resultado = new ArrayList<>();
    buscarMayoresA(this.raiz, elemento, resultado);
    return resultado;
  }

  // Método auxiliar recursivo para acumular en la lista los elementos mayores
  private void buscarMayoresA(Nodo<T> nodo, T elemento, List<T> resultado) {
    if (nodo == null) return;
    buscarMayoresA(nodo.izq, elemento, resultado);
    if (nodo.getInfo().compareTo(elemento) > 0) {
      resultado.add(nodo.getInfo());
    }
    buscarMayoresA(nodo.der, elemento, resultado);
  }

}
