import { Component } from '@angular/core';
import { SubredditModel } from 'src/app/model/subreddit-model';
import { SubredditService } from 'src/app/service/subreddit.service';
import { HttpClient } from '@angular/common/http';
import { Router,ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-subreddit',
  templateUrl: './create-subreddit.component.html',
  styleUrls: ['./create-subreddit.component.css']
})
export class CreateSubredditComponent {
  subredditModel : SubredditModel ={} as SubredditModel;

  constructor(private subredditService: SubredditService,private router: Router,private http: HttpClient) {}



  onSubmit() {
    this.subredditService.createSubreddit(this.subredditModel).subscribe(data => {
      console.log('Subreddit created successfully!');
      this.router.navigate(['/admin/view-subreddit']);
    }, error => {
      console.log('Error creating Subreddit:', error);
    });
  }
  discardPost() {
    this.router.navigateByUrl('/admin/view-subreddit');
  }
}
