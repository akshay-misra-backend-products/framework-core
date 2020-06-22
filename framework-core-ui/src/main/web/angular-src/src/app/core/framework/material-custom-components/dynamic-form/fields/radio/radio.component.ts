import {Component, OnInit} from '@angular/core';
import {Field} from '../field.component';

@Component({
  selector: 'radio-field',
  templateUrl: './radio.component.html',
  styleUrls: ['./radio.component.scss']
})
export class RadioComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
