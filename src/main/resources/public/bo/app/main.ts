import { bootstrap }    from '@angular/platform-browser-dynamic';
import { HTTP_PROVIDERS } from '@angular/http';
import { APP_ROUTER_PROVIDERS } from './routes';
import { AppComponent } from './components/app.component';
import { LocationStrategy,
         HashLocationStrategy } from '@angular/common';

bootstrap(
    AppComponent,
    [ 
      HTTP_PROVIDERS,
      APP_ROUTER_PROVIDERS,
      { provide: LocationStrategy, useClass: HashLocationStrategy }
    ]
)
.catch(err => console.error(err));

