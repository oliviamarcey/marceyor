import { ComponentFixture, TestBed, fakeAsync, tick, inject } from '@angular/core/testing';

import { FormsModule } from '@angular/forms';
import { By }              from '@angular/platform-browser';
import { DebugElement }    from '@angular/core';
import { Router } from '@angular/router';

import '../rxjs-extensions';
import { Observable } from 'rxjs/Observable';

import { TimeAgoPipe } from '../pipe/time-ago.pipe';
import { CommentPipe } from '../pipe/comment.pipe';

import { User } from '../model/user';
import { Kudos } from '../model/kudos';
import { Activity } from '../model/activity';

import { AuthService } from '../service/auth.service';
import { UserService } from '../service/user.service';
import { KudosService } from '../service/kudos.service';
import { ActivityService } from '../service/activity.service';

import { HomeComponent } from './home.component';

let comp:    	HomeComponent;
let fixture: 	ComponentFixture<HomeComponent>;
let de:      	DebugElement;
let el:      	HTMLElement;

let badMan:		User;
let goodGuy: 	User;

let aKudos:		Kudos;
let newKudos:	Kudos;

class MockAuthService {

	currentUser: User;

	constructor() {
		this.currentUser = goodGuy;
	}
}

class MockUserService {

	users: User[];

	constructor() {
		this.users = [badMan, goodGuy];
	}

	getUsers() {
		return new Observable( observer => {
			observer.next(this.users);
			observer.complete();
		});
	}

}

class MockKudosService {
	kudos: Kudos[];

	constructor() {
		this.kudos = [aKudos];
	}

	getAllKudos() {
		return new Observable( observer => {
			observer.next(this.kudos);
			observer.complete();
		});
	}

    save(kudos: Kudos) {
		return new Observable( observer => {
			observer.next(newKudos);
			observer.complete();
		});
    }
}

class MockActivityService {
	
	acts: Activity[];

	constructor() {
		this.acts = [];
	}

	getActivitiesForKudos(id: number) {
		return new Observable( observer => {
			observer.next(this.acts);
			observer.complete();
		});
	}
}

class RouterStub {
  navigateByUrl(url: string) { return url; }
  navigate(params: any) { return params; }
}

describe('HomeComponent', ()=> { 

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [ FormsModule ],
			declarations: [ 
					HomeComponent,
					TimeAgoPipe,
					CommentPipe
			],
			providers: [ 
				{ provide: AuthService, useClass: MockAuthService } , 
				{ provide: UserService, useClass: MockUserService } , 
				{ provide: KudosService, useClass: MockKudosService } , 
				{ provide: ActivityService, useClass: MockActivityService } , 
				{ provide: Router, useClass: RouterStub }
			],
		});

		badMan 				= new User();
		badMan.id			= 101;
		badMan.username		= "badman";
		badMan.password 	= "mwuahah";
		badMan.first 		= "Bad";
		badMan.last 		= "Man";

		goodGuy 			= new User();
		goodGuy.id 			= 303;
		goodGuy.username 	= "goodguy";
		goodGuy.password 	= "tehe";
		goodGuy.first 		= "Good";
		goodGuy.last 		= "Guy";

		aKudos 				= new Kudos();
		aKudos.id 			= 200;
		aKudos.fromUserId 	= 303;
		aKudos.fromUser 	= goodGuy;
		aKudos.toUserId 	= 101;
		aKudos.toUser	 	= badMan;
		aKudos.message 		= "You're not so bad, Bad Man";
		aKudos.amount 		= 100;
		aKudos.activities 	= [];

		newKudos 				= new Kudos();
		newKudos.id 			= 600;
		newKudos.fromUserId 	= 101;
		newKudos.fromUser 		= badMan;
		newKudos.toUserId 		= 303;
		newKudos.toUser	 		= goodGuy;
		newKudos.message 		= "Well, Good Guy, you are the worst...";
		newKudos.amount 		= 0;
		newKudos.activities 	= [];

		fixture = TestBed.createComponent(HomeComponent);
		comp = fixture.componentInstance;

	});


	it('should load users on init', fakeAsync(() => {
		tick();
		fixture.detectChanges();

		expect(comp.currentUser).toBe(goodGuy);
		expect(comp.users).toEqual([badMan, goodGuy]);
	}));


	it('should load kudos on init', fakeAsync( () => {
		tick();
		fixture.detectChanges();

		expect(comp.allKudos).toEqual([aKudos]);
	}));

	it('should create a kudos successfuly', fakeAsync( () => {
		tick();
		fixture.detectChanges();

		comp.kudos = newKudos;
		comp.addKudos();

		tick();
		fixture.detectChanges();

	}));

});