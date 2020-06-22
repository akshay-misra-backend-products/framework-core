import {Component, Input, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {Observable, Subscription} from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { CompositeDetailsService } from './composite-details.service';

@Component({
  selector: 'composite-details',
  templateUrl: './composite-details.component.html',
  styleUrls: ['./composite-details.component.scss']
})
export class CompositeDetailsComponent implements OnInit, OnDestroy, OnChanges {

  @Input() data: any;
  private title: string;
  private form: FormGroup;
  private unsubscribe: Subscription;
  private detailsAPI: string;
  private objectTypeId: string;
  private objectId: string;
  public objectLayoutWrapper: any;
  private groups: Observable<any[]>;
  private details: Observable<any>;
  private compositeTableConfig: any;
  private showChildren: boolean;


  constructor(private route: ActivatedRoute,
              private router: Router,
              private compositeDetailsService: CompositeDetailsService) {}

  ngOnDestroy() {
    this.unsubscribe.unsubscribe();
  }

  ngOnInit() {
    if (this.data) {
      this.objectLayoutWrapper = this.data;
      this.init();
    } else {
      this.load();
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.data) {
      this.objectLayoutWrapper = this.data;
      this.init();
    } else {
      this.load();
    }
  }

  load() {
    this.route.paramMap.subscribe((params) => {
      if (params.has('objectId')) {
        this.objectId = params.get('objectId');
      }

      if (params.has('objectTypeId')) {
        this.objectTypeId = params.get('objectTypeId');
      }
      this.detailsAPI = '/application/api/load/details/' + this.objectTypeId + '/' + this.objectId;
      this.compositeDetailsService.loadObjectDetails(this.detailsAPI).subscribe(
        response => {
          this.objectLayoutWrapper = response.objectLayoutWrapper;
          this.init();
        },);
    });
  }

  init() {
    this.title = this.objectLayoutWrapper.objectName;
    this.details = this.objectLayoutWrapper.detail;
    this.groups = this.objectLayoutWrapper.detail.groups;
    this.compositeTableConfig = this.objectLayoutWrapper.children;
    if (this.compositeTableConfig && this.compositeTableConfig.tables.length > 0) {
      this.showChildren = true;
    }

    this.form = new FormGroup({
      fields: new FormControl(JSON.stringify(this.groups))
    });
    this.unsubscribe = this.form.valueChanges.subscribe((update) => {
      this.groups = JSON.parse(update.fields);
    });
  }
}
