import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DynamicDetailsComponent } from './dynamic-details.component';
import { FieldsComposerComponent } from './fields-composer/fields-composer.component';
import { ReactiveFormsModule} from '@angular/forms';
import { AngularMaterialModules } from '../../../../material-module';
import { MatFormFieldModule, MatInputModule } from '@angular/material';
import { FieldsModule } from './fields/fields.module';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [
    DynamicDetailsComponent,
    FieldsComposerComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FieldsModule,
    AngularMaterialModules,
    MatFormFieldModule,
    MatInputModule,
    RouterModule
  ],
  exports: [DynamicDetailsComponent],
})
export class DynamicDetailsModule { }
