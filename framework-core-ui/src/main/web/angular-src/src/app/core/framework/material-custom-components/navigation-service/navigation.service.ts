import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { MenuItem } from './menu-item';
import {MenuItemConfig} from './menu-item-config';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class NavigationService {

  private serverApi = 'http://localhost:8080';

  private tabs: MenuItem[];

  public westSideNavToggleSubject = new Subject<boolean>();

  public eastSideNavToggleSubject = new Subject<boolean>();

  constructor(private http: HttpClient) { }

  public toggleWestNavbar(toggle: boolean) {
    return this.westSideNavToggleSubject.next(toggle);
  }

  public observeWestNavigation(): Observable<boolean> {
    return this.westSideNavToggleSubject.asObservable();
  }

  public toggleEastNavbar(toggle: boolean) {
    return this.eastSideNavToggleSubject.next(toggle);
  }

  public observeEastNavigation(): Observable<boolean> {
    return this.eastSideNavToggleSubject.asObservable();
  }

  public loadNavigationMenuConfig(): Observable<MenuItemConfig> {
    const URI = `${this.serverApi}` + '/application/api/load/navigation/menu/config';
    return this.http.get<MenuItemConfig>(URI, httpOptions);
  }

  public getNavigationMenuItems() {
    return this.tabs;
  }

  public setNavigationMenutems(items: MenuItem[]) {
    this.tabs = items;
  }

}
