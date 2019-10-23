/* 
Author: Victor Figueira
Date:  23/10/2019
Task: 1. Faça um programa usando Lock para simular a atualização de
um contador que é acessado por múltiplas threads. O
contador pode diminuir e aumentar.
*/
public class Exercicio_1 {
    private static int itr = 10;


    public static void main(String[] args) {
        Counter counter = new Counter();

        new Thread(() -> increase_counter_value(counter)).start();
        new Thread(() -> decrease_counter_value(counter)).start();

    }

    private static void sleepMs(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void increase_counter_value(Counter counter){
        for(int i = 0; i < itr; i++){
            sleepMs(10);
            counter.increment();
        }
    }

    private static void decrease_counter_value(Counter counter){
        for(int i = 0; i < itr; i++){
            sleepMs(5);
            counter.decrement();
        }
    }

}
