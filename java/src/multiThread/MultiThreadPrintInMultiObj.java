package multiThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadPrintInMultiObj implements Runnable
{
    public static void main(String[] args)
    {
        ExecutorService pool = Executors.newFixedThreadPool(threadNum);
        for(int i=0;i<threadNum;i++)
        {
            pool.submit(new MultiThreadPrintInMultiObj(i));
        }
    }
    private static int threadNum = 10;
    private int val;
    private static volatile Integer count = 0;
    private static volatile Object lock = new Object();
    public MultiThreadPrintInMultiObj(int val)
    {
        this.val = val;
    }
    
    @Override
    public void run()
    {
        for(int i=0;i<10;i++)
        {
            print();
        }
    }
    public void print()
    {
        synchronized(lock)
        {
            while(val!=count%threadNum)
            {
                try
                {
                    lock.wait();
                    
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
            lock.notifyAll(); 
        }
    }
}
