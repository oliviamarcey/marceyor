import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Observable } from 'rxjs';

import { User } from '../model/user';

@Injectable()
export class UserService {
    
    private restUrl = '/rest/user/';
    
    constructor(private http: Http) {}
    
    getUsers() {
        return this.http.get(this.restUrl)
            .map( (response:any) => {
                if (response.status == 200) {
                    return response.json() as User[];
                } else {
                    return null;
                }
            });
    }

    getUser(id: number) {
        let url = this.restUrl + id;
        return this.http.get(url)
            .map( (response:any) => {
                if (response.status == 200) {
                    return response.json() as User;
                } else {
                    return null;
                }
            });
    }
    
    save(user: User) {
        if (user.id) {
            return this.put(user);
        }
        return this.post(user);
    }
    
    delete(user: User) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let url = this.restUrl + user.id;
        return this.http
            .delete(url, {
                headers: headers
            })
            .map( (response:any) => {
                if (response.status == 200) {
                    return true;
                } else {
                    return false;
                }
            });
    }

    private post(user: User) {
        let headers = new Headers({
            'Content-Type': 'application/json'
        });
        return this.http
            .post(this.restUrl, JSON.stringify(user), {
                headers: headers
            })
            .map( (response:any) => {
                if (response.status == 200) {
                    return response.json() as User;
                } else {
                    return null;
                }
            });
    }

    private put(user: User) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let url = this.restUrl;
        return this.http
            .put(url, JSON.stringify(user), {
                headers: headers
            })
            .map( (response:any) => {
                if (response.status == 200) {
                    return response.json() as User;
                } else {
                    return null;
                }
            });
    }
}