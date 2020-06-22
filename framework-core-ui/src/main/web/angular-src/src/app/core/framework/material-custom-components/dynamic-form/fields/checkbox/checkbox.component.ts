import {Component, OnInit} from '@angular/core';
import {Field} from '../field.component';

@Component({
  selector: 'checkbox-field',
  templateUrl: './checkbox.component.html',
  styleUrls: ['./checkbox.component.scss']
})
export class CheckboxComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
