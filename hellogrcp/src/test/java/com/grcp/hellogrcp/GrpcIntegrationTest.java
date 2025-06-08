package com.grcp.hellogrcp;

import com.grpc.hellogrcp.HelloRequest;
import com.grpc.hellogrcp.HelloResponse;
import com.grpc.hellogrcp.HelloServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GrpcIntegrationTest {

    @GrpcClient("local-grpc-server")
    private HelloServiceGrpc.HelloServiceBlockingStub helloServiceStub;

    @Test
    void sayHelloTest() {
        HelloRequest request = HelloRequest.newBuilder()
                .setName("Shai")
                .build();

        HelloResponse response = helloServiceStub.sayHello(request);
        String message = response.getMessage();
        System.out.printf("message response: %s%n", message);
        assertEquals("Hello Shai", message);
    }
}
