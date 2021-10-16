package Principal.servicios;

import Principal.MenuOpciones;
import Principal.entidades.Autor;
import Principal.persistencia.AutorDAO;
import java.util.Collection;

public class AutorServicio {

    private final AutorDAO daoAutor = new AutorDAO();

    public void crearAutor(String nombre, boolean alta) {

        try {
            //Validamos
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
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

            Autor autor = new Autor();

            //Creamos el usuario
            autor.setNombre(nombre);
            autor.setAlta(alta);

            daoAutor.guardarAutor(autor);

        } catch (Exception e) {
            System.out.println("No se creó el autor " + e.getMessage());
        }
    }

    public Autor crearAutorParaLibro(String nombre, boolean alta) {

        Autor autor = new Autor();
        try {
            //Validamos
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre");
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
            autor.setNombre(nombre);
            autor.setAlta(alta);

            daoAutor.guardarAutor(autor);

        } catch (Exception e) {
            System.out.println("No se creó el autor " + e.getMessage());
        }
        return autor;
    }

    public Collection<Autor> listaAutores() throws Exception {
        try {
            Collection<Autor> autores = daoAutor.listarAutores();
            return autores;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirAutores() throws Exception {
        try {
            //Listamos los autores
            Collection<Autor> autores = listaAutores();

            //Imprimimos los autores - Solo algunos atributos....
            if (autores.isEmpty()) {
                throw new Exception("No existen autores para imprimir");
            } else {
                System.out.printf("%-10s%-25s\n", "ID", "NOMBRE");
                for (Autor a : autores) {
                    if (a.isAlta()) {
                        System.out.printf("%-10s%-25s\n", a.getId(), a.getNombre());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarAutor(int id) {
        try {
            Autor autor = daoAutor.buscarAutorId(id);

            if (!autor.getId().equals(id)) {
                throw new Exception("El id no existe");
            } else {
                MenuOpciones menuOpciones = new MenuOpciones();
                String nombre = menuOpciones.cargarNombreAutor();

                autor.setNombre(nombre);
                autor.setAlta(true);

                daoAutor.modificarAutor(autor);
            }
        } catch (Exception e) {
            System.out.println("Error al modificar el autor " + e.getMessage());
        }
    }

    public Autor modificarAutorParaLibro(int id) {
        Autor autor = new Autor();
        try {
            autor = daoAutor.buscarAutorId(id);

            if (!autor.getId().equals(id)) {
                throw new Exception("El id no existe");
            } else {
                MenuOpciones menuOpciones = new MenuOpciones();
                String nombre = menuOpciones.cargarNombreAutor();

                autor.setNombre(nombre);
                autor.setAlta(true);

                daoAutor.modificarAutor(autor);
            }
        } catch (Exception e) {
            System.out.println("Error al modificar el autor " + e.getMessage());
        }
        return autor;
    }

    public void eliminarAutor(int id) {
        try {
            if (id == 0) {
                throw new Exception("Debe indicar el id");
            }

            daoAutor.eliminarAutorId(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e.getMessage());
        }
    }

    public Collection<Autor> buscarAutorNombre(String nombre) {
        Collection<Autor> autores = null;
        try {

            autores = daoAutor.buscarAutorNombre(nombre);

        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return autores;
    }

    public void imprimirAutores(String nombre) {
        try {
            Collection<Autor> autores = daoAutor.buscarAutorNombre(nombre);

            if (autores.isEmpty()) {
                System.out.println("No existe el autor ingresado");
            } else {
                for (Autor autor : autores) {
                    System.out.println("ID: " + autor.getId() + ", Nombre: " + autor.getNombre());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al imprimir " + e.getMessage());
        }
    }
    
    public void darAltaBajaAutor(int id, boolean altaBaja) {
        try {
            if (id == 0) {
                throw new Exception("Debe indicar el Id");
            }
            for (Autor autor : listaAutores()) {
                if (autor.getId().equals(id)) {
                    autor.setAlta(altaBaja);
                    daoAutor.modificarAutor(autor);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error al dar de baja " + e.getMessage());
        }
    }
}
