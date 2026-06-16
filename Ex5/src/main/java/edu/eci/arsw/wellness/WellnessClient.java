package edu.eci.arsw.wellness;

import edu.eci.arsw.wellness.appointment.*;
import edu.eci.arsw.wellness.gym.*;
import edu.eci.arsw.wellness.medical.*;
import edu.eci.arsw.wellness.recreation.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

public class WellnessClient {

    public static void main(String[] args) {
        ManagedChannel appointmentChannel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
        ManagedChannel medicalChannel    = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();
        ManagedChannel gymChannel        = ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext().build();
        ManagedChannel recreationChannel = ManagedChannelBuilder.forAddress("localhost", 50054).usePlaintext().build();

        AppointmentServiceGrpc.AppointmentServiceBlockingStub appointmentStub =
                AppointmentServiceGrpc.newBlockingStub(appointmentChannel);
        MedicalServiceGrpc.MedicalServiceBlockingStub medicalStub =
                MedicalServiceGrpc.newBlockingStub(medicalChannel);
        GymServiceGrpc.GymServiceBlockingStub gymStub =
                GymServiceGrpc.newBlockingStub(gymChannel);
        RecreationServiceGrpc.RecreationServiceBlockingStub recreationStub =
                RecreationServiceGrpc.newBlockingStub(recreationChannel);

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Wellness Client (Microservices) ===");
        System.out.println("Commands:");
        System.out.println("  appt request <studentId> <MEDICINE|PSYCHOLOGY|DENTISTRY> <date>");
        System.out.println("  appt cancel <appointmentId>");
        System.out.println("  appt list <studentId>");
        System.out.println("  med list");
        System.out.println("  med get <id>");
        System.out.println("  gym book <studentId> <day> <timeSlot>");
        System.out.println("  gym list <studentId>");
        System.out.println("  rec list");
        System.out.println("  rec borrow <resourceId> <studentId>");
        System.out.println("  rec return <resourceId> <studentId>");
        System.out.println("  exit");
        System.out.println();

        while (true) {
            System.out.print("Enter command: ");
            String line = scanner.nextLine().trim();
            if (line.equalsIgnoreCase("exit")) break;

            String[] p = line.split("\\s+");
            if (p.length < 2) { System.out.println("Unknown command."); continue; }

            try {
                switch (p[0].toLowerCase()) {
                    case "appt" -> handleAppointment(p, appointmentStub);
                    case "med"  -> handleMedical(p, medicalStub);
                    case "gym"  -> handleGym(p, gymStub);
                    case "rec"  -> handleRecreation(p, recreationStub);
                    default     -> System.out.println("Unknown service. Use: appt, med, gym, rec");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }

        appointmentChannel.shutdown();
        medicalChannel.shutdown();
        gymChannel.shutdown();
        recreationChannel.shutdown();
        scanner.close();
    }

    private static void handleAppointment(String[] p,
            AppointmentServiceGrpc.AppointmentServiceBlockingStub stub) {
        switch (p[1].toLowerCase()) {
            case "request" -> {
                if (p.length < 5) { System.out.println("Usage: appt request <studentId> <serviceType> <date>"); return; }
                AppointmentServiceType type = AppointmentServiceType.valueOf(p[3].toUpperCase());
                AppointmentResponse r = stub.requestAppointment(AppointmentRequest.newBuilder()
                        .setStudentId(p[2]).setServiceType(type).setDate(p[4]).build());
                System.out.println(r.getMessage());
            }
            case "cancel" -> {
                if (p.length < 3) { System.out.println("Usage: appt cancel <id>"); return; }
                CancelResponse r = stub.cancelAppointment(CancelRequest.newBuilder().setAppointmentId(p[2]).build());
                System.out.println(r.getMessage());
            }
            case "list" -> {
                if (p.length < 3) { System.out.println("Usage: appt list <studentId>"); return; }
                AppointmentList list = stub.getAppointments(StudentRequest.newBuilder().setStudentId(p[2]).build());
                if (list.getAppointmentsCount() == 0) { System.out.println("No active appointments."); return; }
                for (AppointmentInfo a : list.getAppointmentsList()) {
                    System.out.println("  " + a.getId() + " | " + a.getServiceType() + " | " + a.getDate() + " | " + a.getStatus());
                }
            }
            default -> System.out.println("Unknown appt command.");
        }
    }

    private static void handleMedical(String[] p,
            MedicalServiceGrpc.MedicalServiceBlockingStub stub) {
        switch (p[1].toLowerCase()) {
            case "list" -> {
                SpecialtyList list = stub.getSpecialties(EmptyRequest.newBuilder().build());
                for (Specialty s : list.getSpecialtiesList()) {
                    System.out.println("  [" + s.getId() + "] " + s.getName()
                            + " - " + s.getDescription()
                            + " (slots: " + s.getAvailableSlots() + ")");
                }
            }
            case "get" -> {
                if (p.length < 3) { System.out.println("Usage: med get <id>"); return; }
                Specialty s = stub.getSpecialty(SpecialtyRequest.newBuilder().setSpecialtyId(p[2]).build());
                System.out.println("  [" + s.getId() + "] " + s.getName() + " - " + s.getDescription());
            }
            default -> System.out.println("Unknown med command.");
        }
    }

    private static void handleGym(String[] p,
            GymServiceGrpc.GymServiceBlockingStub stub) {
        switch (p[1].toLowerCase()) {
            case "book" -> {
                if (p.length < 5) { System.out.println("Usage: gym book <studentId> <day> <timeSlot>"); return; }
                GymBookingResponse r = stub.bookSession(GymBookingRequest.newBuilder()
                        .setStudentId(p[2]).setDay(p[3]).setTimeSlot(p[4]).build());
                System.out.println(r.getMessage());
            }
            case "list" -> {
                if (p.length < 3) { System.out.println("Usage: gym list <studentId>"); return; }
                SessionList list = stub.getSessions(GymStudentRequest.newBuilder().setStudentId(p[2]).build());
                if (list.getSessionsCount() == 0) { System.out.println("No gym sessions."); return; }
                for (GymSession s : list.getSessionsList()) {
                    System.out.println("  " + s.getId() + " | " + s.getDay() + " " + s.getTimeSlot());
                }
            }
            default -> System.out.println("Unknown gym command.");
        }
    }

    private static void handleRecreation(String[] p,
            RecreationServiceGrpc.RecreationServiceBlockingStub stub) {
        switch (p[1].toLowerCase()) {
            case "list" -> {
                ResourceList list = stub.getResources(RecEmptyRequest.newBuilder().build());
                for (Resource r : list.getResourcesList()) {
                    String status = r.getAvailable() ? "AVAILABLE" : "BORROWED by " + r.getBorrowedBy();
                    System.out.println("  [" + r.getId() + "] " + r.getName() + " (" + r.getType() + ") - " + status);
                }
            }
            case "borrow" -> {
                if (p.length < 4) { System.out.println("Usage: rec borrow <resourceId> <studentId>"); return; }
                BorrowResponse r = stub.borrowResource(BorrowRequest.newBuilder()
                        .setResourceId(p[2]).setStudentId(p[3]).build());
                System.out.println(r.getMessage());
            }
            case "return" -> {
                if (p.length < 4) { System.out.println("Usage: rec return <resourceId> <studentId>"); return; }
                BorrowResponse r = stub.returnResource(BorrowRequest.newBuilder()
                        .setResourceId(p[2]).setStudentId(p[3]).build());
                System.out.println(r.getMessage());
            }
            default -> System.out.println("Unknown rec command.");
        }
    }
}
