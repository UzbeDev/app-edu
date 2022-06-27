package it.city.edu.controller;

import it.city.edu.entity.User;
import it.city.edu.payload.ApiResponse;
import it.city.edu.payload.ReqEdu;
import it.city.edu.security.CurrentUser;
import it.city.edu.service.EduService;
import it.city.edu.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/eduCenter")
public class EduCenterController {

    @Autowired
    EduService eduService;
    @PostMapping
    public HttpEntity<?> addEdu(@RequestBody ReqEdu reqEdu, @CurrentUser User user) {
        ApiResponse apiResponse = eduService.registerEdu(reqEdu, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editEdu(@PathVariable UUID id,  @RequestBody ReqEdu reqEdu, @CurrentUser User user) {
        ApiResponse apiResponse = eduService.editEdu(id,reqEdu, user);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @PatchMapping("/{id}")
    public HttpEntity<?> changeEnabled(@PathVariable UUID id, @CurrentUser User user ){
        ApiResponse apiResponse = eduService.changeEnabled(id, user);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getEduCenterForDirectorForAdmin(@PathVariable UUID id, @CurrentUser User user){
        return ResponseEntity.ok(eduService.getEduForAdminForDirector(id, user));
    }

//    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("/getPage")
    public HttpEntity<?> getPageEdu(@RequestParam (value = "page", defaultValue = AppConstant.APP_DEFAULT_PAGE) int page,
                                    @RequestParam (value = "size", defaultValue = AppConstant.APP_DEFAULT_SIZE) int size,
                                    @CurrentUser User user){
        return ResponseEntity.ok(eduService.getEduPage(page, size, user));
    }
}
