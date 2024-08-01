package com.microserv.springcloud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microserv.springcloud.Entity.usuario;

@Repository
public interface usuarioRepository extends JpaRepository<usuario, Integer>{

}