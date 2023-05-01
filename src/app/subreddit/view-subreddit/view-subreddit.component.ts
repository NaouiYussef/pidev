import { Component } from '@angular/core';
import { SubredditService } from 'src/app/service/subreddit.service';
import { Router } from '@angular/router';
import { SubredditModel } from 'src/app/model/subreddit-model';
@Component({
  selector: 'app-view-subreddit',
  templateUrl: './view-subreddit.component.html',
  styleUrls: ['./view-subreddit.component.css']
})
export class ViewSubredditComponent {
 public subreddits : SubredditModel[]
 public subreddit : SubredditModel

  constructor(private subredditService: SubredditService, private router: Router) {}

  ngOnInit(): void {
      this.getAllSubreddits();

  }

  private getAllSubreddits(){
    this.subredditService.getAllSubreddits().subscribe(res => {
      this.subreddits = res;
    })  
  }

   deleteSubreddit(id: number) {
    this.subredditService.deleteById(id).subscribe(() => {
      this.subreddits = this.subreddits.filter(subreddit => subreddit.id !== id);
    });
  }
  updateSubreddit(id: number) {
    this.router.navigate(['admin/update-subreddit', id]);


  }
}
