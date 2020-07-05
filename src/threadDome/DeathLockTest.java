package threadDome;

/**
 * @author pengzhihui
 */
public class DeathLockTest {
        private static Object A = new Object(), B = new Object();

        public static void main(String[] args)
        {
            //等待彼此释放资源的两个线程，就很容易造成死锁
            new Thread(() -> {
                System.out.println("线程1开始执行...");
                synchronized (A)
                {
                    try
                    {
                        System.out.println("线程1拿到A锁");
                        //休眠两秒让线程2有时间拿到B锁
                        Thread.sleep(2000);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    synchronized (B)
                    {
                        System.out.println("线程1拿到B锁");
                    }
                }
            }).start();

            new Thread(() -> {
                System.out.println("线程2开始执行...");
                synchronized (B)
                {
                    try
                    {
                        System.out.println("线程2拿到B锁");
                        //休眠两秒让线程1有时间拿到A锁
                        Thread.sleep(2000);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    synchronized (A)
                    {
                        System.out.println("线程2拿到A锁");
                    }
                }
            }).start();

        }
}
