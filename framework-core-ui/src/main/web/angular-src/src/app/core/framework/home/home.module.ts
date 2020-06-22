import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HomeComponent } from './home.component';
import { FlexLayoutModule } from "@angular/flex-layout";
import { CommonMaterialModule } from "../material-custom-components/common-module/common-material.module";
import {MatButtonModule, MatCardModule, MatCommonModule} from "@angular/material";
import {RouterModule} from "@angular/router";

@NgModule({
  declarations: [HomeComponent],
    imports: [
        CommonModule,
        FlexLayoutModule,
        MatCommonModule,
        MatButtonModule,
        CommonMaterialModule,
        MatCardModule,
        RouterModule
    ]
})
export class HomeModule { }
