package m06;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import org.hibernate.Session;

public class Main {
	public static void main(String[]args) {
        System.out.println("INICIO DEL PROGRAMA...");
        
        Configuration cfg = new Configuration().configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
        Session session = sessionFactory.openSession();
        System.out.println("CONFIGURACIÓN REALIZADA");
        
        LibrosCrud librosCrud = new LibrosCrud(sessionFactory);
        LectoresCrud lectoresCrud = new LectoresCrud(sessionFactory);
        PrestamoCrud prestamoCrud = new PrestamoCrud();
        

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
        	try {

	        	System.out.println("--------------BIBLIOTECA-----------------------------\n");
	            System.out.println("Selecciona la opción deseada:\n");
	            System.out.println("1- Insertar libro");
	            System.out.println("2- Eliminar libro");
	            System.out.println("3- Actualizar libro");
	            System.out.println("4- Insertar lector");
	            System.out.println("5- Eliminar lector");
	            System.out.println("6- Actualizar lector");
	            System.out.println("7- Listado de libros");
	            System.out.println("8- Listado de lectores");
	            System.out.println("9- Ver libro por ID");
	            System.out.println("10- Ver lector por ID");
	            System.out.println("11- Listado de libros prestados a un lector");
	            System.out.println("12- Listado de libros disponibles para préstamo");
	            System.out.println("13- Historial de préstamos por lector");
	            System.out.println("14- Prestar libro");
	            System.out.println("15- Devolver libro");
	            System.out.println("16- Salir.");
	            System.out.println("-----------------------------------------------------");
	            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Insertar nuevo libro:");
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();

                    System.out.print("Año de publicación: ");
                    int añoPublicacion = scanner.nextInt();
                    scanner.nextLine();
                    
                    Libro nuevoLibro = new Libro();
                    nuevoLibro.setTitulo(titulo);
                    nuevoLibro.setAutor(autor);
                    nuevoLibro.setAñoPublicacion(añoPublicacion);
                    nuevoLibro.setDisponible(true);
                    
                    Transaction txLibro= null;
                    try {
	                    txLibro = session.beginTransaction();
	                    librosCrud.agregarLibro(nuevoLibro);
	                    txLibro.commit();
	
	                    System.out.println("Libro agregado correctamente.");
                    }catch(Exception e) {
                    	if(txLibro != null) {
                    		txLibro.rollback();
                    }
                    	e.printStackTrace();
                    }
	                 
                    break;
                    
                case 2:
                    System.out.println("Eliminar libro:");
                    System.out.print("Ingrese el ID del libro que desea eliminar: ");
                    long idLibroEliminar = scanner.nextLong();
                    scanner.nextLine();
                    librosCrud.eliminarLibro(idLibroEliminar);
                    break;
                    
                case 3:
                    System.out.println("Actualizar libro:");
                    System.out.print("Ingrese el ID del libro que desea actualizar: ");
                    long idLibroActualizar = scanner.nextLong();
                    scanner.nextLine();
                    Libro libroActualizar = librosCrud.obtenerLibroPorId(idLibroActualizar);
                    if (libroActualizar != null) {
                        System.out.println("Ingrese los nuevos datos del libro:");
                        System.out.print("Título: ");
                        String nuevoTitulo = scanner.nextLine();
                        System.out.print("Autor: ");
                        String nuevoAutor = scanner.nextLine();
                        System.out.print("Año de publicación: ");
                        int nuevoAñoPublicacion = scanner.nextInt();
                        scanner.nextLine();
                        
                        libroActualizar.setTitulo(nuevoTitulo);
                        libroActualizar.setAutor(nuevoAutor);
                        libroActualizar.setAñoPublicacion(nuevoAñoPublicacion);
                        
                        Transaction txActualizarLibro = session.beginTransaction();
                        try {
                            librosCrud.actualizarLibro(libroActualizar);
                            txActualizarLibro.commit();
                            System.out.println("Libro actualizado correctamente.");
                        } catch (Exception e) {
                            if (txActualizarLibro != null) {
                                txActualizarLibro.rollback();
                            }
                            e.printStackTrace();
                            System.out.println("Error al actualizar el libro.");
                        }
                    } else {
                        System.out.println("El libro con el ID " + idLibroActualizar + " no existe.");
                    }
                    break;
                    
                    
                case 4:
                    System.out.println("Insertar nuevo lector:");

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
                    String fechaNacimientoStr = scanner.nextLine().trim();
                    LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr);

                    Lector nuevoLector = new Lector();
                    nuevoLector.setNombre(nombre);
                    nuevoLector.setApellido(apellido);
                    nuevoLector.setEmail(email);
                    nuevoLector.setFechaNacimiento(fechaNacimiento);

                    Transaction txLector = session.beginTransaction();
                    lectoresCrud.agregarLector(nuevoLector);
                    txLector.commit();

                    System.out.println("Lector agregado correctamente.");
                    break;
                    
                case 5:
                    System.out.println("Eliminar lector:");
                    System.out.print("Ingrese el ID del lector que desea eliminar: ");
                    long idLectorEliminar = scanner.nextLong();
                    scanner.nextLine();
                    lectoresCrud.eliminarLector(idLectorEliminar);
                    System.out.println("Lector eliminado correctamente.");
                    break;
                    
                case 6:
                    System.out.println("Actualizar lector:");
                    System.out.print("Ingrese el ID del lector que desea actualizar: ");
                    long idLectorActualizar = scanner.nextLong();
                    scanner.nextLine();
                    Lector lectorActualizar = lectoresCrud.obtenerLectorPorId(idLectorActualizar);
                    if (lectorActualizar != null) {
                        System.out.println("Ingrese los nuevos datos del lector:");
                        System.out.print("Nombre: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Apellido: ");
                        String nuevoApellido = scanner.nextLine();
                        System.out.print("Email: ");
                        String nuevoEmail = scanner.nextLine();
                        System.out.print("Fecha de nacimiento (YYYY-MM-DD): ");
                        String nuevaFechaNacimientoStr = scanner.nextLine().trim();
                        LocalDate nuevaFechaNacimiento = LocalDate.parse(nuevaFechaNacimientoStr);
                        
                        lectorActualizar.setNombre(nuevoNombre);
                        lectorActualizar.setApellido(nuevoApellido);
                        lectorActualizar.setEmail(nuevoEmail);
                        lectorActualizar.setFechaNacimiento(nuevaFechaNacimiento);
                        
                        Transaction txActualizarLector = session.beginTransaction();
                        try {
                            lectoresCrud.actualizarLector(lectorActualizar);
                            txActualizarLector.commit();
                            System.out.println("Lector actualizado correctamente.");
                        } catch (Exception e) {
                            if (txActualizarLector != null) {
                                txActualizarLector.rollback();
                            }
                            e.printStackTrace();
                            System.out.println("Error al actualizar el lector.");
                        }
                    } else {
                        System.out.println("El lector con el ID " + idLectorActualizar + " no existe.");
                    }
                    break;

                case 7:
                    System.out.println("Listado de libros:");

                    String hql = "FROM Libro";
                    Query<Libro> query = session.createQuery(hql, Libro.class);
                    List<Libro> libros = query.list();

                    if (libros.isEmpty()) {
                        System.out.println("No hay libros disponibles.");
                    } else {

                        for (Libro libro : libros) {
                            System.out.println("ID: " + libro.getId());
                            System.out.println("Título: " + libro.getTitulo());
                            System.out.println("Autor: " + libro.getAutor());
                            System.out.println("Año de Publicación: " + libro.getAñoPublicacion());
                            System.out.println("Disponible: " + (libro.isDisponible() ? "Sí" : "No"));
                            System.out.println();
                        }
                    }

                    break;
                    
                    
                case 8:
                    System.out.println("Listado de lectores:");

                    String hql2 = "FROM Lector";
                    Query<Lector> query2 = session.createQuery(hql2, Lector.class);
                    List<Lector> lectores = query2.list();

                    if (lectores.isEmpty()) {
                        System.out.println("No hay lectores registrados.");
                    } else {
                        for (Lector lector : lectores) {
                            System.out.println("ID: " + lector.getId());
                            System.out.println("Nombre: " + lector.getNombre());
                            System.out.println("Apellido: " + lector.getApellido());
                            System.out.println("Email: " + lector.getEmail());
                            System.out.println("Fecha de Nacimiento: " + lector.getFechaNacimiento());
                            System.out.println();
                        }
                    }
                    
                    break;
                    
                case 9:
                    System.out.print("Ingrese el ID del libro que desea ver: ");
                    long libroId = scanner.nextLong();

                    Libro libro = librosCrud.obtenerLibroPorId(libroId);

                    if (libro != null) {

                        System.out.println("Detalles del libro:");
                        System.out.println("ID: " + libro.getId());
                        System.out.println("Título: " + libro.getTitulo());
                        System.out.println("Autor: " + libro.getAutor());
                        System.out.println("Año de publicación: " + libro.getAñoPublicacion());
                        System.out.println("Disponible: " + (libro.isDisponible() ? "Sí" : "No"));
                    } else {
                        System.out.println("El libro con el ID " + libroId + " no existe.");
                    }


                    break;
                    
                case 10:
                    System.out.print("Ingrese el ID del lector que desea ver: ");
                    long lectorId = scanner.nextLong();

                    Lector lector = lectoresCrud.obtenerLectorPorId(lectorId);

                    if (lector != null) {

                        System.out.println("Detalles del lector:");
                        System.out.println("ID: " + lector.getId());
                        System.out.println("Nombre: " + lector.getNombre());
                        System.out.println("Apellido: " + lector.getApellido());
                        System.out.println("Email: " + lector.getEmail());
                        System.out.println("Fecha de nacimiento: " + lector.getFechaNacimiento());
                    } else {

                        System.out.println("El lector con el ID " + lectorId + " no existe.");
                    }


                    break;
                    
                case 11:
                    System.out.println("Listado de libros prestados a un lector:");
                    System.out.print("Ingrese el ID del lector: ");
                    long lectorId2 = scanner.nextLong();

                    Lector lector1 = lectoresCrud.obtenerLectorPorId(lectorId2);
                    if (lector1 != null) {
                        List<Libro> librosPrestados = prestamoCrud.librosPrestadosALector(lector1);

                        if (librosPrestados.isEmpty()) {
                            System.out.println("El lector no tiene libros prestados actualmente.");
                        } else {
                            System.out.println("Libros prestados al lector:");
                            for (Libro libro1 : librosPrestados) {
                                System.out.println("- " + libro1.getTitulo());
                            }
                        }
                    } else {
                        System.out.println("El lector con el ID " + lectorId2 + " no existe.");
                    }
                    break;
                    
                case 12:
                    System.out.println("Listado de libros disponibles para préstamo:");

                    List<Libro> librosDisponibles = librosCrud.librosDisponibles();

                    if (librosDisponibles.isEmpty()) {
                        System.out.println("No hay libros disponibles para préstamo.");
                    } else {
                        System.out.println("Libros disponibles para préstamo:");
                        for (Libro libro1 : librosDisponibles) {
                            System.out.println("- " + libro1.getTitulo());
                        }
                    }
                    break;
                    
                case 13:
                    System.out.println("Historial de préstamos por lector:");
                    System.out.print("Ingrese el ID del lector: ");
                    long lectorId1 = scanner.nextLong();

                    Lector lector2 = lectoresCrud.obtenerLectorPorId(lectorId1);
                    if (lector2 != null) {
                        List<Prestamo> historialPrestamos = prestamoCrud.historialPrestamosPorLector(lector2);

                        if (historialPrestamos.isEmpty()) {
                            System.out.println("El lector no tiene historial de préstamos.");
                        } else {
                            System.out.println("Historial de préstamos del lector:");
                            for (Prestamo prestamo : historialPrestamos) {
                                System.out.println("- Libro: " + prestamo.getLibro().getTitulo() +
                                                   ", Fecha de préstamo: " + prestamo.getFechaPrestamo() +
                                                   ", Fecha de devolución: " + prestamo.getFechaDevolucion());
                            }
                        }
                    } else {
                        System.out.println("El lector con el ID " + lectorId1 + " no existe.");
                    }
                    break;
                    
                case 14:
                    System.out.println("Prestar libro:");
                    System.out.print("Ingrese el ID del libro: ");
                    long libroIdPrestar = scanner.nextLong();
                    System.out.print("Ingrese el ID del lector: ");
                    long lectorIdPrestar = scanner.nextLong();

                    Libro libroPrestar = librosCrud.obtenerLibroPorId(libroIdPrestar);
                    Lector lectorPrestar = lectoresCrud.obtenerLectorPorId(lectorIdPrestar);

                    if (libroPrestar != null && lectorPrestar != null) {
                        if (libroPrestar.isDisponible()) {
                            Transaction txPrestamo = session.beginTransaction();
                            try {
                                Prestamo prestamo = new Prestamo();
                                
                                prestamo.setLibro(libroPrestar);
                                prestamo.setLector(lectorPrestar);
                                prestamo.setFechaPrestamo(LocalDate.now());
                                prestamo.setFechaDevolucion(LocalDate.now().plusWeeks(1));
                                prestamoCrud.registrarPrestamo(prestamo);
                                libroPrestar.setDisponible(false);
                                librosCrud.actualizarLibro(libroPrestar);
                                
                                txPrestamo.commit();
                                
                                System.out.println("Libro prestado correctamente.");
                                
                            } catch (Exception e) {
                                if (txPrestamo != null) {
                                    txPrestamo.rollback();
                                }
                                e.printStackTrace();
                                System.out.println("Error al prestar el libro.");
                            }
                        } else {
                            System.out.println("El libro no está disponible para préstamo.");
                        }
                    } else {
                        System.out.println("El libro o el lector no existen.");
                    }
                    break;
                
                case 15:
                    System.out.println("Devolver libro:");
                    System.out.print("Ingrese el ID del préstamo: ");
                    long prestamoId = scanner.nextLong();

                    Prestamo prestamoDevolver = prestamoCrud.obtenerPrestamoPorId(prestamoId);

                    if (prestamoDevolver != null) {
                        Transaction txDevolucion = session.beginTransaction();
                        try {
                            Libro libroDevuelto = prestamoDevolver.getLibro();
                            libroDevuelto.setDisponible(true);
                            librosCrud.actualizarLibro(libroDevuelto);
                            prestamoDevolver.setFechaDevolucion(LocalDate.now());
                            prestamoCrud.actualizarPrestamo(prestamoDevolver);

                            txDevolucion.commit();

                            System.out.println("Libro devuelto correctamente.");
                        } catch (Exception e) {
                            if (txDevolucion != null) {
                                txDevolucion.rollback();
                            }
                            e.printStackTrace();
                            System.out.println("Error al devolver el libro.");
                        }
                    } else {
                        System.out.println("El préstamo con el ID " + prestamoId + " no existe.");
                    }
                    break;


                case 16:
                    System.out.println("Salir");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
            
        	}catch(InputMismatchException e) {
        		System.out.println("Entrada inválida. Por favor intresa un numero del 1 al 12");
        		scanner.next();
        	}
        } while (opcion != 16);

       
        session.close();
        sessionFactory.close();
        scanner.close();
        
    }

}

