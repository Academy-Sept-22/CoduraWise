import React from "react";
import { Tag } from "../../models/Tags";
import TagCloudTag from "../TagCloudTag/TagCloudTag";

interface TagCloudProps {
  tags: Array<Tag>;
}

const TagCloud = ({ tags }: TagCloudProps) => {
  return (
    <div>
      {tags.map((tag) => (
        <TagCloudTag key={tag.tag} tagName={tag.tag} />
      ))}
    </div>
  );
};

export default TagCloud;
