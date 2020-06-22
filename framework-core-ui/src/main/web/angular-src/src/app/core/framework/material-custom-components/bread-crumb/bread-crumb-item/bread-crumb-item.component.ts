import {Component, Input, OnInit} from '@angular/core';
import {BreadCrumbItem} from './bread-crumb-item';

@Component({
  selector: 'bread-crumb-item',
  templateUrl: './bread-crumb-item.component.html',
  styleUrls: ['./bread-crumb-item.component.scss']
})
export class BreadCrumbItemComponent implements OnInit {

  @Input() item: BreadCrumbItem;

  constructor() { }

  ngOnInit() {
  }

}
