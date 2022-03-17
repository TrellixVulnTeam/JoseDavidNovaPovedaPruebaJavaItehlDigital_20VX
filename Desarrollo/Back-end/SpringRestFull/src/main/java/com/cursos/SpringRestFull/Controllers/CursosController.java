/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cursos.SpringRestFull.Controllers;

import com.cursos.SpringRestFull.DAO.CursosDAO;
import com.cursos.SpringRestFull.Models.Cursos;
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
@RequestMapping("/api/cursos")
public class CursosController {

    @Autowired
    private CursosDAO cursosDao;

    @GetMapping
    public ResponseEntity<Object> index() {

        List<Cursos> cursos = new ArrayList<Cursos>();

        cursosDao.findAll().forEach(cursos::add);

        if (cursos.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }

        return new ResponseEntity<>(cursos, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Cursos> store(@RequestBody Cursos cursos) {

        try {

            Cursos __cursos = cursosDao.save(new Cursos(
                    cursos.getNombre(),
                    cursos.getCosto(),
                    cursos.getDescription(),
                    cursos.getHoras(),
                    cursos.getDirigido_a(),
                    cursos.getModalidad_id()
            ));

            return new ResponseEntity<>(__cursos, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Cursos> update(@PathVariable Long id, @RequestBody Cursos cursos) {

        Optional<Cursos> data = cursosDao.findById(id);

        if (data.isPresent()) {

            Cursos __cursos = data.get();

            __cursos.setNombre(cursos.getNombre());
            __cursos.setCosto(cursos.getCosto());
            __cursos.setDescription(cursos.getDescription());
            __cursos.setHoras(cursos.getHoras());
            __cursos.setDirigido_a(cursos.getDirigido_a());
            __cursos.setModalidad_id(cursos.getModalidad_id());

            return new ResponseEntity<>(cursosDao.save(__cursos), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {

        try {
            cursosDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/filter/{id}")
    public ResponseEntity<Cursos> byId(@PathVariable Long id) {
        
        Optional<Cursos> data = cursosDao.findById(id);
        
        if(data.isPresent()){
            
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
            
        }else{
            
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        }

    }

}
