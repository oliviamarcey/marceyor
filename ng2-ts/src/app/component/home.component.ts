import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { Kudos } from '../model/kudos';
import { Activity } from '../model/activity';
import { User } from '../model/user';

import { KudosService } from '../service/kudos.service';
import { AuthService } from '../service/auth.service';
import { UserService } from '../service/user.service';
import { ActivityService } from '../service/activity.service';

@Component({
    selector: 'home',
    templateUrl: './home.component.html'
})
export class HomeComponent implements OnInit { 

	currentUser: User;

	allKudos: Kudos[];

	kudos: Kudos;
	users: User[];

	message: string;
	loading: boolean;

    constructor(private router: Router, private kudosService: KudosService,
    			private authService: AuthService, private userService: UserService, 
    			private activityService: ActivityService ) {
    	this.kudos = new Kudos();
    }

	ngOnInit() {
		this.currentUser = this.authService.currentUser;

		this.userService.getUsers()
    		.subscribe((response:User[]) => {
    			this.loading = false;
    			this.users = response;
		});

		this.loadKudos();
		this.resetForm();
	}

	addKudos() {   
		this.kudos.fromUserId = this.authService.currentUser.id;

		this.kudosService.save(this.kudos)
			.subscribe((response:any) => {
				this.loadKudos();
				this.resetForm();;
			},
			(err:any) => {
				console.log(err);
			});
	}

	resetForm() {
		this.kudos.toUser = null;
		this.kudos.toUserId = null;
		this.kudos.message = null;
	}

	loadKudos() {
		this.kudosService.getAllKudos()
    		.subscribe((response:Kudos[]) => {
    			this.allKudos = response;

    			for ( let kudos of this.allKudos ) {
    				this.activityService.getActivitiesForKudos(kudos.id)
    					.subscribe( (response:Activity[]) => {
    						kudos.activities = response;

    						let likes = 0, comments = 0;

    						for (let activity of kudos.activities) {
    							if (activity.type == "COMMENT") {
    								comments++;
    							} else if (activity.type == "LIKE" ) {
    								likes++;
    							} 
    						}
    						kudos.numLikes = likes; kudos.numComments = comments;
    					});
    			}
		});
	}

	signOut() {
		this.authService.signOut();
		this.router.navigate(['sign-in']);
	}

	like(kudos: Kudos) {
		let likeAct = new Activity();
		likeAct.fromUserId = this.currentUser.id;
		likeAct.kudosId = kudos.id;
		likeAct.type = "LIKE";


		this.activityService.save(likeAct)
			.subscribe( (res:Activity) => {
				kudos.activities.push(res);
				kudos.numLikes++;
			});
	}

	comment(kudos: Kudos) {
		let commentAct = new Activity();
		commentAct.fromUserId = this.currentUser.id;
		commentAct.kudosId = kudos.id;
		commentAct.type = "COMMENT";
		commentAct.comment = kudos.comment;


		this.activityService.save(commentAct)
			.subscribe( (res:Activity) => {
				res.fromUser = this.currentUser;

				kudos.activities.push(res);
				kudos.numComments++;
				kudos.comment = null;
			});
	}
}