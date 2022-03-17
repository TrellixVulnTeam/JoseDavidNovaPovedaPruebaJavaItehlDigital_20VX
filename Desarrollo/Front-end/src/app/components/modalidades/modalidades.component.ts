import { Component, OnInit } from '@angular/core';
import { ModalidadService } from 'src/app/services/modalidad.service';
import { Cursos } from '../cursos/cursosModel';
import { Modalidades } from './modalidadesModel';

@Component({
  selector: 'app-modalidades',
  templateUrl: './modalidades.component.html',
  styleUrls: ['./modalidades.component.css']
})
export class ModalidadesComponent implements OnInit {

  public modalidades : Modalidades;
  public modalidadesList : any = [];

  constructor(private modalidad : ModalidadService) {
    this.modalidades = new Modalidades ('','','');
   }

  ngOnInit(): void {
    this.listarModalidades();
  }

  listarModalidades(){
    this.modalidad.listarModalidad().subscribe(Response =>{
      this.modalidadesList = Response;
    });
  }

}
