import { Component } from '@angular/core';
import { CommentService } from 'src/app/service/comment.service';
import { CommentPayload } from 'src/app/model/comment-model';
import { Router } from '@angular/router';
@Component({
  selector: 'app-view-comment',
  templateUrl: './view-comment.component.html',
  styleUrls: ['./view-comment.component.css']
})
export class ViewCommentComponent {
  public comments : CommentPayload[];
  public comment : CommentPayload;

  constructor(private commentService: CommentService, private router: Router) {}

  ngOnInit(): void {
      this.getAllComments();

  }

  private getAllComments(){
    this.commentService.getAllComments().subscribe(res => {
      this.comments = res;
    })  
  }

   deleteComment(id: number) {
    this.commentService.deleteById(id).subscribe(() => {
      this.comments = this.comments.filter(comment => comment.id !== id);
    });
  }

}
