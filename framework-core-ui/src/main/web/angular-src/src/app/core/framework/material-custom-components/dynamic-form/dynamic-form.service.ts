import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {GatewayService} from "../../services/constants/gateway.service";

@Injectable({
  providedIn: 'root'
})
export class DynamicFormService {

  constructor(private http: HttpClient, private router: Router,
              private gatewayService :GatewayService) { }

  public createObject(object: any, createAPI: string): Observable<any> {
    let URI = `${this.gatewayService.GATEWAY_URL}` + createAPI;
    return this.http.post<any>(URI, object);
  }
}
