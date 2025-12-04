import java.util.Scanner;

public class BibliotecaApp {

    private BibliotecaService servicio;

    public BibliotecaApp() {
        servicio = new BibliotecaService();
    }

    public static void main(String[] argumentos) {
        BibliotecaApp app = new BibliotecaApp();
        app.ejecutarMenu();
    }

    private void ejecutarMenu() {
        Scanner scanner = new Scanner(System.in);

        int opcion = -1;
        while (opcion != 0) {
            imprimirMenu();
            // CORREGIDO: Validación de entrada
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir newline
            } else {
                scanner.nextLine(); // Limpiar entrada inválida
                opcion = -1;
            }

            if (opcion == 1) {
                registrarLibroDesdeConsola(scanner);
            } else if (opcion == 2) {
                registrarUsuarioDesdeConsola(scanner);
            } else if (opcion == 3) {
                prestarLibroDesdeConsola(scanner);
            } else if (opcion == 4) {
                devolverLibroDesdeConsola(scanner);
            } else if (opcion == 0) {
                System.out.println("Saliendo...");
            } else {
                System.out.println("Opción no válida");
            }
        }

        scanner.close(); // CORREGIDO: Añadido punto y coma
    }

    private void imprimirMenu() {
        System.out.println("=== GESTIÓN BIBLIOTECA ===");
        System.out.println("1. Registrar libro");
        System.out.println("2. Registrar usuario");
        System.out.println("3. Prestar libro");
        System.out.println("4. Devolver libro");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    private void registrarLibroDesdeConsola(Scanner scanner) {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Año publicación: ");
        int anio = scanner.nextInt();
        System.out.print("Ejemplares totales: ");
        int totales = scanner.nextInt();

        Libro libro = new Libro(isbn, titulo, autor, anio, totales); // CORREGIDO: Cambiado "total" por "totales"
        servicio.registrarLibro(libro);
        System.out.println("Libro registrado: " + libro.getTitulo());
    }

    private void registrarUsuarioDesdeConsola(Scanner scanner) {
        System.out.print("ID usuario: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        Usuario usuario = new Usuario(id, nombre); // CORREGIDO: Cambiado "idUsuario" por "id"
        servicio.registrarUsuario(usuario);
        System.out.println("Usuario registrado: " + usuario.getNombre());
    }

    private void prestarLibroDesdeConsola(Scanner scanner) {
        System.out.print("ID usuario: ");
        String id = scanner.nextLine();
        System.out.print("ISBN libro: ");
        String isbn = scanner.nextLine();

        Prestamo p = servicio.prestarLibro(id, isbn);
        if (p != null) {
            System.out.println("Préstamo realizado con éxito");
        } else {
            System.out.println("No se pudo realizar el préstamo");
        }
    }

    private void devolverLibroDesdeConsola(Scanner scanner) {
        System.out.print("ID usuario: ");
        String id = scanner.nextLine();
        System.out.print("ISBN libro: ");
        String isbn = scanner.nextLine();

        servicio.devolverLibro(id, isbn);
        System.out.println("Libro devuelto (si existía un préstamo activo)");
    }
}
