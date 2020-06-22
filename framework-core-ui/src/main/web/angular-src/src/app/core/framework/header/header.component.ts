
import {filter} from 'rxjs/operators';
import { Component, OnInit, Inject, Renderer, ElementRef } from '@angular/core';
import { Location } from '@angular/common';
import { Router, NavigationEnd  } from '@angular/router';
import {faLaptop, faHandsHelping, faSignInAlt, faSignOutAlt, faUserAlt,
  faCaretDown, faPencilAlt, faPhone, faSearch, faShoppingCart, faMapMarkerAlt } from '@fortawesome/free-solid-svg-icons';

import { UserService } from '../services/user.service';
import {Subscription} from "rxjs/index";
import {JwtHelperService} from "@auth0/angular-jwt";
import {User} from "../models/user";


import { DOCUMENT } from '@angular/platform-browser';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  private _router: Subscription;
  private authSubscription: Subscription;
  private routeAuthSubscription: Subscription;
  enterLocation: boolean;

  constructor(private userService: UserService,
              private  jwtHelper: JwtHelperService,
              private renderer: Renderer,
              private router: Router,
              @Inject(DOCUMENT) private document: any,
              private element: ElementRef,
              public location: Location) {
  }

  authenticated = false;
  routeAuthenticated = false;
  fixHeader: boolean;
  user: User;

  faLaptop = faLaptop;
  faHandsHelping = faHandsHelping;
  faSignInAlt = faSignInAlt;
  faSignOutAlt = faSignOutAlt;
  faUserAlt = faUserAlt;
  faCaretDown = faCaretDown;
  faPencilAlt = faPencilAlt;
  faPhone = faPhone;
  faSearch = faSearch;
  faShoppingCart = faShoppingCart;
  faMapMarkerAlt = faMapMarkerAlt;


  ngOnInit() {
    this.enterLocation = false;
    this._router = this.router.events.pipe(filter(event => event instanceof NavigationEnd)).subscribe((event: NavigationEnd) => {
      if (window.outerWidth > 991) {
        window.document.children[0].scrollTop = 0;
      }else{
        window.document.activeElement.scrollTop = 0;
      }

      this.renderer.listenGlobal('window', 'scroll', (event) => {
        const number = window.scrollY;
        var _location = this.location.path();
        _location = _location.split('/')[2];

        if (number > 52 || window.pageYOffset > 52) {
          this.fixHeader = true;
        } else if (_location !== 'login') {
          this.fixHeader = false;
        }
      });
    });


    this.user = {};

    if (this.jwtHelper.isTokenExpired()) {
      this.authenticated = false;
    } else {
      this.authenticated = true;
      this.loadAuthenticatedUser();
    }

    this.authSubscription = this.userService.getAuth().subscribe(authenticated => {
      this.authenticated = authenticated;
      if (authenticated == true) {
        this.loadAuthenticatedUser();
      }
    });

    this.routeAuthSubscription = this.userService.getRouteAuth().subscribe(routeAuthenticated => {
      this.routeAuthenticated = routeAuthenticated;
    });
  }

  private loadAuthenticatedUser() {
    this.userService.getCurrentUser()
      .subscribe(user => {
        this.user = user;
      });
  }

  ngOnDestroy() {
    this.authSubscription.unsubscribe();
    this.routeAuthSubscription.unsubscribe();
  }

  public logout(){
    this.userService.logout();
  }

  displayLocationForm(showForm: boolean) {
    this.enterLocation = showForm;
  }

}
