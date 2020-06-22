import {Component, Input, OnInit} from '@angular/core';
import {MenuItem} from '../../navigation-service/menu-item';

@Component({
  selector: 'menu-item-group',
  templateUrl: './menu-item-group.component.html',
  styleUrls: ['./menu-item-group.component.scss']
})
export class MenuItemGroupComponent implements OnInit {

  @Input() items: MenuItem [];

  constructor() { }

  ngOnInit() {
  }
}
