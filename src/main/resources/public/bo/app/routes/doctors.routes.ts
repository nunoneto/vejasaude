import { RouterConfig } from '@angular/router';
import { ListDoctorsComponent } from '../components/menus/doctors.component'

export const DoctorRoutes: RouterConfig = [
    {
        path: 'doctors',
        component: ListDoctorsComponent,
    }

];