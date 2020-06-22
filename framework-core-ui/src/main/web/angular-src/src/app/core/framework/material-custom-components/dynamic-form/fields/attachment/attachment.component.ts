import {Component, Input, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {Field} from '../field.component';

@Component({
  selector: 'attachment-field',
  templateUrl: './attachment.component.html',
  styleUrls: ['./attachment.component.scss']
})
export class AttachmentComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
