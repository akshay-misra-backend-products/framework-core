import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompositePageComponent } from './composite-page.component';
import {CommonMaterialModule} from '../../common-module/common-material.module';
import {CompositeTableModule} from '../composite-table/composite-table.module';
import {BreadCrumbModule} from '../../bread-crumb/bread-crumb.module';

@NgModule({
  declarations: [CompositePageComponent],
  imports: [
    CommonModule,
    CommonMaterialModule,
    CompositeTableModule,
    BreadCrumbModule
  ]
})
export class CompositePageModule { }
