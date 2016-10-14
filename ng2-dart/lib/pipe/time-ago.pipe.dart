import 'package:angular2/core.dart';

@Pipe(
	name: "TimeAgoPipe"
)
class TimeAgoPipe extends PipeTransform {

	transform(DateTime time) {

		DateTime now = new DateTime.now();
		Duration diff = now.difference(time);

		if ( diff.inDays > 0) {

			int days = diff.inDays;
			return "$days days ago";

		} else if ( diff.inHours > 0 ) {

			int hours = diff.inHours;
			return "$hours hours ago";

		} else if ( diff.inMinutes > 0 ) {

			int mins = diff.inMinutes;
			return "$mins minutes ago";

		} else if ( diff.inSeconds > 0 ) {

			int secs = diff.inSeconds;
			return "$secs seconds ago";

		} else {
			return "Just now";
		}
	}
}