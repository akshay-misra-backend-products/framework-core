import { Component, OnInit } from '@angular/core';
import {Field} from '../fields.component';

@Component({
  selector: 'attachment-view-field',
  templateUrl: './attachment.component.html',
  styleUrls: ['./attachment.component.scss']
})
export class AttachmentComponent extends Field implements OnInit {
  ngOnInit() {
  }
}
