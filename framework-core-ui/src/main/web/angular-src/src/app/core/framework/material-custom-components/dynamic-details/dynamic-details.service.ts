import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {GatewayService} from "../../services/constants/gateway.service";

@Injectable({
  providedIn: 'root'
})
export class DynamicDetailsService {

  constructor(private http: HttpClient, private router: Router,
              private gatewayService :GatewayService) { }

  public updateObject(object: any, updateAPI: string): Observable<any> {
    let URI = `${this.gatewayService.GATEWAY_URL}` + updateAPI;
    return this.http.put<any>(URI, object);
  }
}
