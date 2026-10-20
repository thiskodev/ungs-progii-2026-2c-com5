package prog2.taller;

public class OrdenSuspension extends OrdenDeTrabajo {
    public static final double COSTO_ALINEACION_Y_BALANCEO = 25000.0;

    private double costoRepuestos;

    public OrdenSuspension(int nroOrden, String patente, int horasTrabajadas, double costoRepuestos) {
        super(nroOrden, patente, horasTrabajadas);
        if (costoRepuestos < 0) {
            throw new IllegalArgumentException("El costo de repuestos no puede ser negativo.");
        }
        this.costoRepuestos = costoRepuestos;
    }

    public double getCostoRepuestos() {
        return costoRepuestos;
    }

    @Override
    public double calcularCostoTotal() {
        return calcularCostoManoDeObra() + this.costoRepuestos + COSTO_ALINEACION_Y_BALANCEO;
    }

    @Override
    public String toString() {
        return super.toString() + " - Suspensión (Repuestos: $" + costoRepuestos + ", Alineación/Balanceo: $" + COSTO_ALINEACION_Y_BALANCEO + ")";
    }
}
