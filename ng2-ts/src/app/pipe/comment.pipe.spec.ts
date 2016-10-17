import { CommentPipe } from './comment.pipe';

import { Activity } from '../model/activity';

describe('CommentPipe', () => {

	let pipe = new CommentPipe();

	let likeAct = new Activity();
		likeAct.fromUserId = 101;
		likeAct.kudosId = 202;
		likeAct.type = "LIKE";

	let followAct = new Activity();
		followAct.fromUserId = 101;
		followAct.toUserId = 102;
		followAct.type = "FOLLOW";

	let commentAct = new Activity();
		commentAct.fromUserId = 101;
		commentAct.kudosId = 202;
		commentAct.type = "COMMENT";

	let allActs = [likeAct, followAct, commentAct ];
	let filteredActs = [commentAct];

	it('should filter out non-comment activities', () => {
		expect(pipe.transform(allActs)).toEqual(filteredActs);
	});
});