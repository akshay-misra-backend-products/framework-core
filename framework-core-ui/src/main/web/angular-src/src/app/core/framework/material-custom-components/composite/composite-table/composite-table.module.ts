import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CompositeTableComponent } from './composite-table.component';
import { MatTabsModule } from '@angular/material';
import { DynamicTableModule } from '../../dynamic-table/dynamic-table.module';
import {MultiTypeDynamicTableModule} from "../../multi-type-dynamic-table/multi-type-dynamic-table.module";

@NgModule({
  declarations: [CompositeTableComponent],
    imports: [
        CommonModule,
        MatTabsModule,
        DynamicTableModule,
        MultiTypeDynamicTableModule
    ],
  exports: [CompositeTableComponent],
  entryComponents: [CompositeTableComponent]
})
export class CompositeTableModule { }
