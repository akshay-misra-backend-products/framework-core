import {Component, Input, OnInit} from '@angular/core';
import {MenuItem} from '../../navigation-service/menu-item';

@Component({
  selector: 'nav-item',
  templateUrl: './nav-item.component.html',
  styleUrls: ['./nav-item.component.scss']
})
export class NavItemComponent implements OnInit {

  @Input() item: MenuItem;

  private href: string;

  constructor() { }

  ngOnInit() {
    if (this.item) {
      if (this.item.dummy) {
        this.href = '#' + this.item.id;
      } else {
        this.href = '/application/navigation/' + this.item.objectTypeId + '/' + this.item.id;
      }
    }
  }
}
