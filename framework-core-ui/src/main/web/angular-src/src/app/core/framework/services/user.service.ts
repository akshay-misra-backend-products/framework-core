import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

import { User } from '../models/user';
import {Observable, Subject} from "rxjs/index";

const TOKEN_KEY = 'AuthToken';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private authenticatedSubject = new Subject<any>();
  private routeAuthSubject = new Subject<boolean>();
  constructor(private http: HttpClient, private router: Router) { }
  private authServer = 'http://localhost:8080';


  register(user: User) {
    return this.http.post(`${this.authServer}/users/register`, user);
  }

  login(username: string, password: string) {
    const credentials = {username: username, password: password};
    return this.http.post<any>(`${this.authServer}/token/generate-token`, credentials)
      .pipe(map(user => {
        // login successful if there's a jwt token in the response
        if (user && user.token) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem(TOKEN_KEY, user.token);
          this.authenticate(true);
        }
        return user;
      }));
  }

  getCurrentUser(): Observable<User> {
    return this.http.get(`${this.authServer}/users/current`);
  }

  logout() {
    // not logged in so redirect to login page with the return url
    console.log("logout, un-authenticate, redirecting unauthorized user to /login ");
    localStorage.removeItem(TOKEN_KEY);
    this.router.navigate(['/login']);
    this.authenticate(false);
  }

  authenticate(authenticated = false) {
    this.authenticatedSubject.next(authenticated);
  }

  getAuth(): Observable<boolean> {
    return this.authenticatedSubject.asObservable();
  }

  authenticateRoute(routeAuthenticated = false) {
    this.routeAuthSubject.next(routeAuthenticated);
  }

  getRouteAuth(): Observable<boolean> {
    return this.routeAuthSubject.asObservable();
  }
}
