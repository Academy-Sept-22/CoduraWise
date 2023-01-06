import React from "react";
import { Tag } from "../../models/Tags";
import SearchBarHome from "../SearchBarHome/SearchBarHome";
import TagCloudTag from "../TagCloudTag/TagCloudTag";
import "./TagCloud.scss";

interface TagCloudProps {
  tags: Array<Tag>;
}

const TagCloud = ({ tags }: TagCloudProps) => {
  return (
    <div className="home__container">
      <div className="home__bodySearch">
        <SearchBarHome />
      </div>
      <div className="home__tag-cloud">
        {tags
          ? tags.map((tag) => <TagCloudTag key={tag.tag} tagName={tag.tag} />)
          : null}
      </div>
    </div>
  );
};

export default TagCloud;
