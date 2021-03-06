/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 *
 * Luis Pedro Cuéllar - 18220
 * 25/03/2019
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        VectorHeap<Paciente> patientsQueue = new VectorHeap<>();
        VectorHeap<Paciente> patientsList = new VectorHeap<>();

        String mainMenu = "     --- EMERGENCIA --- \n" +
                "\t 1. Ingresar pacientes a la sala de emergencia\n" +
                "\t 2. Atención de pacientes \n" +
                "\t 3. Salir del hospital\n" +
                "\t Ingrese el número de la opción que desea realizar: ";

        String patientMenu = "     --- ATENCIÓN DE PACIENTES --- \n" +
                "\t 1. Atender a los pacientes\n" +
                "\t 2. Ver lista de pacientes\n" +
                "\t 3. Regresar al menu de Emergencia\n" +
                "\t Ingrese el número de la opción que desea realizar: ";

        int option = 0;

        while (option != 3) {

            System.out.println(mainMenu);
            option = input.nextInt();

            switch (option) {

                // Opcion para ingresar pacientes a la lista
                case 1:
                    // Leer el archivo .txt
                    System.out.print("Ingrese el archivo de texto que desea leer: ");
                    String userFile = input.nextLine();
                    userFile = input.nextLine();

                    try {
                        Stream<String> lines = Files.lines(
                                Paths.get(userFile),
                                StandardCharsets.UTF_8
                        );

                        lines.forEach(line -> {
                            String[] information = line.toUpperCase().split(",");

                            if (information.length == 3) {
                                String nombre = information[0];
                                String sintoma = information[1];
                                String prioridad = information[2];

                                Paciente newPaciente = new Paciente(nombre, sintoma, prioridad);
                                patientsQueue.add(newPaciente);
                                patientsList.add(newPaciente);
                            }
                        });

                        System.out.println(" Los pacientes se han agregado con éxito" );
                    }

                    catch (Exception e) {
                        System.out.println(" No se han encontrado pacientes! ");
                    }

                    break;

                // Opcion para revisar la lista de pacientes
                case 2:

                    int choice = 0;

                    while (choice != 3) {

                        System.out.println(patientMenu);
                        choice = input.nextInt();

                        switch (choice) {

                            // Opción para atender un paciente
                            case 1:
                                // Atender al paciente de mayor priorida, al atender el paciente desplegar mensaje diciendo:
                                // "El paciente " + name + " se ha tratado su " + sympton + " de prioridad " + priority + " se ha tratado con éxito"
                                // después de atenderlo, sacarlo de la queue del hospital
                                boolean wantstoTreat = true;

                                while(wantstoTreat) {

                                    if (!patientsQueue.isEmpty()) {
                                        Paciente treated = patientsQueue.remove();
                                        System.out.println("El paciente " + treated.getName() + " ha sido tratado de " + treated.getSymptoms() + " de prioridad " + treated.getPriority() + " exitosamente");
                                    } else {
                                        System.out.println(" La lista de pacientes se encuentra vacía!");
                                    }

                                    wantstoTreat = false;
                                }

                            break;

                            // Opción para ver la lista de pacientes en espera
                            case 2:
                                // Imprimir la queue con el formato Pacientes.toString(), en orden de su prioridad
                                if (!patientsList.isEmpty()) {
                                    while (!patientsList.isEmpty()){
                                        Paciente patient = patientsList.remove();
                                        System.out.println(patient.toString());
                                    }
                                } else {
                                    System.out.println(" La lista de pacientes se encuentra vacía!");
                                }

                                break;

                            case 3:
                                break;

                            //Por si no se encuentra la opción ingresada
                            default:
                                System.out.println(" *** Ingrese una opción válida! *** ");
                                break;
                        }
                    }

                    break;

                case 3:
                    break;

                // Por si no se encuentra la opción ingresada
                default:
                    System.out.println(" *** Ingrese una opción válida! *** ");
                    break;
            }
        }

        System.out.println(" Gracias por usar el programa <3 ");
    }
}
