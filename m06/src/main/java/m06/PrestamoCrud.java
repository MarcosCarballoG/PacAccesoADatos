package m06;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class PrestamoCrud {
    private SessionFactory sessionFactory;

    public PrestamoCrud() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();
    }

    public void prestarLibro(Libro libro, Lector lector) {
        if (libro.isDisponible()) {
            Prestamo prestamo = new Prestamo();
            prestamo.setLibro(libro);
            prestamo.setLector(lector);
            prestamo.setFechaPrestamo(LocalDate.now());
            libro.setDisponible(false); 

            try (Session session = sessionFactory.openSession()) {
                Transaction tx = session.beginTransaction();
                session.save(prestamo);
                session.update(libro); 
                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El libro no está disponible para préstamo.");
        }
    }

    public void devolverLibro(Prestamo prestamo) {
        prestamo.setFechaDevolucion(LocalDate.now());
        prestamo.getLibro().setDisponible(true); 

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(prestamo);
            session.update(prestamo.getLibro()); 
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Libro> librosPrestadosALector(Lector lector) {
        List<Libro> librosPrestados = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT p.libro FROM Prestamo p WHERE p.lector = :lector AND p.fechaDevolucion IS NULL";
            Query<Libro> query = session.createQuery(hql, Libro.class);
            query.setParameter("lector", lector);
            librosPrestados = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return librosPrestados;
    }
    
    public List<Prestamo> historialPrestamosPorLector(Lector lector) {
        List<Prestamo> historialPrestamos = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Prestamo WHERE lector = :lector ORDER BY fechaPrestamo DESC";
            Query<Prestamo> query = session.createQuery(hql, Prestamo.class);
            query.setParameter("lector", lector);
            historialPrestamos = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return historialPrestamos;
    }
    
    public void registrarPrestamo(Prestamo prestamo) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(prestamo);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Prestamo obtenerPrestamoPorId(long prestamoId) {
        Prestamo prestamo = null;
        try (Session session = sessionFactory.openSession()) {
            prestamo = session.get(Prestamo.class, prestamoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prestamo;
    }

    public void actualizarPrestamo(Prestamo prestamo) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(prestamo);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

