import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import { AuthService } from '../service/auth.service';

import { User } from '../model/user';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {  	

    let creds = localStorage.getItem('creds');

    if (creds != null) {
    	if (this.authService.isSignedIn()) {
    		return true;
    	} else {

    		let url = state.url;
    		let user = new User();
       		let credString = atob(creds);
       		let split = credString.split(":");

       		user.username = split[0];
       		user.password = split[1];

      		this.authService.signIn(user)
           		.subscribe((User:any) => {
           			this.router.navigate([url]);
				}, (error:any) => {
					this.router.navigate(['sign-in']);
				});

			return false;
    	}
    } else {
    	localStorage.removeItem('creds');
    	if (this.router.url !== '/sign-in') {
    		this.router.navigate(['sign-in']);
    		return false;
    	} else {
    		return true;
    	}
    }
  }
}
