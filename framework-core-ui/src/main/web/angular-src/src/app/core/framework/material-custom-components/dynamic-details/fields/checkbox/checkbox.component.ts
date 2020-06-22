import {Component, OnInit} from '@angular/core';
import {Field} from '../fields.component';

@Component({
  selector: 'checkbox-view-field',
  templateUrl: './checkbox.component.html',
  styleUrls: ['./checkbox.component.scss']
})
export class CheckboxComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
