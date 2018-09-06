import { Injectable } from '@angular/core';
import {Http, Headers} from "@angular/http";
import {Observable} from "rxjs/index";

@Injectable()
export class LoginService {

  constructor(private http: Http) { }

  sendCredential(username: string, password: string): Observable<any> {
    const url: string = 'http://localhost:8080/index';
    const params: string = `username=${username}&password=${password}`;
    let headers = new Headers(
      {
        'Content-Type': 'application/x-www-form-urlencoded'
        // 'Access-Control-Allow-Credentials' : true
      });
    return this.http.post(url, params,{headers: headers, withCredentials : true});
  }

  logout(): Observable<any> {
    const url = 'http://localhost:8080/logout';
    return this.http.get(url, { withCredentials: true });
  }
}
