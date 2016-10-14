import 'package:angular2/core.dart';
import 'package:angular2/router.dart';

import '../auth.guard.dart';

import '../model/kudos.dart';

import '../pipe/comment.pipe.dart';
import '../pipe/time-ago.pipe.dart';

@Component(
	selector: 'home',
	templateUrl: './home.component.html',
    pipes: const [     
      CommentPipe,
      TimeAgoPipe
    ],
    directives: const [
    	ROUTER_DIRECTIVES
    ]
)
class HomeComponent {

	Kudos newKudos;
	List<Kudos> allKudos;

	final Router _router;

	HomeComponent(this._router);

	signOut() {
		this._router.navigate(['/sign-in']);
	}
}