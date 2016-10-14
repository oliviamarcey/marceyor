import 'package:angular2/core.dart';
import 'package:angular2/router.dart';

import 'dart:html';

import 'model/user.dart';

import 'service/auth.service.dart';

@Injectable()
class AuthGuard {
	
	final AuthService _authService;
	final Router _router;

	AuthGuard(this._authService, this._router);

	canActivate() {
		var creds = window.localStorage['creds'];

		if (creds != null) {

			var user = new User();
			var credString = window.atob(creds);
			var split = credString.split(":");

			user.username = split[0];
			user.password = split[1];

			this._authService.signIn(user);

			if (_authService.signedIn) {
				return true;
			} else {
				return false;
			}
		} else {
			window.localStorage.remove('creds');
			_router.navigate(['sign-in']);
			return false;
		}
	}
}

canActivateFn(a, b) {
	Injector ij = new Injector();
	AuthGuard ag = ij.get(AuthGuard);

	return ag.canActivate();
}