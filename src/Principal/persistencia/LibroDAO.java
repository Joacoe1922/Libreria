package Principal.persistencia;

import Principal.entidades.Libro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class LibroDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private final EntityManager em = emf.createEntityManager();

    public void guardarLibro(Libro libro) throws Exception {
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }

    public void modificarLibro(Libro libro) throws Exception {
        em.getTransaction().begin();
        em.merge(libro);
        em.getTransaction().commit();
    }

    public void eliminarLibroISBN(Long ISBN) throws Exception {
        Libro libro = buscarLibroPorISBN(ISBN);
        em.getTransaction().begin();
        em.remove(libro);
        em.getTransaction().commit();
    }

    public Libro buscarLibroPorISBN(Long ISBN) throws Exception {
        Libro libro = em.find(Libro.class, ISBN); // Esto que envio es la llave primaria
        return libro;
    }

    public List<Libro> listarLibros() throws Exception {
        List<Libro> libros = em.createQuery("SELECT L FROM Libro L")
                .getResultList();
        return libros;
    }
    
    public List<Libro> buscarLibroTitulo(String titulo) throws Exception {
        List<Libro> libros = em.createQuery("SELECT l "
                + " FROM Libro l"
                + " WHERE l.titulo LIKE :titulo").
                setParameter("titulo", titulo).
                getResultList();
       
        return libros;
    }
    
    public List<Libro> buscarLibroPorAutor(String nombre) throws Exception {
        List<Libro> libros = em.createQuery("SELECT l "
                + " FROM Libro l"
                + " WHERE l.autor.nombre LIKE :nombre").
                setParameter("nombre", nombre).
                getResultList();
       
        return libros;
    }

//    public void eliminarUsuarioCorreo(String correo) throws Exception {
//        Libro1 libro = buscarUsuarioPorCorreoElectronico(correo);
//        em.getTransaction().begin();
//        em.remove(libro);
//        em.getTransaction().commit();
//    }
}
