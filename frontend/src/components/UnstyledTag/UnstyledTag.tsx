import React from "react";
import { Link } from "react-router-dom";
import { routerPaths } from "../../routes/paths";

export interface UnstyledTagProps {
  tagName: string;
}

const UnstyledTag = ({ tagName }: UnstyledTagProps) => {
  const path = routerPaths.tag.replace(":tag", tagName);

  return (
    <div className="tag-container">
      <Link to={path}>{tagName}</Link>
    </div>
  );
};

export default UnstyledTag;
