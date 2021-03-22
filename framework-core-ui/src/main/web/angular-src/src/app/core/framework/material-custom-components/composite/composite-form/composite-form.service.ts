import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {DynamicFormConfig} from './composite-form.component';
import {GatewayService} from "../../../services/constants/gateway.service";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CompositeFormService {

  constructor(private http: HttpClient, private router: Router,
              private gatewayService :GatewayService) { }


  public loadFormConfig(loadAPI: string): Observable<DynamicFormConfig> {
    let URI = `${this.gatewayService.GATEWAY_URL}` + loadAPI;
    return this.http.get<DynamicFormConfig>(URI, httpOptions);
  }
}
