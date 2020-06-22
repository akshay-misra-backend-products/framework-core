import { Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import { MatTabChangeEvent } from '@angular/material';
import { DynamicTableConfig } from '../../dynamic-table/dynamic-table-config';

@Component({
  selector: 'composite-table',
  templateUrl: './composite-table.component.html',
  styleUrls: ['./composite-table.component.scss']
})
export class CompositeTableComponent implements OnInit, OnChanges {

  @Input() data: CompositeTableConfig;
  private tableConfigs: DynamicTableConfig[] = [];

  constructor() {}

  ngOnInit(): void {
    if (this.data) {
      this.tableConfigs = this.data.tables;
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.data) {
      this.tableConfigs = this.data.tables;
    }
  }

  onTabChange(event: MatTabChangeEvent) {
    console.log('..... selectedTabChange, event: ' + event);
  }
}

export interface CompositeTableConfig {
  tables?: DynamicTableConfig[];
}
