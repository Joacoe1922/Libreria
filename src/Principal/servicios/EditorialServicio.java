package Principal.servicios;

import Principal.MenuOpciones;
import Principal.entidades.Editorial;
import Principal.persistencia.EditorialDAO;
import java.util.Collection;

public class EditorialServicio {
    
    private final EditorialDAO daoEditorial = new EditorialDAO();
    
    public void crearEditorial(String nombre) {
        
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
            Editorial editorial = new Editorial();
            
            editorial.setNombre(nombre);
            editorial.setAlta(true);
            
            daoEditorial.guardarEditorial(editorial);
        } catch (Exception e) {
            System.out.println("No se creó el autor " + e.getMessage());
        }
    }
    
    public Editorial crearEditorialParaLibro(String nombre) {
        
        Editorial editorial = new Editorial();
        
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
            editorial.setNombre(nombre);
            editorial.setAlta(true);
            
            daoEditorial.guardarEditorial(editorial);
        } catch (Exception e) {
            System.out.println("No se creó el autor " + e.getMessage());
        }
        return editorial;
    }
    
    public Collection<Editorial> buscarEditorialNombre(String nombre) {
        Collection<Editorial> editoriales = null;
        try {
            
            editoriales = daoEditorial.buscarEditorialNombre(nombre);
            
        } catch (Exception e) {
            System.out.println("Error al buscar " + e.getMessage());
        }
        return editoriales;
    }
    
    public void eliminarEditorial(int id) {
        try {
            if (id == 0) {
                throw new Exception("Debe indicar el id");
            }
            
            daoEditorial.eliminarEditorialId(id);
        } catch (Exception e) {
            System.out.println("Error al eliminar " + e.getMessage());
        }
    }
    
    public void modificarEditorial(int id) {
        try {
            Editorial editorial = daoEditorial.buscarEditorialId(id);
            
            if (!editorial.getId().equals(id)) {
                throw new Exception("El id no existe");
            } else {
                MenuOpciones menuOpciones = new MenuOpciones();
                String nombre = menuOpciones.cargarNombreEditorial();
                
                editorial.setNombre(nombre);
                editorial.setAlta(true);
                
                daoEditorial.modificarEditorial(editorial);
            }
        } catch (Exception e) {
            System.out.println("Error al modificar la editorial " + e.getMessage());
        }
    }
    
    public Editorial modificarEditorialParaLibro(int id) {
        Editorial editorial = new Editorial();
        try {
            editorial = daoEditorial.buscarEditorialId(id);
            
            if (!editorial.getId().equals(id)) {
                throw new Exception("El id no existe");
            } else {
                MenuOpciones menuOpciones = new MenuOpciones();
                String nombre = menuOpciones.cargarNombreEditorial();
                
                editorial.setNombre(nombre);
                editorial.setAlta(true);
                
                daoEditorial.modificarEditorial(editorial);
            }
        } catch (Exception e) {
            System.out.println("Error al modificar la editorial " + e.getMessage());
        }
        return editorial;
    }
    
    public Collection<Editorial> listaEditoriales() throws Exception {
        try {
            Collection<Editorial> editoriales = daoEditorial.listarEditoriales();
            return editoriales;
        } catch (Exception e) {
            throw e;
        }
    }
    
    public void imprimirEditoriales() throws Exception {
        try {
            //Listamos las editoriales
            Collection<Editorial> editoriales = listaEditoriales();

            //Imprimimos las editoriales - Solo algunos atributos....
            if (editoriales.isEmpty()) {
                throw new Exception("No existen editoriales para imprimir");
            } else {
                System.out.printf("%-10s%-25s\n", "ID", "NOMBRE");
                for (Editorial e : editoriales) {
                    if (e.isAlta()) {
                        System.out.printf("%-10s%-25s\n", e.getId(), e.getNombre());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void darAltaBajaEditorial(int id, boolean altaBaja) {
        try {
            if (id == 0) {
                throw new Exception("Debe indicar el Id");
            }
            for (Editorial editorial : listaEditoriales()) {
                if (editorial.getId().equals(id)) {
                    editorial.setAlta(altaBaja);
                    daoEditorial.modificarEditorial(editorial);
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error al dar de baja " + e.getMessage());
        }
    }
}
