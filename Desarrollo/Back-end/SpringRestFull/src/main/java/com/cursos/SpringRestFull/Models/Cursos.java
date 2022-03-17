/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cursos.SpringRestFull.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tblcursos")
@Getter
@Setter
@ToString
public class Cursos {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "costo")
    private float costo;

    @Column(name = "description")
    private String description;

    @Column(name = "horas")
    private int horas;

    @Column(name = "dirigido_a")
    private String dirigido_a;

    @Column(name = "modalidad")
    private String modalidad_id;

    public Cursos() {
    }

    public Cursos(String nombre, float costo, String description, int horas, String dirigido_a, String modalidad) {
        this.nombre = nombre;
        this.costo = costo;
        this.description = description;
        this.horas = horas;
        this.dirigido_a = dirigido_a;
        this.modalidad_id = modalidad;
    }

   

    

    
}
