import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatReferenceFieldConfig} from "../../mat-reference-field/mat-reference-field-config";
import {Router} from "@angular/router";

@Component({
  selector: 'app-select-object-type-popup',
  templateUrl: './select-object-type-popup.component.html',
  styleUrls: ['./select-object-type-popup.component.scss']
})
export class SelectObjectTypePopupComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<SelectObjectTypePopupComponent>,
              private router: Router) {}

  objectTypeConfig: MatReferenceFieldConfig;
  selectedObjectTypeId: string;
  createLink: string;

  onNoClick(): void {
    this.dialogRef.close();
  }

  selectObjectType(event) {
    console.log('@@@@@ selectObjectType, event: ' + JSON.stringify(event.value));
    this.selectedObjectTypeId = event.value;
  }

  onOkClick(): void {
    console.log('@@@@@ onOkClick: ' + this.selectedObjectTypeId);
    this.createLink = '/application/design/create/object/' + this.selectedObjectTypeId + '/5ed3d8c70c349b61e1d9631c';
    this.router.navigate([this.createLink]);
    this.dialogRef.close();
  }

  ngOnInit(): void {
    this.objectTypeConfig = {
      'fieldName': 'Object Type',
      'multiple': false,
      'refIdAttr': true,
      'loadAPI': '/application/api/5ea86babc8ae3bed0b307a4d/load/by/parent/-100'
    }
  }
}
