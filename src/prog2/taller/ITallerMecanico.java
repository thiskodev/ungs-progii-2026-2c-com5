package prog2.taller;

public interface ITallerMecanico {

    /**
     * Registra una orden de tipo mantenimiento preventivo (aceite, filtros).
     */
    void registrarOrdenMantenimiento(int nroOrden, String patente, int horas, double costoInsumos);

    /**
     * Registra una orden de reparación de suspensión (amortiguadores, tren delantero).
     */
    void registrarOrdenSuspension(int nroOrden, String patente, int horas, double costoRepuestos);

    /**
     * Registra una orden de diagnóstico y reparación eléctrica/electrónica.
     */
    void registrarOrdenElectronica(int nroOrden, String patente, int horas, boolean reprogramacionECU);

    /**
     * Obtiene el costo total de una orden específica a partir de su número de orden.
     * Lanza IllegalArgumentException si la orden no existe.
     */
    double calcularCostoOrden(int nroOrden);

    /**
     * Calcula de forma polimórfica la suma total facturada por todas las órdenes registradas.
     */
    double calcularTotalFacturado();

    /**
     * Retorna la cantidad total de órdenes registradas en el taller.
     */
    int cantidadOrdenes();
}
