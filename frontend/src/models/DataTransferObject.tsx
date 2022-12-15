import { MediaType } from "./MediaTypes";

export interface AddResourceBodyDTO {
  title: string;
  link: string;
  tags: Array<string>;
  rating: string;
  mediaType: MediaType;
}

