package prog2;

import java.util.ArrayList;
import java.util.List;

public class ABB<T extends Comparable<T>> extends AB<T>{

  // Inserta un elemento manteniendo el Invariante de Representación (IREP)
  public void insertar(T elemento) {
    this.raiz = insertar(elemento,this.raiz);
  }
  
  // Método auxiliar recursivo para insertar respetando el orden del ABB
  private Nodo<T> insertar(T elemento, Nodo<T> nodo) { 
    if (nodo == null) 
      return new Nodo<>(elemento);
    
    if (nodo.getInfo().compareTo(elemento) > 0) {
      nodo.izq = insertar(elemento, nodo.izq); 
    }
    else if (nodo.getInfo().compareTo(elemento) < 0) {
      nodo.der = insertar(elemento, nodo.der); 
    }
    return nodo; 
  } 
  
  // Verifica si el elemento pertenece al ABB usando el criterio de búsqueda
  public boolean pertence(T elemento) {
    if(this.raiz==null) return false;
    return pertenece(elemento,this.raiz);
  }
  
  // En caso arbol binario busqueda balanceado O(log n)
  // En caso arbol binario busqueda no balanceado O(n)
  private boolean pertenece(T elemento, Nodo<T> nodo) { 
    if (nodo == null) return false; 
    if (nodo.getInfo().compareTo(elemento) > 0) 
      return pertenece(elemento, nodo.izq); 
    if (nodo.getInfo().compareTo(elemento) < 0) 
      return pertenece(elemento, nodo.der); 
    return (elemento.equals(nodo.getInfo())); 
  } 

  // Elimina un elemento del ABB manteniendo el Invariante de Representación (IREP)
  public void eliminar(T elemento) {
    this.raiz = eliminar(this.raiz, elemento);
  }

  // Método auxiliar recursivo para eliminar un nodo controlando sus 3 casos (0, 1 o 2 hijos)
  private Nodo<T> eliminar(Nodo<T> nodo, T elemento) {
    if (nodo == null) {
      return null; // El elemento no se encuentra en el árbol
    }

    int comparacion = elemento.compareTo(nodo.getInfo());

    if (comparacion < 0) {
      // Si el elemento es menor, buscamos por la izquierda
      nodo.izq = eliminar(nodo.izq, elemento);
    } else if (comparacion > 0) {
      // Si el elemento es mayor, buscamos por la derecha
      nodo.der = eliminar(nodo.der, elemento);
    } else {
      // ¡Encontramos el nodo a eliminar!

      // Caso 1: El nodo no tiene hijos o tiene solo un hijo derecho
      if (nodo.izq == null) {
        return nodo.der;
      }
      // Caso 2: El nodo tiene solo un hijo izquierdo
      if (nodo.der == null) {
        return nodo.izq;
      }

      // Caso 3: El nodo tiene DOS hijos
      // Buscamos el sucesor in-order (el valor mínimo del subárbol derecho)
      nodo.info = buscarMinimo(nodo.der);
      
      // Eliminamos el sucesor que duplicamos en el subárbol derecho
      nodo.der = eliminar(nodo.der, nodo.info);
    }

    return nodo;
  }

  // Método auxiliar para encontrar el valor mínimo de un subárbol
  private T buscarMinimo(Nodo<T> nodo) {
    T min = nodo.getInfo();
    while (nodo.izq != null) {
      min = nodo.izq.getInfo();
      nodo = nodo.izq;
    }
    return min;
  }

  /**
   * Retorna una lista con todos los elementos mayores a 'elemento', siguiendo el IREP del ABB.
   * 
   * Complejidad temporal en ABB: O(h + k)
   *  - h: altura del árbol (O(log n) si está balanceado, O(n) si está degenerado).
   *  - k: cantidad de elementos devueltos (los que cumplen la condición).
   * 
   * Justificación de la poda según el IREP:
   *  - Si nodo.info > elemento:
   *      - En el subárbol izquierdo PUEDEN existir elementos mayores a 'elemento' (en el rango (elemento, nodo.info)).
   *      - El nodo actual cumple (se agrega).
   *      - En el subárbol derecho TODOS son mayores a nodo.info y por ende mayores a 'elemento'.
   *      -> Se visitan ambos subárboles.
   *  - Si nodo.info <= elemento:
   *      - Por IREP, todos los elementos del subárbol izquierdo son menores a nodo.info <= elemento.
   *      -> PODA: Se descarta el subárbol izquierdo completo y solo se busca a la derecha.
   * 
   * Mejor caso: O(h) cuando elemento >= max(ABB). Descarta todos los subárboles izquierdos y retorna lista vacía (k=0).
   * Peor caso: O(n) cuando todos los elementos son mayores (k=n) o el árbol está totalmente degenerado.
   * Nota pedagógica: al recorrer en In-Order, la lista devuelta queda ordenada ascendentemente.
   */
  @Override
  public List<T> buscarMayoresA(T elemento) {
    List<T> resultado = new ArrayList<>();
    buscarMayoresA(this.raiz, elemento, resultado);
    return resultado;
  }

  private void buscarMayoresA(Nodo<T> nodo, T elemento, List<T> resultado) {
    if (nodo == null) {
      return;
    }

    int comparacion = nodo.getInfo().compareTo(elemento);

    if (comparacion > 0) {
      buscarMayoresA(nodo.izq, elemento, resultado);
      resultado.add(nodo.getInfo());
      buscarMayoresA(nodo.der, elemento, resultado);
    } else {
      // nodo.info <= elemento -> Poda del subárbol izquierdo
      buscarMayoresA(nodo.der, elemento, resultado);
    }
  }

}