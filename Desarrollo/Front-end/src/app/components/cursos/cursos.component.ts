import { Component, OnInit } from '@angular/core';
import { CursoserviceService } from 'src/app/services/cursoservice.service';
import { ModalidadService } from 'src/app/services/modalidad.service';
import { Modalidades } from '../modalidades/modalidadesModel';
import { Cursos } from './cursosModel';
import * as $ from 'jquery';

@Component({
  selector: 'app-cursos',
  templateUrl: './cursos.component.html',
  styleUrls: ['./cursos.component.css']
})
export class CursosComponent implements OnInit {

  public cursos: Cursos;

  public modalidad: Modalidades;

  public cursoLista: any = [];

  public ModalidadLista: any = [];

  public cursoPorId: any = []

  constructor(private cursosService: CursoserviceService, private modalidadService: ModalidadService) {
    this.cursos = new Cursos('', '', '', '', '', '', '');
    this.modalidad = new Modalidades('', '', '');
  }

  ngOnInit(): void {
    this.listarCursos();
    this.listarModalidad();
    //falta agregar el filtro de búsqueda, usar "pipe"
  }

  listarCursos() {

    this.cursosService.listarCursos().subscribe(Response => {
      this.cursoLista = Response;
    });

  }

  listarModalidad() {

    this.modalidadService.listarModalidad().subscribe(Response => {
      this.ModalidadLista = Response;
    });

  }

  onSubmit(form: any) {
    this.cursosService.registro(this.cursos).subscribe(
      Response => {
        form.reset();
        this.listarCursos();
      },
      error => {
        alert("Error");
      }
    );
  }

  pasoDeDatos(id: String) {

    this.cursosService.buscarCurso(id).subscribe(Response => {

      this.cursoPorId = Response;
      this.cursos.id = this.cursoPorId.id;
      $('#recipient-nombree').val(this.cursoPorId.nombre);
      this.cursos.nombre = this.cursoPorId.nombre;
      $("#recipient-costoe").val(this.cursoPorId.costo);
      this.cursos.costo = this.cursoPorId.costo;
      $('textarea#message-descriptione').val(this.cursoPorId.description);
      this.cursos.description = this.cursoPorId.description;
      $("#recipient-horase").val(this.cursoPorId.horas);
      this.cursos.horas = this.cursoPorId.horas;
      $("#recipient-dirigido_ae").val(this.cursoPorId.dirigido_a);
      this.cursos.dirigido_a = this.cursoPorId.dirigido_a;
      this.cursos.modalidad_id = this.cursoPorId.modalidad_id;

    });


  }

  borrarCurso(id: String) {

    let op = confirm("¿Esta Seguro de Eliminar este Curso?");

    if (op) {

      this.cursosService.eliminarCurso(id).subscribe(Response => {
        this.listarCursos();
      });

    }

  }

  editarCurso(form: any) {
    var op = confirm("El registro sera cambiado, ¿Desea continuar?");

    if (op) {

      this.cursosService.actualizarCurso(this.cursos, this.cursos.id).subscribe(Response => {
        form.reset();
        this.listarCursos();
      });

    }
  }

  filtroJS(){

    jQuery("#filtro").keyup(function () {
      if (jQuery(this).val() != "") {
        jQuery("#tablaCursos tbody>tr").hide();
        jQuery("#tablacursos td:contiene-palabra('" + jQuery(this).val() + "')").parent("tr").show();
      }
      else {
        jQuery("#tablaCursos tbody>tr").show();
      }
    });

    jQuery.extend(jQuery.expr[":"],
      {
        "contiene-palabra": function (elem: { textContent: any; innerText: any; }, i: any, match: any[], array: any) {
          return (elem.textContent || elem.innerText || jQuery(elem).text() || "").toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
        }
      });
  }

}
