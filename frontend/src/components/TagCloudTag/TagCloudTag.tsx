import React from "react";
import UnstyledTag, { UnstyledTagProps } from "../UnstyledTag/UnstyledTag";
import "./TagCloudTag.scss"
const TagCloudTag = ({ tagName }: UnstyledTagProps) => {
  return <UnstyledTag tagName={tagName} />;
};

export default TagCloudTag;
