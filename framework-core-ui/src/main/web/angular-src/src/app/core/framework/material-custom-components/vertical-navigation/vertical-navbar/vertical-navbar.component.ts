import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {NavigationService} from '../../navigation-service/navigation.service';
import {MenuItem} from '../../navigation-service/menu-item';

@Component({
  selector: 'vertical-navbar',
  templateUrl: './vertical-navbar.component.html',
  styleUrls: ['./vertical-navbar.component.scss']
})
export class VerticalNavbarComponent implements OnInit {

  @Output() close = new EventEmitter();

  private tabs: MenuItem[];

    constructor(private navigationService: NavigationService) {
    }

    ngOnInit() {
      this.tabs = this.navigationService.getNavigationMenuItems();
    }
}
