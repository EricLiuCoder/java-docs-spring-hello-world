package com.pcc.carrental.request;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String firstName;

    private String lastName;

    private String alias;

    private String mobilePhoneNum;

    private String emailAddress;
}
