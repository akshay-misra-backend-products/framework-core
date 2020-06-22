import {Component, OnInit} from '@angular/core';
import {Field} from '../fields.component';

@Component({
  selector: 'list-view-field',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent extends Field implements OnInit {
  ngOnInit() {
    console.log('************* ListComponent, field:  ' + JSON.stringify(this.field));
  }
}
