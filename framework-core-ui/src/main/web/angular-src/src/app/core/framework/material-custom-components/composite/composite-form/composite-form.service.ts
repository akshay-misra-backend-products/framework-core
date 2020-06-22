import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {DynamicFormConfig} from './composite-form.component';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CompositeFormService {

  constructor(private http: HttpClient, private router: Router) { }

  private serverApi = 'http://localhost:8080';

  public loadFormConfig(loadAPI: string): Observable<DynamicFormConfig> {
    let URI = `${this.serverApi}` + loadAPI;
    return this.http.get<DynamicFormConfig>(URI, httpOptions);
  }
}
