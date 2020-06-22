import {Component, Input, OnChanges, OnInit} from '@angular/core';

@Component({
  selector: 'fields-view-composer',
  templateUrl: './fields-composer.component.html',
  styleUrls: ['./fields-composer.component.scss']
})
export class FieldsComposerComponent implements OnChanges {

  @Input() field: any;
  @Input() form: any;

  get isValid() { return this.form.controls[this.field.name].valid; }
  get isDirty() { return this.form.controls[this.field.name].dirty; }

  constructor() { }

  ngOnChanges() {
  }

}
