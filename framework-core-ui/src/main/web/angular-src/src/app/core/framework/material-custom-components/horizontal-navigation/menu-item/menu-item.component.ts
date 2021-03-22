import {Component, Input, OnInit} from '@angular/core';
import {MenuItem} from '../../navigation-service/menu-item';

@Component({
  selector: 'menu-item',
  templateUrl: './menu-item.component.html',
  styleUrls: ['./menu-item.component.scss']
})
export class MenuItemComponent implements OnInit {

  @Input() item: MenuItem;

  private href: string;

  constructor() { }

  ngOnInit() {
    if (this.item) {
      this.href = this.item.href;
    }
  }
}
