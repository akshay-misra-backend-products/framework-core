import {Component, OnDestroy, OnInit, Renderer, ViewChild} from '@angular/core';
import {Subscription} from 'rxjs';
import {MatSidenav} from '@angular/material';
import {NavigationService} from '../navigation-service/navigation.service';

@Component({
  selector: 'application-layout',
  templateUrl: './application-layout.component.html',
  styleUrls: ['./application-layout.component.scss']
})
export class ApplicationLayoutComponent implements OnInit, OnDestroy {

  private westSidenavSubscription: Subscription;

  private eastSidenavSubscription: Subscription;

  private hasBackDrop: boolean;

  private load: boolean;

  scrollTop: boolean;

  @ViewChild('westSidenav') public westSidenav: MatSidenav;

  @ViewChild('eastSidenav') public eastSidenav: MatSidenav;

  constructor(private renderer: Renderer,
              private navigationService: NavigationService) {
  }

  ngOnInit() {
    this.hasBackDrop = false;

    this.westSidenavSubscription = this.navigationService.observeWestNavigation().subscribe(() => {
      this.hasBackDrop = true;
      if (!this.load) {
         this.load = true;
      }
      this.westSidenav.toggle();
    });

    this.eastSidenavSubscription = this.navigationService.observeEastNavigation().subscribe(() => {
      this.hasBackDrop = false;
      this.eastSidenav.toggle();
    });

    this.renderer.listenGlobal('window', 'scroll', (event) => {
      const number = window.scrollY;
      if (number > 552 || window.pageYOffset > 552) {
        this.scrollTop = true;
      } else {
        this.scrollTop = false;
      }
      console.log('scrollTop: ' + this.scrollTop)
    });
  }

  ngOnDestroy() {
    this.westSidenavSubscription.unsubscribe();
    this.eastSidenavSubscription.unsubscribe();
  }

  closeWestSideNav() {
    this.westSidenav.close();
  }

  closeEastSideNav() {
    this.eastSidenav.close();
  }

  scrollToTop() {
    var element = document.getElementById("top");
    element.scrollIntoView({
      block: 'start',
      behavior: 'smooth'
    });
  }
}

