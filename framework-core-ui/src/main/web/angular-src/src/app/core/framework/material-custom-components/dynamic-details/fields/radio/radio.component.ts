import {Component, OnInit} from '@angular/core';
import {Field} from '../fields.component';

@Component({
  selector: 'radio-view-field',
  templateUrl: './radio.component.html',
  styleUrls: ['./radio.component.scss']
})
export class RadioComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
