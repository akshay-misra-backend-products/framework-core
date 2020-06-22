import {Component, OnInit} from '@angular/core';
import {Field} from '../fields.component';

@Component({
  selector: 'password-view-field',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.scss']
})
export class PasswordComponent extends Field implements OnInit {

  hide = true;
  ngOnInit() {
  }

}
