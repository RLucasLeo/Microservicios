package com.microserv.springcloud.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microserv.springcloud.Entity.usuario;
import com.microserv.springcloud.Models.Auto;
import com.microserv.springcloud.Models.Moto;
import com.microserv.springcloud.Service.usuarioService;
import com.usuario.service.modelos.Carro;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/usuario")
public class usuarioController {

    @Autowired
    private usuarioService usuService;

    @GetMapping()
    public ResponseEntity<List<usuario>> listarUsuario() {
        List<usuario> usuarios = usuService.getAll();

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<usuario> obtenerUsuario(@PathVariable("id") int id) {
        usuario usu = usuService.getUsuariobyId(id);
        if (usu == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usu);
    }

    @PostMapping()
    public ResponseEntity<usuario> guardarUsuario(@RequestBody usuario usu) {
        usuario nuevoUsu = usuService.save(usu);
        return ResponseEntity.ok(nuevoUsu);
    }

    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallbackGetCarros")
    @GetMapping("/Carros/{usuarioId}")
    public ResponseEntity<List<Auto>> listarCarros(@PathVariable("usuarioId") Integer id) {
        usuario usu = usuService.getUsuariobyId(id);
        if (usu == null) {
            return ResponseEntity.notFound().build();
        }

        List<Auto> autos = usuService.getAutos(id);

        return ResponseEntity.ok(autos);
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "fallbackGetMotos")
    @GetMapping("/Motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") Integer id) {
        usuario usu = usuService.getUsuariobyId(id);
        if (usu == null) {
            return ResponseEntity.notFound().build();
        }

        List<Moto> motos = usuService.getMotos(id);

        return ResponseEntity.ok(motos);
    }

    @CircuitBreaker(name = "carrosCB", fallbackMethod = "fallbackSaveCarros")
    @PostMapping("/Carro/{usuarioId}")
    public ResponseEntity<Auto> guardarCarro(@PathVariable("usuarioId") Integer usuarioId, @RequestBody Auto auto) {
        Auto nuevoAuto = usuService.saveAuto(usuarioId, auto);
        return ResponseEntity.ok(nuevoAuto);
    }

    @CircuitBreaker(name = "motosCB", fallbackMethod = "fallbackGetMotos")
    @PostMapping("/Moto/{usuarioId}")
    public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId") Integer usuarioId, @RequestBody Moto moto) {
        Moto nuevaMoto = usuService.saveMoto(usuarioId, moto);
        return ResponseEntity.ok(nuevaMoto);
    }

    @CircuitBreaker(name = "todosCB", fallbackMethod = "fallbackGetTodos")
    @GetMapping("/todos/{usuarioId}")
    public ResponseEntity<Map<String, Object>> listarTodosLosVehiculos(@PathVariable("usuarioId") Integer usuarioId) {
        Map<String, Object> resultado = usuService.getUsuariosAndVehiculos(usuarioId);
        return ResponseEntity.ok(resultado);
    }

    @SuppressWarnings({ "unused", "unchecked", "rawtypes" })
    private ResponseEntity<List<Carro>> fallbackGetCarros(@PathVariable("usuarioId") Integer id, RuntimeException exception){
        return new ResponseEntity("El usuario: "+ id + " tiene los carros en mantenimiento", HttpStatus.OK);
    }

    @SuppressWarnings({ "unused", "unchecked", "rawtypes" })
    private ResponseEntity<List<Carro>> fallbackSaveCarros(@PathVariable("usuarioId") Integer id, @RequestBody Carro carro, RuntimeException exception){
        return new ResponseEntity("El usuario: "+ id + " no tiene suficiente dinero para guardar el auto en el garage", HttpStatus.OK);
    }

    @SuppressWarnings({ "unused", "unchecked", "rawtypes" })
    private ResponseEntity<List<Moto>> fallbackGetMotos(@PathVariable("usuarioId") Integer id, RuntimeException exception){
        return new ResponseEntity("El usuario: "+ id + " tiene las motocicletas en mantenimiento", HttpStatus.OK);
    }

    @SuppressWarnings({ "unused", "unchecked", "rawtypes" })
    private ResponseEntity<List<Moto>> fallbackSaveMotos(@PathVariable("usuarioId") Integer id, @RequestBody Moto moto, RuntimeException exception){
        return new ResponseEntity("El usuario: "+ id + " no tiene suficiente dinero para guardar la motocicleta en el garage", HttpStatus.OK);
    }

    @SuppressWarnings({ "unused", "unchecked", "rawtypes" })
    private ResponseEntity<List<Moto>> fallbackGetTodos(@PathVariable("usuarioId") Integer id, RuntimeException exception){
        return new ResponseEntity("El usuario: "+ id + " tiene todos los vehiculos en mantenimiento", HttpStatus.OK);
    }


}
