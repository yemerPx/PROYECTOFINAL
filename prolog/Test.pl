% Hechos
sintoma_fisico(dolor_de_cabeza).
sintoma_fisico(dolor_de_espalda).
sintoma_fisico(fatiga).
sintoma_fisico(problemas_gastrointestinales).
sintoma_fisico(insomnio).
sintoma_fisico(palpitaciones).
sintoma_fisico(sudoracion_excesiva).
sintoma_fisico(tension_muscular).
sintoma_fisico(cambios_en_el_apetito).
sintoma_fisico(mareos).

sintoma_social(aislamiento_social).
sintoma_social(dificultad_para_comunicarse).
sintoma_social(disminucion_del_interes_social).
sintoma_social(conflictos_interpersonales).
sintoma_social(reduccion_rendimiento).
sintoma_social(cambios_interaccion_social).
sintoma_social(falta_de_interes_en_eventos).

sintoma_emocional(ansiedad).
sintoma_emocional(irritabilidad).
sintoma_emocional(cambios_de_mood).
sintoma_emocional(tristeza).
sintoma_emocional(sensacion_de_abrumacion).
sintoma_emocional(falta_de_concentracion).
sintoma_emocional(baja_autoestima).
sintoma_emocional(sensacion_de_desesperanza).
sintoma_emocional(preocupacion).
sintoma_emocional(sentimientos_de_culpa).


% Hechos para la asignación de puntos a síntomas

presenta_sintoma(_, dolor_de_cabeza, 1).
presenta_sintoma(_, dolor_de_cabeza, 2).
presenta_sintoma(_, dolor_de_cabeza, 3).
presenta_sintoma(_, dolor_de_espalda, 1).
presenta_sintoma(_, dolor_de_espalda, 2).
presenta_sintoma(_, dolor_de_espalda, 3).
presenta_sintoma(_, fatiga, 1).
presenta_sintoma(_, fatiga, 2).
presenta_sintoma(_, fatiga, 3).
presenta_sintoma(_, problemas_gastrointestinales, 1).
presenta_sintoma(_, problemas_gastrointestinales, 2).
presenta_sintoma(_, problemas_gastrointestinales, 3).
presenta_sintoma(_, insomnio, 1).
presenta_sintoma(_, insomnio, 2).
presenta_sintoma(_, insomnio, 3).
presenta_sintoma(_, palpitaciones, 1).
presenta_sintoma(_, palpitaciones, 2).
presenta_sintoma(_, palpitaciones, 3).
presenta_sintoma(_, sudoracion_excesiva, 1).
presenta_sintoma(_, sudoracion_excesiva, 2).
presenta_sintoma(_, sudoracion_excesiva, 3).
presenta_sintoma(_, tension_muscular, 1).
presenta_sintoma(_, tension_muscular, 2).
presenta_sintoma(_, tension_muscular, 3).
presenta_sintoma(_, cambios_en_el_apetito, 1).
presenta_sintoma(_, cambios_en_el_apetito, 2).
presenta_sintoma(_, cambios_en_el_apetito, 3).
presenta_sintoma(_, mareos, 1).
presenta_sintoma(_, mareos, 2).
presenta_sintoma(_, mareos, 3).
presenta_sintoma(_, aislamiento_social, 1).
presenta_sintoma(_, aislamiento_social, 2).
presenta_sintoma(_, aislamiento_social, 3).
presenta_sintoma(_, ansiedad, 1).  % cuando el síntoma es bajo
presenta_sintoma(_, ansiedad, 2).   % cuando el síntoma es medio
presenta_sintoma(_, ansiedad, 3).   % cuando el síntoma es alto


% Regla para determinar el nivel de síntoma y su correspondencia con números
nivel_sintoma(nada, 0).
nivel_sintoma(bajo, 1).
nivel_sintoma(medio, 2).
nivel_sintoma(alto, 3).


calcular_sintoma(Persona, Sintoma, Nivel) :-
    presenta_sintoma(Persona, Sintoma, Puntuacion),
    nivel_sintoma(Nivel, Puntuacion),
    write(' Síntoma '), write(Sintoma),
    write(' con nivel '), write(Nivel),
    write(' y puntuación '), write(Puntuacion), nl.


