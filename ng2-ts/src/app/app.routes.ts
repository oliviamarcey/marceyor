import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './shared/auth.guard';

import { SignInComponent } from './component/sign-in.component';
import { HomeComponent } from './component/home.component';

const routes: Routes = [                         
    {
        path: 'sign-in',
        component: SignInComponent
    },
    {
        path: '',
        component: HomeComponent,
        canActivate: [AuthGuard]
    }, 
    {
        path: '',
        redirectTo: '',
        pathMatch: 'full'
    }
];

export const routing = RouterModule.forRoot(routes);
export const routedComponents = [ SignInComponent, HomeComponent ];