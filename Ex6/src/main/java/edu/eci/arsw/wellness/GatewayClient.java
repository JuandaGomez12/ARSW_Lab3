package edu.eci.arsw.wellness;

import edu.eci.arsw.wellness.gateway.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class GatewayClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50050)
                .usePlaintext()
                .build();

        WellnessGatewayGrpc.WellnessGatewayBlockingStub stub =
                WellnessGatewayGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Wellness Gateway Client ===");
        System.out.println("Single entry point — port 50050 only.");
        System.out.println("Commands:");
        System.out.println("  request <studentId> <MEDICINE|PSYCHOLOGY|DENTISTRY> <date>");
        System.out.println("  summary <studentId>");
        System.out.println("  gym <studentId> <day> <timeSlot>");
        System.out.println("  borrow <studentId> <resourceId>");
        System.out.println("  exit");
        System.out.println();

        while (true) {
            System.out.print("Enter command: ");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) break;

            String[] p = line.split("\\s+");
            try {
                switch (p[0].toLowerCase()) {

                    case "request" -> {
                        if (p.length < 4) { System.out.println("Usage: request <studentId> <serviceType> <date>"); break; }
                        AppointmentGatewayResponse r = stub.requestAppointment(
                                AppointmentGatewayRequest.newBuilder()
                                        .setStudentId(p[1]).setServiceType(p[2]).setDate(p[3]).build());
                        System.out.println(r.getMessage());
                        if (r.getSuccess()) System.out.println("Appointment ID: " + r.getAppointmentId());
                    }

                    case "summary" -> {
                        if (p.length < 2) { System.out.println("Usage: summary <studentId>"); break; }
                        WellnessSummaryResponse r = stub.getStudentWellnessSummary(
                                WellnessSummaryRequest.newBuilder().setStudentId(p[1]).build());
                        System.out.println("Wellness Summary for: " + r.getStudentId());
                        System.out.println("  Appointments:");
                        r.getActiveAppointmentsList().forEach(a -> System.out.println("    - " + a));
                        System.out.println("  Gym Sessions:");
                        r.getGymSessionsList().forEach(s -> System.out.println("    - " + s));
                        System.out.println("  Borrowed Resources:");
                        r.getBorrowedResourcesList().forEach(b -> System.out.println("    - " + b));
                    }

                    case "gym" -> {
                        if (p.length < 4) { System.out.println("Usage: gym <studentId> <day> <timeSlot>"); break; }
                        GymGatewayResponse r = stub.reserveGymSession(
                                GymGatewayRequest.newBuilder()
                                        .setStudentId(p[1]).setDay(p[2]).setTimeSlot(p[3]).build());
                        System.out.println(r.getMessage());
                    }

                    case "borrow" -> {
                        if (p.length < 3) { System.out.println("Usage: borrow <studentId> <resourceId>"); break; }
                        RecreationGatewayResponse r = stub.reserveRecreationResource(
                                RecreationGatewayRequest.newBuilder()
                                        .setStudentId(p[1]).setResourceId(p[2]).build());
                        System.out.println(r.getMessage());
                    }

                    default -> System.out.println("Unknown command.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }

        channel.shutdown();
        scanner.close();
    }
}
