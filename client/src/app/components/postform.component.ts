import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpService } from '../services/http.service';
import { lastValueFrom } from 'rxjs';

@Component({
  selector: 'app-postform',
  templateUrl: './postform.component.html',
  styleUrls: ['./postform.component.css']
})
export class PostformComponent {

  constructor(private fb: FormBuilder,
    private http: HttpService) { }

  postForm!: FormGroup

  ngOnInit(): void {
    this.postForm = this.fb.group({
      comment: this.fb.control<string>('', [Validators.required]),
      picture: this.fb.control<Blob>(new Blob)
    })
  }

  savePost() {
    const values = this.postForm.value
    const response = lastValueFrom(this.http.postComment(values))
    console.debug(response)
  }
}
