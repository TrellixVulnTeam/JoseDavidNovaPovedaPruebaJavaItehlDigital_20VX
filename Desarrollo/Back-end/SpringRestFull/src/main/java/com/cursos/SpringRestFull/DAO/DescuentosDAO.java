/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cursos.SpringRestFull.DAO;

import com.cursos.SpringRestFull.Models.Descuentos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author david
 */
public interface DescuentosDAO extends JpaRepository<Descuentos, Long>{
    
}
