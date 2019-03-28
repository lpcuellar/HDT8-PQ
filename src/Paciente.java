/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 *
 * Luis Pedro Cuéllar - 18220
 * 25/03/2019
 */

public class Paciente implements Comparable<Paciente> {

    private String name;
    private String symptoms;
    private String priority;

    public Paciente(String nombre, String sintomas, String prioridad) {
        this.name = nombre;
        this.symptoms = sintomas;
        this.priority = prioridad;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Paciente o) {
        if (this.priority.compareTo(o.priority) < 0) {
            return -1;
        }

        if (this.priority.compareTo(o.priority) > 0) {
            return 1;
        }

        return 0;
    }

    public String toString() {
        return "Nombre: " + name + "\n" +
                "\t Síntomas: " + symptoms + "\n" +
                "\t Prioridad; " + priority + "\n";
    }
}

