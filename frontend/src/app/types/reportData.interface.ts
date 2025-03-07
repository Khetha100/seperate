import { PostInterface } from "./postInterface.interface";

export interface ReportData {
    id: number;
    reason: ReportReason,
    postId: PostInterface,
    description: string;
}

export enum ReportReason {
  InappropriateContent = "InappropriateContent",
  Spam = "Spam",
  OffensiveLanguage = "OffensiveLanguage",
  Other = "Other"
}




