import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import {MatButtonModule, MatCommonModule, MatIconModule, MatMenuModule, MatToolbarModule} from '@angular/material';
import {HorizontalNavbarComponent} from './horizontal-navbar/horizontal-navbar.component';
import {MenuItemGroupComponent} from './menu-item-group/menu-item-group.component';
import {MenuItemComponent} from './menu-item/menu-item.component';
import {NavigationServiceModule} from '../navigation-service/navigation-service.module';
import {FlexLayoutModule} from '@angular/flex-layout';

@NgModule({
  declarations: [
    HorizontalNavbarComponent,
    MenuItemComponent,
    MenuItemGroupComponent
  ],
  imports: [
    CommonModule,
    MatIconModule,
    RouterModule,
    MatCommonModule,
    MatToolbarModule,
    MatMenuModule,
    MatButtonModule,
    FlexLayoutModule,
    NavigationServiceModule,
  ],
  exports: [
    HorizontalNavbarComponent,
    MenuItemComponent]
})
export class HorizontalNavigationModule { }
