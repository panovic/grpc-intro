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
        System.out.println(marko.equals(marko2)); // true (equals compares each field)

        // serialize a person object and write it to a file
        final Path path = Paths.get("marko.ser");
        Files.write(path, marko.toByteArray());

        // read a person from a file and deserialize
        byte[] bytes = Files.readAllBytes(path);
        Person newMarko = Person.parseFrom(bytes);
        System.out.println("\nDeserialized:\n" + newMarko);
    }
}
