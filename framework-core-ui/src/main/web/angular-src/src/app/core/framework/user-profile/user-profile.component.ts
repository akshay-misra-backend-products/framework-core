import { Component, OnInit } from '@angular/core';

import { UserService } from '../services/user.service';
import {User} from "../models/user";


@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

  constructor(private userService: UserService) { }
  user: User;

  ngOnInit() {
    this.user = {};
    this.userService.authenticateRoute(false);
    this.userService.getCurrentUser()
      .subscribe(user => {
        console.log("current user: "+user.firstName+" "+user.lastName);
        this.user = user;
      });
  }
}
