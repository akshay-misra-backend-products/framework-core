import {Component, OnInit} from '@angular/core';
import {Field} from '../field.component';

@Component({
  selector: 'password-field',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.scss']
})
export class PasswordComponent extends Field implements OnInit {

  hide = true;
  ngOnInit() {
  }

}
