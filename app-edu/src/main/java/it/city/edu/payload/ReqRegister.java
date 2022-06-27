package it.city.edu.payload;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

@Data
public class ReqRegister {
    @NotNull(message = "FirstName cannot be empty")
    private String firstName;
    @NotNull(message = "LastName cannot be empty")
    private String lastName;
    private String middleName;
    @Pattern(regexp="[A-Z]{2}",message="length must be 2")
    private String passSerial;
    @Pattern(regexp="[a-b0-9]{7,14}",message="length must be between 7, 13")
    private String passNumber;
    @NotBlank(message = "BirthDate cannot be empty")
    private Date birthDate;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",message = "Password length must be between 8, 20")
    private String password;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",message = "Password length must be between 8, 20")
    private String prePassword;
    @Pattern(regexp = "^[+][9][9][8][0-9]{9}$", message = "Tel raqam notugre")
    private String phoneNumber;

    private ReqContact reqContact;
    private String role;


}
