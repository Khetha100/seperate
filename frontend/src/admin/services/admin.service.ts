import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';




interface SignInRequest {
  email: string;
  password: string;
}

interface SignUpRequest {
  firstName: string;
  lastName: string;
  email: string;
  password: string;
}

interface SignInResponse {
  status: string;
  adminUser: {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
  };
}

interface SignUpResponse {
  status: string;
  adminUser: {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
  };
}


@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private apiUrl = environment.SERVER + '/api/v1/adminAuth';

  constructor(private http: HttpClient) {}

  // Dashboard
  getDashboardData(): Observable<any> {
    return this.http.get(`${this.apiUrl}/dashboard/summary`);
  }

  // temporarilyDeleteUser(userId: number): Observable<string> {
  //   return this.http.put<string>(
  //     `${this.apiUrl}/${userId}/temporarily-delete`,
  //     {}
  //   );
  // }

  // // Dashboard
  // getDashboardData(): Observable<any> {
  //   return this.http.get(`${this.apiUrl}/dashboard/summary`);
  // }

  // Content Moderation
  getReportedContent(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/content/reported`);
  }

  deleteContent(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/content/${id}`);
  }

  // User Management
  getUsers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/users`);
  }

  temporarilyDeleteUser(id: number): Observable<void> {
    return this.http.post<void>(
      `${this.apiUrl}/users/${id}/temporarily-delete`,
      {}
    );
  }

  permanentlyDeleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/users/${id}`);
  }

  // Analytics
  getAnalyticsData(): Observable<any> {
    return this.http.get(`${this.apiUrl}/analytics/user-roles`);
  }

  getNewUsersCount(startDate: string): Observable<number> {
    return this.http.get<number>(
      `${this.apiUrl}/analytics/new-users?startDate=${startDate}`
    );
  }

  // Donations
  getDonations(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/donations`);
  }

  getDonationSummary(): Observable<any> {
    return this.http.get(`${this.apiUrl}/donations/summary`);
  }

  getTotalDonations(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/donations/total`);
  }

  getUniqueDonorsCount(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/donations/unique-donors`);
  }

  signIn(credentials: SignInRequest): Observable<SignInResponse> {
    return this.http.post<SignInResponse>(
      `${this.apiUrl}/adminSignIn`,
      credentials
    );
  }

  signUp(userData: SignUpRequest): Observable<any> {
    console.log("Inside signupService");
    return this.http.post(`${this.apiUrl}/adminSignUp`, userData);
  }

  signOut(): Observable<any> {
    return this.http.get(`${this.apiUrl}/adminSignOut`);
  }

  getCurrentAdmin(): Observable<any> {
    return this.http.get(`${this.apiUrl}/current-admin`);
  }
}