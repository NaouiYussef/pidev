import { Component } from '@angular/core';
import { CommentPayload } from 'src/app/model/comment-model';
import { Router,ActivatedRoute } from '@angular/router';
import { CommentService } from 'src/app/service/comment.service';
import { HttpClient } from '@angular/common/http';
import { PostService } from 'src/app/service/post.service';
import { throwError } from 'rxjs';
import { CreatePostPayload } from 'src/app/post/create-post/create-post.payload';
import { FormGroup, FormBuilder, Validators,FormControl} from '@angular/forms';

@Component({
  selector: 'app-create-comment',
  templateUrl: './create-comment.component.html',
  styleUrls: ['./create-comment.component.css']
})
export class CreateCommentComponent {
  commentPayload : CommentPayload ={} as CommentPayload;
  postPayload: Array<CreatePostPayload>;
  createCommentForm : FormGroup;
  constructor(private commentService: CommentService,private router: Router,private http: HttpClient, private postService :PostService) {}
  ngOnInit(){
    this.postService.getAllPosts().subscribe((data) => {
      this.postPayload = data;
    }, error => {
      throwError(error);
    });
  }

  onSubmit() {
    this.commentService.postComment(this.commentPayload).subscribe(data => {
      console.log('Comment created successfully!');
      this.router.navigate(['/admin/view-comment']);
    }, error => {
      console.log('Error creating post:', error);
    });
  }

  discardPost() {
    this.router.navigateByUrl('/admin/comments');
  }
}
