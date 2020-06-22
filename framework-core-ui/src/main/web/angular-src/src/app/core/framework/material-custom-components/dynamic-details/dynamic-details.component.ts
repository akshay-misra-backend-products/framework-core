import {ChangeDetectionStrategy, Component, EventEmitter, Input, OnChanges, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {DynamicDetailsService} from './dynamic-details.service';

@Component({
  selector: 'dynamic-object-details',
  templateUrl: './dynamic-details.component.html',
  styleUrls: ['./dynamic-details.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DynamicDetailsComponent implements OnChanges {

  @Output() submit = new EventEmitter();
  @Input() details: any;
  private groups: any[] = [];
  form: FormGroup;
  formGroup: FormGroup;
  formGroups: FormGroup[] = [];
  object: any = {};
  private title: string;

  constructor(private dynamicDetailsService: DynamicDetailsService) { }

  ngOnChanges() {
    if (this.details) {
      this.title = this.details.objectName;
      this.groups = this.details.groups;
    }
    const fieldCtrls = {};
    for (const g of this.groups) {
      for (const f of g.fields) {
        if (f.type !== 'checkbox') {
          fieldCtrls[f.name] = new FormControl(f.value);
        } else {
          const opts = {};
          for (const opt of f.options) {
            opts[opt.id] = new FormControl(opt.name);
          }
          fieldCtrls[f.name] = new FormGroup(opts);
        }
      }
      this.formGroup = new FormGroup(fieldCtrls);
      g.formGroup = this.formGroup;
      this.formGroups.push(this.formGroup);
    }
    this.form = new FormGroup(fieldCtrls);
  }

  isFormValid(): boolean {
    for (const form of this.formGroups) {
      console.log('isFormValid: ' + form.valid);
      if (!form.valid) {
        return false;
      }
    }
    return true;
  }

  onSubmit() {
    console.log('---- onSubmit, updateAPI: ' + this.details.updateAPI)
    this.object = this.form.value;
    this.object.id = this.details.id;
    console.log('---- onSubmit formGroups data: ' + JSON.stringify(this.object));

    if (this.object.id) {
      this.dynamicDetailsService.updateObject(this.object, this.details.updateAPI)
        .subscribe(object => {
            console.log('updateObject response.body:  ' + object);
            if (object) {
              this.object = {};
            }
          },
        );
    }
  }
}
