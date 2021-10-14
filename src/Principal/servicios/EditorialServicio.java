package Principal.servicios;

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
    
    
}
