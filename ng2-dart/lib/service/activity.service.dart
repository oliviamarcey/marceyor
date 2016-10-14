import 'dart:async';
import 'dart:convert';

import 'package:angular2/core.dart';
import 'package:http/http.dart';

import '../model/activity.dart';

@Injectable()
class ActivityService {

	static final _headers = { 'Content-Type' : 'application/json' };
	static const _restUrl = '/rest/activity/';

	final Client _http;

	ActivityService(this._http);

	Future<List<Activity>> getActivities() async {
		try {
			final response = await _http.get(_restUrl);
			final activitys = _extractData(response)
			.map((value) => new Activity.fromJson(value))
			.toList();

			return activitys;
		} catch (e) {
			throw _handleError(e);
		}
	}

	Future<Activity> getActivity(int id) async {
		var url = '$_restUrl${id}';
		try {
			final response = await _http.get(url);
			final activity = _extractData(response)
			.map((value) => new Activity.fromJson(value));

			return activity;
		} catch (e) {
			throw _handleError(e);
		}
	}

	Future<Activity> save(Activity activity) async {
		if (activity.id != null) {
			return this._put(activity);
		} else {
			return this._post(activity);
		}
	}	

	Future<Activity> delete(int id) async {
		var url = '$_restUrl${id}';
		try {
			final response = await _http.delete(url);
			final activity = _extractData(response)
			.map((value) => new Activity.fromJson(value));

			return activity;
		} catch (e) {
			throw _handleError(e);
		}
	}

	Future<Activity> _post(Activity activity) async {
		try {
     		final response = await _http.post(_restUrl,
         	headers: _headers, body: JSON.encode(activity));
      
      		return new Activity.fromJson(_extractData(response));
   		} catch (e) {
      		throw _handleError(e);
    	}
	}

	Future<Activity> _put(Activity activity) async {
		try {
     		final response = await _http.put(_restUrl,
         	headers: _headers, body: JSON.encode(activity));
      
      		return new Activity.fromJson(_extractData(response));
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