import {Component, OnInit} from '@angular/core';
import {Field} from '../field.component';

@Component({
  selector: 'list-field',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
