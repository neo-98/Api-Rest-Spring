package com.usta.democrud.rest;
import java.net.URI;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.usta.democrud.model.Persona;
import com.usta.democrud.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200",  maxAge = 3600)
@RequestMapping ("/api/persona/") //--> ruta para acceder
public class PersonaREST {

    @Autowired // le dice al programa que existe una entidad, un r
    // epositorio y un servicio
    private PersonaService personaService;

    @PostMapping("/crear")
    private ResponseEntity<Persona> guardar (@RequestBody Persona persona){
        Persona temporal = personaService.create(persona);

        try {
            return ResponseEntity.created(new URI("/api/persona"+temporal.getIdPersona())).body(temporal);

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // @GetMapping ---> Ruta general
    @GetMapping("/listar")//ruta personalizada
    private ResponseEntity<List<Persona>> listarTodasLasPersona (){
        return ResponseEntity.ok(personaService.getAllPersonas());
    }

    @DeleteMapping("/eliminar")
    private ResponseEntity<Void> eliminarPersona (@RequestBody Persona persona){
        personaService.delete(persona);
        return ResponseEntity.ok().build();
    }

    @GetMapping (value = "findby/{id}")
    private ResponseEntity<Optional<Persona>> listarPersonasPorID (@PathVariable ("id") Long id){
        return ResponseEntity.ok(personaService.findById(id));
    }

    @DeleteMapping(value = "deleteby/{id}")
    private ResponseEntity<Void> eliminarPersonaById (@PathVariable ("id") Long id){
        personaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/editar")
    private ResponseEntity<Persona> editar (@RequestBody Persona persona){
        Persona temporal = personaService.create(persona);

        try {
            return ResponseEntity.created(new URI("/api/persona"+temporal.getIdPersona())).body(temporal);

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/listarCorreoJSON1/{correo}")//ruta personalizada
    private ResponseEntity<String> listarPersonaCorreoJSON1(@PathVariable ("correo") String correo){
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body("{\"name\": \"mary\", \"apellido\": \"test2\"}");
    }

    @GetMapping("/listarCorreoJSON3/{correo}")//ruta personalizada
    private ResponseEntity<String> listarPersonaCorreoJSON3(@PathVariable ("correo") String correo){
        String []vect = personaService.findAllByCorreoDos(correo).split(",");
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body("{\"name\": " + vect[0] + ", \"apellido\": " + vect[1]   + "}");
    }

    @GetMapping("/listarCorreoJSON2/{correo}")//ruta personalizada
    private ResponseEntity<String> listarPersonaCorreoJSON2(@PathVariable ("correo") String correo){

        List<String> listStrings = personaService.findAllByCorreo(correo);

        Gson objGson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(objGson.toJson(listStrings));

        return ResponseEntity.ok(objGson.toJson(listStrings));

    }


    @GetMapping("/listarCorreoString/{correo}")//ruta personalizada
    private ResponseEntity<String> listarPersonaCorreoString(@PathVariable ("correo") String correo) {
        return ResponseEntity.ok(personaService.findAllByCorreoDos(correo));
    }
}
