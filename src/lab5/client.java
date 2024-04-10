package lab5;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public class client {
    public static void main(String[] args) {
        String host = "vk.ru";
        int timeout = 1000;
        int count = 5;

        try {
            InetAddress address = InetAddress.getByName(host);
            ArrayList<Long> rttList = new ArrayList<>();
            int lostCount = 0;

            System.out.println("PING " + host + ":");

            for (int i = 0; i < count; i++) {
                long startTime = System.currentTimeMillis();

                try {
                    if (address.isReachable(timeout)) {
                        long rtt = System.currentTimeMillis() - startTime;
                        rttList.add(rtt);
                        System.out.println("Reply from " + address + ": time=" + rtt + "ms");
                    } else {
                        System.out.println("Request timed out");
                        lostCount++;
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (!rttList.isEmpty()) {
                long minRTT = rttList.stream().min(Long::compareTo).orElse(0L);
                long maxRTT = rttList.stream().max(Long::compareTo).orElse(0L);
                long sumRTT = rttList.stream().mapToLong(Long::longValue).sum();
                double avgRTT = (double) sumRTT / rttList.size();
                double lossPercentage = (double) lostCount / count * 100;

                System.out.println("\n--- " + host + " ping statistics ---");
                System.out.println(count + " packets transmitted, " + (count - lostCount) + " received, " +
                        lossPercentage + "% packet loss");
                System.out.println("rtt min/avg/max = " + minRTT + "/" + avgRTT + "/" + maxRTT + " ms");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
