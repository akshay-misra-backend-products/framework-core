import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

import { UserService } from '../services/user.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(private router: Router,
              private  jwtHelper: JwtHelperService,
              private  userService: UserService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    console.log("canActivate, isTokenExpired: "+this.jwtHelper.isTokenExpired());

    // DELETE BELOW BLOCK AFTER TESTING
    if (true) {
      return true
    }

    if (this.jwtHelper.isTokenExpired()) {
      this.userService.logout();
      return false;
    } else {
      return true;
    }
  }
}
