import { MediaType } from "./MediaTypes";

export interface AddResourceBodyDTO {
  title: string;
  link: string;
  tags: string;
  rating: string;
  mediaType: MediaType;
}

