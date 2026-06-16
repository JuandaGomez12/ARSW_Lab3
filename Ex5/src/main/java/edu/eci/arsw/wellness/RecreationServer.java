package edu.eci.arsw.wellness;

import edu.eci.arsw.wellness.recreation.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RecreationServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(50054)
                .addService(new RecreationServiceImpl())
                .build();
        server.start();
        System.out.println("RecreationService running on port 50054");
        server.awaitTermination();
    }

    static class RecreationServiceImpl extends RecreationServiceGrpc.RecreationServiceImplBase {

        private final Map<String, Resource> resources = new ConcurrentHashMap<>();

        public RecreationServiceImpl() {
            resources.put("BB01", Resource.newBuilder().setId("BB01").setName("Basketball").setType("SPORT").setAvailable(true).build());
            resources.put("TT01", Resource.newBuilder().setId("TT01").setName("Table Tennis").setType("SPORT").setAvailable(true).build());
            resources.put("BD01", Resource.newBuilder().setId("BD01").setName("Badminton Set").setType("SPORT").setAvailable(true).build());
            resources.put("BK01", Resource.newBuilder().setId("BK01").setName("Board Game Kit").setType("LEISURE").setAvailable(true).build());
        }

        @Override
        public void getResources(RecEmptyRequest req, StreamObserver<ResourceList> obs) {
            obs.onNext(ResourceList.newBuilder().addAllResources(resources.values()).build());
            obs.onCompleted();
        }

        @Override
        public synchronized void borrowResource(BorrowRequest req, StreamObserver<BorrowResponse> obs) {
            Resource r = resources.get(req.getResourceId());
            if (r == null) {
                obs.onNext(BorrowResponse.newBuilder().setSuccess(false).setMessage("Resource not found").build());
            } else if (!r.getAvailable()) {
                obs.onNext(BorrowResponse.newBuilder().setSuccess(false)
                        .setMessage(req.getResourceId() + " is already borrowed by " + r.getBorrowedBy()).build());
            } else {
                resources.put(req.getResourceId(), r.toBuilder()
                        .setAvailable(false).setBorrowedBy(req.getStudentId()).build());
                obs.onNext(BorrowResponse.newBuilder().setSuccess(true)
                        .setMessage(req.getResourceId() + " lent to " + req.getStudentId()).build());
            }
            obs.onCompleted();
        }

        @Override
        public synchronized void returnResource(BorrowRequest req, StreamObserver<BorrowResponse> obs) {
            Resource r = resources.get(req.getResourceId());
            if (r == null) {
                obs.onNext(BorrowResponse.newBuilder().setSuccess(false).setMessage("Resource not found").build());
            } else if (r.getAvailable()) {
                obs.onNext(BorrowResponse.newBuilder().setSuccess(false).setMessage("Resource is not borrowed").build());
            } else {
                resources.put(req.getResourceId(), r.toBuilder()
                        .setAvailable(true).setBorrowedBy("").build());
                obs.onNext(BorrowResponse.newBuilder().setSuccess(true)
                        .setMessage(req.getResourceId() + " returned").build());
            }
            obs.onCompleted();
        }
    }
}
