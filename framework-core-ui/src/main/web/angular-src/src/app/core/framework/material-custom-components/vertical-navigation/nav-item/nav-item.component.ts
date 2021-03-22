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
      this.href = this.item.href;
    }
  }
}
