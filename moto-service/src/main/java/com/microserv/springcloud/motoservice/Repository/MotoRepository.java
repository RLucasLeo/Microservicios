package com.microserv.springcloud.motoservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microserv.springcloud.motoservice.Entity.Moto;

public interface MotoRepository extends JpaRepository<Moto, Integer>{

    List<Moto> findByUsuarioId(Integer usuarioId);

}
