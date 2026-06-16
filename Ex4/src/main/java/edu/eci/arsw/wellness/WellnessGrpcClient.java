package edu.eci.arsw.wellness;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class WellnessGrpcClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        AppointmentServiceGrpc.AppointmentServiceBlockingStub stub =
                AppointmentServiceGrpc.newBlockingStub(channel);

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Wellness Appointment Client (gRPC) ===");
        System.out.println("Commands:");
        System.out.println("  request <studentId> <MEDICINE|PSYCHOLOGY|DENTISTRY> <date>");
        System.out.println("  cancel <appointmentId>");
        System.out.println("  list <studentId>");
        System.out.println("  exit");
        System.out.println();

        while (true) {
            System.out.print("Enter command: ");
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = line.split("\\s+");

            if (parts[0].equalsIgnoreCase("request") && parts.length == 4) {
                ServiceType serviceType;
                try {
                    serviceType = ServiceType.valueOf(parts[2].toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid service type. Use: MEDICINE, PSYCHOLOGY, DENTISTRY");
                    continue;
                }

                AppointmentRequest request = AppointmentRequest.newBuilder()
                        .setStudentId(parts[1])
                        .setServiceType(serviceType)
                        .setDate(parts[3])
                        .build();

                AppointmentResponse response = stub.requestAppointment(request);
                System.out.println(response.getMessage());
                if (response.getSuccess()) {
                    Appointment a = response.getAppointment();
                    System.out.println("  ID: " + a.getId()
                            + " | Student: " + a.getStudentId()
                            + " | Service: " + a.getServiceType()
                            + " | Date: " + a.getDate()
                            + " | Status: " + a.getStatus());
                }

            } else if (parts[0].equalsIgnoreCase("cancel") && parts.length == 2) {
                CancelRequest request = CancelRequest.newBuilder()
                        .setAppointmentId(parts[1])
                        .build();

                CancelResponse response = stub.cancelAppointment(request);
                System.out.println(response.getMessage());

            } else if (parts[0].equalsIgnoreCase("list") && parts.length == 2) {
                StudentRequest request = StudentRequest.newBuilder()
                        .setStudentId(parts[1])
                        .build();

                AppointmentList list = stub.getAppointments(request);
                if (list.getAppointmentsCount() == 0) {
                    System.out.println("No active appointments for student: " + parts[1]);
                } else {
                    for (Appointment a : list.getAppointmentsList()) {
                        System.out.println("  " + a.getId()
                                + " | " + a.getServiceType()
                                + " | " + a.getDate()
                                + " | " + a.getStatus());
                    }
                }

            } else {
                System.out.println("Unknown command or wrong number of arguments.");
            }

            System.out.println();
        }

        channel.shutdown();
        scanner.close();
    }
}
