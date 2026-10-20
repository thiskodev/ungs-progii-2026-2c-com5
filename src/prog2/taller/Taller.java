package prog2.taller;

import java.util.ArrayList;
import java.util.List;

public class Taller implements ITallerMecanico {
    private String nombre;
    private List<OrdenDeTrabajo> ordenes;

    public Taller(String nombre) {
        this.nombre = nombre;
        this.ordenes = new ArrayList<>();
    }

    public Taller() {
        this("Taller Central");
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void registrarOrdenMantenimiento(int nroOrden, String patente, int horas, double costoInsumos) {
        validarNroOrdenUnico(nroOrden);
        this.ordenes.add(new OrdenMantenimiento(nroOrden, patente, horas, costoInsumos));
    }

    @Override
    public void registrarOrdenSuspension(int nroOrden, String patente, int horas, double costoRepuestos) {
        validarNroOrdenUnico(nroOrden);
        this.ordenes.add(new OrdenSuspension(nroOrden, patente, horas, costoRepuestos));
    }

    @Override
    public void registrarOrdenElectronica(int nroOrden, String patente, int horas, boolean reprogramacionECU) {
        validarNroOrdenUnico(nroOrden);
        this.ordenes.add(new OrdenElectronica(nroOrden, patente, horas, reprogramacionECU));
    }

    @Override
    public double calcularCostoOrden(int nroOrden) {
        OrdenDeTrabajo orden = buscarOrden(nroOrden);
        if (orden == null) {
            throw new IllegalArgumentException("No existe ninguna orden con número: " + nroOrden);
        }
        return orden.calcularCostoTotal();
    }

    /**
     * Calcula la suma total facturada invocando polimórficamente calcularCostoTotal().
     * Sin utilizar 'instanceof' ni estructuras condicionales por tipo.
     */
    @Override
    public double calcularTotalFacturado() {
        double total = 0.0;
        for (OrdenDeTrabajo orden : this.ordenes) {
            total += orden.calcularCostoTotal();
        }
        return total;
    }

    @Override
    public int cantidadOrdenes() {
        return this.ordenes.size();
    }

    public List<OrdenDeTrabajo> getOrdenes() {
        return this.ordenes;
    }

    private OrdenDeTrabajo buscarOrden(int nroOrden) {
        for (OrdenDeTrabajo orden : this.ordenes) {
            if (orden.getNroOrden() == nroOrden) {
                return orden;
            }
        }
        return null;
    }

    private void validarNroOrdenUnico(int nroOrden) {
        if (buscarOrden(nroOrden) != null) {
            throw new IllegalArgumentException("Ya existe una orden con el número: " + nroOrden);
        }
    }
}
