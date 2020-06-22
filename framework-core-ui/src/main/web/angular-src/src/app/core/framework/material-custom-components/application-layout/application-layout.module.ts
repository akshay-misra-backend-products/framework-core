import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApplicationLayoutComponent } from './application-layout.component';
import {MatButtonModule, MatCommonModule, MatIconModule, MatSidenavModule} from '@angular/material';
import {RouterModule} from '@angular/router';
import {FlexLayoutModule} from '@angular/flex-layout';
import {NavigationServiceModule} from '../navigation-service/navigation-service.module';
import {FooterComponent} from '../../footer/footer.component';
import {AlertComponent} from '../../alert/alert.component';
import {VerticalNavigationModule} from '../vertical-navigation/vertical-navigation.module';
import {NotifierModule} from 'angular-notifier';
import {NotificationPanelModule} from '../notification-panel/notification-panel.module';
import {HeaderModule} from '../../header/header.module';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    ApplicationLayoutComponent,
    FooterComponent,
    AlertComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CommonModule,
    MatIconModule,
    RouterModule,
    MatCommonModule,
    MatButtonModule,
    FlexLayoutModule,
    MatSidenavModule,
    NavigationServiceModule,
    VerticalNavigationModule,
    NotifierModule,
    NotificationPanelModule,
    HeaderModule
  ],
  exports: [ApplicationLayoutComponent]
})
export class ApplicationLayoutModule { }
