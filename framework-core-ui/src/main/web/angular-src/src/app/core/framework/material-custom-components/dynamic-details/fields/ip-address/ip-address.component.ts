import { Component, OnInit } from '@angular/core';
import {Field} from '../fields.component';

@Component({
  selector: 'ip-address-view-field',
  templateUrl: './ip-address.component.html',
  styleUrls: ['./ip-address.component.scss']
})
export class IpAddressComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
