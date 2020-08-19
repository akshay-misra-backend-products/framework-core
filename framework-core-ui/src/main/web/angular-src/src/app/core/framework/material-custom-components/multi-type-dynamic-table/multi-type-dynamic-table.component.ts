import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import {SelectionModel} from "@angular/cdk/collections";
import {MatDialog} from "@angular/material/dialog";
import {SelectObjectTypePopupComponent} from "./select-object-type-popup/select-object-type-popup.component";
import {MultiTypeDynamicTableService} from "./multi-type-dynamic-table.service";
import {DynamicTableConfig} from "./dynamic-table-config";
import {MatReferenceFieldConfig} from "../mat-reference-field/mat-reference-field-config";

@Component({
  selector: 'multi-type-dynamic-table',
  templateUrl: './multi-type-dynamic-table.component.html',
  styleUrls: ['./multi-type-dynamic-table.component.scss']
})
export class MultiTypeDynamicTableComponent implements OnInit {

  objectTypeConfig: MatReferenceFieldConfig;
  selectedObjectTypeId: string;
  dataSource: MatTableDataSource<any>;
  selection = new SelectionModel<any>(true, []);
  isLoadingResults = false;
  data: any[] = [];
  displayedColumns: string[];
  /*topToolbar: any[];
  inlineToolbar: any[];*/
  tableTitle: string;
  createAPI: string;
  deleteAPI: string;
  detailsAPI: string;
  createByObjectType: boolean;
  createButton: boolean;
  createObjectsName: string;
  deleteObjectsName: string;

  @Input() dynamicTableConfig: DynamicTableConfig;
  @Output() deleteObject = new EventEmitter();
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  private columns: any[];

  constructor(private dynamicTableService: MultiTypeDynamicTableService,
              public dialog: MatDialog) {}

  ngOnInit() {
    this.isLoadingResults = true;
    this.createButton = false;
    console.log('ngOnInit, loadAPI: ', this.dynamicTableConfig.loadAPI);
    this.init();

    if (this.dynamicTableConfig.loadAPI) {
      this.loadObject(this.dynamicTableConfig.loadAPI);
    }

    this.objectTypeConfig = {
      'fieldName': 'Load Object Type',
      'multiple': false,
      'refIdAttr': true,
      'loadAPI': '/application/api/5ea86babc8ae3bed0b307a4d/load/all'
    }
  }

  loadObject(loadAPI: string) {
    this.dynamicTableService.loadObjects(loadAPI).subscribe(
      response => {
        console.log(' response : ' + response);
        this.data = response;
        /*this.loading = false;*/
        this.init();
      }, );
  }

  init() {
    this.createObjectsName = 'Create ' + this.dynamicTableConfig.name;
    this.deleteObjectsName = 'Delete ' + this.dynamicTableConfig.name;
    this.createAPI = this.dynamicTableConfig.createAPI;
    if (this.createAPI) {
      this.createButton = true;
    }
    this.deleteAPI = this.dynamicTableConfig.deleteAPI;
    this.detailsAPI = this.dynamicTableConfig.detailsAPI;
    this.createByObjectType = this.dynamicTableConfig.createByObjectType;
    this.data = Object.assign(this.data);
    this.columns = this.getColumns();
    this.displayedColumns = this.columns.map(x => x.columnDef);
    /*this.topToolbar = this.dynamicTableConfig.topToolbar;
    this.inlineToolbar = this.dynamicTableConfig.inlineToolbar;*/
    this.tableTitle = this.dynamicTableConfig.name;

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
    if (this.dynamicTableConfig.columns.length !== 0 ) {
      for (const column of this.dynamicTableConfig.columns) {
        columns.push({
          columnDef: column.columnDef, header: column.header,
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

  openDialog(): void {
    const dialogRef = this.dialog.open(SelectObjectTypePopupComponent, {
      width: '350px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed with result: ' + result);
    });
  }

  selectObjectType(event) {
    console.log('@@@@@ selectObjectType, event: ' + JSON.stringify(event.value));
    this.selectedObjectTypeId = event.value;

    const loadAPI = '/application/api/' + this.selectedObjectTypeId + '/load/all';
    console.log('*** ********* selectObjectType, loadAPI: ' + loadAPI);
    this.loadObject(loadAPI);
  }
}
