import {Component, OnInit, ViewChild, ElementRef, Output, EventEmitter} from '@angular/core';
import {AbstractControl, FormControl, Validators} from '@angular/forms';
import {Observable} from 'rxjs';
import { of } from 'rxjs/observable/of';
import {debounceTime, finalize, map, startWith, switchMap, tap} from 'rxjs/operators';
import {COMMA, ENTER} from '@angular/cdk/keycodes';

import {MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {Field} from '../fields.component';
import {ReferenceService} from './reference.service';

@Component({
  selector: 'reference-view-field',
  templateUrl: './reference.component.html',
  styleUrls: ['./reference.component.scss']
})
export class ReferenceComponent extends Field implements OnInit {
  visible = true;
  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  formControl: AbstractControl;
  // @Input() field: ReferenceConfig;
  private options: any[] = [];
  private selectedOptions: any[];
  private selectedChips: any[];
  private label: string;
  private filteredOptions: Observable<any>;
  private isLoading = false;
  private errorMsg: string;

  @ViewChild('chipsInput') chipList: ElementRef<HTMLInputElement>;
  @Output() updateSelectedOptions = new EventEmitter();

  constructor(private referenceService: ReferenceService) {
    super();
  }

  get isValid() { return this.formControl.valid; }
  get isDirty() { return this.formControl.dirty; }

  /*
  *   TODO: Add prohibit selected feature in future.
  * */
  ngOnInit() {
    this.selectedOptions = [];
    this.selectedChips = [];
    this.label = this.field.label;
    this.formControl = this.form.controls[this.field.name];

    if (this.field.value) {
      if (Array.isArray(this.field.value)) {
        const context = this;
        this.field.value.forEach(function (value) {
          return context.setValueToFormControl(value);
        });
      } else {
        this.setValueToFormControl(this.field.value);
      }
    }

    if (this.field.options === undefined) {
      this.field.options = [];
    }

    if (this.field.loadAPI && this.field.options.length === 0) {
      this.formControl.valueChanges
        .pipe(
          debounceTime(500),
          tap(() => {
            this.errorMsg = '';
            this.isLoading = true;
          }),
          switchMap(value => this.referenceService.loadObjects(
            this.field.loadAPI)
            .pipe(
              finalize(() => {
                this.isLoading = false;
              }),
            )
          )
        )
        .subscribe(data => {
          this.filteredOptions = of(data);
          if (this.selectedOptions.length === 0 && this.field.required) {
            this.formControl.setErrors(Validators.required);
          }
        });
    } else {
      if (this.field.options) {
        this.options = this.field.options;
        this.filteredOptions = this.formControl.valueChanges
          .pipe(
            startWith(null),
            map((value: any | null) => value ? this._filter(value) : this.options.slice())
          );
      }
    }
  }

  remove(chip: any): void {
    const chipIndex = this.selectedChips.indexOf(chip);
    if (chipIndex >= 0) {
      this.selectedChips.splice(chipIndex, 1);
    }
    const index = this.selectedOptions.indexOf(chip);
    if (index >= 0) {
      this.selectedOptions.splice(index, 1);
    }
    this.emitValues();
    this.formControl.setValue(this.selectedOptions);
  }

  emitValues() {
    if (this.field.multiple) {
      this.updateSelectedOptions.emit(this.selectedOptions);
    } else {
      this.updateSelectedOptions.emit(this.selectedOptions.length === 0 ? null : this.selectedOptions.values().next().value);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.setValueToFormControl(event.option.value);
    // this.chipList.errorState = true; // mat-error not working
  }

  setValueToFormControl(value: any) {
    let selectedValue = '';
    if (this.field.refIdAttr) {
      selectedValue = value.id;
    } else {
      selectedValue = value;
    }
    if (this.field.multiple) {
      this.selectedOptions.push(selectedValue);
      this.selectedChips.push(value);
    } else {
      this.selectedOptions.splice(0, this.selectedOptions.length);
      this.selectedOptions.push(selectedValue);
      this.selectedChips.splice(0, this.selectedChips.length);
      this.selectedChips.push(value);
    }
    this.chipList.nativeElement.value = '';
    if (this.field.multiple) {
      this.formControl.setValue(this.selectedOptions);
    } else {
      this.formControl.setValue(selectedValue);
    }
    this.emitValues();
  }

  private _filter(value: any): any[] {
    const filterValue = value ? value.name.toLowerCase() : null;
    return this.options.filter(option => option.name.toLocaleLowerCase().indexOf(value) === 0);
  }
}
