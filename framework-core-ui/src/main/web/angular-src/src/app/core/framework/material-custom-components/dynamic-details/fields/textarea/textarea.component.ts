import {Component, OnInit} from '@angular/core';
import {Field} from '../fields.component';

@Component({
  selector: 'textarea-view-field',
  templateUrl: './textarea.component.html',
  styleUrls: ['./textarea.component.scss']
})
export class TextareaComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
