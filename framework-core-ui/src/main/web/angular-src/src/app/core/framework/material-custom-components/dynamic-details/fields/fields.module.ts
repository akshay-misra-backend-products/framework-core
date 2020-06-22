import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AttachmentComponent } from './attachment/attachment.component';
import { CheckboxComponent } from './checkbox/checkbox.component';
import { CurrencyComponent } from './currency/currency.component';
import { EmailComponent } from './email/email.component';
import { IpAddressComponent } from './ip-address/ip-address.component';
import { ListComponent } from './list/list.component';
import { PasswordComponent } from './password/password.component';
import { RadioComponent } from './radio/radio.component';
import { ReferenceComponent } from './reference/reference.component';
import { TextareaComponent } from './textarea/textarea.component';
import { TextboxComponent } from './textbox/textbox.component';
import {ReactiveFormsModule} from '@angular/forms';
import {AngularMaterialModules} from '../../../../../material-module';
import {MatFormFieldModule, MatInputModule} from '@angular/material';
import { NumberComponent } from './number/number.component';
import { DateComponent } from './date/date.component';

@NgModule({
  declarations: [
    AttachmentComponent,
    CheckboxComponent,
    CurrencyComponent,
    EmailComponent,
    IpAddressComponent,
    ListComponent,
    PasswordComponent,
    RadioComponent,
    ReferenceComponent,
    TextareaComponent,
    TextboxComponent,
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
    CurrencyComponent,
    CheckboxComponent,
    AttachmentComponent,
    EmailComponent,
    ReferenceComponent,
    IpAddressComponent,
    NumberComponent,
    DateComponent
  ]
})
export class FieldsModule { }
