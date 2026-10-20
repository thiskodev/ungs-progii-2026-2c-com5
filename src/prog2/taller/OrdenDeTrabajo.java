package prog2.taller;

public abstract class OrdenDeTrabajo {
    public static final double VALOR_HORA_BASE = 15000.0;

    private int nroOrden;
    private String patente;
    private int horasTrabajadas;

    public OrdenDeTrabajo(int nroOrden, String patente, int horasTrabajadas) {
        if (horasTrabajadas < 0) {
            throw new IllegalArgumentException("Las horas trabajadas no pueden ser negativas.");
        }
        this.nroOrden = nroOrden;
        this.patente = patente;
        this.horasTrabajadas = horasTrabajadas;
    }

    public int getNroOrden() {
        return nroOrden;
    }

    public String getPatente() {
        return patente;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    /**
     * Calcula el costo de la mano de obra común a todas las órdenes.
     */
    public double calcularCostoManoDeObra() {
        return this.horasTrabajadas * VALOR_HORA_BASE;
    }

    /**
     * Método polimórfico a ser implementado por cada subclase según sus particularidades.
     */
    public abstract double calcularCostoTotal();

    @Override
    public String toString() {
        return "Orden #" + nroOrden + " [Patente: " + patente + ", Horas: " + horasTrabajadas + "]";
    }
}
