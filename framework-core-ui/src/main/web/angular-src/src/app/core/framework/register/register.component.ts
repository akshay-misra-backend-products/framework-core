import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { first } from 'rxjs/operators';
import {faLaptop, faHandsHelping} from '@fortawesome/free-solid-svg-icons';


import { UserService } from '../services/user.service';
import { AlertService } from '../services/alert.service';
import { User } from '../models/user'

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  private user : User;
  loading = false;
  submitted = false;
  faLaptop = faLaptop;
  faHandsHelping = faHandsHelping;

  constructor(
    private router: Router,
    private userService: UserService,
    private alertService: AlertService) { }

  ngOnInit() {
    this.userService.authenticateRoute(false);
    this.user = {};
  }

  onSubmit() {
    this.submitted = true;
    this.loading = true;
    this.userService.register(this.user)
      .pipe(first())
      .subscribe(
        data => {
          this.alertService.success('Registration successful', true);
          this.router.navigate(['/login']);
        },
        error => {
          this.alertService.error(error);
          this.loading = false;
        });
  }
}
