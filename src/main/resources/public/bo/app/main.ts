import { bootstrap }    from '@angular/platform-browser-dynamic';
import { HTTP_PROVIDERS } from '@angular/http';
import { APP_ROUTER_PROVIDERS } from './routes/routes';
import { AppComponent } from './components/app.component';
import { LocationStrategy,
         HashLocationStrategy } from '@angular/common';
import { disableDeprecatedForms, provideForms } from '@angular/forms';

bootstrap(
    AppComponent,
    [ 
      HTTP_PROVIDERS,
      APP_ROUTER_PROVIDERS,
      { provide: LocationStrategy, useClass: HashLocationStrategy },
      provideForms(),
      disableDeprecatedForms()
    ]
)
.catch(err => console.error(err));

