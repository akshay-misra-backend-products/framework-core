import {Component, OnInit} from '@angular/core';
import {Field} from '../fields.component';

@Component({
  selector: 'currency-view-field',
  templateUrl: './currency.component.html',
  styleUrls: ['./currency.component.scss']
})
export class CurrencyComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
