public class dz4 {
    volatile int status=0;
    public static void main(String[] args){
        dz4 lock=new dz4();
        Thread th1=new Thread(new Task("A",lock,1));
        Thread th2=new Thread(new Task("B",lock,2));
        Thread th3=new Thread(new Task("C",lock,3));
        th1.start();
        th2.start();
        th3.start();
    }

}
class Task implements Runnable{
    private String message;
    private final dz4 lock;
    private int k;

    Task(String txt,dz4 obj,int k){
        message=txt;
        this.lock=obj;
        this.k=k;
    }
    @Override
    public void run(){
        while (lock.status<13){
            synchronized (lock){
                while(!((lock.status %3)==0)&&k==1){
                    try{
                        lock.wait();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                while(!((lock.status %3)==1)&&k==2){
                    try{
                        lock.wait();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                while(!((lock.status %3)==2)&&k==3){
                    try{
                        lock.wait();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println(message);
                lock.status++;
                if((lock.status %3)==0)System.out.println("...");
                lock.notifyAll();
            }
        }
    }
}
