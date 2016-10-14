import 'dart:async';
import 'dart:convert';

import 'package:angular2/core.dart';
import 'package:http/http.dart';

import '../model/user.dart';

@Injectable()
class UserService {

	static final _headers = { 'Content-Type' : 'application/json' };
	static const _restUrl = '/rest/user/';

	final Client _http;

	UserService(this._http);

	Future<List<User>> getUsers() async {
		try {
			final response = await _http.get(_restUrl);
			final users = _extractData(response)
			.map((value) => new User.fromJson(value))
			.toList();

			return users;
		} catch (e) {
			throw _handleError(e);
		}
	}

	Future<User> getUser(int id) async {
		var url = '$_restUrl${id}';
		try {
			final response = await _http.get(url);
			final user = _extractData(response)
			.map((value) => new User.fromJson(value));

			return user;
		} catch (e) {
			throw _handleError(e);
		}
	}

	Future<User> save(User user) async {
		if (user.id != null) {
			return this._put(user);
		} else {
			return this._post(user);
		}
	}	

	Future<User> delete(int id) async {
		var url = '$_restUrl${id}';
		try {
			final response = await _http.delete(url);
			final user = _extractData(response)
			.map((value) => new User.fromJson(value));

			return user;
		} catch (e) {
			throw _handleError(e);
		}
	}

	Future<User> _post(User user) async {
		try {
     		final response = await _http.post(_restUrl,
         	headers: _headers, body: JSON.encode(user));
      
      		return new User.fromJson(_extractData(response));
   		} catch (e) {
      		throw _handleError(e);
    	}
	}

	Future<User> _put(User user) async {
		try {
     		final response = await _http.put(_restUrl,
         	headers: _headers, body: JSON.encode(user));
      
      		return new User.fromJson(_extractData(response));
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