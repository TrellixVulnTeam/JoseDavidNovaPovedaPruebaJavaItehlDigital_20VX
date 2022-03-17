/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cursos.SpringRestFull.Controllers;

import com.cursos.SpringRestFull.DAO.DescuentosDAO;
import com.cursos.SpringRestFull.Models.Cursos;
import com.cursos.SpringRestFull.Models.Descuentos;
import com.cursos.SpringRestFull.Models.Modalidades;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/descuentos")
public class DescuentosController {

    @Autowired
    private DescuentosDAO descuentosDao;

    @GetMapping
    public ResponseEntity<Object> index() {

        List<Descuentos> descuentos = new ArrayList<>();

        descuentosDao.findAll().forEach(descuentos::add);

        if (descuentos.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        return new ResponseEntity<>(descuentos, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Descuentos> store(@RequestBody Descuentos descuentos) {

        try {

            Descuentos __descuentos = descuentosDao.save(new Descuentos(
                    descuentos.getNombre(),
                    descuentos.getModalidad_id(),
                    descuentos.getPais(),
                    descuentos.getDescuento(),
                    descuentos.getCurso_id(),
                    descuentos.getFecha_final()
            ));

            return new ResponseEntity<>(__descuentos, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Descuentos> update(@PathVariable Long id, @RequestBody Descuentos descuentos) {

        Optional<Descuentos> data = descuentosDao.findById(id);

        if (data.isPresent()) {

            Descuentos __descuentos = data.get();

            __descuentos.setNombre(descuentos.getNombre());
            __descuentos.setModalidad_id(descuentos.getModalidad_id());
            __descuentos.setPais(descuentos.getPais());
            __descuentos.setDescuento(descuentos.getDescuento());
            __descuentos.setCurso_id(descuentos.getCurso_id());
            __descuentos.setFecha_final(descuentos.getFecha_final());

            return new ResponseEntity<>(descuentosDao.save(__descuentos), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {

        try {
            descuentosDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @GetMapping("/filter/{id}")
    public ResponseEntity<Descuentos> byId(@PathVariable Long id) {
        
        Optional<Descuentos> data = descuentosDao.findById(id);
        
        if(data.isPresent()){
            
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
            
        }else{
            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        }

    } 

}
