package com.assignment;

import java.io.BufferedReader;
  
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;



public class RewardsDemo {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = RewardsDemo.class.getResourceAsStream("Input");
        String data = readFromInputStream(inputStream);

        Map<String, Map<String, Integer>> points = new HashMap<String, Map<String, Integer>>();

        for (String line : data.split("\n")) {
            String[] customer = line.split("\\s+");

            points.putIfAbsent(customer[0], new HashMap<String, Integer>());

            Map<String, Integer> monthlyMap = points.get(customer[0]);

            monthlyMap.put(customer[1], monthlyMap.getOrDefault(customer[1], 0) + calculatePoints(
                    Integer.valueOf(customer[2])
            ));
        }

        for (String customer : points.keySet()) {
            int total = 0;
            for(String month : points.get(customer).keySet()) {
                total += points.get(customer).get(month);
                System.out.println("Customer " + customer + " points for "
                        + month + " are " + points.get(customer).get(month));
            }
            System.out.println("Customer " + customer + " points for" +
                    " all months are " + total);
        }
    }

    private static int calculatePoints(int amount) {
        if (amount > 100) return (amount - 100) * 2 + 50;
        if (amount > 50) return amount - 50;
        return 0;
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = br.readLine()) != null) {
            resultStringBuilder.append(line).append("\n");
        }

        return resultStringBuilder.toString();
    }
}
