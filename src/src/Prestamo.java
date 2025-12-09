import java.time.LocalDate;

public class Prestamo {

    private Usuario usuario; // mal: Debería ser Biblioteca según el sistema
    private Libro libro;
    private LocalDate fechaInicio;
    private LocalDate fechaFinEstimada;
    private boolean devuelto;

    // mal: Constructor espera Usuario pero el sistema usa Biblioteca
    public Prestamo(Usuario usuario, Libro libro, LocalDate fechaInicio, LocalDate fechaFinEstimada) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaInicio = fechaInicio;
        this.fechaFinEstimada = fechaFinEstimada;
        this.devuelto = false;
    }

    public class Usuario {

        public Object getId() {
            return usuario.getId();
        }
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
        libro.devolverEjemplar();
    }

    public int calcularRetrasoEnDias(LocalDate hoy) {
        if (hoy == null || fechaFinEstimada == null || devuelto) {
            return 0;
        }

        if (hoy.isAfter(fechaFinEstimada)) {
            long dias = java.time.temporal.ChronoUnit.DAYS.between(fechaFinEstimada, hoy);
            return (int) Math.max(0, dias);
        }

        return 0;
    }
}