import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import { Observable } from 'rxjs';

import { Kudos } from '../model/kudos';

@Injectable()
export class KudosService {
    
    private restUrl = '/rest/kudos/';
    
    constructor(private http: Http) {}
    
    getAllKudos() {
        return this.http.get(this.restUrl)
            .map( (response:any) => {
                if (response.status == 200) {
                    return response.json() as Kudos[];
                } else {
                    return null
                }
            });
    }
    
    getKudos(id: number) {
        let url = this.restUrl + id;
        return this.http.get(url)
        	.map( (response:any) => {
        		if (response.status == 200) {
        			return response.json() as Kudos;
        		} else {
        			return null
        		}
        	});
    }
    
    save(kudos: Kudos) {
        if (kudos.id) {
            return this.put(kudos);
        }
        return this.post(kudos);
    }
    
    delete(kudos: Kudos) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let url = this.restUrl + kudos.id;
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

    private post(kudos: Kudos) {
        let headers = new Headers({
            'Content-Type': 'application/json'
        });
        return this.http
            .post(this.restUrl, JSON.stringify(kudos), {
                headers: headers
            })
            .map( (response:any) => {
            	if (response.status == 200) {
            		return response.json() as Kudos;
            	}
            });
    }

    private put(kudos: Kudos) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        let url = this.restUrl;
        return this.http
            .put(url, JSON.stringify(kudos), {
                headers: headers
            })
            .map( (response:any) => {
            	if (response.status == 200) {
            		return response.json() as Kudos;
            	} else {
            		return null;
            	}
            });
    }
}