import React from "react";
import { Tag } from "../../models/Tags";
import TagCloudTag from "../TagCloudTag/TagCloudTag";
import "./TagCloud.scss";

interface TagCloudProps {
  tags: Array<Tag>;
}

const TagCloud = ({ tags }: TagCloudProps) => {
  return (
    <div className="tag-cloud">
      {tags.map((tag) => (
        <TagCloudTag key={tag.tag} tagName={tag.tag} />
      ))}
    </div>
  );
};

export default TagCloud;
