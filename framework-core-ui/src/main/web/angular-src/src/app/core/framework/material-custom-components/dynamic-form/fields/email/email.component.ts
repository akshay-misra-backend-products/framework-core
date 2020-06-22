import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Field} from '../field.component';

@Component({
  selector: 'email-field',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.scss']
})
export class EmailComponent extends Field implements OnInit {

  getErrorMessage() {
    const message = super.getErrorMessage();
    return message ? message : this.form.controls[this.field.name].hasError('email') ? 'Not a valid email' : '';
  }

  ngOnInit() {
  }

}
