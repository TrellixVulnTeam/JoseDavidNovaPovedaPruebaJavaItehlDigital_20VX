/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cursos.SpringRestFull.Controllers;

import com.cursos.SpringRestFull.DAO.ModalidadesDAO;
import com.cursos.SpringRestFull.Models.Modalidades;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/modalidades")
public class ModalidadesController {

    @Autowired
    private ModalidadesDAO modalidadesDao;

    @GetMapping
    public ResponseEntity<Object> index() {

        List<Modalidades> modalidades = new ArrayList<Modalidades>();

        modalidadesDao.findAll().forEach(modalidades::add);

        if (modalidades.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(modalidades, HttpStatus.OK);

    }

    @GetMapping("/filter/{id}")
    public ResponseEntity<Modalidades> byId(@PathVariable Long id) {

        Optional<Modalidades> data = modalidadesDao.findById(id);

        if (data.isPresent()) {

            return new ResponseEntity<>(data.get(), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

}
