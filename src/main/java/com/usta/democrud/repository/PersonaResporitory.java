package com.usta.democrud.repository;
import com.usta.democrud.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonaResporitory extends JpaRepository<Persona, Long>{

    @Query("SELECT p.nombre, p.correo FROM Persona p WHERE p.correo = ?1")
    public String findAllByCorreoDos(String Corrreo);



    @Query("SELECT p.nombre, p.correo FROM Persona p WHERE p.correo = ?1")
    public List<String> findAllByCorreo(String Corrreo);
}