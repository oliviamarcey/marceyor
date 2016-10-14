import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../model/user';
import { AuthService } from '../service/auth.service';

@Component({
    selector: 'sign-in',
    templateUrl: './sign-in.component.html'
})
export class SignInComponent { 

    model: User;
    errorMessage: string;
    loading: boolean;

    constructor(private router: Router, private authService: AuthService) {   
        this.model = new User();
	 }

    signIn() {
      this.loading = true;
      this.errorMessage = null;
      setTimeout(() => { 
              this.authService.signIn(this.model)
                  .subscribe((result:User) => {
                    this.loading = false;
                     if (result) {
                       this.router.navigate(['']);
                     }
            },
            (err: any) => {
              this.loading = false;
              if (err.status == 401) {
                this.errorMessage = "Invalid username or password";
              }  
            });
      }, 1000);
    }

    signUp() {
        this.router.navigate(['sign-up']);
    }

}