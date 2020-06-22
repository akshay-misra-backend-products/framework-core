import { Component, OnInit } from '@angular/core';
import {Field} from '../fields.component';

@Component({
  selector: 'number-view-field',
  templateUrl: './number.component.html',
  styleUrls: ['./number.component.scss']
})
export class NumberComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
