import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SubredditModel } from '../model/subreddit-model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubredditService {
  constructor(private http: HttpClient) { }

  getAllSubreddits(): Observable<Array<SubredditModel>> {
    return this.http.get<Array<SubredditModel>>('http://localhost:8080/api/subreddit');
  }
  getSubreddit(id : number ): Observable<SubredditModel> {
    return this.http.get<SubredditModel>('http://localhost:8080/api/subreddit/' + id );
  }

  createSubreddit(subredditModel: SubredditModel): Observable<SubredditModel> {
    return this.http.post<SubredditModel>('http://localhost:8080/api/subreddit/',
      subredditModel);
      
  }
  updateSubreddit(id :number ,subredditModel: SubredditModel): Observable<any> {
    return this.http.put('http://localhost:8080/api/subreddit/'+ subredditModel.id , subredditModel);
}

deleteById(id: number): Observable<void> {
  return this.http.delete<void>('http://localhost:8080/api/subreddit/' + id);
}
}