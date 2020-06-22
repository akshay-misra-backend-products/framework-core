import {Component, OnInit} from '@angular/core';
import {Field} from '../field.component';

@Component({
  selector: 'textarea-field',
  templateUrl: './textarea.component.html',
  styleUrls: ['./textarea.component.scss']
})
export class TextareaComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
