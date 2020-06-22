import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompositeDetailsComponent } from './composite-details.component';
import {DynamicDetailsModule} from '../../dynamic-details/dynamic-details.module';
import {CompositeTableModule} from '../composite-table/composite-table.module';

@NgModule({
  declarations: [CompositeDetailsComponent],
  imports: [
    CommonModule,
    DynamicDetailsModule,
    CompositeTableModule
  ],
  exports: [CompositeDetailsComponent],
  entryComponents: [CompositeDetailsComponent]
})
export class CompositeDetailsModule { }
