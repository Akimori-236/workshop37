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
  postForm!: FormGroup
  selectedFile!: File;

  constructor(private fb: FormBuilder,
    private http: HttpService) { }

  ngOnInit(): void {
    this.postForm = this.fb.group({
      comment: this.fb.control<string>('', [Validators.required]),
      picture: this.fb.control<Blob | null>(null)
    });
  }

  onFileSelected(event: any) {
    // collect selected file
    this.selectedFile = event.target.files[0] as File
  }

  savePost() {
    const commentData = this.postForm.value['comment'];
    // send it
    lastValueFrom(this.http.postComment(commentData, this.selectedFile))
      .then(
        (response) => { console.debug(response) }
      )
      .catch(
        (error) => { console.warn(error) }
      );
  }
}

