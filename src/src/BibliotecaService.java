import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BibliotecaService {

    private Map<String, Libro> librosPorIsbn = new HashMap<>();
    private Map<String, Biblioteca> usuariosPorId = new HashMap<>();
    private ArrayList<Prestamo> prestamos = new ArrayList<>();

    public void registrarLibro(Libro libro) {
        if (libro == null) return;
        //Elimine codigo aqui, porque no hacia falta, creo
        librosPorIsbn.put(libro.getIsbn(), libro);
    }

    public void registrarUsuario(Biblioteca biblioteca) {
        if (biblioteca == null || biblioteca.getNombre() == null || biblioteca.getNombre().trim().isEmpty()) { // Validación cambiada
            return;
        }
        usuariosPorId.put(biblioteca.getId(), biblioteca);
    }

    public Prestamo prestarLibro(String idUsuario, String isbn) { // De private a public
        Usuario u = usuariosPorId.get(idUsuario);
        Libro l = librosPorIsbn.get(isbn);

        if (u == null || l == null) {
            System.out.println("No existe usuario o libro");
            return null;
        }

        if (!l.estaDisponible() || !u.tieneHuecoParaOtroPrestamo()) { //Validación añadida
            System.out.println("No se puede realizar el préstamo");
            return null;
        }

        l.prestarEjemplar();

        // Fechas actuales añadidas
        Prestamo p = new Prestamo(u, l, java.time.LocalDate.now(),
                java.time.LocalDate.now().plusDays(14));
        prestamos.add(p);

        //Añadir préstamo a la lista de préstamos activos del usuario
        if (u.getPrestamosActivos() != null) {
            u.getPrestamosActivos().add(p);
        }

        return p; //Devolver el préstamo creado
    }

    public void devolverLibro(String idUsuario, String isbn) {
        for (Prestamo p : prestamos) {
            if (p.getUsuario().getId().equals(idUsuario) &&
                    p.getLibro().getIsbn().equals(isbn) && //Cambiado == por equals
                    !p.isDevuelto()) {
                p.marcarDevuelto();

                //Removido préstamo de la lista activa del usuario
                Usuario u = p.getUsuario();
                if (u.getPrestamosActivos() != null) {
                    u.getPrestamosActivos().remove(p);
                }
                break;
            }
        }
    }

    public boolean puedePrestar(String idUsuario, String isbn) {
        Usuario u = usuariosPorId.get(idUsuario);
        Libro l = librosPorIsbn.get(isbn);

        //Aqui cambie el codiog
        if (u == null || l == null) {
            return false;
        }

        if (!l.estaDisponible()) {
            return false;
        }

        // Se cuenta los préstamos activos del usuario
        int contadorPrestamos = 0;
        for (Prestamo p : prestamos) {
            if (p.getUsuario().getId().equals(idUsuario) && !p.isDevuelto()) { // CORREGIDO: equals en lugar de ==
                contadorPrestamos++;
            }
        }

        return contadorPrestamos < u.getMaximoPrestamosSimultaneos();





