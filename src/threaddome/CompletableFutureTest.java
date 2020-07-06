package threaddome;

import java.util.concurrent.CompletableFuture;

/**
 * @author pengzhihui
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFuture <String> completableFuture1 = CompletableFuture.supplyAsync(()-> queryCode("1","1"));
        CompletableFuture <String> completableFuture2 = CompletableFuture.supplyAsync(()-> queryCode("2","2"));
        // 用anyOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> result1 = CompletableFuture.anyOf(completableFuture1,completableFuture2);
        result1.thenAccept(System.out::println);
        CompletableFuture <Double> completableFutureDouble1= result1.thenApplyAsync((code) -> fetchPrice((String) code,"1"));
        CompletableFuture <Double> completableFutureDouble2= result1.thenApplyAsync((code) -> fetchPrice((String) code,"2"));
        // 用allOf合并为一个新的CompletableFuture:
        CompletableFuture<Object> result2 = CompletableFuture.anyOf(completableFutureDouble1,completableFutureDouble2 );
        //输出结果
        result2.thenAccept((r)-> System.out.println("price"+r));
        CompletableFuture<Void> result3 = CompletableFuture.allOf(completableFutureDouble1,completableFutureDouble2 );
        result3.join();
        result3.thenAccept((r)->System.out.println("price3"+r));
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result1.exceptionally(e-> null);
    }

    static String queryCode(String name, String url) {
        System.out.println("query code from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        if(name=="1"){
            return "601857";
        }
        else{
            return "2233";
        }
    }

    static Double fetchPrice(String code, String url) {
        System.out.println("query price from " + url + "...");
        try {
            Thread.sleep((long) (Math.random() * 100));
        } catch (InterruptedException e) {
        }
        return 5 + Math.random() * 20;
    }
}
