import { Component, OnInit } from '@angular/core';
import {NavigationService} from '../../navigation-service/navigation.service';
import {MenuItem} from '../../navigation-service/menu-item';

@Component({
  selector: 'horizontal-navbar',
  templateUrl: './horizontal-navbar.component.html',
  styleUrls: ['./horizontal-navbar.component.scss']
})
export class HorizontalNavbarComponent implements OnInit {

  private tabs: MenuItem[] = [];

  constructor(private navigationService: NavigationService) {
  }

  ngOnInit() {
    this.navigationService.loadNavigationMenuConfig().subscribe(
      response => {
        this.tabs = response.items;
        this.navigationService.setNavigationMenutems(this.tabs);
        /*this.loading = false;*/
      }, );
  }

}
