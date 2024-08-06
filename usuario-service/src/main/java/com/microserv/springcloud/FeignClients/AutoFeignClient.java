package com.microserv.springcloud.FeignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microserv.springcloud.Models.Auto;

@FeignClient(name = "auto-service")
@RequestMapping("/Carro")
public interface AutoFeignClient {

    @PostMapping
    public Auto save(@RequestBody Auto auto);

     @GetMapping("/usuario/{usuarioId}")
    public List<Auto> getAutos(@PathVariable("usuarioId") Integer usuarioId);

}
