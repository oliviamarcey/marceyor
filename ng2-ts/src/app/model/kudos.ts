import { User } from './user';
import { Activity } from './activity';

export class Kudos {
	
	public id: number;
	public fromUserId: number;
	public fromUser: User;
	public toUserId: number;
	public toUser: User;
	public amount: number;
	public message: string;
	public entryDateTime: Date;

	public activities: Activity[];
	public numLikes: number;
	public numComments: number;

	public comment: string;
	
	constructor() { }

}