import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Comment } from '../models/comment';
import { Observable } from 'rxjs';

const POST_URL: string = "http://localhost:8080/api/post"

@Injectable({
  providedIn: 'root'
})
export class HttpService {

  constructor(private http: HttpClient) { }

  //response {"imageKey": "myobjects/ad361820.jpg"}
  postComment(commentData: Comment): Observable<any> {
    return this.http.post(POST_URL, commentData)
      .pipe()
  }

}
