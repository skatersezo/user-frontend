import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs/index";

@Injectable()
export class UserService {

  private baseURL: string = 'http://localhost:8080';

  constructor(private http: Http) { }

  getUsers() {
    const url = `${this.baseURL}/api/user/all`;
    return this.http.get(url, { withCredentials: true });
  }

  getPrimaryTransactionList(username: string) {
    const url = `${this.baseURL}/api/user/primary/transaction?username=${username}`;
    return this.http.get(url, { withCredentials: true });
  }

  getSavingsTransactionList(username: string) {
    const url = `${this.baseURL}/api/user/primary/transaction?username=${username}`;
    return this.http.get(url, { withCredentials: true });
  }

  enableUser(username: string) {
    const url = `${this.baseURL}/api/user/${username}/enable`;
    return this.http.get(url, { withCredentials: true });
  }

  disableUser(username: string) {
    const url = `${this.baseURL}/api/user/${username}/disable`;
    return this.http.get(url, { withCredentials: true });
  }
}
