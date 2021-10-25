package org.example.protobuf;

import org.example.models.*;

public class CompositionDemo {
    public static void main(String[] args) {
        Car acura = Car.newBuilder()
                .setMake("Acura")
                .setModel("RDX")
                .setYear(2013)
                .setBodyStyle(BodyStyle.SUV)
                .build();

        Car mazda = Car.newBuilder()
                .setMake("Mazda")
                .setModel("3")
                .setYear(2016)
                .build();
        System.out.println(mazda);
        System.out.println("Mazda body style: " + mazda.getBodyStyle()); // UNKNOWN (default enum with value 0)

        Address home = Address.newBuilder()
                .setStreet("1234 Abc St")
                .setCity("Vancouver")
                .setPostalCode("A1B 2C3")
                .build();

        Person luka = Person.newBuilder()
                .setName("Luka")
                .setAge(16)
                .setAddress(home)
                .addCar(mazda)
                .build();
        System.out.println(luka);

        Dealer dealer = Dealer.newBuilder()
                .putYearModel(2013, acura)
                .putYearModel(2016, mazda)
                .build();
        System.out.println(dealer);
        System.out.println("Body style for 2016 model year: " + dealer.getYearModelOrThrow(2016).getBodyStyle()); // UNKNOWN (default (0) enum value)

        Person person = Person.newBuilder().build();
        // builds an empty person object with fields set to default values (uint=0, string="", enum=firstValue, bool=false, repeated/map=empty)
        System.out.println("Empty person address: " + person.getAddress());
        System.out.println("Empty person has address: " + person.hasAddress());
        System.out.println("Empty person has nickname: " + person.hasNickname()); // use google.protobuf.wrappers (like StringValue instead of string to get hasField() capability)
    }

}
