class User {
	
	int id;
	String username;
	String password;
	String first;
	String last;

	User(this.id, this.username, this.password, this.first, this.last);

	factory User.fromJson(Map<String, dynamic> user) =>
      new User(_toInt(user['id']), user['username'], user['password'], 
      			user['first'], user['last']);

	Map toJson() => { 'id': id, 'username': username, 'password': password, 
					'first': first, 'last':last };
}

int _toInt(id) => id is int ? id : int.parse(id);