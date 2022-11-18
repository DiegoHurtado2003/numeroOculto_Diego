public class numeroOculto extends Thread {

    /*
    Un hilo debe generar un número al azar entre cero y cien,
    que deben intentar adivinar otros diez hilos.
     Si un hilo acierta el número, debe terminar su ejecución
      inmediatamente. Y el resto de los hilos deben también
       terminar su ejecución en cuanto propongan un número
        y se les avise de que otro hilo ya ha acertado el número.

Se propone utilizar una clase NumeroOculto con un método:
 int propuestaNumero(int num) que devuelva los siguientes valores:

-1 si el juego ya ha terminado porque un hilo ha adivinado el número.
1 si el número propuesto (num) es el número oculto.
0 en otro caso.
No hace falta crear una clase para el hilo que genera el número al azar.
Es el hilo inicial, que ejecuta el método main, y que crea el resto de hilos.
     */


    public static int numeroOculto = (int) (Math.random() * 100 + 1);


    public static synchronized int propuestaNumero(int num) {
        int resultado;

        if (num == numeroOculto) {
            resultado = 1;
            //Acabas de adivinar el número
        } else if (num == 1) {
            resultado = -1;
            //El número ya ha sido adivinado
        } else {
            resultado = 0;
        }

        return resultado;
    }


    public void run() {
        int numeroPropuesto = (int) (Math.random() * 100 + 1);
        //Mientras el número no haya sido adivinado los hilos siguen funcionando
        while (propuestaNumero(numeroPropuesto) != -1) {
            System.out.println("Soy el bucle "+ getName() +" y estoy trabajando");

            if (propuestaNumero(numeroPropuesto) == 1) {
                Thread.currentThread().interrupt();
            }
            numeroPropuesto = (int) (Math.random() * 100 + 1);

        }
        System.out.println("El número oculto: "+ numeroOculto + " ha sido adivinado");
        Thread.currentThread().interrupt();
    }


}
