% Ejercicio 2 - Suma de numeros hasta llegar a 1

sumaHastaUno(1, 1).
sumaHastaUno(X, Y) :- X > 1, N is X - 1, sumaHastaUno(N, R), Y is X + R.
