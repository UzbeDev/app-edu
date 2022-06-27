package it.city.edu.controller;

import it.city.edu.entity.CompanyInfo;
import it.city.edu.payload.ApiResponse;
import it.city.edu.payload.ReqCompanyInfo;
import it.city.edu.payload.ResCompanyInfo;
import it.city.edu.service.CompanyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/companyInfo")
public class CompanyInfoController {
    @Autowired
    CompanyInfoService companyInfoService;


    @PostMapping
    @ResponseBody
    public HttpEntity<?> saveCompanyInfo(@Valid @RequestBody ReqCompanyInfo reqCompanyInfo){
        ApiResponse apiResponse = companyInfoService.saveCompanyInfo(reqCompanyInfo);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @GetMapping("/{id}")
    @ResponseBody
    public ResCompanyInfo getOneCompanyInfo(CompanyInfo companyInfo){
        return  companyInfoService.getOneCompanyInfo(companyInfo);
    }

    @GetMapping
    @ResponseBody
    public HttpEntity<?> getCompanyInfo(){
        return (HttpEntity<?>) companyInfoService.getCompanyInfoList();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> updateCompanyInfo(@PathVariable UUID id, @RequestBody ReqCompanyInfo reqCompanyInfo){
        ApiResponse apiResponse = companyInfoService.editCompanyInfo(id, reqCompanyInfo);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public HttpEntity<?> delete(@PathVariable UUID id){
        ApiResponse apiResponse = companyInfoService.deleteCompanyInfo(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200 : 409).body(apiResponse);
    }
}
