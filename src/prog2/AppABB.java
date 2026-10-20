package prog2;

public class AppABB {

	public static void main(String[] args) {
		ABB<Integer> abb = new ABB<>();
		abb.insertar(10);
		abb.insertar(20);
		abb.insertar(80);
		abb.insertar(200);
		abb.insertar(1000);
		//abb.inOrder();
		System.out.println("Raiz " );
		abb.imprimirEnNivel(0);
		System.out.println("Nivel 1 " );
		abb.imprimirEnNivel(1);
		System.out.println(abb.pertence(20));
		System.out.println("Mayores a 20: " + abb.buscarMayoresA(20));
		System.out.println("Mayores a 5 (todos): " + abb.buscarMayoresA(5));
		System.out.println("Mayores a 1500 (ninguno, mejor caso poda): " + abb.buscarMayoresA(1500));
	}

}
