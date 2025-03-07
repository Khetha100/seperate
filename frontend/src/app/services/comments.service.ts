import { Injectable } from '@angular/core';
import { CommentsInterface } from '../types/comments.interface';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {
  private comment: string[] = [];
  commentsArray: CommentsInterface[] = [];

  apiUrl: string = environment.SERVER;

  constructor(private http:HttpClient) { }

  getComments(): Observable<CommentsInterface[]> {
    return this.http.get<CommentsInterface[]>(this.apiUrl+'/comments');
  }

  addComments(CommentsInterface: CommentsInterface): Observable<CommentsInterface> {
    console.log(CommentsInterface)
    return this.http.post<CommentsInterface>(this.apiUrl+'/comments', CommentsInterface);
  }
  clearComments(): void {
    this.comment = [];
  }

  putLikes(commentsInterface: CommentsInterface): Observable<CommentsInterface[]> {
    return this.http.put<CommentsInterface[]>(this.apiUrl+'/comments', commentsInterface);
  }
}