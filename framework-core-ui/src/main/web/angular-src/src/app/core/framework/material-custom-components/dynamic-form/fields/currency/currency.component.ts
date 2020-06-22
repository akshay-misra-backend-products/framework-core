import {Component, OnInit} from '@angular/core';
import {Field} from '../field.component';

@Component({
  selector: 'currency-field',
  templateUrl: './currency.component.html',
  styleUrls: ['./currency.component.scss']
})
export class CurrencyComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
