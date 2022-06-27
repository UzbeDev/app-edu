package it.city.edu.service;

import it.city.edu.entity.CompanyAware;
import it.city.edu.entity.CompanyInfo;
import it.city.edu.payload.ApiResponse;
import it.city.edu.payload.ReqCompanyAware;
import it.city.edu.payload.ReqCompanyInfo;
import it.city.edu.payload.ResCompanyInfo;
import it.city.edu.repository.AwareRepository;
import it.city.edu.repository.CompanyAwareRepository;
import it.city.edu.repository.CompanyInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyInfoService implements CompanyInfoSer {

    @Autowired
    CompanyInfoRepository companyInfoRepository;
    @Autowired
    ContactService contactService;
    @Autowired
    AwareRepository awareRepository;
    @Autowired
    CompanyAwareRepository companyAwareRepository;


    @Override
    public ApiResponse saveCompanyInfo(ReqCompanyInfo reqCompanyInfo) {
        boolean exists = companyInfoRepository.existsByNameEqualsIgnoreCaseOrBrandName(reqCompanyInfo.getName(), reqCompanyInfo.getBrandName());
        if (!exists) {
            CompanyInfo companyInfo = new CompanyInfo();
            companyInfo.setName(reqCompanyInfo.getName());
            companyInfo.setBrandName(reqCompanyInfo.getBrandName());
            companyInfo.setDefinition(reqCompanyInfo.getDefinition());
            companyInfo.setContact(contactService.saveContact(reqCompanyInfo.getReqContact()));
            List<CompanyAware> companyAwares = new ArrayList<>();
            for (ReqCompanyAware reqCompanyAware : reqCompanyInfo.getReqCompanyAwares()) {
                CompanyAware companyAware = new CompanyAware();
                companyAware.setAware(awareRepository.findById(reqCompanyAware.getAwareId()).orElseThrow(() -> new ResourceAccessException("getAwareCompany")));
                companyAware.setNameEn(reqCompanyAware.getNameEn());
                companyAware.setNameRu(reqCompanyAware.getNameRu());
                companyAware.setNameUz(reqCompanyAware.getNameUz());
                companyAwares.add(companyAware);
                companyAwareRepository.save(companyAware);
            }
            companyInfo.setCompanyAwares(companyAwares);


            return new ApiResponse("Saved CompanyInfo", true);
        }
        return new ApiResponse("Already exist", false);
    }

    @Override
    public List<ResCompanyInfo> getCompanyInfoList() {
        return companyInfoRepository.findAll().stream().map(this::getOneCompanyInfo).collect(Collectors.toList());
    }

    @Override
    public ResCompanyInfo getOneCompanyInfo(CompanyInfo companyInfo) {
        return  new ResCompanyInfo(companyInfo.getId(), companyInfo.getBrandName(), companyInfo.getDefinition());
    }

    @Override
    public ApiResponse editCompanyInfo(UUID id, ReqCompanyInfo reqCompanyInfo) {
        boolean exist = companyInfoRepository.existsByNameEqualsIgnoreCaseOrBrandNameEqualsIgnoreCaseAndIdNot(reqCompanyInfo.getName(), reqCompanyInfo.getBrandName(), id);
        if (!exist) {
            CompanyInfo companyInfo = new CompanyInfo();
            companyInfo.setName(reqCompanyInfo.getName());
            companyInfo.setBrandName(reqCompanyInfo.getBrandName());
            companyInfo.setDefinition(reqCompanyInfo.getDefinition());
            companyInfo.setContact(contactService.saveContact(reqCompanyInfo.getReqContact()));
            List<CompanyAware> companyAwares = new ArrayList<>();
            for (ReqCompanyAware reqCompanyAware : reqCompanyInfo.getReqCompanyAwares()) {
                CompanyAware companyAware = new CompanyAware();
                companyAware.setAware(awareRepository.findById(reqCompanyAware.getAwareId()).orElseThrow(() -> new ResourceAccessException("getAwareCompany")));
                companyAware.setNameEn(reqCompanyAware.getNameEn());
                companyAware.setNameRu(reqCompanyAware.getNameRu());
                companyAware.setNameUz(reqCompanyAware.getNameUz());
                companyAwares.add(companyAware);
                companyAwareRepository.save(companyAware);
            }
            companyInfo.setCompanyAwares(companyAwares);
            return new ApiResponse("Update CompanyInfo", true);
        } else {
            return new ApiResponse("Bunday CompanyInfo bor", false);
        }
    }

    @Override
    public ApiResponse deleteCompanyInfo(UUID id) {
       try {
           companyInfoRepository.deleteById(id);
           return new ApiResponse("o'chdi", true);
       }catch (Exception e){
           return new ApiResponse("Xatolik", false);
       }

    }



}
