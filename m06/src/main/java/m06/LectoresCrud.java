package m06;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

public class LectoresCrud {

    private SessionFactory sessionFactory;

    public LectoresCrud(SessionFactory session) {
        this.sessionFactory = session;
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        session = configuration.buildSessionFactory();
    }

    public void agregarLector(Lector lector) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(lector);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Lector obtenerLector(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Lector.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actualizarLector(Lector lector) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(lector);
            tx.commit();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarLector(long idLectorEliminar) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Lector lector = session.get(Lector.class, idLectorEliminar);
            if (lector != null) {
                session.delete(lector);
                tx.commit();
            } else {
                System.out.println("El lector con ID " + idLectorEliminar + " no existe.");
            }
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Lector obtenerLectorPorId(Long id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Lector lector = session.get(Lector.class, id);
            tx.commit();
            return lector;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
 
}

