import { Component, ComponentFactoryResolver, OnInit, ViewChild } from '@angular/core';
import { CompositeTableComponent } from '../composite-table/composite-table.component';
import { ActivatedRoute } from '@angular/router';
import { CompositePageService } from './composite-page.service';
import { DynamicComponentDirective } from '../../common-module/dynamic/directives/dynamic-component.directive';
import { DynamicComponentService } from '../../common-module/dynamic/service/dynamic-component.service';
import { BreadCrumbItem } from '../../bread-crumb/bread-crumb-item/bread-crumb-item';
import { CompositeFormComponent } from '../composite-form/composite-form.component';
import { CompositeDetailsComponent } from '../composite-details/composite-details.component';

@Component({
  selector: 'app-composite-page',
  templateUrl: './composite-page.component.html',
  styleUrls: ['./composite-page.component.scss']
})
export class CompositePageComponent implements OnInit {

  @ViewChild(DynamicComponentDirective) dynamicComponent: DynamicComponentDirective;

  private parentObjectTypeId: string;
  private parentId: string;
  private objectId: string;
  private objectTypeId: string;
  private loadAPI: string;
  private operation: string;
  private items: BreadCrumbItem[] = [];
  private pageTitle: string;

  constructor(private route: ActivatedRoute,
              private compositePageService: CompositePageService,
              private componentFactoryResolver: ComponentFactoryResolver,
              private dynamicComponentService: DynamicComponentService) { }

  ngOnInit() {
    this.route.data.subscribe((data) => {
      this.operation = data.operation;
    });

    this.route.paramMap.subscribe((params) => {
      if (params.has('parentObjectTypeId')) {
        this.parentObjectTypeId = params.get('parentObjectTypeId');
      }

      if (params.has('parentId')) {
        this.parentId = params.get('parentId');
      }

      if (params.has('objectId')) {
        this.objectId = params.get('objectId');
      }

      if (params.has('objectTypeId')) {
        this.objectTypeId = params.get('objectTypeId');
      }

      if ('load_nav' === this.operation) {
        this.loadAPI = '/application/api/load/tab/config/'
          + this.parentObjectTypeId
          + '/'
          + this.parentId
          + '/'
          + this.objectTypeId
          + '/'
          + this.objectId;
      } else if ('load_details' === this.operation) {
        this.loadAPI = '/application/api/load/details/'
          + this.parentObjectTypeId
          + '/'
          + this.parentId
          + '/'
          + this.objectTypeId
          + '/'
          + this.objectId;
      } else if ('load_form' === this.operation) {
        this.loadAPI = '/application/api/load/Form/config/'
          + this.parentObjectTypeId
          + '/'
          + this.parentId
          + '/'
          + this.objectTypeId;
      }

      this.compositePageService.loadPageDetails(this.loadAPI).subscribe(
        response => {
          this.items = response.breadCrumbConfig.breadCrumbs;
          this.pageTitle = response.name;
          this.renderDynamicComponent(response);
        }, );
    });
  }

  renderDynamicComponent(response: any) {
    if ('load_nav' === this.operation) {
      this.dynamicComponentService
        .loadComponent(
          this.dynamicComponent.viewContainerRef,
          CompositeTableComponent,
          response.layoutConfig
        );
    } else if ('load_form' === this.operation) {
      this.dynamicComponentService
        .loadComponent(
          this.dynamicComponent.viewContainerRef,
          CompositeFormComponent,
          response.layoutConfig
        );
    } else if ('load_details' === this.operation) {
      this.dynamicComponentService
        .loadComponent(
          this.dynamicComponent.viewContainerRef,
          CompositeDetailsComponent,
          response.layoutConfig
        );
    }
  }
}

