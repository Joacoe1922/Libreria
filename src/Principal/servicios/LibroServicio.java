package Principal.servicios;

import Principal.MenuOpciones;
import Principal.entidades.Autor;
import Principal.entidades.Editorial;
import Principal.entidades.Libro;
import Principal.persistencia.LibroDAO;
import java.util.Collection;
import java.util.List;

public class LibroServicio {
    
    private final LibroDAO daoLibro = new LibroDAO();
    
    public void crearLibro(String titulo, int anio, int ejemplares, int ejemplaresPrestados) {
        
        try {
            //Validamos
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("Debe indicar el titulo");
            }
//            if (correoElectronico.contains("@") == false) {
//                throw new Exception("El correo electrónico es incorrecto");
//            }
//            if (clave == null || clave.trim().isEmpty()) {
//                throw new Exception("Debe indicar la clave");
//            }
//            if (clave.length() < 6) {
//                throw new Exception("La clave no puede tener menos de 6 caracteres");
//            }

            //Creamos el usuario
            Libro libro = new Libro();
            AutorServicio autorServicio = new AutorServicio();
            EditorialServicio editorialServicio = new EditorialServicio();
            MenuOpciones menuOpciones = new MenuOpciones();

            //valido si el autor exite. Si es así lo selecciono, sino lo creo.
            String nombreAutor = menuOpciones.cargarNombreAutor();
            
            Collection<Autor> autorBuscado = autorServicio.buscarAutorNombre(nombreAutor);
            
            if (autorBuscado.isEmpty()) {
                Autor autor = autorServicio.crearAutorParaLibro(nombreAutor, true);
                libro.setAutor(autor);
            } else {
                System.out.println("El autor ingresado ya existe. Se seleccionará!");
                for (Autor autor : autorBuscado) {
                    libro.setAutor(autor);
                    break;
                }
            }

            //valido si la editorial exite. Si es así lo selecciono, sino lo creo.
            String nombreEditorial = menuOpciones.cargarNombreEditorial();
            
            Collection<Editorial> editorialBuscada = editorialServicio.buscarEditorialNombre(nombreEditorial);
            
            if (editorialBuscada.isEmpty()) {
                Editorial editorial = editorialServicio.crearEditorialParaLibro(nombreEditorial, true);
                libro.setEditorial(editorial);
            } else {
                System.out.println("La editorial ingresada ya existe. Se seleccionará!");
                for (Editorial editorial : editorialBuscada) {
                    libro.setEditorial(editorial);
                    break;
                }
            }

            //seteo los valores
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);
            libro.setAlta(true);
            
            daoLibro.guardarLibro(libro);
        } catch (Exception e) {
            System.out.println("No se creó el libro " + e.getMessage());
        }
    }
    
    public void darAltaBajaLibro(long ISBN, boolean altaBaja) {
        try {
            if (ISBN == 0) {
                throw new Exception("Debe indicar el ISBN");
            }
            for (Libro libro : listaLibros()) {
                if (libro.getISBN().equals(ISBN)) {
                    libro.setAlta(altaBaja);
                    daoLibro.modificarLibro(libro);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error al dar de baja " + e.getMessage());
        }
    }
    
    public void modificarLibro(Long ISBN) {
        try {
            AutorServicio autorServicio = new AutorServicio();
            EditorialServicio editorialServicio = new EditorialServicio();
            
            Libro libro = daoLibro.buscarLibroPorISBN(ISBN);
            
            if (!libro.getISBN().equals(ISBN)) {
                throw new Exception("El ISBN no existe");
            } else {
                MenuOpciones menuOpciones = new MenuOpciones();
                String titulo = menuOpciones.cargarTitulo();
                int anio = menuOpciones.cargarAnio();
                int ejemplares = menuOpciones.cargarEjemplares();
                int ejemplaresPrestados = menuOpciones.cargarEjemplaresPrestados();
                
                libro.setTitulo(titulo);
                libro.setAnio(anio);
                libro.setEjemplares(ejemplares);
                libro.setEjemplaresPrestados(ejemplaresPrestados);
                libro.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);
                libro.setAutor(autorServicio.modificarAutorParaLibro(libro.getAutor().getId()));
                libro.setAlta(true);
                
                daoLibro.modificarLibro(libro);
            }
            
        } catch (Exception e) {
            System.out.println("Error al modificar el libro " + e.getMessage());
        }
    }
    
    public void eliminarLibro(long ISBN) {
        try {
            if (ISBN == 0) {
                throw new Exception("Debe indicar el ISBN");
            }
            daoLibro.eliminarLibroISBN(ISBN);
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e.getMessage());
        }
    }
    
    public Collection<Libro> listaLibros() throws Exception {
        
        try {
            Collection<Libro> libros = daoLibro.listarLibros();
            return libros;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirLibros() throws Exception {
        try {
            //Listamos los libros
            Collection<Libro> libros = listaLibros();

            //Imprimimos los libros - Solo algunos atributos....
            if (libros.isEmpty()) {
                throw new Exception("No existen libros para imprimir");
            } else {
                System.out.printf("%-10s%-25s%-25s%-25s\n", "ISBN", "TITULO", "AUTOR (id, nombre)", "EDITORIAL (id, nombre)");
                for (Libro l : libros) {
                    if (l.isAlta()) {
                        System.out.printf("%-10s%-25s%-25s%-25s\n", l.getISBN(),
                                l.getTitulo(), l.getAutor(), l.getEditorial());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void imprimirLibroPorISBN(long ISBN) {
        try {
            Libro libro = daoLibro.buscarLibroPorISBN(ISBN);
            
            System.out.println("ISBN: " + libro.getISBN() + ", Titulo: " + libro.getTitulo()
                    + ", Autor: (Id: " + libro.getAutor().getId() + ", Nombre: " + libro.getAutor().getNombre()
                    + "), Editorial: (Id: " + libro.getEditorial().getId() + ", Nombre: " + libro.getEditorial().getNombre() + ")");
            
        } catch (Exception e) {
            System.out.println("Error al imprimir libro por isbn " + e.getMessage());
        }
    }
    
    public void imprimirLibroPorTitulo(String titulo) {
        try {
            List<Libro> libros = daoLibro.buscarLibroTitulo(titulo);
            
            for (Libro libro : libros) {
                System.out.println("ISBN: " + libro.getISBN() + ", Titulo: " + libro.getTitulo()
                        + ", Autor: (Id: " + libro.getAutor().getId() + ", Nombre: " + libro.getAutor().getNombre()
                        + "), Editorial: (Id: " + libro.getEditorial().getId() + ", Nombre: " + libro.getEditorial().getNombre() + ")");
            }
        } catch (Exception e) {
            System.out.println("Error al imprimir libro por titulo " + e.getMessage());
        }
    }
    
    public void imprimirLibroPorNombreAutor(String nombre) {
        try {
            List<Libro> libros = daoLibro.buscarLibroPorAutor(nombre);
            
            for (Libro libro : libros) {
                System.out.println("ISBN: " + libro.getISBN() + ", Titulo: " + libro.getTitulo()
                        + ", Autor: (Id: " + libro.getAutor().getId() + ", Nombre: " + libro.getAutor().getNombre()
                        + "), Editorial: (Id: " + libro.getEditorial().getId() + ", Nombre: " + libro.getEditorial().getNombre() + ")");
            }
        } catch (Exception e) {
            System.out.println("Error al imprimir libro por autor " + e.getMessage());
        }
    }
    
}
