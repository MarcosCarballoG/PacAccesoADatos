package m06;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prestamoId;
	
	@ManyToOne
	@JoinColumn(name = "libro_id")
    private Libro libro;
	
	@ManyToOne
	@JoinColumn(name = "lector_id")
    private Lector lector;
    
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;


    public Prestamo() {

    }

    public Long getPrestamoId() {
        return prestamoId;
    }

    public void setPrestamoId(Long id) {
        this.prestamoId = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }
}



