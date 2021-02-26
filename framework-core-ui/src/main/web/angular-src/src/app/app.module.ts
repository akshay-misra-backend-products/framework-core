import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { FlexLayoutModule } from "@angular/flex-layout";
import {AngularMaterialModules} from './material-module';

import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import {JwtModule} from '@auth0/angular-jwt';
import { NgxSummernoteModule } from 'ngx-summernote';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';

import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppComponent } from './app.component';

import { LoginComponent } from './core/framework/login/login.component';
import { RegisterComponent } from './core/framework/register/register.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthGuard } from './core/framework/guards/auth.guard';
import { AlertService } from './core/framework/services/alert.service';
import { UserService } from './core/framework/services/user.service';
import { ErrorInterceptor } from './core/framework/helpers/error.interceptor';
import { UserProfileComponent } from './core/framework/user-profile/user-profile.component';
import { DynamicFormModule} from './core/framework/material-custom-components/dynamic-form/dynamic-form.module';
import { DynamicDetailsModule} from './core/framework/material-custom-components/dynamic-details/dynamic-details.module';
import { MatToolbarModule, MatSidenavModule, MatListModule } from "@angular/material";
import { ApplicationLayoutModule } from './core/framework/material-custom-components/application-layout/application-layout.module';
import { CommonMaterialModule } from './core/framework/material-custom-components/common-module/common-material.module';
import { CompositeTableModule } from './core/framework/material-custom-components/composite/composite-table/composite-table.module';
import { CompositeFormModule } from './core/framework/material-custom-components/composite/composite-form/composite-form.module';
import { CompositeDetailsModule } from './core/framework/material-custom-components/composite/composite-details/composite-details.module';
import { CompositePageModule } from './core/framework/material-custom-components/composite/composite-page/composite-page.module';
import { HomeModule } from "./core/framework/home/home.module";
import { MatReferenceFieldModule } from "./core/framework/material-custom-components/mat-reference-field/mat-reference-field.module";
import { JwtInterceptor } from "./core/framework/interceptors/jwt.interceptor";


export function tokenGetter() {
  return localStorage.getItem('AuthToken');
}

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    UserProfileComponent,
  ],

  //Modules go here
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatListModule,
    FlexLayoutModule,
    AngularMaterialModules,
    FontAwesomeModule,
    HttpClientModule,
    FormsModule,
    MatReferenceFieldModule,
    CommonMaterialModule,
    CompositeTableModule,
    CompositeFormModule,
    CompositeDetailsModule,
    CompositePageModule,
    HomeModule,
    DynamicFormModule,
    DynamicDetailsModule,
    ApplicationLayoutModule,
    ReactiveFormsModule,
    AppRoutingModule,
    InfiniteScrollModule,
    NgxSummernoteModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter,
        whitelistedDomains: [''],
        skipWhenExpired: true
      }
    })
  ],
  schemas: [ NO_ERRORS_SCHEMA ],
  //All the services go here
  providers:
    [ AuthGuard,
      AlertService,
      UserService,
      { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
      { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
      ],

  bootstrap: [AppComponent]
})
export class AppModule { }
