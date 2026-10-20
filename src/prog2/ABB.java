package prog2;

import java.util.ArrayList;
import java.util.List;

public class ABB<T extends Comparable<T>> extends AB<T>{

	public void insertar(T elemento) {
		this.raiz = insertar(elemento,this.raiz);
	}
	
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
