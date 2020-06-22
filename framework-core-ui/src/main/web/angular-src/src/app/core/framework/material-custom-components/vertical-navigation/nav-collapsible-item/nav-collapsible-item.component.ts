import {Component, Input, OnInit} from '@angular/core';
import {MenuItem} from '../../navigation-service/menu-item';

@Component({
  selector: 'nav-collapsable-item',
  templateUrl: './nav-collapsible-item.component.html',
  styleUrls: ['./nav-collapsible-item.component.scss']
})
export class NavCollapsibleItemComponent implements OnInit {

  @Input() item: MenuItem;

  constructor() { }

  ngOnInit() {
  }

}
