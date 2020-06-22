import {Component, OnInit} from '@angular/core';
import {Field} from '../field.component';

@Component({
  selector: 'textbox-field',
  templateUrl: './textbox.component.html',
  styleUrls: ['./textbox.component.scss']
})
export class TextboxComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
