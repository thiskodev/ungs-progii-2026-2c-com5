package prog2;

public class App {

	public static void main(String[] args) {
		recursividad();
		AB<Integer> ab = new AB<>();
		ab.agregarRaiz(15);
		ab.agregarDerARaiz(4);
		ab.agregarIzqARaiz(80);
		ab.agregarIzqA(50,60);		
		ab.agregarDerA(4, 2);
		ab.agregarDerA(2, 100);
		ab.agregarDerA(100, 20);
		ab.imprimirEnNivel(4);
		System.out.println("Mayores a 10 en AB: " + ab.buscarMayoresA(10));
		// System.out.println(ab.balanceado());
		// System.out.println(ab.altura());
		// System.out.println(ab.buscar(4));
		//System.out.println(ab.cantNodos());
		//ab.preOrder();
		// System.out.println(ab.buscar(500));
	}

	private static void recursividad() {
		int[] numeros = {2,3,4,5};
		imprimirRecursivo(numeros,numeros.length);
	}

	private static void imprimirRecursivo(int[] numeros, int i) {
		if(i==0) {
			System.out.println(numeros[0]);
			return;
		}
		imprimirRecursivo(numeros, i-1);
	}
	

}
