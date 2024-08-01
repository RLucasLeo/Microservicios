package com.microserv.springcloud.motoservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microserv.springcloud.motoservice.Entity.Moto;
import com.microserv.springcloud.motoservice.Service.MotoService;

@RestController
@RequestMapping("/Moto")
public class MotoController {

    @Autowired
    private MotoService motoServ;

    @GetMapping()
    public ResponseEntity<List<Moto>> listarMotos(){
        List<Moto> motos = motoServ.getAll();

        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> obtenerMoto(@PathVariable("id") int id){
        Moto moto = motoServ.getMotobyId(id);
        if(moto == null){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(moto);
    }

    @PostMapping()
    public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto) {
        Moto nuevaMoto = motoServ.save(moto); 
        return ResponseEntity.ok(nuevaMoto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotosPorUsuarioId(@PathVariable("usuarioId") Integer id) {
        List<Moto> motos = motoServ.byUsuarioId(id);
        if(motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motos);
    }
}
