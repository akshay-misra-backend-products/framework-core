import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MultiTypeDynamicTableComponent } from './multi-type-dynamic-table.component';
import {SelectObjectTypePopupComponent} from "./select-object-type-popup/select-object-type-popup.component";
import {RouterModule} from "@angular/router";
import {MatReferenceFieldModule} from "../mat-reference-field/mat-reference-field.module";
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatButtonModule} from "@angular/material/button";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatDialogModule} from "@angular/material/dialog";
import {FlexLayoutModule, FlexModule} from "@angular/flex-layout";

@NgModule({
  declarations: [
    MultiTypeDynamicTableComponent,
    SelectObjectTypePopupComponent,
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatSortModule,
    MatIconModule,
    MatInputModule,
    MatTooltipModule,
    MatButtonModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatCheckboxModule,
    RouterModule,
    MatDialogModule,
    FlexLayoutModule,
    FlexModule,
    MatReferenceFieldModule
  ],
  exports: [
    MultiTypeDynamicTableComponent
  ],
  entryComponents: [SelectObjectTypePopupComponent]
})
export class MultiTypeDynamicTableModule { }
