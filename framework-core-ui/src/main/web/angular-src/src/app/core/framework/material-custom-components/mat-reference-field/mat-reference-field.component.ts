import {Component, OnInit, Input, ViewChild, ElementRef, Output, EventEmitter} from '@angular/core';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import { of } from 'rxjs/observable/of';
import {debounceTime, finalize, map, startWith, switchMap, tap} from 'rxjs/operators';
import {COMMA, ENTER} from '@angular/cdk/keycodes';

import {MatReferenceFieldConfig} from './mat-reference-field-config';
import {MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {MatReferenceFieldService} from './mat-reference-field.service';

@Component({
  selector: 'mat-reference-field',
  templateUrl: './mat-reference-field.component.html',
  styleUrls: ['./mat-reference-field.component.scss']
})
export class MatReferenceFieldComponent implements OnInit {
  visible = true;
  selectable = true;
  removable = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  formControl = new FormControl();
  @Input() fieldConfig: MatReferenceFieldConfig;
  @Input() appearance: string;
  private options: any[] = [];
  private selectedOptions: any[];
  private selectedChips: any[];
  private fieldName: string;
  private filteredOptions: Observable<any>;
  private isLoading = false;
  private errorMsg: string;

  @ViewChild('chipsInput') chipsInput: ElementRef<HTMLInputElement>;
  @Output() updateSelectedOptions = new EventEmitter();

  constructor(private matReferenceFieldService: MatReferenceFieldService) {
  }

  /*
  *   TODO: Add prohibit selected feature in future.
  * */
  ngOnInit() {
    console.log('******* appearance: ' + this.appearance)
    if (!this.appearance) {
      this.appearance = 'outline';
    }
    this.selectedOptions = [];
    this.selectedChips = [];
    this.fieldName = this.fieldConfig.fieldName;

    if (this.fieldConfig.options === undefined) {
      this.fieldConfig.options = [];
    }

    if (this.fieldConfig.options.length === 0 && this.fieldConfig.loadAPI) {
      this.formControl.valueChanges
        .pipe(
          debounceTime(500),
          tap(() => {
            this.errorMsg = '';
            this.isLoading = true;
          }),
          switchMap(value => this.matReferenceFieldService.loadObjects(
            this.fieldConfig.loadAPI)
            .pipe(
              finalize(() => {
                this.isLoading = false;
              }),
            )
          )
        )
        .subscribe(data => {
          this.filteredOptions = of(data);
        });
    } else {
      this.options = this.fieldConfig.options;
      this.filteredOptions = this.formControl.valueChanges
        .pipe(
          startWith(null),
          map((value: any | null ) => value ? this._filter(value) : this.options.slice())
        );
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
  }

  emitValues() {
    if (this.fieldConfig.multiple) {
      this.updateSelectedOptions.emit(this.selectedOptions);
    } else {
      console.log('--------- emitValues, ' + this.selectedOptions.values().next());
      this.updateSelectedOptions.emit(this.selectedOptions.length === 0 ? null :
        this.selectedOptions.values().next());
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.setValueToFormControl(event.option.value);
    // this.chipList.errorState = true; // mat-error not working
  }

  setValueToFormControl(value: any) {
    let selectedValue = '';
    if (this.fieldConfig.refIdAttr) {
      selectedValue = value.id;
    } else {
      selectedValue = value;
    }
    if (this.fieldConfig.multiple) {
      this.selectedOptions.push(selectedValue);
      this.selectedChips.push(value);
    } else {
      this.selectedOptions.splice(0, this.selectedOptions.length);
      this.selectedOptions.push(selectedValue);
      this.selectedChips.splice(0, this.selectedChips.length);
      this.selectedChips.push(value);
    }
    this.chipsInput.nativeElement.value = '';
    if (this.fieldConfig.multiple) {
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
