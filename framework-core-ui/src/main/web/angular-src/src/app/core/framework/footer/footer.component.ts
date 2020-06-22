import { Component, OnInit } from '@angular/core';
import {faEnvelope, faBoxOpen, faShoppingCart, faMoneyBillWaveAlt, faTruckLoading} from '@fortawesome/free-solid-svg-icons';
import {Subscription} from "rxjs/index";

import {JwtHelperService} from "@auth0/angular-jwt";


import { UserService } from '../services/user.service';


@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {
  private subscription: Subscription;


  faEnvelope = faEnvelope;
  faBoxOpen = faBoxOpen;
  faShoppingCart = faShoppingCart;
  faMoneyBillWaveAlt = faMoneyBillWaveAlt;
  faTruckLoading = faTruckLoading;


  constructor(private userService: UserService,
              private  jwtHelper: JwtHelperService) { }

  authenticated = false;


  ngOnInit() {
    if (this.jwtHelper.isTokenExpired()) {
      this.authenticated = false;
    } else {
      this.authenticated = true;
    }

    this.authenticated = true;

    this.subscription = this.userService.getAuth().subscribe(authenticated => {
      this.authenticated = authenticated;
    });
  }

}
