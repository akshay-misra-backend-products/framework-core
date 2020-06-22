import {Component, OnInit} from '@angular/core';
import {Field} from '../field.component';

@Component({
  selector: 'ipaddress-field',
  templateUrl: './ipaddress.component.html',
  styleUrls: ['./ipaddress.component.scss']
})
export class IpaddressComponent extends Field implements OnInit {
  ngOnInit() {
  }

}
