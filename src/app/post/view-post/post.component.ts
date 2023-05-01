import { Component,OnInit } from '@angular/core';
import { PostModel } from '../../model/post-model';
import { PostService } from '../../service/post.service';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';
import { CreatePostPayload } from '../create-post/create-post.payload';
import { Router } from '@angular/router';
@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit  {
  public posts: PostModel[];
  public post : PostModel
  public postPayload : CreatePostPayload
  public editPosts : PostModel;
  public deletePosts : PostModel;
  var = "hello";

  constructor(private postService: PostService, private router: Router) {}

  ngOnInit(): void {
      this.getAllPosts();

  }

  private getAllPosts(){
    this.postService.getAllPosts().subscribe(res => {
      this.posts = res;
    })  
  }

   deletePost(id: number) {
    this.postService.deleteById(id).subscribe(() => {
      this.posts = this.posts.filter(post => post.id !== id);
    });
  }
  updatePost(id: number) {
    this.router.navigate(['admin/edit-post', id]);


  }
}