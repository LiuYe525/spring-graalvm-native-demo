package com.lye.nativeimagedemo.service;


import com.lye.nativeimagedemo.proto.HelloReply;
import com.lye.nativeimagedemo.proto.HelloRequest;
import com.lye.nativeimagedemo.proto.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class GrpcServerService extends SimpleGrpc.SimpleImplBase {

    public GrpcServerService() {
        log.info("Created {}.", this);
    }

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        log.info("Received grpc request {}.", req.getName());
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}