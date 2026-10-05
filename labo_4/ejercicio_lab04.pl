% Ejercicio 3 - Separar los dígitos de un número

almacenar(Numero, Lista) :-
    separar_digitos(Numero, ListaInvertida),
    invertir_lista(ListaInvertida, Lista).

separar_digitos(0, []) :- !.

separar_digitos(Numero, [Digito|Resto]) :-
    Digito is Numero mod 10,
    NuevoNumero is Numero // 10,
    separar_digitos(NuevoNumero, Resto).

invertir_lista(Lista, ListaInvertida) :-
    invertir_aux(Lista, [], ListaInvertida).

invertir_aux([], Acumulador, Acumulador).

invertir_aux([Cabeza|Cola], Acumulador, Resultado) :-
    invertir_aux(Cola, [Cabeza|Acumulador], Resultado).
