package prog2.taller;

public class OrdenElectronica extends OrdenDeTrabajo {
    public static final double COSTO_ESCANEO = 30000.0;
    public static final double RECARGO_REPROGRAMACION = 0.20;

    private boolean reprogramacionECU;

    public OrdenElectronica(int nroOrden, String patente, int horasTrabajadas, boolean reprogramacionECU) {
        super(nroOrden, patente, horasTrabajadas);
        this.reprogramacionECU = reprogramacionECU;
    }

    public boolean isReprogramacionECU() {
        return reprogramacionECU;
    }

    @Override
    public double calcularCostoTotal() {
        double manoDeObra = calcularCostoManoDeObra();
        double recargo = reprogramacionECU ? (manoDeObra * RECARGO_REPROGRAMACION) : 0.0;
        return manoDeObra + recargo + COSTO_ESCANEO;
    }

    @Override
    public String toString() {
        return super.toString() + " - Electrónica (Escaneo: $" + COSTO_ESCANEO + ", Reprogramación: " + (reprogramacionECU ? "Sí" : "No") + ")";
    }
}
