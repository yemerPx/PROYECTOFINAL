/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author YEMER
 */
class Recomendaciones {
     public static String obtenerRecomendaciones(int puntajeTotal) {
        String nivelDepresion;

        if (puntajeTotal <= 40) {
            nivelDepresion = "LEVE";
        } else if (puntajeTotal <= 80) {
            nivelDepresion = "MODERADA";
        } else {
            nivelDepresion = "CRITICA";
        }

        StringBuilder recomendaciones = new StringBuilder("Recomendaciones para la depresión " + nivelDepresion + ":\n");

        if (nivelDepresion.equals("LEVE")) {
            recomendaciones.append("1. Mantén una rutina diaria.\n");
            recomendaciones.append("2. Realiza actividad física regularmente.\n");
            recomendaciones.append("3. Habla con amigos o familiares sobre tus sentimientos.\n");
        } else if (nivelDepresion.equals("MODERADA")) {
            recomendaciones.append("1. Busca apoyo de un profesional de la salud mental.\n");
            recomendaciones.append("2. Considera la posibilidad de terapia cognitivo-conductual.\n");
            recomendaciones.append("3. Encuentra actividades que te brinden satisfacción y alegría.\n");
        } else if (nivelDepresion.equals("CRITICA")) {
            recomendaciones.append("1. Busca ayuda profesional urgente y considera hospitalización si es necesario.\n");
            recomendaciones.append("2. No dudes en hablar con amigos o familiares sobre lo que estás pasando.\n");
            recomendaciones.append("3. Evita el aislamiento y la soledad, busca compañía y apoyo.\n");
        }

        return recomendaciones.toString();
    }
}
