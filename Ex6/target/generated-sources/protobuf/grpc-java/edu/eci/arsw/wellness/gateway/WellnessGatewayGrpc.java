package edu.eci.arsw.wellness.gateway;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.63.0)",
    comments = "Source: gateway.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class WellnessGatewayGrpc {

  private WellnessGatewayGrpc() {}

  public static final java.lang.String SERVICE_NAME = "WellnessGateway";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<edu.eci.arsw.wellness.gateway.AppointmentGatewayRequest,
      edu.eci.arsw.wellness.gateway.AppointmentGatewayResponse> getRequestAppointmentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RequestAppointment",
      requestType = edu.eci.arsw.wellness.gateway.AppointmentGatewayRequest.class,
      responseType = edu.eci.arsw.wellness.gateway.AppointmentGatewayResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.eci.arsw.wellness.gateway.AppointmentGatewayRequest,
      edu.eci.arsw.wellness.gateway.AppointmentGatewayResponse> getRequestAppointmentMethod() {
    io.grpc.MethodDescriptor<edu.eci.arsw.wellness.gateway.AppointmentGatewayRequest, edu.eci.arsw.wellness.gateway.AppointmentGatewayResponse> getRequestAppointmentMethod;
    if ((getRequestAppointmentMethod = WellnessGatewayGrpc.getRequestAppointmentMethod) == null) {
      synchronized (WellnessGatewayGrpc.class) {
        if ((getRequestAppointmentMethod = WellnessGatewayGrpc.getRequestAppointmentMethod) == null) {
          WellnessGatewayGrpc.getRequestAppointmentMethod = getRequestAppointmentMethod =
              io.grpc.MethodDescriptor.<edu.eci.arsw.wellness.gateway.AppointmentGatewayRequest, edu.eci.arsw.wellness.gateway.AppointmentGatewayResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RequestAppointment"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.gateway.AppointmentGatewayRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.gateway.AppointmentGatewayResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WellnessGatewayMethodDescriptorSupplier("RequestAppointment"))
              .build();
        }
      }
    }
    return getRequestAppointmentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.eci.arsw.wellness.gateway.WellnessSummaryRequest,
      edu.eci.arsw.wellness.gateway.WellnessSummaryResponse> getGetStudentWellnessSummaryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetStudentWellnessSummary",
      requestType = edu.eci.arsw.wellness.gateway.WellnessSummaryRequest.class,
      responseType = edu.eci.arsw.wellness.gateway.WellnessSummaryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.eci.arsw.wellness.gateway.WellnessSummaryRequest,
      edu.eci.arsw.wellness.gateway.WellnessSummaryResponse> getGetStudentWellnessSummaryMethod() {
    io.grpc.MethodDescriptor<edu.eci.arsw.wellness.gateway.WellnessSummaryRequest, edu.eci.arsw.wellness.gateway.WellnessSummaryResponse> getGetStudentWellnessSummaryMethod;
    if ((getGetStudentWellnessSummaryMethod = WellnessGatewayGrpc.getGetStudentWellnessSummaryMethod) == null) {
      synchronized (WellnessGatewayGrpc.class) {
        if ((getGetStudentWellnessSummaryMethod = WellnessGatewayGrpc.getGetStudentWellnessSummaryMethod) == null) {
          WellnessGatewayGrpc.getGetStudentWellnessSummaryMethod = getGetStudentWellnessSummaryMethod =
              io.grpc.MethodDescriptor.<edu.eci.arsw.wellness.gateway.WellnessSummaryRequest, edu.eci.arsw.wellness.gateway.WellnessSummaryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetStudentWellnessSummary"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.gateway.WellnessSummaryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.gateway.WellnessSummaryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WellnessGatewayMethodDescriptorSupplier("GetStudentWellnessSummary"))
              .build();
        }
      }
    }
    return getGetStudentWellnessSummaryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.eci.arsw.wellness.gateway.GymGatewayRequest,
      edu.eci.arsw.wellness.gateway.GymGatewayResponse> getReserveGymSessionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReserveGymSession",
      requestType = edu.eci.arsw.wellness.gateway.GymGatewayRequest.class,
      responseType = edu.eci.arsw.wellness.gateway.GymGatewayResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.eci.arsw.wellness.gateway.GymGatewayRequest,
      edu.eci.arsw.wellness.gateway.GymGatewayResponse> getReserveGymSessionMethod() {
    io.grpc.MethodDescriptor<edu.eci.arsw.wellness.gateway.GymGatewayRequest, edu.eci.arsw.wellness.gateway.GymGatewayResponse> getReserveGymSessionMethod;
    if ((getReserveGymSessionMethod = WellnessGatewayGrpc.getReserveGymSessionMethod) == null) {
      synchronized (WellnessGatewayGrpc.class) {
        if ((getReserveGymSessionMethod = WellnessGatewayGrpc.getReserveGymSessionMethod) == null) {
          WellnessGatewayGrpc.getReserveGymSessionMethod = getReserveGymSessionMethod =
              io.grpc.MethodDescriptor.<edu.eci.arsw.wellness.gateway.GymGatewayRequest, edu.eci.arsw.wellness.gateway.GymGatewayResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReserveGymSession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.gateway.GymGatewayRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.gateway.GymGatewayResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WellnessGatewayMethodDescriptorSupplier("ReserveGymSession"))
              .build();
        }
      }
    }
    return getReserveGymSessionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<edu.eci.arsw.wellness.gateway.RecreationGatewayRequest,
      edu.eci.arsw.wellness.gateway.RecreationGatewayResponse> getReserveRecreationResourceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReserveRecreationResource",
      requestType = edu.eci.arsw.wellness.gateway.RecreationGatewayRequest.class,
      responseType = edu.eci.arsw.wellness.gateway.RecreationGatewayResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<edu.eci.arsw.wellness.gateway.RecreationGatewayRequest,
      edu.eci.arsw.wellness.gateway.RecreationGatewayResponse> getReserveRecreationResourceMethod() {
    io.grpc.MethodDescriptor<edu.eci.arsw.wellness.gateway.RecreationGatewayRequest, edu.eci.arsw.wellness.gateway.RecreationGatewayResponse> getReserveRecreationResourceMethod;
    if ((getReserveRecreationResourceMethod = WellnessGatewayGrpc.getReserveRecreationResourceMethod) == null) {
      synchronized (WellnessGatewayGrpc.class) {
        if ((getReserveRecreationResourceMethod = WellnessGatewayGrpc.getReserveRecreationResourceMethod) == null) {
          WellnessGatewayGrpc.getReserveRecreationResourceMethod = getReserveRecreationResourceMethod =
              io.grpc.MethodDescriptor.<edu.eci.arsw.wellness.gateway.RecreationGatewayRequest, edu.eci.arsw.wellness.gateway.RecreationGatewayResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReserveRecreationResource"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.gateway.RecreationGatewayRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  edu.eci.arsw.wellness.gateway.RecreationGatewayResponse.getDefaultInstance()))
              .setSchemaDescriptor(new WellnessGatewayMethodDescriptorSupplier("ReserveRecreationResource"))
              .build();
        }
      }
    }
    return getReserveRecreationResourceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static WellnessGatewayStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WellnessGatewayStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WellnessGatewayStub>() {
        @java.lang.Override
        public WellnessGatewayStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WellnessGatewayStub(channel, callOptions);
        }
      };
    return WellnessGatewayStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static WellnessGatewayBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WellnessGatewayBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WellnessGatewayBlockingStub>() {
        @java.lang.Override
        public WellnessGatewayBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WellnessGatewayBlockingStub(channel, callOptions);
        }
      };
    return WellnessGatewayBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static WellnessGatewayFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<WellnessGatewayFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<WellnessGatewayFutureStub>() {
        @java.lang.Override
        public WellnessGatewayFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new WellnessGatewayFutureStub(channel, callOptions);
        }
      };
    return WellnessGatewayFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void requestAppointment(edu.eci.arsw.wellness.gateway.AppointmentGatewayRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.gateway.AppointmentGatewayResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRequestAppointmentMethod(), responseObserver);
    }

    /**
     */
    default void getStudentWellnessSummary(edu.eci.arsw.wellness.gateway.WellnessSummaryRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.gateway.WellnessSummaryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetStudentWellnessSummaryMethod(), responseObserver);
    }

    /**
     */
    default void reserveGymSession(edu.eci.arsw.wellness.gateway.GymGatewayRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.gateway.GymGatewayResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReserveGymSessionMethod(), responseObserver);
    }

    /**
     */
    default void reserveRecreationResource(edu.eci.arsw.wellness.gateway.RecreationGatewayRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.gateway.RecreationGatewayResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReserveRecreationResourceMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service WellnessGateway.
   */
  public static abstract class WellnessGatewayImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return WellnessGatewayGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service WellnessGateway.
   */
  public static final class WellnessGatewayStub
      extends io.grpc.stub.AbstractAsyncStub<WellnessGatewayStub> {
    private WellnessGatewayStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WellnessGatewayStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WellnessGatewayStub(channel, callOptions);
    }

    /**
     */
    public void requestAppointment(edu.eci.arsw.wellness.gateway.AppointmentGatewayRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.gateway.AppointmentGatewayResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRequestAppointmentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStudentWellnessSummary(edu.eci.arsw.wellness.gateway.WellnessSummaryRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.gateway.WellnessSummaryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetStudentWellnessSummaryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reserveGymSession(edu.eci.arsw.wellness.gateway.GymGatewayRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.gateway.GymGatewayResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReserveGymSessionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reserveRecreationResource(edu.eci.arsw.wellness.gateway.RecreationGatewayRequest request,
        io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.gateway.RecreationGatewayResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReserveRecreationResourceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service WellnessGateway.
   */
  public static final class WellnessGatewayBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<WellnessGatewayBlockingStub> {
    private WellnessGatewayBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WellnessGatewayBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WellnessGatewayBlockingStub(channel, callOptions);
    }

    /**
     */
    public edu.eci.arsw.wellness.gateway.AppointmentGatewayResponse requestAppointment(edu.eci.arsw.wellness.gateway.AppointmentGatewayRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRequestAppointmentMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.eci.arsw.wellness.gateway.WellnessSummaryResponse getStudentWellnessSummary(edu.eci.arsw.wellness.gateway.WellnessSummaryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetStudentWellnessSummaryMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.eci.arsw.wellness.gateway.GymGatewayResponse reserveGymSession(edu.eci.arsw.wellness.gateway.GymGatewayRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReserveGymSessionMethod(), getCallOptions(), request);
    }

    /**
     */
    public edu.eci.arsw.wellness.gateway.RecreationGatewayResponse reserveRecreationResource(edu.eci.arsw.wellness.gateway.RecreationGatewayRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReserveRecreationResourceMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service WellnessGateway.
   */
  public static final class WellnessGatewayFutureStub
      extends io.grpc.stub.AbstractFutureStub<WellnessGatewayFutureStub> {
    private WellnessGatewayFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected WellnessGatewayFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new WellnessGatewayFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.eci.arsw.wellness.gateway.AppointmentGatewayResponse> requestAppointment(
        edu.eci.arsw.wellness.gateway.AppointmentGatewayRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRequestAppointmentMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.eci.arsw.wellness.gateway.WellnessSummaryResponse> getStudentWellnessSummary(
        edu.eci.arsw.wellness.gateway.WellnessSummaryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetStudentWellnessSummaryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.eci.arsw.wellness.gateway.GymGatewayResponse> reserveGymSession(
        edu.eci.arsw.wellness.gateway.GymGatewayRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReserveGymSessionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<edu.eci.arsw.wellness.gateway.RecreationGatewayResponse> reserveRecreationResource(
        edu.eci.arsw.wellness.gateway.RecreationGatewayRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReserveRecreationResourceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REQUEST_APPOINTMENT = 0;
  private static final int METHODID_GET_STUDENT_WELLNESS_SUMMARY = 1;
  private static final int METHODID_RESERVE_GYM_SESSION = 2;
  private static final int METHODID_RESERVE_RECREATION_RESOURCE = 3;

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
        case METHODID_REQUEST_APPOINTMENT:
          serviceImpl.requestAppointment((edu.eci.arsw.wellness.gateway.AppointmentGatewayRequest) request,
              (io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.gateway.AppointmentGatewayResponse>) responseObserver);
          break;
        case METHODID_GET_STUDENT_WELLNESS_SUMMARY:
          serviceImpl.getStudentWellnessSummary((edu.eci.arsw.wellness.gateway.WellnessSummaryRequest) request,
              (io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.gateway.WellnessSummaryResponse>) responseObserver);
          break;
        case METHODID_RESERVE_GYM_SESSION:
          serviceImpl.reserveGymSession((edu.eci.arsw.wellness.gateway.GymGatewayRequest) request,
              (io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.gateway.GymGatewayResponse>) responseObserver);
          break;
        case METHODID_RESERVE_RECREATION_RESOURCE:
          serviceImpl.reserveRecreationResource((edu.eci.arsw.wellness.gateway.RecreationGatewayRequest) request,
              (io.grpc.stub.StreamObserver<edu.eci.arsw.wellness.gateway.RecreationGatewayResponse>) responseObserver);
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
          getRequestAppointmentMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              edu.eci.arsw.wellness.gateway.AppointmentGatewayRequest,
              edu.eci.arsw.wellness.gateway.AppointmentGatewayResponse>(
                service, METHODID_REQUEST_APPOINTMENT)))
        .addMethod(
          getGetStudentWellnessSummaryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              edu.eci.arsw.wellness.gateway.WellnessSummaryRequest,
              edu.eci.arsw.wellness.gateway.WellnessSummaryResponse>(
                service, METHODID_GET_STUDENT_WELLNESS_SUMMARY)))
        .addMethod(
          getReserveGymSessionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              edu.eci.arsw.wellness.gateway.GymGatewayRequest,
              edu.eci.arsw.wellness.gateway.GymGatewayResponse>(
                service, METHODID_RESERVE_GYM_SESSION)))
        .addMethod(
          getReserveRecreationResourceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              edu.eci.arsw.wellness.gateway.RecreationGatewayRequest,
              edu.eci.arsw.wellness.gateway.RecreationGatewayResponse>(
                service, METHODID_RESERVE_RECREATION_RESOURCE)))
        .build();
  }

  private static abstract class WellnessGatewayBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    WellnessGatewayBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return edu.eci.arsw.wellness.gateway.GatewayProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("WellnessGateway");
    }
  }

  private static final class WellnessGatewayFileDescriptorSupplier
      extends WellnessGatewayBaseDescriptorSupplier {
    WellnessGatewayFileDescriptorSupplier() {}
  }

  private static final class WellnessGatewayMethodDescriptorSupplier
      extends WellnessGatewayBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    WellnessGatewayMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (WellnessGatewayGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new WellnessGatewayFileDescriptorSupplier())
              .addMethod(getRequestAppointmentMethod())
              .addMethod(getGetStudentWellnessSummaryMethod())
              .addMethod(getReserveGymSessionMethod())
              .addMethod(getReserveRecreationResourceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
