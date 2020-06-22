import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ApplicationHeaderModule} from '../material-custom-components/application-header/application-header.module';
import {HorizontalNavigationModule} from '../material-custom-components/horizontal-navigation/horizontal-navigation.module';
import {HeaderComponent} from './header.component';

@NgModule({
  declarations: [HeaderComponent],
  imports: [
    CommonModule,
    ApplicationHeaderModule,
    HorizontalNavigationModule,
  ],
  exports: [HeaderComponent]
})
export class HeaderModule { }
