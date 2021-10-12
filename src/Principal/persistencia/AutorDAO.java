package Principal.persistencia;

import Principal.entidades.Autor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AutorDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarAutor(Autor autor) throws Exception {
        em.getTransaction().begin();
        em.persist(autor);
        em.getTransaction().commit();
    }

    public void modificarAutor(Autor autor) throws Exception {
        em.getTransaction().begin();
        em.merge(autor);
        em.getTransaction().commit();
    }

    public void eliminarAutorId(int id) throws Exception {
        Autor autor = buscarAutorId(id);
        em.getTransaction().begin();
        em.remove(autor);
        em.getTransaction().commit();
    }

    public Autor buscarAutorId(int id) throws Exception {
        Autor autor = em.find(Autor.class, id); // Esto que envio es la llave primaria
        return autor;
    }

    public List<Autor> buscarAutorNombre(String nombre) throws Exception {
        List<Autor> autores = em.createQuery("SELECT a "
                + " FROM Autor a"
                + " WHERE a.nombre LIKE :nombre").
                setParameter("nombre", nombre).
                getResultList();
       
        return autores;
    }

    //hacer metodo de eliminar
//    public Usuario buscarUsuarioPorCorreoElectronico(String correoElectronico) throws Exception {
//        Usuario usuario = (Usuario) em.createQuery("SELECT d "
//                + " FROM Usuario d"
//                + " WHERE d.correoElectronico LIKE :correoElectronico").
//                setParameter("correoElectronico", correoElectronico).
//                getSingleResult();      
//        return usuario;
//    }
}
