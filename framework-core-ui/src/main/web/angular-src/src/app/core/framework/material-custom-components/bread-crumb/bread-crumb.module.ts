import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BreadCrumbComponent } from './bread-crumb.component';
import { BreadCrumbItemComponent } from './bread-crumb-item/bread-crumb-item.component';
import { NavigationServiceModule } from '../navigation-service/navigation-service.module';
import { RouterModule } from '@angular/router';
import { MatButtonModule, MatIconModule, MatToolbarModule } from '@angular/material';

@NgModule({
  declarations: [
    BreadCrumbComponent,
    BreadCrumbItemComponent
  ],
  imports: [
    CommonModule,
    NavigationServiceModule,
    RouterModule,
    MatIconModule,
    MatButtonModule,
    MatToolbarModule
  ],
  exports: [BreadCrumbComponent]
})
export class BreadCrumbModule { }
