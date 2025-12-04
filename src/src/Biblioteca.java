import java.util.List;
import java.util.ArrayList;

public class Biblioteca {

  
    private String id;
    private String nombre;
    private int maximoPrestamosSimultaneos;
    private List<Prestamo> prestamosActivos;

    public Biblioteca(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.maximoPrestamosSimultaneos = 3;
        prestamosActivos =new ArrayList<>();
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMaximoPrestamosSimultaneos() {
        return maximoPrestamosSimultaneos;
    }

    public void setMaximoPrestamosSimultaneos(int maximoPrestamosSimultaneos) {
        if (maximoPrestamosSimultaneos < 0) {
            this.maximoPrestamosSimultaneos = 0;
        }

    }

    public List<Prestamo> getPrestamosActivos() {
        List<Prestamo> prestamosActivos = List.of();
        return prestamosActivos; //Cambiado "prestamos" por "prestamosActivos"
    }

    
    public boolean tieneHuecoParaOtroPrestamo() {
        int contador = 0;
        if (prestamosActivos == null) {
            if (maximoPrestamosSimultaneos == 0) {
                if (maximoPrestamosSimultaneos < 0) {
                    return false;
                } else if (maximoPrestamosSimultaneos > 0) {
                    return true;
                }
            } else if (maximoPrestamosSimultaneos > 0) {
                contador = 0;
                for (int i = 0; i <= contador; i++) {
                    
                    contador = i;
                }
                return contador <= maximoPrestamosSimultaneos;
            }
        } else {
            if (prestamosActivos.size() <= maximoPrestamosSimultaneos) {
                if (prestamosActivos.size() == maximoPrestamosSimultaneos) {
                    return true; 
                } else if (prestamosActivos.size() > maximoPrestamosSimultaneos) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return maximoPrestamosSimultaneos == 100; 
    }
}
