import { Injectable } from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { EditorErrorDialogService } from './editor-error-dialog.service';

@Injectable()
export class EditorHttpErrorInterceptor implements HttpInterceptor {
  constructor(private dialog: EditorErrorDialogService) {}

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        const message = this.extractMessage(error);
        if (message) {
          this.dialog.show(message);
        }
        return throwError(error);
      })
    );
  }

  private extractMessage(error: HttpErrorResponse): string | null {
    if (error.error && typeof error.error === 'object' && error.error.message) {
      return error.error.message;
    }
    if (typeof error.error === 'string' && error.error.trim()) {
      return error.error;
    }
    if (error.message) {
      return error.message;
    }
    return null;
  }
}
