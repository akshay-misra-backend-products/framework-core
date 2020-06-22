import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationPanelComponent } from './notification-panel.component';
import {MatButtonModule, MatCommonModule, MatIconModule, MatListModule, MatMenuModule, MatToolbarModule} from '@angular/material';
import {FlexLayoutModule} from '@angular/flex-layout';

@NgModule({
  declarations: [NotificationPanelComponent],
  imports: [
    CommonModule,
    MatCommonModule,
    MatListModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MatToolbarModule,
    FlexLayoutModule,
  ],
  exports: [NotificationPanelComponent]
})
export class NotificationPanelModule { }
