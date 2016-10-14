import 'user.dart';
import 'kudos.dart';

class Activity {

	int id;
	int kudosId;
	Kudos kudos;
	int fromUserId;
	User fromUser;
	int toUserId;
	User toUser;
	String type;
	String comment;
	DateTime entryDateTime;

	Activity(this.id, this.kudosId, this.kudos, this.fromUserId, this.fromUser, 
			this.toUserId, this.toUser, this.type, this.comment, this.entryDateTime);

	factory Activity.fromJson(Map<String, dynamic> activity) =>
      new Activity( _toInt(activity['id']),
      	      		_toInt(activity['kudosId']),
      				new Kudos.fromJson(activity['kudos']), 
      				_toInt(activity['fromUserId']),
      				new User.fromJson(activity['fromUser']), 
      				_toInt(activity['toUserId']),
      				new User.fromJson(activity['toUser']),
      				activity['type'],
      				activity['comment'], 
      				_toDateTime(activity['entryDateTime'])
      			);

	Map toJson() => { 	'id': id, 'kudosId': kudosId, 'kudos': kudos.toJson(),
					 	'fromUserId': fromUserId, 'fromUser': fromUser.toJson(),
						'toUserId': toUserId, 'toUser': toUser.toJson(),
						'type': type, 'comment': comment,
						'entryDateTime': entryDateTime };
	
}

int _toInt(id) => id is int ? id : int.parse(id);
DateTime _toDateTime(id) => id is DateTime ? id : DateTime.parse(id);