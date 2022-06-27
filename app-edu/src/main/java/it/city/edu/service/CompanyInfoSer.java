package it.city.edu.service;

import it.city.edu.entity.CompanyInfo;
import it.city.edu.payload.ApiResponse;
import it.city.edu.payload.ReqCompanyInfo;
import it.city.edu.payload.ResAware;
import it.city.edu.payload.ResCompanyInfo;

import java.util.List;
import java.util.UUID;

public interface CompanyInfoSer {
    ApiResponse saveCompanyInfo(ReqCompanyInfo reqCompanyInfo);

    List<ResCompanyInfo> getCompanyInfoList();

    ResCompanyInfo getOneCompanyInfo(CompanyInfo companyInfo);

    ApiResponse editCompanyInfo(UUID id, ReqCompanyInfo reqCompanyInfo);

    ApiResponse deleteCompanyInfo(UUID id);
}
