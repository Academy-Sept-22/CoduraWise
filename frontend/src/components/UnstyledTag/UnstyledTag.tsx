import React from "react";
import { Link } from "react-router-dom";

export interface UnstyledTagProps {
  tagName: string;
}

const UnstyledTag = ({ tagName }: UnstyledTagProps) => {
  return (
    <div>
      <Link to="linkToTagName">{tagName}</Link>
    </div>
  );
};

export default UnstyledTag;
