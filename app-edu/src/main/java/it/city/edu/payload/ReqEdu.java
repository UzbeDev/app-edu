package it.city.edu.payload;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class ReqEdu {
    private String name;
    private String brand;
    private String  tin;
    private Date foundedDate;
    private String accountNumber;
    private ReqContact reqContact;
    private String licenseNumber;
    private Date licenseExpire;
    private List<Integer> edoTypesId;
    private UUID logoId;
    private UUID licenseId;
    private List<UUID> photosId;
}
