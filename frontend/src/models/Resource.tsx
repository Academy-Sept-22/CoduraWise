import { MediaType } from "./MediaTypes";

export interface Resource {
  id: number;
  title: string;
  link: string;
  mediaType: MediaType;
  averageRating: number;
}
