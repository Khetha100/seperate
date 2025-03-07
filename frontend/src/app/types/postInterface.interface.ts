import { CommentsInterface } from "./comments.interface";
import { User } from "./user.interface";

export interface PostInterface {
    id?: number;
    imageUrl: string;
    name: string;
    description: string;
    reports?: Report[];
    postLikes?: LikeInterface[];
    date: Date;
    userInfo?: User;
    userInfoId: number;
    comments?: CommentsInterface[];
}


// id?: number;
//   title: string;
//   description: string;
//   subscriptionChannel?: string;
//   communityId?: number;
//   discussionId: number;
//   userInfoId?: number;
//   community?: Community;
//   userInfo?: User;

export interface LikeInterface {
  id?: number;
  userId: number;
  postId: number;
  date: Date;
}