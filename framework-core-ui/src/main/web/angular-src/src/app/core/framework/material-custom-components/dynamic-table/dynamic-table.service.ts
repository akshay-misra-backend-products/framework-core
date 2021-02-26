import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {GatewayService} from "../../services/constants/gateway.service";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class DynamicTableService {

  constructor(private http: HttpClient, private router: Router,
              private gatewayService :GatewayService) { }

  public loadObjects(loadAPI: string): Observable<any> {
    let URI = `${this.gatewayService.FRAMEWORK_SERVICE_URL}` + loadAPI;
    return this.http.get<any[]>(URI, httpOptions);
  }
}
