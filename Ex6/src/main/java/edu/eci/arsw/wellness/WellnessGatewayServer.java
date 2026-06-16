package edu.eci.arsw.wellness;

import edu.eci.arsw.wellness.appointment.*;
import edu.eci.arsw.wellness.gateway.*;
import edu.eci.arsw.wellness.gym.*;
import edu.eci.arsw.wellness.recreation.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;

public class WellnessGatewayServer {

    public static void main(String[] args) throws Exception {
        Server gateway = ServerBuilder.forPort(50050)
                .addService(new WellnessGatewayImpl())
                .build();
        gateway.start();
        System.out.println("WellnessGateway running on port 50050");
        System.out.println("  -> AppointmentService @ 50051");
        System.out.println("  -> MedicalService     @ 50052");
        System.out.println("  -> GymService         @ 50053");
        System.out.println("  -> RecreationService  @ 50054");
        gateway.awaitTermination();
    }

    static class WellnessGatewayImpl extends WellnessGatewayGrpc.WellnessGatewayImplBase {

        private final AppointmentServiceGrpc.AppointmentServiceBlockingStub appointmentStub;
        private final GymServiceGrpc.GymServiceBlockingStub gymStub;
        private final RecreationServiceGrpc.RecreationServiceBlockingStub recreationStub;

        WellnessGatewayImpl() {
            ManagedChannel apptChannel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
            ManagedChannel gymChannel  = ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext().build();
            ManagedChannel recChannel  = ManagedChannelBuilder.forAddress("localhost", 50054).usePlaintext().build();

            appointmentStub  = AppointmentServiceGrpc.newBlockingStub(apptChannel);
            gymStub          = GymServiceGrpc.newBlockingStub(gymChannel);
            recreationStub   = RecreationServiceGrpc.newBlockingStub(recChannel);
        }

        @Override
        public void requestAppointment(AppointmentGatewayRequest req,
                                       StreamObserver<AppointmentGatewayResponse> obs) {
            AppointmentServiceType type;
            try {
                type = AppointmentServiceType.valueOf(req.getServiceType().toUpperCase());
            } catch (IllegalArgumentException e) {
                obs.onNext(AppointmentGatewayResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Invalid service type: " + req.getServiceType())
                        .build());
                obs.onCompleted();
                return;
            }

            AppointmentResponse internal = appointmentStub.requestAppointment(
                    AppointmentRequest.newBuilder()
                            .setStudentId(req.getStudentId())
                            .setServiceType(type)
                            .setDate(req.getDate())
                            .build());

            obs.onNext(AppointmentGatewayResponse.newBuilder()
                    .setSuccess(internal.getSuccess())
                    .setMessage(internal.getMessage())
                    .setAppointmentId(internal.getAppointmentId())
                    .build());
            obs.onCompleted();
        }

        @Override
        public void getStudentWellnessSummary(WellnessSummaryRequest req,
                                              StreamObserver<WellnessSummaryResponse> obs) {
            String studentId = req.getStudentId();

            // Active appointments
            AppointmentList apptList = appointmentStub.getAppointments(
                    StudentRequest.newBuilder().setStudentId(studentId).build());
            List<String> appointments = new ArrayList<>();
            for (AppointmentInfo a : apptList.getAppointmentsList()) {
                appointments.add(a.getId() + " | " + a.getServiceType() + " | " + a.getDate());
            }

            // Gym sessions
            SessionList sessionList = gymStub.getSessions(
                    GymStudentRequest.newBuilder().setStudentId(studentId).build());
            List<String> sessions = new ArrayList<>();
            for (GymSession s : sessionList.getSessionsList()) {
                sessions.add(s.getId() + " | " + s.getDay() + " " + s.getTimeSlot());
            }

            // Borrowed resources
            ResourceList resourceList = recreationStub.getResources(
                    RecEmptyRequest.newBuilder().build());
            List<String> borrowed = new ArrayList<>();
            for (Resource r : resourceList.getResourcesList()) {
                if (!r.getAvailable() && r.getBorrowedBy().equals(studentId)) {
                    borrowed.add(r.getId() + " | " + r.getName());
                }
            }

            obs.onNext(WellnessSummaryResponse.newBuilder()
                    .setStudentId(studentId)
                    .addAllActiveAppointments(appointments)
                    .addAllGymSessions(sessions)
                    .addAllBorrowedResources(borrowed)
                    .build());
            obs.onCompleted();
        }

        @Override
        public void reserveGymSession(GymGatewayRequest req,
                                      StreamObserver<GymGatewayResponse> obs) {
            GymBookingResponse internal = gymStub.bookSession(
                    GymBookingRequest.newBuilder()
                            .setStudentId(req.getStudentId())
                            .setDay(req.getDay())
                            .setTimeSlot(req.getTimeSlot())
                            .build());

            obs.onNext(GymGatewayResponse.newBuilder()
                    .setSuccess(internal.getSuccess())
                    .setMessage(internal.getMessage())
                    .build());
            obs.onCompleted();
        }

        @Override
        public void reserveRecreationResource(RecreationGatewayRequest req,
                                              StreamObserver<RecreationGatewayResponse> obs) {
            BorrowResponse internal = recreationStub.borrowResource(
                    BorrowRequest.newBuilder()
                            .setStudentId(req.getStudentId())
                            .setResourceId(req.getResourceId())
                            .build());

            obs.onNext(RecreationGatewayResponse.newBuilder()
                    .setSuccess(internal.getSuccess())
                    .setMessage(internal.getMessage())
                    .build());
            obs.onCompleted();
        }
    }
}
