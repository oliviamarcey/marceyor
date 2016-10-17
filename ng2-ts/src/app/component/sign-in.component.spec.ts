import { ComponentFixture, TestBed, fakeAsync, tick, inject } from '@angular/core/testing';

import { FormsModule } from '@angular/forms';
import { By }              from '@angular/platform-browser';
import { DebugElement }    from '@angular/core';
import { Router } from '@angular/router';

import '../rxjs-extensions';
import {Observable} from 'rxjs/Observable';

import { User } from '../model/user';

import { AuthService } from '../service/auth.service';
 
import { SignInComponent } from './sign-in.component';

let comp:    	SignInComponent;
let fixture: 	ComponentFixture<SignInComponent>;
let de:      	DebugElement;
let el:      	HTMLElement;

let badMan: 	User;
let goodGuy: 	User;

class MockAuthService {

	signIn(user: User) {
		return new Observable(observer => {
			if (user != null && user.id == 101) {
        		observer.error({ status: 401 });
   				observer.complete();
			} else if (user != null && user.id == 303) {
				observer.next(user);
   				observer.complete();	
			}
      	});
	}
}

class RouterStub {
  navigateByUrl(url: string) { return url; }
  navigate(params: any) { return params; }
}

describe('SignInComponent', ()=> { 

	badMan = new User();
	badMan.id = 101;
	badMan.username = "badman";
	badMan.password = "mwuahah";

	goodGuy = new User();
	goodGuy.id = 303;
	goodGuy.username = "goodguy";
	goodGuy.password = "tehe";

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [FormsModule],
			declarations: [ SignInComponent ],
			providers: [ 
				{ provide: AuthService, useClass: MockAuthService } , 
				{ provide: Router, useClass: RouterStub }
			],
	});

	fixture = TestBed.createComponent(SignInComponent);
	comp = fixture.componentInstance;

	});


	it('should show error on bad credentials', fakeAsync(() => {
		comp.model = badMan;
		comp.signIn();

		tick();

  		fixture.detectChanges();

		de = fixture.debugElement.query(By.css('div.alert'));
		expect(de).not.toBe(null);

		el = de.nativeElement;
		expect(el.textContent).toContain("Invalid username or password");

	}));

	it('should show no error on successful login and route', fakeAsync( () => {

		comp.model = goodGuy;
		comp.signIn();

		tick();

  		fixture.detectChanges();

		de = fixture.debugElement.query(By.css('div.alert'));
		expect(de).toBe(null);

	}));
});