import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PostInterface } from '../types/postInterface.interface';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private posts: string[] = [
    // 'Education is the key to unlocking potential—whether in the classroom or the pool. 🌊📚 #LifelongLearning #MakingWaves',
  ];

  postArray: PostInterface[] = [];

  apiUrl: string = environment.SERVER;

  constructor(private http:HttpClient) {}

  getPosts(): Observable<PostInterface[]> {
    return this.http.get<PostInterface[]>(this.apiUrl + '/api/v1/posts');
  }

  addPost(postInterface: PostInterface): Observable<PostInterface> {
    return this.http.post<PostInterface>(
      this.apiUrl + '/api/v1/posts',
      postInterface
    );
  }

  // getDate(): Observable<Date> {}

  clearPosts(): void {
    this.posts = [];
  }

  postLikes(postInterface: PostInterface): Observable<PostInterface[]> {
    return this.http.put<PostInterface[]>(
      this.apiUrl + '/api/v1/posts',
      postInterface
    );
  }
}