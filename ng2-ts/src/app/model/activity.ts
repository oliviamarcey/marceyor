import { Kudos } from './kudos';
import { User } from './user';

export class Activity {
	
	public id: number;
	public kudosId: number;
	public kudos: Kudos;
	public fromUserId: number;
	public fromUser: User;
	public toUserId: number;
	public toUser: User;
	public type: string;
	public comment: string;
	public entryDateTime: Date;


	constructor() { }

}