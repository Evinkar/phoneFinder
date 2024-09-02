package ru.lukyanov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.lukyanov.model.PhoneNumber;
import ru.lukyanov.repository.NumberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NumberService {
    @Autowired
    NumberRepository numberRepository;

    public PhoneNumber savePhoneNumber(PhoneNumber phoneNumber) {
        return numberRepository.save(phoneNumber);
    }

    public Optional<PhoneNumber> finById(Long id){
        return numberRepository.findById(id);
    }

    public List<PhoneNumber> findAll(){
        return numberRepository.findAll();
    }

    public void delete (PhoneNumber phoneNumber){
        numberRepository.delete(phoneNumber);
    }

    public PhoneNumber saveOrUpbatePhoneNumber(PhoneNumber phoneNumber) {
        if (finById(phoneNumber.getNumber()).isPresent()){
         delete(phoneNumber);
        }
        return numberRepository.save(phoneNumber);
    }

}
