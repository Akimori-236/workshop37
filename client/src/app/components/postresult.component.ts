import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { HttpService } from '../services/http.service';

@Component({
  selector: 'app-postresult',
  templateUrl: './postresult.component.html',
  styleUrls: ['./postresult.component.css']
})
export class PostresultComponent {
  postIDForm!: FormGroup

  constructor(
    private fb: FormBuilder,
    private fileSvc: HttpService) { }

  ngOnInit(): void {
    this.postIDForm = this.fb.group({
      postID: this.fb.control<string>('', [Validators.required]),
    })

  }
  search() {
    const postID:string = this.postIDForm.value['postID']
    this.fileSvc.getComment(postID)
  }
}
