import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cursos } from '../components/cursos/cursosModel';

@Injectable({
  providedIn: 'root'
})
export class CursoserviceService {

  public url = 'http://127.0.0.1:8080/api/cursos';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {
  }

  listarCursos(): Observable<Cursos[]> {
    return this.http.get<Cursos[]>(this.url, this.httpOptions);
  }

  registro(cursos: Cursos): Observable<any> {
    let body = JSON.stringify(cursos);
    let params = body;
    return this.http.post(this.url, params, this.httpOptions);

  }

  buscarCurso(id: String): Observable<Cursos[]> {
    return this.http.get<Cursos[]>(this.url + "/filter/" + id, this.httpOptions);
  }

  eliminarCurso(id: String) {
    return this.http.delete(this.url + "/" +id, this.httpOptions);
  }

  actualizarCurso(cursos: Cursos, id: String): Observable<any>{
    let body = JSON.stringify(cursos);
    let params = body;
    return this.http.put(this.url+"/"+id, params, this.httpOptions );

  }

}
