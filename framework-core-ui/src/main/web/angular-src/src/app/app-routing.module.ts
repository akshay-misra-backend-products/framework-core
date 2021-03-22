import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {AuthGuard} from "./core/framework/guards/auth.guard";
import {LoginComponent} from "./core/framework/login/login.component";
import {RegisterComponent} from "./core/framework/register/register.component";
import {UserProfileComponent} from "./core/framework/user-profile/user-profile.component";

import {CompositePageComponent} from './core/framework/material-custom-components/composite/composite-page/composite-page.component';
import {HomeComponent} from "./core/framework/home/home.component";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: UserProfileComponent,
    canActivate: [AuthGuard]
  },
  { path: '', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'application/navigation/:parentObjectTypeId/:parentId/:objectTypeId/:objectId',
    component: CompositePageComponent,
    data : { operation : 'load_nav' },
    canActivate: [AuthGuard]
  },
  { path: 'application/create/object/:parentObjectTypeId/:parentId/:objectTypeId',
    component: CompositePageComponent,
    data : { operation : 'load_form' },
    canActivate: [AuthGuard]
  },
  { path: 'application/object/details/:parentObjectTypeId/:parentId/:objectTypeId/:objectId',
    component: CompositePageComponent,
    data : { operation : 'load_details' },
    canActivate: [AuthGuard]
  },

  // otherwise redirect to home
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
