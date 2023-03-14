package com.usta.democrud.service;
import java.util.List;
import java.util.Optional;

import com.usta.democrud.model.Persona;
import com.usta.democrud.repository.PersonaResporitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    @Autowired
    private PersonaResporitory personaResporitory;


    public Persona create (Persona persona) {
        return personaResporitory.save(persona);
    }

    public List<Persona> getAllPersonas (){
        return personaResporitory.findAll();
    }

    public void delete (Persona persona) {
        personaResporitory.delete(persona);
    }

    public Optional<Persona> findById (Long id) {
        return personaResporitory.findById(id);
    }

    public void deleteById (Long id) {
        personaResporitory.deleteById(id);
    }

    public List<String> findAllByCorreo(String correo){
        return personaResporitory.findAllByCorreo(correo);
    }

    public String findAllByCorreoDos(String correo){
        return personaResporitory.findAllByCorreoDos(correo);
    }
}
