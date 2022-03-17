import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Descuentos } from '../components/descuentos/descuentosModel';

@Injectable({
  providedIn: 'root'
})
export class DescuentoService {

  public url = 'http://127.0.0.1:8080/api/descuentos';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient){}

  listarDescuentos(): Observable<Descuentos[]>{

    console.log(this.http.get<Descuentos[]>(this.url, this.httpOptions));

    return this.http.get<Descuentos[]>(this.url, this.httpOptions);

  }

  registro(descuentos: Descuentos): Observable<any>{

    let body = JSON.stringify(descuentos);
    let params = body;
    console.log(params);
    return this.http.post(this.url, params, this.httpOptions);

  }

  buscarDescuento(id: String): Observable<Descuentos[]> {
    return this.http.get<Descuentos[]>(this.url + "/filter/" + id, this.httpOptions);
  }

  eliminarDescuento(id: String) {
    return this.http.delete(this.url + "/" +id, this.httpOptions);
  }

  actualizarDescuento(Descuentos: Descuentos, id: String): Observable<any>{
    let body = JSON.stringify(Descuentos);
    let params = body;
    return this.http.put(this.url+"/"+id, params, this.httpOptions );

  }


}
