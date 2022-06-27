package it.city.edu.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqLogin {
    @Pattern(regexp="[A-Z]{2}",message="length must be 2")
    private String passSerial;
    @Pattern(regexp="[a-z0-9]{7,14}",message="length must be between 7, 13")
    private String passNumber;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$",message = "Password length must be between 8, 20")
    private String password;

    private String phoneNumber;

    public ReqLogin(String passSerial, String passNumber, String phoneNumber) {
        this.passSerial = passSerial;
        this.passNumber = passNumber;
        this.phoneNumber = phoneNumber;
    }
}
