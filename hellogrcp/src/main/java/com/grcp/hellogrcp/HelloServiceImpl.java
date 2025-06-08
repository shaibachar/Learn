package com.grcp.hellogrcp;

import com.grpc.hellogrcp.HelloRequest;
import com.grpc.hellogrcp.HelloResponse;
import com.grpc.hellogrcp.HelloServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void sayHello(HelloRequest request,
                         io.grpc.stub.StreamObserver<HelloResponse> responseObserver) {
        String name = request.getName();
        HelloResponse response = HelloResponse.newBuilder()
                .setMessage("Hello " + name)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
