import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {DynamicComponentDirective} from './dynamic/directives/dynamic-component.directive';

@NgModule({
  declarations: [DynamicComponentDirective],
  imports: [
    CommonModule
  ],
  exports: [DynamicComponentDirective]
})
export class CommonMaterialModule { }
