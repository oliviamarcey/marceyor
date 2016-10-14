import 'user.dart';
import 'activity.dart';

class Kudos {
	
	int id;
	int fromUserId;
	User fromUser;
	int toUserId;
	User toUser;
	int amount;
	String message;
	DateTime entryDateTime;

	List<Activity> activities;
	int numLikes;
	int numComments;

	String comment;

	Kudos(this.id, this.fromUserId, this.fromUser, this.toUserId, this.toUser,
		 	this.amount, this.message, this.entryDateTime);

	factory Kudos.fromJson(Map<String, dynamic> kudos) =>
      new Kudos( 	_toInt(kudos['id']),
      				_toInt(kudos['fromUserId']),
      				new User.fromJson(kudos['fromUser']), 
      				_toInt(kudos['toUserId']),
      				new User.fromJson(kudos['toUser']),
      				_toInt(kudos['amount']),
      				kudos['message'], 
      				_toDateTime(kudos['entryDateTime'])
      			);

	Map toJson() => { 	'id': id, 'fromUserId': fromUserId, 'fromUser': fromUser.toJson(),
						'toUserId': toUserId, 'toUser': toUser.toJson(), 
						'amount': amount, 'message': message, 'entryDateTime': entryDateTime };
}

int _toInt(id) => id is int ? id : int.parse(id);
DateTime _toDateTime(id) => id is DateTime ? id : DateTime.parse(id);