package com.pharosproduction.users.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class UserServer {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("gRPC userServer starting...");

    Server server = ServerBuilder.forPort(5000)
      .addService(new UserServiceImpl())
      .build();
    server.start();

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println("Received Shutdown...");
      server.shutdown();
      System.out.println("UserServer Stopped");
    }));

    server.awaitTermination();
  }
}



