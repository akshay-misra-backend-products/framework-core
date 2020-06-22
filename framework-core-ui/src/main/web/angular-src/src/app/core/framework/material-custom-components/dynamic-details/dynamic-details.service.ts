import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DynamicDetailsService {

  constructor(private http: HttpClient, private router: Router) { }

  private serverApi = 'http://localhost:8080';

  public updateObject(object: any, updateAPI: string): Observable<any> {
    let URI = `${this.serverApi}/` + updateAPI;
    return this.http.put<any>(URI, object);
  }
}
