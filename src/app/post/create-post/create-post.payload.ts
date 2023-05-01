export class CreatePostPayload {
    id : number;
    postName: string;
    subredditName: string;
    url?: string;
    description: string;
    userName: string;
}