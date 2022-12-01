import React from "react";
import UnstyledTag, { UnstyledTagProps } from "../UnstyledTag/UnstyledTag";

const TagCloudTag = ({ tagName }: UnstyledTagProps) => {
  return <UnstyledTag tagName={tagName} />;
};

export default TagCloudTag;
