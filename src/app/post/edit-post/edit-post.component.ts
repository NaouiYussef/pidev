import { Component } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PostModel } from 'src/app/model/post-model'; 
import { PostService } from 'src/app/service/post.service';
import { CreatePostPayload } from '../create-post/create-post.payload';
import { throwError } from 'rxjs';
@Component({
  selector: 'app-edit-post',
  templateUrl: './edit-post.component.html',
  styleUrls: ['./edit-post.component.css']
})
export class EditPostComponent {
  post: CreatePostPayload ={} as CreatePostPayload;
  postForm: FormGroup;
  id:number;

  constructor(private postService: PostService,private formBuilder: FormBuilder, private route: ActivatedRoute, private router: Router) {  }

  ngOnInit(): void {
    this.post = new CreatePostPayload();
  this.id = this.route.snapshot.params['id'];
  this.postService.getPost(this.id)
    .subscribe(data => {
      console.log(data);
      this.post = data;
    }, error => console.log(error));
  }

  editPost() {
    this.postService.editPost(this.post.id, this.post)
      .subscribe(data => console.log(data), error => console.log(error));
    this.post = new CreatePostPayload();
    this.router.navigate(['/admin/post']);
  }
  discardPost() {
    this.router.navigateByUrl('/admin/post');
  }
}