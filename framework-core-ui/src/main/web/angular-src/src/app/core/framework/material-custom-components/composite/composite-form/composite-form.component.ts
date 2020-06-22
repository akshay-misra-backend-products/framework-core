import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CompositeFormService} from './composite-form.service';

@Component({
  selector: 'composite-form',
  templateUrl: './composite-form.component.html',
  styleUrls: ['./composite-form.component.scss']
})
export class CompositeFormComponent implements OnInit {

  @Input() data: any;
  private loadAPI: string;
  private objectTypeId: string;
  private details: DynamicFormConfig;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private compositeFormService: CompositeFormService) {
  }

  ngOnInit() {
    if (this.data) {
      this.details = this.data;
    } else {
      this.load();
    }
  }

  load() {
    this.route.paramMap.subscribe((params) => {
      if (params.has('objectTypeId')) {
        this.objectTypeId = params.get('objectTypeId');
      }
      this.loadAPI = '/application/api/load/Form/config/' + this.objectTypeId;
      this.compositeFormService.loadFormConfig(this.loadAPI).subscribe(
        response => {
          console.log(' response : ' + response);
          this.details = response;
        },);
    });
  }

}

export interface DynamicFormConfig {
  formTitle?: string;
  groups?: any[];
  createAPI?: string;
  cancelLink?: string;
  objectTypeId?: string;
}
