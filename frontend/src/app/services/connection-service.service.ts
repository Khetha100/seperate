import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/internal/Observable';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ConnectionService {
  private apiUrl = environment.SERVER + '/api/v1/';

  constructor(private http: HttpClient,
    private authService: AuthService
  ) {}

  // sendConnectionRequest(
  //   senderId: number,
  //   receiverId: number
  // ): Observable<any> {
   
  //   const params = new HttpParams()
  //     .set('senderId', senderId.toString())
  //     .set('receiverId', receiverId.toString());

  //   return this.http.post<string>(`${this.apiUrl}/connect`, params);
  // }

  // Send connection request
  sendConnectionRequest(senderId: number, receiverId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/connect`, { senderId, receiverId });
  }

  // Accept connection request
  acceptConnectionRequest(connectionId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/accept/${connectionId}`, {});
  }

  // Reject connection request
  rejectConnectionRequest(connectionId: number): Observable<any> {
    return this.http.post(`${this.apiUrl}/reject/${connectionId}`, {});
  }

  // Get user connections
  getUserConnections(userId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/user/${userId}`);
  }
  
}
