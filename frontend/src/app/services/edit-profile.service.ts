import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EditProfileService {

  constructor(private http: HttpClient) { }
    private apiUrl = environment.SERVER + '/api';


    getProfile(id: string) {
      return this.http.get(`${this.apiUrl}/profile/${id}`);
    }

  //thiss ervice method sends data to the backend withthe updated profile data
  updateProfile(userId: string, profileData: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/profile/${userId}`, profileData);
  }

  //this gets the edited profile from the backedn
  getEdittedProfile(userId: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/profile/${userId}`);
  }

}
