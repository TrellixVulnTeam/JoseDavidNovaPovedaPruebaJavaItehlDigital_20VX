
package com.cursos.SpringRestFull.DAO;

import com.cursos.SpringRestFull.Models.Cursos;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CursosDAO extends JpaRepository<Cursos, Long>{
    
}
