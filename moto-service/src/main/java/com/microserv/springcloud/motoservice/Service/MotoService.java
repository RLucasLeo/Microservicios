package com.microserv.springcloud.motoservice.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microserv.springcloud.motoservice.Entity.Moto;
import com.microserv.springcloud.motoservice.Repository.MotoRepository;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepo;

    public List<Moto> getAll(){
        return motoRepo.findAll();
    }

    public Moto getMotobyId(Integer id){
        return motoRepo.findById(id).orElse(null);
    }

    public Moto save(Moto moto){
        Moto nuevoMoto = motoRepo.save(moto);
        return nuevoMoto;
    }

    public List<Moto> byUsuarioId(Integer id){
        return motoRepo.findByUsuarioId(id);
    }
}
