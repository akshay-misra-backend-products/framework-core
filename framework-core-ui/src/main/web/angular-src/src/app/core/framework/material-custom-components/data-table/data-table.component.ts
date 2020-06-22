import {Component, OnInit, ViewChild, Input, Output, EventEmitter} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {SelectionModel} from '@angular/cdk/collections';
import {DataTableConfig} from './data-table-config';
import {MatTableService} from './mat-table.service';

@Component({
  selector: 'data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.scss']
})
export class DataTableComponent implements OnInit {
  dataSource: MatTableDataSource<any>;
  selection = new SelectionModel<any>(true, []);
  isLoadingResults = false;
  data: any[] = [];
  displayedColumns: string[];
  topToolbar: any[];
  inlineToolbar: any[];
  tableTitle: string;

  @Input() dataTableConfig: DataTableConfig;
  @Output() deleteObject = new EventEmitter();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  private columns: any[];

  constructor(private matTableService: MatTableService) {}

  ngOnInit() {
    this.isLoadingResults = true;
    this.data = Object.assign(this.dataTableConfig.data);
    console.log('ngOnInit, loadAPI: ', this.dataTableConfig.loadAPI);
    if (this.data.length === 0 && this.dataTableConfig.loadAPI) {
      this.matTableService.loadObjects(this.dataTableConfig.loadAPI).subscribe(
        response => {
          console.log(' response : ' + response);
          this.data = response;
          /*this.loading = false;*/
          this.init();
        }, );
    } else {
      this.init();
    }
  }

  init() {
    this.columns = this.getColumns();
    this.displayedColumns = this.columns.map(x => x.columnDef);
    console.log('ngOnInit, dataTableConfig: ', this.dataTableConfig);
    this.topToolbar = this.dataTableConfig.topToolbar;
    this.inlineToolbar = this.dataTableConfig.inlineToolbar;
    this.tableTitle = this.dataTableConfig.tableTitle;

    // Assign the data to the data source for the table to render
    this.dataSource = new MatTableDataSource(this.data);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.isLoadingResults = false;
  }

  applyFilter(filterValue: string) {
    this.isLoadingResults = true;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
    this.isLoadingResults = false;
  }

/** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }

  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: any): string {
    if (!row) {
      return `${this.isAllSelected() ? 'select' : 'deselect'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.id + 1}`;
  }

/** Delete selected row */
  removeSelectedRows() {
    this.isLoadingResults = true;
    const rows: string[] = [];
    this.selection.selected.forEach(item => {
        console.log('removeSelectedRows, item: ', item);
        rows.push(item.id);
        let index: number = this.data.findIndex(d => d === item);
        console.log('removeSelectedRows, index: ', index);
        this.dataSource.data.splice(index, 1);
    });
    this.deleteItems(rows);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.selection = new SelectionModel<Element>(true, []);
    this.isLoadingResults = false;
  }

  /** Delete current row */
  removeCurrentRow(row) {
    this.isLoadingResults = true;
    const rows: string[] = [];
    console.log('removeCurrentRow, row: ', row);
    rows.push(row.id);
    let index: number = this.data.findIndex(d => d === row);
    console.log('removeCurrentRow, index: ', index);
    this.dataSource.data.splice(index, 1);
    this.deleteItems(rows);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.selection = new SelectionModel<Element>(true, []);
    this.isLoadingResults = false;
  }

  /** Delete row */
  deleteItem(row) {
    console.log('deleteItem, current object id: ', row.id);
    const rows: string[] = [];
    rows.push(row.id);
    this.deleteItems(rows);
  }

  /** Delete rows */
  deleteItems(rows) {
    console.log('deleteItems, rows: ', rows);
    this.deleteObject.emit(rows);
  }

  private getColumns(): string[] {
    const columns: any[] = [];
    columns.push({columnDef: 'select'});
    if (this.data.length !== 0 ) {
      for (const key of Object.keys(this.data[0])) {
        columns.push({
          columnDef: key, header: key.toLocaleUpperCase(),
          dataName: function (row) {
            return row[this.columnDef];
          }
        });
      }
    }

    columns.push({ columnDef: 'actions', header: 'ACTIONS',
      dataName: function(row) {return row[this.columnDef];}});
    return columns;
  }
}
