package nl.prbed.hu.aviation.security.presentation.dto;

import io.swagger.annotations.ApiModelProperty;

public class LoginDto {
    @ApiModelProperty(notes = "The unique username of the customer")
    public String username;

    @ApiModelProperty(notes = "The password. This must be at least 5 characters long")
    public String password;
}
