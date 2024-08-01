package com.microserv.springcloud.autoservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microserv.springcloud.autoservice.Entity.Auto;
import com.microserv.springcloud.autoservice.Service.AutoService;


@RestController
@RequestMapping("/Auto")
public class AutoController {

    @Autowired
    private AutoService autoServ;

    @GetMapping()
    public ResponseEntity<List<Auto>> listarAutos(){
        List<Auto> autos = autoServ.getAll();

        if(autos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(autos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auto> obtenerAuto(@PathVariable("id") int id){
        Auto auto = autoServ.getAutobyId(id);
        if(auto == null){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(auto);
    }

    @PostMapping()
    public ResponseEntity<Auto> guardarAuto(@RequestBody Auto auto) {
        Auto nuevoAuto = autoServ.save(auto); 
        return ResponseEntity.ok(nuevoAuto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Auto>> listarAutosPorUsuarioId(@PathVariable("usuarioId") Integer id) {
        List<Auto> autos = autoServ.byUsuarioId(id);
        if(autos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(autos);
    }
    
}
