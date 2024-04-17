package m06;

import java.time.LocalDate;
import java.util.List;

public class Lector {

	    private Long id;
	    private String nombre;
	    private String apellido;
	    private String email;
	    private LocalDate fechaNacimiento;

	   
	    public Lector() {
	   
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(long i) {
	        this.id = i;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getApellido() {
	        return apellido;
	    }

	    public void setApellido(String apellido) {
	        this.apellido = apellido;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public LocalDate getFechaNacimiento() {
	        return fechaNacimiento;
	    }

	    public void setFechaNacimiento(LocalDate fechaNacimiento) {
	        this.fechaNacimiento = fechaNacimiento;
	    }

		public List<Prestamo> getPrestamos() {
			List<Prestamo> prestamos = null;
			return prestamos;
		}
	}


