package threadDome;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 计算1+n 通过分治法
 * @author pengzhihui
 */
public class ForkJoinDome {
    public static void main(String[] args) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        SumTack sumTack =new SumTack(1,100);
        Future<Integer> sum= pool.submit(sumTack);
        try {
            System.out.println("计算结果是："+sum.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        Runnable r = () ->{
          while (true){
              try {
                  Thread.currentThread().isInterrupted();
                  Thread.sleep(1000);

              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              finally {

              }
          }
        };
    }
}
    /**
     * RecursiveTack 表示有返回结果的任务
     * RecursiveAction  表示没返回结果的任务
     *
     * */
    class SumTack extends RecursiveTask<Integer> {
        private int start,end;
        /**
         * 定义序列化 避免重复
         * */
        private static final long serialVersionUID = -7524245439872879478L;
        /**
         * 定义线程数目
         * */
        private static final int THREAD_SIZE = 10;

        /**
         * 构造函数
         * */
        SumTack(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        protected Integer compute() {
            int sum =0;
            boolean isMid = (end-start)>THREAD_SIZE;
            if(isMid){
                int mid =(end+start)/2;
                SumTack sumTack1= new SumTack(start,mid);
                SumTack sumTack2= new SumTack(mid+1,end);
                //执行子任务
                sumTack1.fork();
                sumTack2.fork();
                //获取结果
                int sum1 =sumTack1.join();
                int sum2 =sumTack2.join();
                sum =sum1+sum2;
            }
            else{
                for(int i =start;i<=end;i++){
                    sum+=i;
                }
            }
            return sum;
        }
}
