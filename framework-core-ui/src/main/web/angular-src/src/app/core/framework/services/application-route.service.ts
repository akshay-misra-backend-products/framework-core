import { Injectable } from '@angular/core';
import {NavigationEnd, Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ApplicationRouteService {

  private previousUrl: string;
  private currentUrl: string;

  constructor(private router: Router) {
    this.currentUrl = this.router.url;
    router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.previousUrl = this.currentUrl;
        this.currentUrl = event.url;
      }
    });
  }

  public getPreviousRoute(): string {
    return this.previousUrl;
  }

  public getCurrentRoute(): string {
    return this.currentUrl;
  }

  public navigate(url: string) {
    this.router.navigate([url]);
  }
}
