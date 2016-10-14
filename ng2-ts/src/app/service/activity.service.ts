import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Observable } from 'rxjs';

import { Activity } from '../model/activity';

@Injectable()
export class ActivityService {
    
    private restUrl = '/rest/activity/';
    
    constructor(private http: Http) {}
    
    getActivitiesForKudos(kudosId: number) {
        return this.http.get(this.restUrl + '/kudos/' + kudosId)
            .map( (response:any) => {
                if (response.status == 200) {
                    return response.json() as Activity[];
                } else {
                    return null;
                }
            });
    }

    getActivity(id: number) {
        let url = this.restUrl + id;
        return this.http.get(url)
            .map( (response:any) => {
                if (response.status == 200) {
                    return response.json() as Activity;
                } else {
                    return null;
                }
            });
    }
    
    save(activity: Activity) {
        if (activity.id) {
            return this.put(activity);
        }
        return this.post(activity);
    }
    
    delete(activity: Activity) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let url = this.restUrl + activity.id;
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

    private post(activity: Activity) {
        let headers = new Headers({
            'Content-Type': 'application/json'
        });
        return this.http
            .post(this.restUrl, JSON.stringify(activity), {
                headers: headers
            })
            .map( (response:any) => {
                if (response.status == 200) {
                    return response.json() as Activity;
                } else {
                    return null;
                }
            });
    }

    private put(activity: Activity) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let url = this.restUrl;
        return this.http
            .put(url, JSON.stringify(activity), {
                headers: headers
            })
            .map( (response:any) => {
                if (response.status == 200) {
                    return response.json() as Activity;
                } else {
                    return null;
                }
            });
    }
}