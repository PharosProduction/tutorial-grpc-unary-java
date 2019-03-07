package com.pharosproduction.users.server;

import com.pharosproduction.users.User;
import com.pharosproduction.users.UserRequest;
import com.pharosproduction.users.UserResponse;
import com.pharosproduction.users.UserServiceGrpc;
import io.grpc.stub.StreamObserver;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

  @Override
  public void user(UserRequest request, StreamObserver<UserResponse> responseObserver) {
    User user = request.getUser();
    String firstName = user.getFirstName();
    String lastName = user.getLastName();
    String responseStr = firstName + " " + lastName;

    UserResponse response = UserResponse.newBuilder()
      .setResult(responseStr)
      .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
