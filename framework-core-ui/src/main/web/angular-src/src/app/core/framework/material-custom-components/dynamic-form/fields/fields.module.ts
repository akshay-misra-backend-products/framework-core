import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ReactiveFormsModule} from '@angular/forms';
import {TextboxComponent} from './textbox/textbox.component';
import {TextareaComponent} from './textarea/textarea.component';
import {RadioComponent} from './radio/radio.component';
import {PasswordComponent} from './password/password.component';
import {ListComponent} from './list/list.component';
import {IpaddressComponent} from './ipaddress/ipaddress.component';
import {CurrencyComponent} from './currency/currency.component';
import {CheckboxComponent} from './checkbox/checkbox.component';
import {AttachmentComponent} from './attachment/attachment.component';
import {AngularMaterialModules} from '../../../../../material-module';
import { EmailComponent } from './email/email.component';
import {MatFormFieldModule, MatInputModule} from '@angular/material';
import { ReferenceComponent } from './reference/reference.component';
import { NumberComponent } from './number/number.component';
import { DateComponent } from './date/date.component';

@NgModule({
  declarations: [
    TextboxComponent,
    TextareaComponent,
    RadioComponent,
    PasswordComponent,
    ListComponent,
    IpaddressComponent,
    CurrencyComponent,
    CheckboxComponent,
    AttachmentComponent,
    EmailComponent,
    ReferenceComponent,
    NumberComponent,
    DateComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    AngularMaterialModules,
    MatFormFieldModule,
    MatInputModule
  ],
  exports: [
    TextboxComponent,
    TextareaComponent,
    RadioComponent,
    PasswordComponent,
    ListComponent,
    IpaddressComponent,
    CurrencyComponent,
    CheckboxComponent,
    AttachmentComponent,
    EmailComponent,
    ReferenceComponent,
    NumberComponent,
    DateComponent
  ]
})
export class FieldsModule { }
