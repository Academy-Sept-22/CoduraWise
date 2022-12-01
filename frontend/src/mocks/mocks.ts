import { Resource } from "../models/Resource";

import { Tag } from "../models/Tags";

export const mockResources: Array<Resource> = [
  { id: 1, title: "Java", link: "Java Link", averageRating: 1 },
  { id: 2, title: "Python", link: "Python Link", averageRating: 4 },
  { id: 3, title: "Javascript", link: "Javascript Link", averageRating: 3 },
];

export const mockTags: Array<Tag> = [
  { tag: "Java" },
  { tag: "JavaScript" },
  { tag: "Agile" },
  { tag: "React" },
  { tag: "Beginners" },
  { tag: "HTML" },
  { tag: "Leadership" },
  { tag: "S1" },
  { tag: "S2" },
  { tag: "S3" },
];
