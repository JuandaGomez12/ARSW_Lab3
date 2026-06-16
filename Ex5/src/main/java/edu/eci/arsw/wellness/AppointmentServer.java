package edu.eci.arsw.wellness;

import edu.eci.arsw.wellness.appointment.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AppointmentServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(50051)
                .addService(new AppointmentServiceImpl())
                .build();
        server.start();
        System.out.println("AppointmentService running on port 50051");
        server.awaitTermination();
    }

    static class AppointmentServiceImpl extends AppointmentServiceGrpc.AppointmentServiceImplBase {

        private final Map<String, AppointmentInfo> appointments = new ConcurrentHashMap<>();
        private final AtomicInteger counter = new AtomicInteger(1);

        @Override
        public void requestAppointment(AppointmentRequest req,
                                       StreamObserver<AppointmentResponse> obs) {
            String id = "APT-" + counter.getAndIncrement();
            AppointmentInfo info = AppointmentInfo.newBuilder()
                    .setId(id)
                    .setStudentId(req.getStudentId())
                    .setServiceType(req.getServiceType())
                    .setDate(req.getDate())
                    .setStatus(AppointmentStatus.REQUESTED)
                    .build();
            appointments.put(id, info);
            obs.onNext(AppointmentResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Appointment created: " + id)
                    .setAppointmentId(id)
                    .build());
            obs.onCompleted();
        }

        @Override
        public void cancelAppointment(CancelRequest req, StreamObserver<CancelResponse> obs) {
            AppointmentInfo existing = appointments.get(req.getAppointmentId());
            if (existing == null) {
                obs.onNext(CancelResponse.newBuilder().setSuccess(false)
                        .setMessage("Not found: " + req.getAppointmentId()).build());
            } else if (existing.getStatus() == AppointmentStatus.CANCELLED) {
                obs.onNext(CancelResponse.newBuilder().setSuccess(false)
                        .setMessage("Already cancelled").build());
            } else {
                appointments.put(req.getAppointmentId(),
                        existing.toBuilder().setStatus(AppointmentStatus.CANCELLED).build());
                obs.onNext(CancelResponse.newBuilder().setSuccess(true)
                        .setMessage("Cancelled: " + req.getAppointmentId()).build());
            }
            obs.onCompleted();
        }

        @Override
        public void getAppointments(StudentRequest req, StreamObserver<AppointmentList> obs) {
            List<AppointmentInfo> result = new ArrayList<>();
            for (AppointmentInfo a : appointments.values()) {
                if (a.getStudentId().equals(req.getStudentId())
                        && a.getStatus() != AppointmentStatus.CANCELLED) {
                    result.add(a);
                }
            }
            obs.onNext(AppointmentList.newBuilder().addAllAppointments(result).build());
            obs.onCompleted();
        }
    }
}
