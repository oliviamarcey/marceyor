import 'dart:async';
import 'dart:convert';
import 'dart:html';

import 'package:angular2/core.dart';
import 'package:angular2/router.dart';

import 'package:http/http.dart';

import '../model/user.dart';

@Injectable()
class AuthService {
	
	static const _restUrl = '/rest/auth/';

	bool signedIn;
	User currentUser;

	final Router _router;
	final Client _http;

	AuthService(this._http, this._router);

	signOut() {
		signedIn = false;
		window.localStorage.remove('creds');
		currentUser = null;
	}

	Future<User> signIn(User user) async {

		var authData = window.btoa("$user.username:$user.password");

		var headas ={ 'Content-Type' : 'application/x-www-form-urlencoded',
						'Authorization': 'Basic $authData' };
		try {
			final response = await _http.get(_restUrl, headers: headas) ;
			final user = _extractData(response)
			.map((value) => new User.fromJson(value));

			_router.navigate(['']);

			return user;
		} catch (e) {
			throw _handleError(e);
		}
	}

	dynamic _extractData(Response resp) => JSON.decode(resp.body)['data'];

	Exception _handleError(dynamic e) {
    	print(e); // for demo purposes only
    	return new Exception('Server error; cause: $e');
  	}
}
