package Principal.persistencia;

import Principal.entidades.Editorial;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EditorialDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarEditorial(Editorial editorial) throws Exception {
        em.getTransaction().begin();
        em.persist(editorial);
        em.getTransaction().commit();
    }

    public void modificarEditorial(Editorial editorial) throws Exception {
        em.getTransaction().begin();
        em.merge(editorial);
        em.getTransaction().commit();
    }

    
    public List<Editorial> buscarEditorialNombre(String nombre) throws Exception {
        List<Editorial> editoriales = em.createQuery("SELECT e "
                + " FROM Editorial e"
                + " WHERE e.nombre LIKE :nombre").
                setParameter("nombre", nombre).
                getResultList();
       
        return editoriales;
    }
}
