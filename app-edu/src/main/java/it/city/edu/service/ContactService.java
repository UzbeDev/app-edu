package it.city.edu.service;

import it.city.edu.entity.Contact;
import it.city.edu.exception.ExistException;
import it.city.edu.payload.ReqContact;
import it.city.edu.payload.ResContact;
import it.city.edu.repository.ContactRepository;
import it.city.edu.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.Optional;
import java.util.UUID;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    DistrictRepository districtRepository;


    public Contact saveContact(ReqContact reqContact){
        if (contactRepository.existsByEmailEqualsIgnoreCase(reqContact.getEmail())) {
            throw new  ExistException("Bunday email mavjud");
        }
        Contact contact=new Contact(
                districtRepository.findById(reqContact.getDistrictId()).orElseThrow(() -> new ResourceAccessException("getDistrict")),
                reqContact.getEmail(),
                reqContact.getFax(),
                reqContact.getPhoneNumbers()
        );
        Contact saveContact = contactRepository.save(contact);
        return saveContact;
    }


    public Contact editContact(UUID id, ReqContact reqContact){
        if (contactRepository.existsByEmailEqualsIgnoreCaseAndIdNot(reqContact.getEmail(), id)) {
            throw new  ExistException("Bunday email mavjud");
        }
        Optional<Contact> byId = contactRepository.findById(id);
        if (byId.isPresent()){
            Contact contact = byId.get();
            contact.setDistrict(districtRepository.findById(reqContact.getDistrictId()).orElseThrow(() -> new ResourceAccessException("GetDistrict")));
            contact.setEmail(reqContact.getEmail());
            contact.setFax(reqContact.getFax());
            contact.setPhoneNumbers(reqContact.getPhoneNumbers());
            return contactRepository.save(contact);
        }
        throw new ExistException("Bunday contact yuq");
    }

    public ResContact getContact(UUID id){
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()){
            Contact contact = optionalContact.get();
            return new ResContact(
                    contact.getDistrict(),
                    contact.getEmail(),
                    contact.getFax(),
                    contact.getPhoneNumbers()
            );
        }
        return null;
    }
}
