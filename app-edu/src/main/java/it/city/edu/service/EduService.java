package it.city.edu.service;

import it.city.edu.entity.User;
import it.city.edu.payload.ApiResponse;
import it.city.edu.payload.ReqEdu;
import it.city.edu.payload.ResEdu;
import it.city.edu.payload.ResPageable;

import java.util.UUID;

public interface EduService {
    ApiResponse registerEdu(ReqEdu reqEdu, User user);
    ApiResponse editEdu(UUID id, ReqEdu reqEdu, User user);
    ApiResponse changeEnabled(UUID id, User user);
    ResEdu getEduForAdminForDirector(UUID id, User user);
    ResPageable getEduPage(int page, int size, User user);



}
