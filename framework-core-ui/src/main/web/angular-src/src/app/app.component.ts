
import {filter} from 'rxjs/operators';
import {Component, OnInit, ElementRef} from '@angular/core';
import { HttpClient } from '@angular/common/http'
import {Subscription} from "rxjs/index";
import { Router, NavigationEnd  } from '@angular/router';

import {faLongArrowAltUp} from '@fortawesome/free-solid-svg-icons';
import {JwtHelperService} from "@auth0/angular-jwt";

import { UserService } from "./core/framework/services/user.service";
import {Location} from "@angular/common";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'Products';
  authenticated = false;
  routeAuthenticated = true;
  scrollTop: boolean;

  private _router: Subscription;
  private authSubscription: Subscription;
  private routeAuthSubscription: Subscription;

  faLongArrowAltUp = faLongArrowAltUp;


  constructor(public http: HttpClient,
              private  jwtHelper: JwtHelperService,
              private userService: UserService,
              private router: Router,
              private element: ElementRef,
              public location: Location) {

    if (this.jwtHelper.isTokenExpired()) {
      this.authenticated = false;
    } else {
      this.authenticated = true;
    }

    this.authSubscription = this.userService.getAuth().subscribe(authenticated => {
      this.authenticated = authenticated;
    });

    this.routeAuthSubscription = this.userService.getRouteAuth().subscribe(routeAuthenticated => {
      this.routeAuthenticated = routeAuthenticated;
    });
  }

  ngOnInit() {
    this._router = this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe((event: NavigationEnd) => {
      if (window.outerWidth > 991) {
        window.document.children[0].scrollTop = 0;
      } else {
        window.document.activeElement.scrollTop = 0;
      }
    });
  }
}
