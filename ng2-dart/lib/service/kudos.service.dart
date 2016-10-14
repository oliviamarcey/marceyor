import 'dart:async';
import 'dart:convert';

import 'package:angular2/core.dart';
import 'package:http/http.dart';

import '../model/kudos.dart';

@Injectable()
class KudosService {

	static final _headers = { 'Content-Type' : 'application/json' };
	static const _restUrl = '/rest/kudos/';

	final Client _http;

	KudosService(this._http);

	Future<List<Kudos>> getAllKudos() async {
		try {
			final response = await _http.get(_restUrl);
			final kudoss = _extractData(response)
			.map((value) => new Kudos.fromJson(value))
			.toList();

			return kudoss;
		} catch (e) {
			throw _handleError(e);
		}
	}

	Future<Kudos> getKudos(int id) async {
		var url = '$_restUrl${id}';
		try {
			final response = await _http.get(url);
			final kudos = _extractData(response)
			.map((value) => new Kudos.fromJson(value));

			return kudos;
		} catch (e) {
			throw _handleError(e);
		}
	}

	Future<Kudos> save(Kudos kudos) async {
		if (kudos.id != null) {
			return this._put(kudos);
		} else {
			return this._post(kudos);
		}
	}	

	Future<Kudos> delete(int id) async {
		var url = '$_restUrl${id}';
		try {
			final response = await _http.delete(url);
			final kudos = _extractData(response)
			.map((value) => new Kudos.fromJson(value));

			return kudos;
		} catch (e) {
			throw _handleError(e);
		}
	}

	Future<Kudos> _post(Kudos kudos) async {
		try {
     		final response = await _http.post(_restUrl,
         	headers: _headers, body: JSON.encode(kudos));
      
      		return new Kudos.fromJson(_extractData(response));
   		} catch (e) {
      		throw _handleError(e);
    	}
	}

	Future<Kudos> _put(Kudos kudos) async {
		try {
     		final response = await _http.put(_restUrl,
         	headers: _headers, body: JSON.encode(kudos));
      
      		return new Kudos.fromJson(_extractData(response));
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