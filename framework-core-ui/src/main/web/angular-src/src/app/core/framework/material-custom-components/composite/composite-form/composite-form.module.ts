import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CompositeFormComponent} from './composite-form.component';
import {DynamicFormModule} from '../../dynamic-form/dynamic-form.module';
import {FlexLayoutModule} from '@angular/flex-layout';

@NgModule({
  declarations: [CompositeFormComponent],
  imports: [
    CommonModule,
    FlexLayoutModule,
    DynamicFormModule
  ],
  exports: [CompositeFormComponent],
  entryComponents: [CompositeFormComponent]
})
export class CompositeFormModule { }
