import { bootstrap }    from '@angular/platform-browser-dynamic';
import { HTTP_PROVIDERS } from '@angular/http';

// Components
import { LoginComponent } from './components/login.component';


bootstrap(
    LoginComponent,
    [ HTTP_PROVIDERS ]
);
