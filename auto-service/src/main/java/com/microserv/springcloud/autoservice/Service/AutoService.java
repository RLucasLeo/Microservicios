package com.microserv.springcloud.autoservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microserv.springcloud.autoservice.Entity.Auto;
import com.microserv.springcloud.autoservice.Repository.AutoRepository;

@Service
public class AutoService {

    @Autowired
    private AutoRepository autoRepo;

    public List<Auto> getAll(){
        return autoRepo.findAll();
    }

    public Auto getAutobyId(Integer id){
        return autoRepo.findById(id).orElse(null);
    }

    public Auto save(Auto auto){
        Auto nuevoAuto = autoRepo.save(auto);
        return nuevoAuto;
    }

    public List<Auto> byUsuarioId(Integer id){
        return autoRepo.findByUsuarioId(id);
    }
}
