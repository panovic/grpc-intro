package org.example.protobuf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import org.example.json.JPerson;
import org.example.models.Person;

public class PerformanceTest {
    public static void main(String[] args) {
        JPerson jPerson = new JPerson();
        jPerson.setName("Marko");
        jPerson.setAge(11);
        Runnable jsonRunnable =  () -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                byte[] bytes = mapper.writeValueAsBytes(jPerson);
                JPerson jPerson1 = mapper.readValue(bytes, JPerson.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };


        Person protoPerson = Person.newBuilder()
                .setName("Marko")
                .setAge(11)
                .build();
        Runnable protoBufRunnable = () -> {
            try {
                byte[] bytes = protoPerson.toByteArray();
                Person protoPerson1 = Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            runPerformanceTest(jsonRunnable, "json");
            runPerformanceTest(protoBufRunnable, "protoBuf");
        }
    }

    private static void runPerformanceTest(Runnable runnable, String testName) {
        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            runnable.run();
        }
        final long endTime = System.currentTimeMillis();
        System.out.println(String.format("%s: %d ms", testName, (endTime-startTime)));
    }
}
