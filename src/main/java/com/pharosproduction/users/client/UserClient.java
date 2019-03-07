package com.pharosproduction.users.client;

import com.pharosproduction.users.User;
import com.pharosproduction.users.UserRequest;
import com.pharosproduction.users.UserResponse;
import com.pharosproduction.users.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class UserClient {

  public static void main(String[] args) {
    System.out.println("gRPC client starting...");

    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5000)
      .usePlaintext()
      .build();
    UserServiceGrpc.UserServiceBlockingStub client = UserServiceGrpc.newBlockingStub(channel);

    User greeting = User.newBuilder()
      .setFirstName("John")
      .setLastName("Doe")
      .build();

    UserRequest request = UserRequest.newBuilder()
      .setUser(greeting)
      .build();

    UserResponse response = client.user(request);
    System.out.println("RESPONSE IS: " + response.getResult());

    System.out.println("Shutting down channel");
    channel.shutdown();
  }
}
