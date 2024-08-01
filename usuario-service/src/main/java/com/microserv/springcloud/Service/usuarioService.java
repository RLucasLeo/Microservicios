package com.microserv.springcloud.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microserv.springcloud.Entity.usuario;
import com.microserv.springcloud.FeignClients.AutoFeignClient;
import com.microserv.springcloud.FeignClients.MotoFeignClient;
import com.microserv.springcloud.Models.Auto;
import com.microserv.springcloud.Models.Moto;
import com.microserv.springcloud.Repository.usuarioRepository;

@Service
public class usuarioService {

    @Autowired
    private AutoFeignClient autoFeign;
    @Autowired
    private MotoFeignClient motoFeign;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private usuarioRepository usuRepo;
//Aqui utilizamos RestTemplate
    public List<Auto> getAutos(Integer usuarioId){
        @SuppressWarnings("unchecked")
        List<Auto> autos = restTemplate.getForObject("http://localhost:8081/Auto/usuario/" + usuarioId, List.class);
        return autos;
    }

    public List<Moto> getMotos(Integer usuarioId){
        @SuppressWarnings("unchecked")
        List<Moto> motos = restTemplate.getForObject("http://localhost:8082/Moto/usuario/" + usuarioId, List.class);
        return motos;
    }
//Aqui utilizamos FeignClient
    public Auto saveAuto(Integer usuarioId, Auto auto){
        auto.setUsuarioId(usuarioId);
        Auto nuevoAuto = autoFeign.save(auto);
        return nuevoAuto;
    }

    public Moto saveMoto(Integer usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeign.save(moto);
        return nuevaMoto;
    }

    public Map<String, Object> getUsuariosAndVehiculos(Integer usuarioId){
        Map<String, Object> resultado = new HashMap<>();
        usuario usu = usuRepo.findById(usuarioId).orElse(null);

        if(usu == null){
            resultado.put("Mensaje", "El usuario no existe");
            return resultado;
        }
        resultado.put("Usuario", usu);
        List<Auto> autos = autoFeign.getAutos(usuarioId);
        if(autos.isEmpty()){
            resultado.put("Autos", "El usuario no tiene autos");
        }else{
            resultado.put("Autos", autos);
        }

        List<Moto> motos = motoFeign.getMotos(usuarioId);
        if(motos.isEmpty()){
            resultado.put("Motos", "El usuario no tiene motos");
        }else{
            resultado.put("Motos", motos);
        }
        return resultado;
    }


//metodos de usuario---
    public List<usuario> getAll(){
        return usuRepo.findAll();
    }

    public usuario getUsuariobyId(int id){
        return usuRepo.findById(id).orElse(null);
    }

    public usuario save(usuario usu){
        usuario nuevoUsuario = usuRepo.save(usu);
        return nuevoUsuario;
    }
}
