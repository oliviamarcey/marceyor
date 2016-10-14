import { Injectable, EventEmitter } from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Observable } from 'rxjs';

import { User } from '../model/user';

@Injectable()
export class AuthService {
 
  private restUrl = '/rest/auth';
  private signedIn: boolean;

  public currentUser: User;
  public currentUserChanged = new EventEmitter();
  public authLoading = new EventEmitter();

  constructor(private http: Http ) { }
 
  signOut() {
    this.signedIn = false;
    localStorage.removeItem('creds');
    this.currentUser = null;
    this.currentUserChanged.emit(this.currentUser);
  }
 
  signIn(user: User) {

    this.authLoading.emit(true);

    let authData = btoa(user.username + ":" + user.password);

    let headers = new Headers({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + authData
    });
    
    return this.http
      .get(this.restUrl, {
        headers: headers
      })
      .map((res:any) => {
        this.authLoading.emit(false);
        if (res.status == 200) {
          
          this.signedIn = true;
          localStorage.setItem('creds', authData);
          this.currentUser = res.json() as User;

          this.currentUserChanged.emit(this.currentUser);

          return this.currentUser;
        } else {
          return null;
        }
      });
   }

   isSignedIn() {
     return this.signedIn == true && this.currentUser != null && localStorage.getItem('creds') != null;
   }
}