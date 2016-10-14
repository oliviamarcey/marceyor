import 'package:angular2/core.dart';

import '../model/activity.dart';

@Pipe(	
	name: 'CommentPipe',
  	pure: false
)
class CommentPipe extends PipeTransform {

  transform(Activity act) {
  	if (act != null) {
  		return act.type == "COMMENT";
	} else {
		return false;
  	}
  }
}