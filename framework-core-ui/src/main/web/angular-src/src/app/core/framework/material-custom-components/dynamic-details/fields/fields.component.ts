import {Input} from '@angular/core';
import {FormGroup} from '@angular/forms';

export abstract class Field {

  @Input() field: any = {};
  @Input() form: FormGroup;
  get isValid() { return this.form.controls[this.field.name].valid; }
  get isDirty() { return this.form.controls[this.field.name].dirty; }

  getErrorMessage(): string {
    if (this.form.controls[this.field.name].hasError('required')) {
      return 'You must enter a value';
    }
    return '';
  }
}
