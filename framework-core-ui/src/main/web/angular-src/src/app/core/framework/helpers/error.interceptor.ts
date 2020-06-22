import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Router } from '@angular/router';

import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { UserService } from '../services/user.service';
import {NotifierService} from 'angular-notifier';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private userService: UserService,
              private router: Router,
              private notifierService: NotifierService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      console.log('ErrorInterceptor, err:   ' + JSON.stringify(err));
      if (err.status === 401) {
        // auto logout if 401 response returned from api
        this.userService.logout();
        // location.reload(true);
        this.router.navigate(['/login']);
      }

      const error = err.message || err.statusText;
      console.log(' ' + JSON.stringify(error));
      this.notifierService.notify('error', error);
      return throwError(error);
    }))
  }
}
