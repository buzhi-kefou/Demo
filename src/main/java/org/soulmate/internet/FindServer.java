package org.soulmate.internet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class FindServer {

    static ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);

    static CountDownLatch latch = new CountDownLatch(508);

    static String url = "http://ip:port/mycim2/login.jsp";

    public static void main(String[] args) throws InterruptedException {
        long millis = System.currentTimeMillis();

        concatUrl();
        while (latch.getCount() != 0) {
            Thread.sleep(500);
        }
        threadPool.shutdownNow();
        System.out.println("finish judge");
        System.out.println("total time: " + (System.currentTimeMillis() - millis));
    }


    public static void concatUrl() throws InterruptedException {
        for (int i = 1; i < 255; i++)
            reachable("192.168.1." + i);
        System.out.println("finish concat");
    }

    public static void reachable(final String ip) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (InetAddress.getByName(ip).isReachable(2000)) {
                        submit(url.replace("ip", ip).replace("port", "8888"));
                        submit(url.replace("ip", ip).replace("port", "9999"));
                    } else {
                        latch.countDown();
                        latch.countDown();
                    }
                } catch (IOException e) {

                }
            }
        }).start();
    }

    public static void submit(final String url) {
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.connect();
                    if (connection.getResponseCode() == 200)
                        System.out.println(url);
                } catch (IOException e) {

                } finally {
                    latch.countDown();
                }
            }
        });
    }

}
