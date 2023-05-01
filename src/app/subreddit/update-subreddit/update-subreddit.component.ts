import { Component } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { SubredditModel } from 'src/app/model/subreddit-model';
import { SubredditService } from 'src/app/service/subreddit.service';

@Component({
  selector: 'app-update-subreddit',
  templateUrl: './update-subreddit.component.html',
  styleUrls: ['./update-subreddit.component.css']
})
export class UpdateSubredditComponent {
  subreddit: SubredditModel ={} as SubredditModel;
  id : number;

  constructor(private subredditService: SubredditService, private router: Router, private route: ActivatedRoute ){  }
  ngOnInit(): void {
    this.subreddit = new SubredditModel();
  this.id = this.route.snapshot.params['id'];
  this.subredditService.getSubreddit(this.id)
    .subscribe(data => {
      console.log(data);
      this.subreddit = data;
    }, error => console.log(error));
  }

  OnSubmit() {
    this.subredditService.updateSubreddit(this.subreddit.id, this.subreddit)
      .subscribe(data => console.log(data), error => console.log(error));
    this.subreddit = new SubredditModel();
    this.router.navigate(['/admin/view-subreddit']);
  }
  discardPost() {
    this.router.navigateByUrl('/admin/view-subreddit');
  }
}
