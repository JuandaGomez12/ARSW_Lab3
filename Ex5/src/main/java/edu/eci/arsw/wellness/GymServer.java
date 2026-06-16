package edu.eci.arsw.wellness;

import edu.eci.arsw.wellness.gym.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class GymServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(50053)
                .addService(new GymServiceImpl())
                .build();
        server.start();
        System.out.println("GymService running on port 50053");
        server.awaitTermination();
    }

    static class GymServiceImpl extends GymServiceGrpc.GymServiceImplBase {

        private final Map<String, GymSession> sessions = new ConcurrentHashMap<>();
        private final AtomicInteger counter = new AtomicInteger(1);

        @Override
        public void bookSession(GymBookingRequest req, StreamObserver<GymBookingResponse> obs) {
            String id = "GYM-" + counter.getAndIncrement();
            sessions.put(id, GymSession.newBuilder()
                    .setId(id)
                    .setStudentId(req.getStudentId())
                    .setDay(req.getDay())
                    .setTimeSlot(req.getTimeSlot())
                    .build());
            obs.onNext(GymBookingResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Session booked: " + id)
                    .setSessionId(id)
                    .build());
            obs.onCompleted();
        }

        @Override
        public void getSessions(GymStudentRequest req, StreamObserver<SessionList> obs) {
            List<GymSession> result = new ArrayList<>();
            for (GymSession s : sessions.values()) {
                if (s.getStudentId().equals(req.getStudentId())) result.add(s);
            }
            obs.onNext(SessionList.newBuilder().addAllSessions(result).build());
            obs.onCompleted();
        }
    }
}
