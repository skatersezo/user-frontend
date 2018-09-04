import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/index";

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) { }

  /**
   * DEFINIR RETORNO DE TIPO PARA AMBOS METODOS:
   *  Observable<tipo?>
   */

  sendCredential(username: string, password: string): Observable<any> {
    const url = 'http:/localhost:8080/index';
    const params = `username=${username}&password=${password}`;
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/x-www-form-urlencoded',
        'Access-Control-Allow-Credentials' : 'true'
      })
    };
    return this.http.post(url, params, httpOptions);
  }

  /**
   * DEFINIR RETORNO DE TIPO PARA AMBOS METODOS:
   *  Observable<tipo?>
   */

  logout() {
    const url = 'http://localhost:8080/logout';
    return this.http.get(url, { withCredentials: true });
  }
}
