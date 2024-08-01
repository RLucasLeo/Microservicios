package com.microserv.springcloud.autoservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microserv.springcloud.autoservice.Entity.Auto;
import java.util.List;


public interface AutoRepository extends JpaRepository<Auto, Integer> {

    List<Auto> findByUsuarioId(Integer usuarioId);
}
