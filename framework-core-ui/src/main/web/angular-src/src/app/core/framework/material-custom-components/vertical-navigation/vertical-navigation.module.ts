import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavItemComponent } from './nav-item/nav-item.component';
import { NavCollapsibleItemComponent } from './nav-collapsible-item/nav-collapsible-item.component';
import { VerticalNavbarComponent } from './vertical-navbar/vertical-navbar.component';
import {MatButtonModule, MatCommonModule, MatIconModule, MatMenuModule, MatToolbarModule} from '@angular/material';
import { RouterModule } from '@angular/router';
import { NavigationServiceModule } from '../navigation-service/navigation-service.module';
import {FlexLayoutModule } from '@angular/flex-layout';

@NgModule({
  declarations: [
    VerticalNavbarComponent,
    NavItemComponent,
    NavCollapsibleItemComponent
  ],
  imports: [
    CommonModule,
    MatIconModule,
    RouterModule,
    MatCommonModule,
    MatMenuModule,
    MatButtonModule,
    MatToolbarModule,
    FlexLayoutModule,
    NavigationServiceModule
  ],
  exports: [VerticalNavbarComponent]
})
export class VerticalNavigationModule { }
