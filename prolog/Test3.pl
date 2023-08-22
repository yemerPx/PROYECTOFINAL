
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


clasificar_depresion(PuntajeTotal) :-
    PuntajeTotal =< 25,
    write('Depresion leve\n'),
    recomendaciones('LEVE').

clasificar_depresion(PuntajeTotal) :-
    PuntajeTotal >= 26, PuntajeTotal =< 50,
    write('Depresiï¿½n moderada\n'),
    recomendaciones('MODERADA').

clasificar_depresion(PuntajeTotal) :-
    PuntajeTotal >= 51,
    write('Depresiï¿½n crï¿½tica\n'),
    recomendaciones('CRITICA').



% Reglas para las recomendaciones segï¿½n el nivel de depresiï¿½n
recomendaciones('LEVE') :-
    write('Recomendaciones'),
    write('1. Mantï¿½n una rutina diaria.\n'),
    write('2. Realiza actividad fïsica regularmente.\n'),
    write('3. Habla con amigos o familiares sobre tus sentimientos.\n').

recomendaciones('MODERADA') :-
    write('Recomendaciones'),
    write('1. Busca apoyo de un profesional de la salud mental.\n'),
    write('2. Considera la posibilidad de terapia cognitivo-conductual.\n'),
    write('3. Encuentra actividades que te brinden satisfacciï¿½n y alegrï¿½a.\n').

recomendaciones('CRITICA') :-
    write('Recomendaciones'),
    write('1. Busca ayuda profesional urgente y considera hospitalizaciïon si es necesario.\n'),
    write('2. No dudes en hablar con amigos o familiares sobre lo que esas pasando.\n'),
    write('3. Evita el aislamiento y la soledad, busca compaïa y apoyo.\n').

