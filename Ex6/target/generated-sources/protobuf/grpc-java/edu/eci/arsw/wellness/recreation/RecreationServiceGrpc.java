package edu.eci.arsw.wellness.recreation;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: recreation.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class RecreationServiceGrpc {

  private RecreationServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "RecreationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<edu.eci.arsw.wellness.recreation.RecEmptyRequest,
      edu.eci.arsw.wellness.recreation.ResourceList> getGetResourcesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetResources",
      requestType = edu.eci.arsw.wellness.recreation.RecEmptyRequest.class,
      responseType = edu.eci.arsw.wellness.recreation.ResourceList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.eci.arsw.wellness.recreation.RecEmptyRequest,
      edu.eci.arsw.wellness.recreation.ResourceList> getGetResourcesMethod() {
    io.grpc.MethodDescriptor<edu.eci.arsw.wellness.recreation.RecEmptyRequest, edu.eci.arsw.wellness.recreation.ResourceList> getGetResourcesMethod;
    if ((getGetResourcesMethod = RecreationServiceGrpc.getGetResourcesMethod) == null) {
      synchronized (RecreationServiceGrpc.class) {
        if ((getGetResourcesMethod = RecreationServiceGrpc.getGetResourcesMethod) == null) {
          RecreationServiceGrpc.getGetResourcesMethod = getGetResourcesMethod =
              io.grpc.MethodDescriptor.<edu.eci.arsw.wellness.recreation.RecEmptyRequest, edu.eci.arsw.wellness.recreation.ResourceList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetResources"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.recreation.RecEmptyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.recreation.ResourceList.getDefaultInstance()))
              .setSchemaDescriptor(new RecreationServiceMethodDescriptorSupplier("GetResources"))
              .build();
        }
      }
    }
    return getGetResourcesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.eci.arsw.wellness.recreation.BorrowRequest,
      edu.eci.arsw.wellness.recreation.BorrowResponse> getBorrowResourceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BorrowResource",
      requestType = edu.eci.arsw.wellness.recreation.BorrowRequest.class,
      responseType = edu.eci.arsw.wellness.recreation.BorrowResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.eci.arsw.wellness.recreation.BorrowRequest,
      edu.eci.arsw.wellness.recreation.BorrowResponse> getBorrowResourceMethod() {
    io.grpc.MethodDescriptor<edu.eci.arsw.wellness.recreation.BorrowRequest, edu.eci.arsw.wellness.recreation.BorrowResponse> getBorrowResourceMethod;
    if ((getBorrowResourceMethod = RecreationServiceGrpc.getBorrowResourceMethod) == null) {
      synchronized (RecreationServiceGrpc.class) {
        if ((getBorrowResourceMethod = RecreationServiceGrpc.getBorrowResourceMethod) == null) {
          RecreationServiceGrpc.getBorrowResourceMethod = getBorrowResourceMethod =
              io.grpc.MethodDescriptor.<edu.eci.arsw.wellness.recreation.BorrowRequest, edu.eci.arsw.wellness.recreation.BorrowResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "BorrowResource"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.recreation.BorrowRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.recreation.BorrowResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RecreationServiceMethodDescriptorSupplier("BorrowResource"))
              .build();
        }
      }
    }
    return getBorrowResourceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.eci.arsw.wellness.recreation.BorrowRequest,
      edu.eci.arsw.wellness.recreation.BorrowResponse> getReturnResourceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReturnResource",
      requestType = edu.eci.arsw.wellness.recreation.BorrowRequest.class,
      responseType = edu.eci.arsw.wellness.recreation.BorrowResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.eci.arsw.wellness.recreation.BorrowRequest,
      edu.eci.arsw.wellness.recreation.BorrowResponse> getReturnResourceMethod() {
    io.grpc.MethodDescriptor<edu.eci.arsw.wellness.recreation.BorrowRequest, edu.eci.arsw.wellness.recreation.BorrowResponse> getReturnResourceMethod;
    if ((getReturnResourceMethod = RecreationServiceGrpc.getReturnResourceMethod) == null) {
      synchronized (RecreationServiceGrpc.class) {
        if ((getReturnResourceMethod = RecreationServiceGrpc.getReturnResourceMethod) == null) {
          RecreationServiceGrpc.getReturnResourceMethod = getReturnResourceMethod =
              io.grpc.MethodDescriptor.<edu.eci.arsw.wellness.recreation.BorrowRequest, edu.eci.arsw.wellness.recreation.BorrowResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReturnResource"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.recreation.BorrowRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.recreation.BorrowResponse.getDefaultInstance()))
              .setSchemaDescriptor(new RecreationServiceMethodDescriptorSupplier("ReturnResource"))
              .build();
        }
      }
    }
    return getReturnResourceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RecreationServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RecreationServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RecreationServiceStub>() {
        @java.lang.Override
        public RecreationServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RecreationServiceStub(channel, callOptions);
        }
      };
    return RecreationServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RecreationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RecreationServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RecreationServiceBlockingStub>() {
        @java.lang.Override
        public RecreationServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RecreationServiceBlockingStub(channel, callOptions);
        }
      };
    return RecreationServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RecreationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<RecreationServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<RecreationServiceFutureStub>() {
        @java.lang.Override
        public RecreationServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new RecreationServiceFutureStub(channel, callOptions);
        }
      };
    return RecreationServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getResources(edu.eci.arsw.wellness.recreation.RecEmptyRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.recreation.ResourceList> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetResourcesMethod(), responseObserver);
    }

    /**
     */
    default void borrowResource(edu.eci.arsw.wellness.recreation.BorrowRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.recreation.BorrowResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getBorrowResourceMethod(), responseObserver);
    }

    /**
     */
    default void returnResource(edu.eci.arsw.wellness.recreation.BorrowRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.recreation.BorrowResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReturnResourceMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service RecreationService.
   */
  public static abstract class RecreationServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return RecreationServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service RecreationService.
   */
  public static final class RecreationServiceStub
      extends io.grpc.stub.AbstractAsyncStub<RecreationServiceStub> {
    private RecreationServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RecreationServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RecreationServiceStub(channel, callOptions);
    }

    /**
     */
    public void getResources(edu.eci.arsw.wellness.recreation.RecEmptyRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.recreation.ResourceList> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetResourcesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void borrowResource(edu.eci.arsw.wellness.recreation.BorrowRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.recreation.BorrowResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getBorrowResourceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void returnResource(edu.eci.arsw.wellness.recreation.BorrowRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.recreation.BorrowResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReturnResourceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service RecreationService.
   */
  public static final class RecreationServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<RecreationServiceBlockingStub> {
    private RecreationServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RecreationServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RecreationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public edu.eci.arsw.wellness.recreation.ResourceList getResources(edu.eci.arsw.wellness.recreation.RecEmptyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetResourcesMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.eci.arsw.wellness.recreation.BorrowResponse borrowResource(edu.eci.arsw.wellness.recreation.BorrowRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getBorrowResourceMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.eci.arsw.wellness.recreation.BorrowResponse returnResource(edu.eci.arsw.wellness.recreation.BorrowRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReturnResourceMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service RecreationService.
   */
  public static final class RecreationServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<RecreationServiceFutureStub> {
    private RecreationServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RecreationServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new RecreationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.eci.arsw.wellness.recreation.ResourceList> getResources(
        edu.eci.arsw.wellness.recreation.RecEmptyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetResourcesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.eci.arsw.wellness.recreation.BorrowResponse> borrowResource(
        edu.eci.arsw.wellness.recreation.BorrowRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getBorrowResourceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.eci.arsw.wellness.recreation.BorrowResponse> returnResource(
        edu.eci.arsw.wellness.recreation.BorrowRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReturnResourceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_RESOURCES = 0;
  private static final int METHODID_BORROW_RESOURCE = 1;
  private static final int METHODID_RETURN_RESOURCE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_RESOURCES:
          serviceImpl.getResources((edu.eci.arsw.wellness.recreation.RecEmptyRequest) request,
              (io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.recreation.ResourceList>) responseObserver);
          break;
        case METHODID_BORROW_RESOURCE:
          serviceImpl.borrowResource((edu.eci.arsw.wellness.recreation.BorrowRequest) request,
              (io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.recreation.BorrowResponse>) responseObserver);
          break;
        case METHODID_RETURN_RESOURCE:
          serviceImpl.returnResource((edu.eci.arsw.wellness.recreation.BorrowRequest) request,
              (io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.recreation.BorrowResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetResourcesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              edu.eci.arsw.wellness.recreation.RecEmptyRequest,
              edu.eci.arsw.wellness.recreation.ResourceList>(
                service, METHODID_GET_RESOURCES)))
        .addMethod(
          getBorrowResourceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              edu.eci.arsw.wellness.recreation.BorrowRequest,
              edu.eci.arsw.wellness.recreation.BorrowResponse>(
                service, METHODID_BORROW_RESOURCE)))
        .addMethod(
          getReturnResourceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              edu.eci.arsw.wellness.recreation.BorrowRequest,
              edu.eci.arsw.wellness.recreation.BorrowResponse>(
                service, METHODID_RETURN_RESOURCE)))
        .build();
  }

  private static abstract class RecreationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RecreationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return edu.eci.arsw.wellness.recreation.RecreationProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RecreationService");
    }
  }

  private static final class RecreationServiceFileDescriptorSupplier
      extends RecreationServiceBaseDescriptorSupplier {
    RecreationServiceFileDescriptorSupplier() {}
  }

  private static final class RecreationServiceMethodDescriptorSupplier
      extends RecreationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    RecreationServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RecreationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RecreationServiceFileDescriptorSupplier())
              .addMethod(getGetResourcesMethod())
              .addMethod(getBorrowResourceMethod())
              .addMethod(getReturnResourceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
