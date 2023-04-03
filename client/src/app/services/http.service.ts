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

  //response {"commentId": "ad361820"}
  postComment(commentData: Comment, file: Blob, filename: string): Observable<any> {
    // MAKE THE MULTIPART FORM DATA
    const formData = new FormData();
    formData.append('comment', JSON.stringify(commentData));
    formData.append('imgFile', file, filename);

    return this.http.post(POST_URL, formData)
      .pipe();
  }


}
