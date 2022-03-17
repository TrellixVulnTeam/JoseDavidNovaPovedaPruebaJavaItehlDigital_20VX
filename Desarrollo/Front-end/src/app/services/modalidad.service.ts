import { HttpClient, HttpHeaders } from '@angular/common/http';
import { identifierModuleUrl } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Modalidades } from '../components/modalidades/modalidadesModel';

@Injectable({
  providedIn: 'root'
})
export class ModalidadService {

  public url = 'http://127.0.0.1:8080/api/modalidades';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http : HttpClient) { }

  listarModalidad(): Observable<Modalidades[]> {
    return this.http.get<Modalidades[]>(this.url, this.httpOptions);
  }

  buscarModalidad(id: String): Observable<Modalidades[]> {
    return this.http.get<Modalidades[]>(this.url + "/filter/" + id, this.httpOptions);
  }

}
