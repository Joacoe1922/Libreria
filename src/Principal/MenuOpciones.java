package Principal;

import Principal.servicios.AutorServicio;
import Principal.servicios.EditorialServicio;
import Principal.servicios.LibroServicio;
import java.util.Scanner;

public class MenuOpciones {

    private Scanner leer = new Scanner(System.in).useDelimiter("\n");

    private LibroServicio libroServicio = new LibroServicio();
    private AutorServicio autorServicio = new AutorServicio();
    private EditorialServicio editorialServicio = new EditorialServicio();

    //MENUS
    public void menuOpciones() {
        try {
            salto();
            int op;
            do {
                System.out.println("-- MENU PRINCIPAL --");
                System.out.println("---------------------");
                System.out.println("SELECCIONE UNA OPCION");
                System.out.println("1. MENU LIBRO");
                System.out.println("2. MENU AUTOR");
                System.out.println("3. MENU EDITORIAL");
                System.out.println("0. SALIR");
                op = leer.nextInt();
                salto();

                switch (op) {
                    case 1:
                        menuLibros();
                        break;
                    case 2:
                        menuAutor();
                        break;
                    case 3:
                        menuEditorial();
                        break;
                    case 0:
                        break;
                }
                salto();
            } while (op != 0);
        } catch (Exception e) {
            System.out.println("Error de sistema. " + e.getMessage());
        }
    }

    public void menuLibros() {
        try {
            int op;
            do {
                System.out.println("-- MENU LIBRO --");
                System.out.println("---------------------");
                System.out.println("SELECCIONE UNA OPCION");
                System.out.println("1. CARGAR LIBRO");
                System.out.println("2. MODIFICAR LIBRO");
                System.out.println("3. DAR ALTA/BAJA LIBRO");
                System.out.println("4. ELIMINAR LIBRO POR ISBN");
                System.out.println("5. MOSTRAR LIBROS");
                System.out.println("6. BUSQUEDA DE LIBRO POR ISBN");
                System.out.println("7. BUSQUEDA DE LIBRO POR TITULO");
                System.out.println("8. BUSQUEDA DE LIBRO/S POR AUTOR");
                System.out.println("0. VOLVER");
                op = leer.nextInt();

                salto();

                switch (op) {
                    case 1:
                        libroServicio.crearLibro(cargarTitulo(), cargarAnio(), cargarEjemplares(),
                                cargarEjemplaresPrestados());
                        break;
                    case 2:
                        libroServicio.modificarLibro(cargarISBN());
                        break;
                    case 3:
                        libroServicio.darAltaBajaLibro(cargarISBN(), altaBaja());
                        break;
                    case 4:
                        libroServicio.eliminarLibro(cargarISBN());
                        break;
                    case 5:
                        libroServicio.imprimirLibros();
                        break;
                    case 6:
                        libroServicio.imprimirLibroPorISBN(cargarISBN());
                        break;
                    case 7:
                        libroServicio.imprimirLibroPorTitulo(cargarTitulo());
                        break;
                    case 8:
                        libroServicio.imprimirLibroPorNombreAutor(cargarNombreAutor());
                        break;
                    case 0:
                        break;
                }
                salto();
            } while (op != 0);
        } catch (Exception e) {
            System.out.println("Error de sistema. " + e.getMessage());
        }
    }

    public void menuAutor() {
        try {
            int op;
            do {
                System.out.println("-- MENU AUTOR --");
                System.out.println("---------------------");
                System.out.println("SELECCIONE UNA OPCION");
                System.out.println("1. CARGAR AUTOR");
                System.out.println("2. MODIFICAR AUTOR");
                System.out.println("3. ELIMINAR AUTOR");
                System.out.println("4. BUSCAR AUTOR POR NOMBRE");
                System.out.println("0. VOLVER");
                op = leer.nextInt();
                salto();

                switch (op) {
                    case 1:
                        autorServicio.crearAutor(cargarNombreAutor(), true);
                        break;
                    case 2:
                        autorServicio.modificarAutor(cargarId());
                        break;
                    case 3:
                        autorServicio.eliminarAutor(cargarId());
                        break;
                    case 4:
                        autorServicio.imprimirAutores(cargarNombreAutor());
                        break;
                    case 0:
                        break;
                }
                salto();
            } while (op != 0);
        } catch (Exception e) {
            System.out.println("Error de sistema. " + e.getMessage());
        }
    }

    public void menuEditorial() {
        try {
            salto();
            int op;
            do {
                System.out.println("-- MENU EDITORIAL --");
                System.out.println("---------------------");
                System.out.println("SELECCIONE UNA OPCION");
                System.out.println("1. CREAR EDITORIAL");
                System.out.println("0. VOLVER");
                op = leer.nextInt();
                salto();

                switch (op) {
                    case 1:
                        editorialServicio.crearEditorial(cargarNombreEditorial());
                        break;
                    case 0:
                        break;
                }
                salto();
            } while (op != 0);
        } catch (Exception e) {
            System.out.println("Error de sistema. " + e.getMessage());
        }
    }

    //METODOS
    public String cargarTitulo() {
        System.out.println("INGRESE EL TITULO");
        String titulo = leer.next();
        return titulo;
    }

    public int cargarAnio() {
        System.out.println("INGRESE EL AÑO");
        int anio = leer.nextInt();
        return anio;
    }

    public int cargarEjemplares() {
        System.out.println("INGRESE LOS EJEMPLARES");
        int ejemplares = leer.nextInt();
        return ejemplares;
    }

    public int cargarEjemplaresPrestados() {
        System.out.println("INGRESE LOS EJEMPLARES PRESTADOS");
        int ejemplaresPrestados = leer.nextInt();
        return ejemplaresPrestados;
    }

    public String cargarNombreAutor() {
        System.out.println("INGRESE EL NOMBRE DEL AUTOR");
        String nombre = leer.next();
        return nombre;
    }

    public String cargarNombreEditorial() {
        System.out.println("INGRESE EL NOMBRE DE LA EDITORIAL");
        String nombre = leer.next();
        return nombre;
    }

    public long cargarISBN() {
        System.out.println("INGRESE EL ISBN");
        long ISBN = leer.nextLong();
        return ISBN;
    }

    public int cargarId() {
        System.out.println("INGRESE EL ID DEL AUTOR A ELIMINAR");
        int id = leer.nextInt();
        return id;
    }

    public boolean altaBaja() {
        String op;
        do {
            System.out.println("INDIQUE SI DESEA DAR DE (A)LTA O (B)AJA");
            op = leer.next().toUpperCase();
        } while (!"A".equals(op) && !"B".equals(op));
        if ("A".equals(op)) {
            return true;
        } else {
            return false;
        }
    }

    public void salto() {
        System.out.println("");
    }
}
