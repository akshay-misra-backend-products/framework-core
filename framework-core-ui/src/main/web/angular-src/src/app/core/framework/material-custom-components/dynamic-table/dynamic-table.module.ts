import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DynamicTableComponent} from './dynamic-table.component';
import {
  MatButtonModule, MatCheckboxModule,
  MatIconModule,
  MatInputModule,
  MatPaginatorModule,
  MatProgressSpinnerModule, MatSortModule,
  MatTableModule,
  MatTooltipModule,
  MatDialogModule
} from '@angular/material';
import {RouterModule} from '@angular/router';
import {MatReferenceFieldModule} from "../mat-reference-field/mat-reference-field.module";

@NgModule({
  declarations: [
    DynamicTableComponent,
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
    MatReferenceFieldModule
  ],
  exports: [
    DynamicTableComponent
  ]
})
export class DynamicTableModule { }
