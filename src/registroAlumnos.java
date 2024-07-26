import java.util.ArrayList;
import java.util.Scanner;

class alumnos {
    String nombre;
    double notas;

    public alumnos(String nombre, double notas) {
        this.nombre = nombre;
        this.notas = notas;
    }

    public String getNombre() {
        return nombre;
    }

    public double getNotas() {
        return notas;
    }
    public void setNotas(double notas) {
        this.notas = notas;
    }
}

public class registroAlumnos {

    private static ArrayList<alumnos> alumno = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opciones;

        do {
            mostrarMenu();
            opciones = obtenerOpciones();

            switch (opciones) {
                case 1:
                registrarAlumnos();
                break;
                case 2:
                mostrarAlumnos();
                break;
                case 3:
                mostraPromedioNotas();
                break;
                case 4:
                buscarAlumnos();
                break;
                case 5:
                modificarNotas();
                break;
                case 6:
                eliminarAlumnos();
                break;
                case 7:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
                    break;
            }
        } while (opciones!= 7);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENU DE OPCIONES ---");
        System.out.println("1. Registrar alumno.");
        System.out.println("2. Mostrar todos los alumnos.");
        System.out.println("3. Mostrar promedio de notas.");
        System.out.println("4. Buscar alumno por nombre.");
        System.out.println("5. Modificar nota por nombre.");
        System.out.println("6. Eliminar alumno por nombre.");
        System.out.println("7. Salir.");
        System.out.print("Selecciones una opcion: ");
    }

    private static int obtenerOpciones() {
        int opciones;
        while (true) {
            String entrada = scanner.nextLine();
            if (esunmumerovalido(entrada)) {
                opciones = Integer.parseInt(entrada);
                break;
            } else {
                System.out.println("Entrada no válida. Ingrese un numero: ");
            }
        }
        return opciones;
    }
    private static boolean esunmumerovalido(String entrada) {
        try {
            Integer.parseInt(entrada);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }

    private static void registrarAlumnos() {
        System.out.println("Ingrese el nombre del alumno: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio.");
            return;
        }

        System.out.println("Ingrese la nota del alumno (0.00 - 10.00): ");
        String inputNota = scanner.nextLine();
        if (esnotavalida(inputNota)) {
            double nota = Double.parseDouble(inputNota);
            alumno.add(new alumnos(nombre, nota));
            System.out.println("Alumno registrado exitosamente.");
        } else {
            System.out.println("Nota no valida. Debe estar entre 0.00 y 10.00.");
        }
    }
    private static boolean esnotavalida(String input) {
        try {
            double nota = Double.parseDouble(input);
            return nota >= 0 && nota <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void mostrarAlumnos() {
        if (alumno.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            System.out.println("\n--- LISTA DE ALUMNOS ---");
            for (alumnos alumno : alumno) {
                System.out.println("Nombre: " +alumno.getNombre() + ", Nota: " +alumno.getNotas());
            }
        }
    }
    private static void mostraPromedioNotas() {
        if (alumno.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            double suma = 0;
            for (alumnos alumno : alumno) {
                suma += alumno.getNotas();
            }
            double promedio = suma / alumno.size();
            System.out.println("El promedio de las notas es: " +promedio);
        }
    }
    private static void buscarAlumnos() {
        System.out.println("Ingrese el nombre del alumno a buscar: ");
        String nombre = scanner.nextLine().trim();

        for (alumnos alumno : alumno) {
            if (alumno.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Nombre: " +alumno.getNombre() + ", nota: " +alumno.getNotas());
                return;
            }
        }
        System.out.println("Alumno no encontrado.");
    }

    private static void modificarNotas() {
        System.out.println("Ingrese el nombre del alumno cuya nota desa modifcar: ");
        String nombre = scanner.nextLine().trim();

        for (alumnos alumno : alumno) {
            if (alumno.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Ingrese la nueva nota (0.00 - 10.00): ");
                String inputNota = scanner.nextLine();
                if (esnotavalida(inputNota)) {
                    double nuevanota = Double.parseDouble(inputNota);
                    alumno.setNotas(nuevanota);
                    System.out.println("Nota modificada con exito.");
                } else {
                    System.out.println("Nota no valida. Debe estar entre 0.00 y 10.00.");
                }
                return;
            }
        }
        System.out.println("Alumno no encontrado.");
    }
private static void eliminarAlumnos() {
    System.out.println("Ingrese el nombre del alumno que desea eliminar: ");
    String nombre = scanner.nextLine().trim();

    for (alumnos alumnos: alumno) {
        if (alumnos.getNombre().equalsIgnoreCase(nombre)) {
            alumno.remove(alumnos);
            System.out.println("Alumno eliminado con exito.");
            return;
        }
    }
    System.out.println("Alumno no encontrado.");
}
}