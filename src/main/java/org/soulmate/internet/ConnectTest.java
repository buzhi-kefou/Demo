package org.soulmate.internet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

public class ConnectTest {

    public static void main(String[] args) throws IOException {
        System.out.println(InetAddress.getByName("192.168.1.43").isReachable(2000));
    }
}
