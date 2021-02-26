import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GatewayService {

  public GATEWAY_URL: string = 'http://localhost:8765/';

  public FRAMEWORK_SERVICE_URL = this.GATEWAY_URL + 'gbss-framework-core-service';

  public WEB_APP_AUTH_SERVICE_URL = this.GATEWAY_URL + 'gbss-identity-service';


  constructor() { }
}
