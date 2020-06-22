import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';

import {faLaptop, faHandsHelping} from '@fortawesome/free-solid-svg-icons';


import { UserService } from '../services/user.service';
import { AlertService } from '../services/alert.service';

import { User } from '../models/user'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private user : User;
  loading = false;
  submitted = false;
  returnUrl: string;
  faLaptop = faLaptop;
  faHandsHelping = faHandsHelping;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private alertService: AlertService) {
    console.log("LoginComponent, constructor:   ");
  }

  ngOnInit() {
    console.log("LoginComponent, ngOnInit:   ");
    this.userService.authenticateRoute(false);
    this.user = {};
    // reset login status
    this.userService.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  onSubmit() {
    this.submitted = true;
    this.loading = true;
    this.userService.login(this.user.userName, this.user.password)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
