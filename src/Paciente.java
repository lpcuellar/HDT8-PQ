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

    /**
     * @param nombre The name of the patient, type String
     * @param sintoma The symptom of the patient, type String
     * @param prioridad The priority of the patient, type String
     */
    public Paciente(String nombre, String sintoma, String prioridad) {
        this.name = nombre;
        this.symptoms = sintoma;
        this.priority = prioridad;
    }


    /**
     * @param o, The other patient that will be compared to, to compare their priority, type Paciente
     * @return -1 if the priority of patient is lesser than the patient o,
     *         0 if the priorities are the same,
     *         1 if the priority of patient is greater than the patient o
     */
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

    /**
     * @return The attributes of the patient in a format that looks kinda nice
     */
    public String toString() {
        return "    Nombre: " + name + "\n" +
                "\t Síntomas: " + symptoms + "\n" +
                "\t Prioridad; " + priority + "\n" +
                "\t ";
    }

    /**
     * @return The name of the patient
     */
    public String getName() {
        return name;
    }

    /**
     * @param name of the patient, type String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The symptoms of the patient
     */
    public String getSymptoms() {
        return symptoms;
    }

    /**
     * @param symptoms of the patient, type String
     */
    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    /**
     * @return the priority of the Patient
     */
    public String getPriority() {
        return priority;
    }

    /**
     * @param priority of the patient, type String
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }
}

