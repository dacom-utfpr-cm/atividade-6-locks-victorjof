/* 
Author: Victor Figueira
Date:  23/10/2019
Task: 3. Faça uma classe ArrayListThreadSafe usando ReadWriteLock.
Teste usando threads que realizam leitura e escrita para essa
estrutura.
*/
public class Exercicio_3 {
    private static int itr = 50;
    private static int num_threads = 3;

    public static void main(String[] args) {
        ArrayListThreadSafe list = new ArrayListThreadSafe();

        for(int i = 0; i <3;i++) {
            new Thread(() -> producer(list)).start();
            new Thread(() -> consumer(list)).start();
        }
    }

    private static void sleepMs(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void consumer(ArrayListThreadSafe list){
        int aux1,aux2;
        for (int i = 0; i <itr ; i++) {
            sleepMs(200);
            aux1 = list.remove_head();//'write' operation
            //toString() iterates the entire list, while other threads modify it, so it is highly prone to cause an error in a non-concurrent list
            System.out.printf("Removed head %d from list -> %s%n",aux1,list.toString());//read operation
            aux2 = list.get_head();//read operation
            System.out.printf("List head is now %d%n",aux2);
        }
    }

    public static void producer(ArrayListThreadSafe list){
        for (int i = 0; i < itr; i++) {
            sleepMs(100);
            list.add(i);//write operation
            System.out.printf("Added %d to list -> %s%n",i,list.toString());//read operation
        }
    }

}
