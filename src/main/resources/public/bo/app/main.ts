import { bootstrap }    from '@angular/platform-browser-dynamic';
import { HTTP_PROVIDERS } from '@angular/http';
import {Component, provide} from '@angular/core';
import {
  ROUTER_DIRECTIVES,
  ROUTER_PROVIDERS,
} from '@angular/router';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';


// Components
import { AppComponent } from './components/app.component';

bootstrap(
    AppComponent,
    [ 
      HTTP_PROVIDERS,
      provide(LocationStrategy, {useClass: HashLocationStrategy})
    ]
);
