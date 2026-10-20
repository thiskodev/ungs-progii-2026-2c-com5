package prog2.taller;

public class OrdenMantenimiento extends OrdenDeTrabajo {
    private double costoInsumos;

    public OrdenMantenimiento(int nroOrden, String patente, int horasTrabajadas, double costoInsumos) {
        super(nroOrden, patente, horasTrabajadas);
        if (costoInsumos < 0) {
            throw new IllegalArgumentException("El costo de insumos no puede ser negativo.");
        }
        this.costoInsumos = costoInsumos;
    }

    public double getCostoInsumos() {
        return costoInsumos;
    }

    @Override
    public double calcularCostoTotal() {
        return calcularCostoManoDeObra() + this.costoInsumos;
    }

    @Override
    public String toString() {
        return super.toString() + " - Mantenimiento (Insumos: $" + costoInsumos + ")";
    }
}
