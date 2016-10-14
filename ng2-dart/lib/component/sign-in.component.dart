import 'package:angular2/core.dart';
import 'package:angular2/router.dart';

import '../model/user.dart';

import '../service/auth.service.dart';

@Component(
	selector: 'sign-in',
	templateUrl: './sign-in.component.html')
class SignInComponent {
	
	User model;
	
	String errorMessage;
	bool loading;

	final Router _router;
	final AuthService _authService;

	SignInComponent(this._router, this._authService);

	signIn() {
		loading = true;
		errorMessage = null;

		this._authService.signIn(model)
			.then((User user) {
				this.loading = false;
				this._router.navigate(['']);
			})
			.catchError((error) {
				this.loading = false;
				this.errorMessage = "Nope";
			});
	}

	signUp() {
		_router.navigate(['sign-up']);
	}
}
