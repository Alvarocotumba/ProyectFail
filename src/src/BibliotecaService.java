import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BibliotecaService {

    private Map<String, Libro> librosPorIsbn = new HashMap<>();
    // ser Map<String, Biblioteca>
    private Map<String, Biblioteca> usuariosPorId = new HashMap<>(); // CORREGIDO
    private ArrayList<Prestamo> prestamos = new ArrayList<>();

    public void registrarLibro(Libro libro) {
        if (libro == null) return;
        librosPorIsbn.put(libro.getIsbn(), libro);
    }

    //debe recibir Biblioteca
    public void registrarUsuario(Biblioteca biblioteca) { // CORREGIDO
        if (biblioteca == null) return;
        usuariosPorId.put(biblioteca.getId(), biblioteca);
    }

    public Prestamo prestarLibro(String idUsuario, String isbn) {
        //usuariosPorId es de Biblioteca
        Biblioteca b = usuariosPorId.get(idUsuario); // CORREGIDO
        Libro l = librosPorIsbn.get(isbn);

        // b es Biblioteca, no tiene el metodo tieneHuecoParaOtroPrestamo()
        if (b == null || l == null || !l.estaDisponible() || !b.tieneHuecoParaOtroPrestamo()) {
            return null;
        }

        l.prestarEjemplar();
        // el constructor Prestamo espera Usuario, no Biblioteca
        // Prestamo usa Usuario pero ttodo el sistema usa Biblioteca
        // Se cambia: Prestamo p = new Prestamo(b, l, java.time.LocalDate.now(),
        //        java.time.LocalDate.now().plusDays(14));

        //Cambiar Prestamo para que use Biblioteca en lugar de Usuario
        Prestamo p = new Prestamo(null, l, java.time.LocalDate.now(),
                java.time.LocalDate.now().plusDays(14)); // mal: null porque no hay Usuario
        prestamos.add(p);
        // b.getPrestamosActivos().add(p); // mal: b es Biblioteca, tiene este metodo
        return p;
    }

    public void devolverLibro(String idUsuario, String isbn) {
        for (Prestamo p : prestamos) {
            //p.getUsuario() devuelve Usuario, pero nosotros tenemos Biblioteca
            if (p.getUsuario().getId().equals(idUsuario) && // mal: getUsuario() es null
                    p.getLibro().getIsbn().equals(isbn) &&
                    !p.isDevuelto()) {
                p.marcarDevuelto();
                // p.getUsuario().getPrestamosActivos().remove(p);
                break;
            }
        }
    }

    public boolean puedePrestar(String idUsuario, String isbn) {
        Biblioteca b = usuariosPorId.get(idUsuario); // CORREGIDO
        Libro l = librosPorIsbn.get(isbn);

        if (b == null || l == null || !l.estaDisponible()) return false;

        int contador = 0;
        for (Prestamo p : prestamos) {
            // mal: p.getUsuario() es null o Usuario, no Biblioteca
            if (p.getUsuario().getId().equals(idUsuario) && !p.isDevuelto()) {
                contador++;
            }
        }
        return contador < b.getMaximoPrestamosSimultaneos(); // b es Biblioteca
    }
}