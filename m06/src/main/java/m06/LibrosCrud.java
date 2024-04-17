package m06;

import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LibrosCrud {
    private SessionFactory sessionFactory;

    public LibrosCrud(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void agregarLibro(Libro libro) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(libro);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public void actualizarLibro(Libro libro) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.update(libro);
            tx.commit();
            session.clear();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public void eliminarLibro(long idLibroEliminar) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            Libro libro = session.get(Libro.class, idLibroEliminar);
            if (libro != null) {
                session.delete(libro);
                tx.commit();
                System.out.println("Libro eliminado correctamente");
            } else {
                System.out.println("El libro con ID " + idLibroEliminar + " no existe.");
            }
            session.clear();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    public Libro obtenerLibroPorId(Long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Libro libro = session.get(Libro.class, id);
            tx.commit();
            return libro;
            
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }


    public List librosDisponibles() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            String hql = "FROM Libro WHERE disponible = true";
            Query<Libro> query = session.createQuery(hql, Libro.class);
            List<Libro> libros = query.list();
            tx.commit();
            return libros;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        }

    }
}

