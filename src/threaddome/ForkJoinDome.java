package threaddome;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 计算1+n 通过分治法
 * @author pengzhihui
 */
public class ForkJoinDome {
    public static void main(String[] args) {
        Instant forkInstant = Instant.now();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        SumTack sumTack =new SumTack(0L,50000000000L);
        Future<Long> sum= pool.submit(sumTack);
        try {
            System.out.println("计算结果是："+sum.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        //通过并行流的方式计算出结果 lamba
        Instant instant = Instant.now();
        System.out.println("forkJoin执行时间"+ Duration.between(forkInstant, instant).toMillis() + "ms");
        Long result = LongStream.rangeClosed(0L,50000000000L).parallel().reduce(0,Long::sum);
        Instant endInstant = Instant.now();
        System.out.println("并行流的执行时间"+ Duration.between(instant, endInstant).toMillis() + "ms");
        System.out.println("并行流的执行结果:"+ result);

    }
}
    /**
     * RecursiveTack 表示有返回结果的任务
     * RecursiveAction  表示没返回结果的任务
     *
     * */
    class SumTack extends RecursiveTask<Long> {
        private long start,end;
        /**
         * 定义序列化 避免重复
         * */
        private static final long serialVersionUID = -7524245439872879478L;
        /**
         * 定义线程数目
         * */
        private static final long THREAD_SIZE = 10000L;

        /**
         * 构造函数
         * */
        SumTack(long start, long end){
            this.start = start;
            this.end = end;
        }
        @Override
        protected Long compute() {
            long sum =0;
            boolean isMid = (end-start)>THREAD_SIZE;
            if(isMid){
                long mid =(end+start)/2;
                SumTack sumTack1= new SumTack(start,mid);
                SumTack sumTack2= new SumTack(mid+1,end);
                //执行子任务
                sumTack1.fork();
                sumTack2.fork();
                //获取结果
                long sum1 =sumTack1.join();
                long sum2 =sumTack2.join();
                sum =sum1+sum2;
            }
            else{
                for(long i =start;i<=end;i++){
                    sum+=i;
                }
            }
            return sum;
        }
}
