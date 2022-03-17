/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cursos.SpringRestFull.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbldescuentos")
@Getter
@Setter
@ToString
public class Descuentos {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres")
    private String nombre;

    @Column(name = "modalidad")
    private String modalidad_id;

    @Column(name = "pais")
    private String pais;

    @Column(name = "descuento")
    private String descuento;

    @Column(name = "curso")
    private String curso_id;

    @Column(name = "fecha_final")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha_final;

    public Descuentos() {
    }

    public Descuentos(String nombre, String modalidad_id, String pais, String descuento, String curso_id, Date fecha_final) {
        this.nombre = nombre;
        this.modalidad_id = modalidad_id;
        this.pais = pais;
        this.descuento = descuento;
        this.curso_id = curso_id;
        this.fecha_final = fecha_final;
    }

   
    
}
