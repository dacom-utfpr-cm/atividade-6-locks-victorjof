/* 
Author: Victor Figueira
Date:  23/10/2019
Task: 2. Crie uma classe SharedFifoQueue e use Conditions para
controlar se a fila está vazia ou cheia. Teste usando threads
produtoras e consumidoras.
*/

public class Exercicio_2 {

    private static int itr = 5;

    public static void main(String[] args) throws InterruptedException {
        SharedFifoQueue queue = new SharedFifoQueue(5);
        new Thread(() -> producer(queue)).start();
        new Thread(() -> consumer(queue)).start();
        new Thread(() -> consumer(queue)).start();
        new Thread(() -> producer(queue)).start();


    }

    private static void sleepMs(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void producer(SharedFifoQueue queue){

        for(int i = 0; i<itr; i++){
            sleepMs(100);
            try {
                queue.add(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("added %d to queue -> %s%n",i,queue.toString());
        }

    }

    public static void consumer(SharedFifoQueue queue){
        int value_removed=-1;
        for(int i = 0; i<itr; i++){
            sleepMs(300);
            try {
                value_removed = queue.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("removed %d from queue -> %s%n",value_removed,queue.toString());
        }

    }
}
