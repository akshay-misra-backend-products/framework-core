import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DynamicFormService {

  constructor(private http: HttpClient, private router: Router) { }

  private serverApi = 'http://localhost:8080';

  public createObject(object: any, createAPI: string): Observable<any> {
    let URI = `${this.serverApi}/` + createAPI;
    return this.http.post<any>(URI, object);
  }
}
