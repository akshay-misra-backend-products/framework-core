import { Component, OnInit } from '@angular/core';
import {Field} from '../../../dynamic-details/fields/fields.component';

@Component({
  selector: 'number-field',
  templateUrl: './number.component.html',
  styleUrls: ['./number.component.scss']
})
export class NumberComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
