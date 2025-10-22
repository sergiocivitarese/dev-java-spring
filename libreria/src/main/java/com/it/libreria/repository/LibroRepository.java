package com.it.libreria.repository;

import com.it.libreria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

                    //ricorda l'ordine nel JPA è <Entity, Primary_Key>
public interface LibroRepository extends JpaRepository<Libro, Integer> {

}
