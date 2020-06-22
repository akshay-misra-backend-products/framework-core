import {Component, OnInit} from '@angular/core';
import {Field} from '../fields.component';

@Component({
  selector: 'email-view-field',
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
