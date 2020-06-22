import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'notification-panel',
  templateUrl: './notification-panel.component.html',
  styleUrls: ['./notification-panel.component.scss']
})
export class NotificationPanelComponent implements OnInit {

  @Output() close = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

}
