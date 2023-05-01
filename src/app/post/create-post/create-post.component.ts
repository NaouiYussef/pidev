import { Component , OnInit} from '@angular/core'
import { CreatePostPayload } from './create-post.payload';
import { PostService } from 'src/app/service/post.service';
import { Router,ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators,FormControl} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { PostModel } from 'src/app/model/post-model';
import { SubredditModel } from 'src/app/model/subreddit-model';
import { SubredditService } from 'src/app/service/subreddit.service';
import { throwError } from 'rxjs';


@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent {
    postPayload: CreatePostPayload ={} as CreatePostPayload ;
    createPostForm: FormGroup;
    items: any= [];
    posts: PostModel[] ;
    subreddits: Array<SubredditModel>;

  
    constructor(private postService: PostService,private router: Router,private http: HttpClient,  private subredditService: SubredditService) {}
   
    ngOnInit() {
      this.createPostForm = new FormGroup({
        postName: new FormControl('', Validators.required),
        subredditName: new FormControl('', Validators.required),
        url: new FormControl('', Validators.required),
        description: new FormControl('', Validators.required),
      });
      this.subredditService.getAllSubreddits().subscribe((data) => {
        this.subreddits = data;
      }, error => {
        throwError(error);
      });
    }
  
    createPost() {
      this.postPayload.postName = this.createPostForm.get('postName').value;
      this.postPayload.subredditName = this.createPostForm.get('subredditName').value;
      this.postPayload.url = this.createPostForm.get('url').value;
      this.postPayload.description = this.createPostForm.get('description').value;
  
      this.postService.createPost(this.postPayload).subscribe((data) => {
        this.router.navigateByUrl('/admin/post');
      }, error => {
        throwError(error);
      })
    }
  
    discardPost() {
      this.router.navigateByUrl('/admin/post');
    }
  

  
  }