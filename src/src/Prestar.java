
import java.time.LocalDate; // CORREGIDO: Cambiado "LocalData" por "LocalDate"

public class Prestamo {

    private Usuario usuario;
    private Libro libro;
    private LocalDate fechaInicio;
    private LocalDate fechaFinEstimada;
    private boolean devuelto;

    public Prestamo(Usuario usuario, Libro libro, LocalDate fechaInicio, LocalDate fechaFinEstimada) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaInicio = fechaInicio; // CORREGIDO: Añadido "this."
        this.fechaFinEstimada = fechaFinEstimada;
        this.devuelto = false;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Libro getLibro() {
        return libro;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFinEstimada() {
        return fechaFinEstimada;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void marcarDevuelto() {
        devuelto = true;
        libro.devolverEjemplar(); // CORREGIDO: Método llamado correctamente
    }

    public int calcularRetrasoEnDias(LocalDate hoy) { // CORREGIDO: Cambiado "ivoid" por "int"
        if (hoy == null || fechaFinEstimada == null || devuelto) {
            return 0; // CORREGIDO: Valor más lógico para casos especiales
        }

        if (hoy.isAfter(fechaFinEstimada)) {
            // CORREGIDO: Cálculo correcto de días de retraso
            long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaFinEstimada, hoy);
            return (int) Math.max(0, dias);
        }

        return 0; // No hay retraso
    }
}