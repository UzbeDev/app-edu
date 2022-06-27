package it.city.edu.service;

import it.city.edu.entity.EduCenter;
import it.city.edu.entity.User;
import it.city.edu.entity.UserEdu;
import it.city.edu.entity.template.AbsEntity;
import it.city.edu.payload.ApiResponse;
import it.city.edu.payload.ReqEdu;
import it.city.edu.payload.ResEdu;
import it.city.edu.payload.ResPageable;
import it.city.edu.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EduServiceImp implements EduService {
    @Autowired
    ContactService contactService;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    EduTypeRepository eduTypeRepository;
    @Autowired
    EduCenterRepository eduCenterRepository;
    @Autowired
    LegalRepository legalRepository;
    @Autowired
    UserEduRepository userEduRepository;
    @Autowired
    RoleRepository roleRepository;


    @Override
    public ApiResponse registerEdu(ReqEdu reqEdu, User user) {
        try {
            if (!eduCenterRepository.existsByNameEqualsIgnoreCase(reqEdu.getName())) {
                if (!eduCenterRepository.existsByBrandEqualsIgnoreCase(reqEdu.getBrand())) {
                    if (!eduCenterRepository.existsByTinEqualsIgnoreCase(reqEdu.getTin())) {
                        if (reqEdu.getLicenseExpire().after(new Date())) {
                            if (reqEdu.getFoundedDate().before(new Date())) {
                                EduCenter eduCenter = new EduCenter();
                                eduCenter.setName(reqEdu.getName());
                                eduCenter.setBrand(reqEdu.getBrand());
                                eduCenter.setTin(reqEdu.getTin());
                                eduCenter.setFoundedDate(reqEdu.getFoundedDate());
                                eduCenter.setAccountNumber(reqEdu.getAccountNumber());
                                eduCenter.setContact(contactService.saveContact(reqEdu.getReqContact()));
                                eduCenter.setLicenseExpire(reqEdu.getLicenseExpire());
                                eduCenter.setLicenseNumber(reqEdu.getLicenseNumber());
                                eduCenter.setEdoTypes(eduTypeRepository.findAllById(reqEdu.getEdoTypesId()));
                                eduCenter.setLogo(attachmentRepository.findById(reqEdu.getLogoId()).orElseThrow(() -> new ResourceAccessException("GetLogo")));
                                eduCenter.setLicense(attachmentRepository.findById(reqEdu.getLicenseId()).orElseThrow(() -> new ResourceAccessException("GetLicense")));
                                eduCenter.setPhotos(attachmentRepository.findAllById(reqEdu.getPhotosId()));
                                EduCenter savedEduCenter = eduCenterRepository.save(eduCenter);
//                                User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                                UserEdu userEdu = new UserEdu();
                                userEdu.setUser(user);
                                userEdu.setEduCenter(savedEduCenter);
                                userEdu.setLegals(new HashSet<>(legalRepository.findAll()));
                                userEduRepository.save(userEdu);
                                return new ApiResponse("Edu registratsiyadan o'tdi", true);
                            } else {
                                return new ApiResponse("Tashkil qilingan sana bugundan oldin bulishi kerak", false);
                            }
                        } else {
                            return new ApiResponse("License muddati utgan", false);
                        }
                    } else {
                        return new ApiResponse("Bu STIR mavjud", false);
                    }
                } else {
                    return new ApiResponse("Brand avvaldan mavjud", false);
                }
            } else {
                return new ApiResponse("Bu nom avvaldan mavjud", false);
            }

        } catch (Exception e) {
            return new ApiResponse(e.getMessage(), false);
        }
    }

    @Override
    public ApiResponse editEdu(UUID id, ReqEdu reqEdu, User user) {
        try {
            UserEdu userEdu = new UserEdu();
            for (UserEdu userEdus : user.getUserEdus()) {
                if (userEdus.getEduCenter().getId().equals(id)) {
                    userEdu = userEdus;
                    break;
                }
            }
            if (user.getRoles().size() > 1 || userEdu.getLegals().size() > 4) {
                if (!eduCenterRepository.existsByNameEqualsIgnoreCaseAndIdNot(reqEdu.getName(), id)) {
                    if (!eduCenterRepository.existsByBrandEqualsIgnoreCaseAndIdNot(reqEdu.getBrand(), id)) {
                        if (!eduCenterRepository.existsByTinEqualsIgnoreCaseAndIdNot(reqEdu.getTin(), id)) {
                            if (reqEdu.getLicenseExpire().after(new Date())) {
                                if (reqEdu.getFoundedDate().before(new Date())) {
                                    EduCenter eduCenter = eduCenterRepository.findById(id).orElseThrow(() -> new ResourceAccessException("getEduCenter"));
                                    eduCenter.setName(reqEdu.getName());
                                    eduCenter.setBrand(reqEdu.getBrand());
                                    eduCenter.setTin(reqEdu.getTin());
                                    eduCenter.setFoundedDate(reqEdu.getFoundedDate());
                                    eduCenter.setAccountNumber(reqEdu.getAccountNumber());
                                    eduCenter.setContact(contactService.editContact(eduCenter.getContact().getId(), reqEdu.getReqContact()));
                                    eduCenter.setLicenseExpire(reqEdu.getLicenseExpire());
                                    eduCenter.setLicenseNumber(reqEdu.getLicenseNumber());
                                    eduCenter.setEdoTypes(eduTypeRepository.findAllById(reqEdu.getEdoTypesId()));
                                    eduCenter.setLogo(attachmentRepository.findById(reqEdu.getLogoId()).orElseThrow(() -> new ResourceAccessException("GetLogo")));
                                    eduCenter.setLicense(attachmentRepository.findById(reqEdu.getLicenseId()).orElseThrow(() -> new ResourceAccessException("GetLicense")));
                                    eduCenter.setPhotos(attachmentRepository.findAllById(reqEdu.getPhotosId()));
                                    eduCenterRepository.save(eduCenter);
                                    return new ApiResponse("Edu tahrirlandi", true);
                                } else {
                                    return new ApiResponse("Tashkil qilingan sana bugundan oldin bulishi kerak", false);
                                }
                            } else {
                                return new ApiResponse("License muddati utgan", false);
                            }
                        } else {
                            return new ApiResponse("Bu STIR mavjud", false);
                        }
                    } else {
                        return new ApiResponse("Brand avvaldan mavjud", false);
                    }
                } else {
                    return new ApiResponse("Bu nom avvaldan mavjud", false);
                }
            }
            return new ApiResponse("Sizda bunday huquq yuq", false);
        } catch (Exception e) {
            return new ApiResponse(e.getMessage(), false);
        }
    }

    @Override
    public ApiResponse changeEnabled(UUID id, User user) {

        Optional<EduCenter> optionalEduCenter = eduCenterRepository.findById(id);
        if (optionalEduCenter.isPresent()) {
            EduCenter eduCenter = optionalEduCenter.get();
            if (user.getRoles().size() > 1) {
                eduCenter.setEnabled(!eduCenter.isEnabled());
            } else {
                UserEdu userEdu = new UserEdu();
                for (UserEdu userEdus : user.getUserEdus()) {
                    if (userEdus.getEduCenter().getId().equals(id)) {
                        userEdu = userEdus;
                        break;
                    }
                }
                if (userEdu.getLegals().size() > 4) {
                    eduCenter.setEnabled(false);
                } else {
                    return new ApiResponse("Sizning huquqingiz yuq", false);
                }
            }
            EduCenter save = eduCenterRepository.save(eduCenter);
            return new ApiResponse(save.isEnabled() ? "Edu center yoqildi" : "Edu center o'chirildi", true);
        }
        return new ApiResponse("Bundan edu topilmadi", false);
    }

    @Override
    public ResEdu getEduForAdminForDirector(UUID id, User user) {
        Optional<EduCenter> byId = eduCenterRepository.findById(id);
        if (byId.isPresent()) {
            UserEdu userEdu = new UserEdu();
            for (UserEdu userEdus : user.getUserEdus()) {
                if (userEdus.getEduCenter().getId().equals(id)) {
                    userEdu = userEdus;
                    break;
                }
            }
            EduCenter eduCenter = byId.get();
            if (user.getRoles().size() > 1 || userEdu.getLegals().size() > 3) {
                return new ResEdu(
                        eduCenter.getId(),
                        eduCenter.getName(),
                        eduCenter.getBrand(),
                        eduCenter.getTin(),
                        eduCenter.getFoundedDate(),
                        eduCenter.getAccountNumber(),
                        contactService.getContact(eduCenter.getContact().getId()),
                        eduCenter.getLicenseNumber(),
                        eduCenter.getLicenseExpire(),
                        eduCenter.getEdoTypes(),
                        eduCenter.getLogo().getId(),
                        eduCenter.getLicense().getId(),
                        eduCenter.getPhotos().stream().map(AbsEntity::getId).collect(Collectors.toList())
                );
            }
        }
        return null;
    }

    @Override
    public ResPageable getEduPage(int page, int size, User user) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EduCenter> eduCenterPage = eduCenterRepository.findAll(pageable);
        List<EduCenter> eduCenterList = eduCenterPage.getContent();
        List<ResEdu> resEdus = new ArrayList<>();
        for (EduCenter eduCenter : eduCenterList) {
            ResEdu resEdu = getEduForAdminForDirector(eduCenter.getId(), user);
            resEdus.add(resEdu);
        }
        return new ResPageable(
                page,
                size,
                eduCenterPage.getTotalPages(),
                eduCenterPage.getTotalElements(),
                resEdus
        );
    }


}
