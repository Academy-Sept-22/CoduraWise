import React from "react";

export interface UnstyledTagProps {
  tagName: string;
}

const UnstyledTag = ({ tagName }: UnstyledTagProps) => {
  return <div>{tagName}</div>;
};

export default UnstyledTag;
