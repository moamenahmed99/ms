package com.moamenproject.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
    name = "Customer",
    description = "Schema to hold Customer and Account information"
)
public class CustomerDto {
    @Schema(
        description = "Customer name", example = "John Doe"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of customer name should be between 5 and 30 characters")
    private String name;

    @Schema(
        description = "Customer Email", example = "JohnDoe@mail.com"
    )
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email address should be a valid value")
    private String Email;
    
    @Schema(
        description = "Customer Mobile Number", example = "12345678900"
    )
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile Number must be 11 digits")
    private String mobileNumber;
    
    @Schema(
        description = "Customer Account details"
    )
    private AccountsDto accountsDto;
}
