import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {EditorModule} from '@groupdocs.examples.angular/editor';
import {EditorErrorDialogComponent} from './editor-error-dialog.component';
import {EditorHttpErrorInterceptor} from './editor-http-error.interceptor';

@NgModule({
  declarations: [AppComponent, EditorErrorDialogComponent],
  imports: [BrowserModule, HttpClientModule, EditorModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: EditorHttpErrorInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
