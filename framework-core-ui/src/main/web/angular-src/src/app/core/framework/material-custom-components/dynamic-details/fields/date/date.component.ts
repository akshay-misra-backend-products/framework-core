import { Component, OnInit } from '@angular/core';
import {Field} from '../fields.component';

@Component({
  selector: 'date-view-field',
  templateUrl: './date.component.html',
  styleUrls: ['./date.component.scss']
})
export class DateComponent extends Field implements OnInit {
  ngOnInit() {
    const date = new Date(this.field.value);
    this.field.value = date;
  }
}
