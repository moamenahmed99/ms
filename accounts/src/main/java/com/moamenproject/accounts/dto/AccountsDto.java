package com.moamenproject.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
    name = "Account",
    description = "Schema to hold Account information"
)
public class AccountsDto {

    @Schema(
        description = "Account Number of EazyBank Account", example = "1234567890"
    )
    @NotEmpty(message = "AccountNumber cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 11 digits")
    private Long accountNumber;

    @Schema(
        description = "Account Type of EazyBank Account", example = "Savings"
    )
    @NotEmpty(message = "AccountType cannot be null or empty")
    private String accountType;

    @Schema(
        description = "Eazy Bank branch address", example = "123 NewYork"
    )
    @NotEmpty(message = "BranchAddress cannot be null or empty")
    private String branchAddress;
}
