import {ComponentFactoryResolver, Injectable, Type, ViewContainerRef} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DynamicComponentService {

  constructor(private componentFactoryResolver: ComponentFactoryResolver) { }

  loadComponent(viewContainerRef: ViewContainerRef, component: Type<any>, data: any) {
    const componentFactory = this.componentFactoryResolver
      .resolveComponentFactory(component);
    viewContainerRef.clear();
    const componentRef = viewContainerRef.createComponent(componentFactory);
    const dynamicComponent: any =  componentRef.instance;
    dynamicComponent.data = data;
  }
}
