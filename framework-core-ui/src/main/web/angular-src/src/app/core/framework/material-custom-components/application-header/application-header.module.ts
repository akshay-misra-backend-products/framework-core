import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ApplicationHeaderComponent} from './application-header.component';
import {MatButtonModule, MatCommonModule, MatIconModule, MatMenuModule, MatToolbarModule} from '@angular/material';
import {RouterModule} from '@angular/router';
import {NavigationServiceModule} from '../navigation-service/navigation-service.module';
import {FlexLayoutModule} from '@angular/flex-layout';

@NgModule({
  declarations: [ApplicationHeaderComponent],
  imports: [
    CommonModule,
    MatIconModule,
    RouterModule,
    MatCommonModule,
    MatToolbarModule,
    MatMenuModule,
    MatButtonModule,
    FlexLayoutModule,
    NavigationServiceModule
  ],
  exports: [ApplicationHeaderComponent]
})
export class ApplicationHeaderModule { }
