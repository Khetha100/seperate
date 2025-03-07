import { CommonModule, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ReportPostComponent } from '../../components/report-post/report-post.component';
import { CommentsService } from '../../services/comments.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AddContentService } from '../../services/add-content.service';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import * as uuid from 'uuid';
import { CommentsInterface } from '../../types/comments.interface';

@Component({
  selector: 'app-comments-page',
  imports: [CommonModule, RouterLink, ReportPostComponent, ReactiveFormsModule],
  templateUrl: './comments-page.component.html',
  styleUrl: './comments-page.component.css',
})
export class CommentsPageComponent {
  commentForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private commentsService: CommentsService,
    private addContentService: AddContentService,
    private snackBar: MatSnackBar
  ) {
    this.commentForm = this.formBuilder.group({
      commentContent: [''],
    });
  }
  comment: CommentsInterface[] = [];
  ngOnInit(): void {
    this.addContentService.uuidValue = `${uuid.v4().toLowerCase()}`;
    this.commentsService.getComments().subscribe((data) => {
      console.log(data);
      this.comment = data;
    });
  }

  isIconActive: boolean = false;
  location: any;

  toggleIcon(): void {
    this.isIconActive = !this.isIconActive;
  }
  commentIcon: string = 'icons/comment.svg';
  postedComments: number = 0;
  // comments: string[] = [];

  PostComment(newComment: string) {
    // if (newComment.trim() !== "") {

    this.postedComments++;
    // console.log("new comment");
    // }

    const commentsInterface: CommentsInterface = {
      // id: number,
      name: 'Sino Fipaza',
      description: newComment,
      numberOfLikes: 0,
      date: new Date(),
    };

    this.comment.push(commentsInterface);
    this.commentsService.addComments(commentsInterface).subscribe((data) => {
      console.log(data);
      this.comment.unshift(data);
    });
  }

  icon: string = 'icons/heart.svg';
  icon2: string = 'icons/likedHeart.svg';
  clickedPicture: boolean = false;
  likeCount: number = 0;
  OnClick() {
    this.clickedPicture = !this.clickedPicture;
    this.icon = this.clickedPicture ? this.icon2 : 'icons/heart.svg';

    if (this.clickedPicture) {
      this.likeCount++;
    } else {
      this.likeCount--;
    }
  }

  // goBack() {
  //   this.location.back()
  // }
}
