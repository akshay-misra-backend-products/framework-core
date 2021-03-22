import {ChangeDetectionStrategy, Component, EventEmitter, Input, OnChanges, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {DynamicFormService} from './dynamic-form.service';
import {Router} from '@angular/router';
import {ApplicationRouteService} from '../../services/application-route.service';

@Component({
  selector: 'dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: ['./dynamic-form.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DynamicFormComponent implements OnChanges {

  @Output() submit = new EventEmitter();
  @Input() details: any;
  private groups: any[];
  form: FormGroup;
  formGroup: FormGroup;
  formGroups: FormGroup[] = [];
  object: any = {};
  private title: string;
  private cancelLink: string;

  constructor(private dynamicFormService: DynamicFormService,
              private applicationRouteService: ApplicationRouteService,
              private router: Router) {
  }

  ngOnChanges() {
    if (this.details) {
      this.title = this.details.formTitle;
      this.groups = this.details.groups;
      this.cancelLink = this.details.cancelLink;
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
    this.object = this.form.value;
    console.log('------------createAPI: ' + this.details.createAPI);

    this.dynamicFormService.createObject(this.object, this.details.createAPI)
      .subscribe(object => {
          if (object) {
            this.object = {};
            this.router.navigate([this.cancelLink]);
          }
        },
      );
  }

}
