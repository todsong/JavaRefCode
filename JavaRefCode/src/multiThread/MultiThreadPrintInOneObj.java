package multiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadPrintInOneObj implements Runnable
{

    public static void main(String[] args)
    {
        MultiThreadPrintInOneObj t = new MultiThreadPrintInOneObj(10);
    }
    private ExecutorService pool;
    private static int threadNum;
    private static volatile Integer count=0;
    public MultiThreadPrintInOneObj(int val)
    {
        threadNum = val;
        this.pool = Executors.newFixedThreadPool(threadNum);
        for(int i=0;i<threadNum;i++)
        {
            pool.submit(this);
        }
    }
    
    @Override
    public void run()
    {
        for(int i=0;i<10;i++)
        {
            print();
        }
       // System.out.println("end");
    }
    public synchronized void print()
    {
        int val = Integer.parseInt(Thread.currentThread().getName().split("-")[3])-1;
        while(val!=count%threadNum)
        {
            try
            {
                //System.out.println("wait");
                wait();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        count++;
        
        System.out.print(val);
        if(count%threadNum==0)
        {
            System.out.print("---"+count/threadNum);
        }
        System.out.println();        
        notifyAll();
    }
}
