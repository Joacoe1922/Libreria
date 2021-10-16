package Principal.persistencia;

import Principal.entidades.Editorial;
import Principal.entidades.Libro;
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

    public void eliminarEditorialId(int id) throws Exception {
        Editorial editorial = buscarEditorialId(id);
        LibroDAO libroDAO = new LibroDAO();
        List<Libro> libros = libroDAO.listarLibrosPorEditorial(id);
        for (Libro libro : libros) {
            if (libro.getEditorial().getId().equals(editorial.getId())) {
                System.out.println("No se puede eliminar una editorial asociada a libro/s existente/s");
            } else {
                em.getTransaction().begin();
                em.remove(editorial);
                em.getTransaction().commit();
            }
        }

    }

    public Editorial buscarEditorialId(int id) throws Exception {
        Editorial editorial = em.find(Editorial.class, id); // Esto que envio es la llave primaria        
        return editorial;
    }

    public List<Editorial> listarEditoriales() throws Exception {
        List<Editorial> editoriales = em.createQuery("SELECT E FROM Editorial E")
                .getResultList();
        return editoriales;
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
