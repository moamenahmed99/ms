package com.moamenproject.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "AccountNumber cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 11 digits")
    private Long accountNumber;

    @NotEmpty(message = "AccountType cannot be null or empty")
    private String accountType;

    @NotEmpty(message = "BranchAddress cannot be null or empty")
    private String branchAddress;
}
