import { Component, OnInit } from '@angular/core';
import { CursoserviceService } from 'src/app/services/cursoservice.service';
import { DescuentoService } from 'src/app/services/descuento.service';
import { ModalidadService } from 'src/app/services/modalidad.service';
import { Cursos } from '../cursos/cursosModel';
import { Modalidades } from '../modalidades/modalidadesModel';
import { Descuentos } from './descuentosModel';
import * as $ from 'jquery';

@Component({
  selector: 'app-descuentos',
  templateUrl: './descuentos.component.html',
  styleUrls: ['./descuentos.component.css']
})
export class DescuentosComponent implements OnInit {

  public modalidades: Modalidades;

  public modalidadLista: any = [];

  public cursos: Cursos;

  public cursoLista: any = [];

  public descuento: Descuentos;

  public descuentoLista: any = [];

  public descuentoPorId: any = [];

  public t: String = '';

  constructor(private cursoService: CursoserviceService, private modalidadService: ModalidadService, private descuentosService: DescuentoService) {
    this.modalidades = new Modalidades('', '', '');
    this.cursos = new Cursos('', '', '', '', '', '', '');
    this.descuento = new Descuentos('','', '', '', '', '', '');
  }

  ngOnInit(): void {
    this.listarDescuento();
    this.listarCurso();
    this.listarModalidad();

    //falta agregar el listener del select para poner en automático los valores de descuento dependiendo la modalidad



  }

  listarDescuento() {
    this.descuentosService.listarDescuentos().subscribe(Response => {
      this.descuentoLista = Response;
    })
  }

  listarCurso(){
    this.cursoService.listarCursos().subscribe(Response => {
      this.cursoLista = Response;
    });
  }

  listarModalidad(){
    this.modalidadService.listarModalidad().subscribe(Response =>{
      this.modalidadLista = Response;
    });
  }

  pasoDeDatos(id: String) {

    this.descuentosService.buscarDescuento(id).subscribe(Response => {

      this.descuentoPorId = Response;
      this.descuento.id = this.descuentoPorId.id;

      $('#recipient-nombree').val(this.descuentoPorId.nombre);
      this.descuento.nombre = this.descuentoPorId.nombre;

      this.descuento.modalidad_id = this.descuentoPorId.modalidad_id;
      $('#recipient-modalidad_ide').val(this.descuentoPorId.modalidad_id);

      this.descuento.pais = this.descuentoPorId.pais;
      $('#recipient-paise').val(this.descuentoPorId.pais);

      this.descuento.descuento = this.descuentoPorId.descuento;
      $("#recipient-descuentose").val(this.descuentoPorId.descuento);

      this.descuento.curso_id = this.descuentoPorId.curso_id;

      $('#recipient-curso_ide').val(this.descuentoPorId.curso_id);

      this.descuento.fecha_final = this.descuentoPorId.fecha_final;
      $('#recipient-fecha_finale').val(this.descuentoPorId.fecha_final);

    });


  }

  borrarDescuentos(id: String) {

    let op = confirm("¿Esta Seguro de Eliminar este Descuento?");

    if (op) {

      this.descuentosService.eliminarDescuento(id).subscribe(Response => {
        this.listarDescuento();
      });

    }

  }

  onSubmit(form: any){

    this.descuentosService.registro(this.descuento).subscribe(Response =>{
      form.reset();
      this.listarDescuento();
    },
    error =>{
      alert("Error");
      console.log(error);
    });

  }

  editarDescuento(form: any) {

    console.log(this.descuento);

    var op = confirm("El registro sera cambiado, ¿Desea continuar?");

    if (op) {

      this.descuentosService.actualizarDescuento(this.descuento, this.descuento.id).subscribe(Response => {
        form.reset();
        this.listarDescuento();
      });

    }
  }

}
