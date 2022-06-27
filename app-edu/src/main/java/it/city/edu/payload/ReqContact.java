package it.city.edu.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Data
public class ReqContact {
    @NotBlank
    private Integer districtId;
    @Email(message = "Siz noture email kiritdingiz")
    private String email;
    @NotNull(message = "fax bush bulmasligi kerak")
    private String fax;
    @Pattern(regexp = "^[+][9][9][8][0-9]{9}$", message = "Tel raqam notugre")
    private Set<String> phoneNumbers;
}
