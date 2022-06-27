package it.city.edu.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReqCompanyInfo {

    private String name;

    private String brandName;

    private List<ReqCompanyAware> reqCompanyAwares;

    private ReqContact reqContact;

    private String definition;


}
