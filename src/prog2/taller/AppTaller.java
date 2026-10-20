package prog2.taller;

public class AppTaller {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE TALLER MECÁNICO ===");

        ITallerMecanico taller = new Taller("Taller UNGS Motors");

        // 1. Registrar Orden de Mantenimiento: 2 hs ($30.000) + Insumos ($45.000) = $75.000
        taller.registrarOrdenMantenimiento(101, "AA123BB", 2, 45000.0);

        // 2. Registrar Orden de Suspensión: 3 hs ($45.000) + Repuestos ($120.000) + Alineación/Balanceo ($25.000) = $190.000
        taller.registrarOrdenSuspension(102, "AF456CD", 3, 120000.0);

        // 3. Registrar Orden Electrónica SIN reprogramación: 1 h ($15.000) + Escaneo ($30.000) = $45.000
        taller.registrarOrdenElectronica(103, "AC789EF", 1, false);

        // 4. Registrar Orden Electrónica CON reprogramación: 2 hs ($30.000) + 20% MO ($6.000) + Escaneo ($30.000) = $66.000
        taller.registrarOrdenElectronica(104, "AD321GH", 2, true);

        System.out.println("\nTotal de órdenes registradas: " + taller.cantidadOrdenes());

        System.out.println("\n--- Detalle individual de cada orden ---");
        System.out.println("Costo Orden #101 (Mantenimiento):   $" + taller.calcularCostoOrden(101));
        System.out.println("Costo Orden #102 (Suspensión):      $" + taller.calcularCostoOrden(102));
        System.out.println("Costo Orden #103 (Electrónica Std): $" + taller.calcularCostoOrden(103));
        System.out.println("Costo Orden #104 (Electrónica Rep): $" + taller.calcularCostoOrden(104));

        // Total esperado: 75000 + 190000 + 45000 + 66000 = 376000
        System.out.println("\n--- Facturación Total (Polimórfica) ---");
        System.out.println("TOTAL FACTURADO: $" + taller.calcularTotalFacturado());

        System.out.println("\n--- Prueba de caso borde: orden inexistente ---");
        try {
            taller.calcularCostoOrden(999);
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción capturada correctamente: " + e.getMessage());
        }
    }
}
