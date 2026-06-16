package edu.eci.arsw.wellness;

import edu.eci.arsw.wellness.medical.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Map;

public class MedicalServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(50052)
                .addService(new MedicalServiceImpl())
                .build();
        server.start();
        System.out.println("MedicalService running on port 50052");
        server.awaitTermination();
    }

    static class MedicalServiceImpl extends MedicalServiceGrpc.MedicalServiceImplBase {

        private final Map<String, Specialty> specialties = Map.of(
                "MED", Specialty.newBuilder().setId("MED").setName("General Medicine")
                        .setDescription("Primary care and general consultations").setAvailableSlots(10).build(),
                "PSY", Specialty.newBuilder().setId("PSY").setName("Psychology")
                        .setDescription("Mental health and counseling").setAvailableSlots(6).build(),
                "DEN", Specialty.newBuilder().setId("DEN").setName("Dentistry")
                        .setDescription("Oral health and dental care").setAvailableSlots(4).build()
        );

        @Override
        public void getSpecialties(EmptyRequest req, StreamObserver<SpecialtyList> obs) {
            obs.onNext(SpecialtyList.newBuilder().addAllSpecialties(specialties.values()).build());
            obs.onCompleted();
        }

        @Override
        public void getSpecialty(SpecialtyRequest req, StreamObserver<Specialty> obs) {
            Specialty s = specialties.get(req.getSpecialtyId());
            if (s != null) {
                obs.onNext(s);
            } else {
                obs.onNext(Specialty.newBuilder().setId("").setName("Not found").build());
            }
            obs.onCompleted();
        }
    }
}
