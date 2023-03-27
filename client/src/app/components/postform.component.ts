import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-postform',
  templateUrl: './postform.component.html',
  styleUrls: ['./postform.component.css']
})
export class PostformComponent {

  constructor(private fb: FormBuilder) { }

  postForm!: FormGroup

  ngOnInit(): void {
    this.postForm = this.fb.group({
      comment: this.fb.control<string>('', [Validators.required]),
      picture: this.fb.control<Blob>(new Blob)
    })
  }

  savePost() {

  }
}
