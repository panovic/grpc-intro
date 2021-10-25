package org.example.protobuf;

import org.example.models.Person;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProtobufDemo {
    public static void main(String[] args) throws IOException {
        Person marko = Person.newBuilder()
                .setName("Marko")
                .setAge(11)
                .build();
        Person marko2 = Person.newBuilder()
                .setName("Marko")
                .setAge(11)
                .build();
        System.out.println(marko);
        System.out.println(marko.equals(marko2)); // true

        final Path path = Paths.get("marko.ser");
        Files.write(path, marko.toByteArray());
    }
}
