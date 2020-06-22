import { Component, OnInit } from '@angular/core';
import {NavigationService} from '../navigation-service/navigation.service';
import {Subscription} from "rxjs";
import {User} from "../../models/user";
import {UserService} from "../../services/user.service";
import {JwtHelperService} from "@auth0/angular-jwt";

@Component({
  selector: 'application-header',
  templateUrl: './application-header.component.html',
  styleUrls: ['./application-header.component.scss']
})
export class ApplicationHeaderComponent implements OnInit {

  private authSubscription: Subscription;
  authenticated = false;
  fixHeader: boolean;
  user: User;

  constructor(private navigationService: NavigationService,
              private userService: UserService,
              private  jwtHelper: JwtHelperService) {}

  ngOnInit() {
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
  }

  private loadAuthenticatedUser() {
    this.userService.getCurrentUser()
      .subscribe(user => {
        this.user = user;
      });
  }

  ngOnDestroy() {
    this.authSubscription.unsubscribe();
  }

  public logout(){
    this.userService.logout();
  }

  openWestSideNav() {
     this.navigationService.toggleWestNavbar(true);
  }

  openEastSideNav() {
    this.navigationService.toggleEastNavbar(true);
  }

}
