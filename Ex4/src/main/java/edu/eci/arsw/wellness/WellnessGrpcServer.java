package edu.eci.arsw.wellness;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class WellnessGrpcServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(50051)
                .addService(new AppointmentServiceImpl())
                .build();

        server.start();
        System.out.println("WellnessGrpcServer started on port 50051");
        server.awaitTermination();
    }

    static class AppointmentServiceImpl extends AppointmentServiceGrpc.AppointmentServiceImplBase {

        private final Map<String, Appointment> appointments = new ConcurrentHashMap<>();
        private final AtomicInteger idCounter = new AtomicInteger(1);

        @Override
        public void requestAppointment(AppointmentRequest request,
                                       StreamObserver<AppointmentResponse> responseObserver) {
            if (request.getStudentId().isBlank() || request.getDate().isBlank()) {
                AppointmentResponse response = AppointmentResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Student ID and date are required")
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
                return;
            }

            String id = "APT-" + idCounter.getAndIncrement();
            Appointment appointment = Appointment.newBuilder()
                    .setId(id)
                    .setStudentId(request.getStudentId())
                    .setServiceType(request.getServiceType())
                    .setDate(request.getDate())
                    .setStatus(AppointmentStatus.REQUESTED)
                    .build();

            appointments.put(id, appointment);

            AppointmentResponse response = AppointmentResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Appointment created with ID: " + id)
                    .setAppointment(appointment)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void cancelAppointment(CancelRequest request,
                                      StreamObserver<CancelResponse> responseObserver) {
            String id = request.getAppointmentId();
            Appointment existing = appointments.get(id);

            CancelResponse response;
            if (existing == null) {
                response = CancelResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Appointment not found: " + id)
                        .build();
            } else if (existing.getStatus() == AppointmentStatus.CANCELLED) {
                response = CancelResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Appointment " + id + " is already cancelled")
                        .build();
            } else {
                appointments.put(id, existing.toBuilder()
                        .setStatus(AppointmentStatus.CANCELLED)
                        .build());
                response = CancelResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage("Appointment " + id + " cancelled successfully")
                        .build();
            }

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void getAppointments(StudentRequest request,
                                    StreamObserver<AppointmentList> responseObserver) {
            String studentId = request.getStudentId();
            List<Appointment> result = new ArrayList<>();

            for (Appointment a : appointments.values()) {
                if (a.getStudentId().equals(studentId)
                        && a.getStatus() != AppointmentStatus.CANCELLED) {
                    result.add(a);
                }
            }

            AppointmentList list = AppointmentList.newBuilder()
                    .addAllAppointments(result)
                    .build();

            responseObserver.onNext(list);
            responseObserver.onCompleted();
        }
    }
}
