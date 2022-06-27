package it.city.edu.payload;

import it.city.edu.entity.EdoType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ResEdu {
    private UUID id;
    private String name;
    private String brand;
    private String  tin;
    private Date foundedDate;
    private String accountNumber;
    private ResContact resContact;
    private String licenseNumber;
    private Date licenseExpire;
    private List<EdoType> edoTypes;
    private UUID logoId;
    private UUID licenseId;
    private List<UUID> photosId;
}
