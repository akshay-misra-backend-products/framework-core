import {Component, Input, OnInit} from '@angular/core';
import {BreadCrumbItem} from './bread-crumb-item/bread-crumb-item';

@Component({
  selector: 'bread-crumb',
  templateUrl: './bread-crumb.component.html',
  styleUrls: ['./bread-crumb.component.scss']
})
export class BreadCrumbComponent implements OnInit {

  @Input() items: BreadCrumbItem[] = [];

  constructor() { }

  ngOnInit() {
  }

}
