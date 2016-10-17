import { Pipe } from '@angular/core';
import { Activity } from '../model/activity';


// Tell Angular2 we're creating a Pipe with TypeScript decorators
@Pipe({
  name: 'CommentPipe',
  pure: false
})
export class CommentPipe {

  transform(value:any) {
  	if (value != null) {
    	return value.filter((activity:Activity) => {
      	return activity.type == 'COMMENT';
    	});
	  } else {
		  return null;
  	}
  }
}