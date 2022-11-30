import React from "react";
import "./StarRating.scss";

interface StarRatingProps {
  rating: number;
}

const StarRating = ({ rating }: StarRatingProps) => {
  const style = { "--rating": rating } as React.CSSProperties;

  return (
    <div
      className="star-container"
      style={style}
      aria-label="Rating of this product is 2.3 out of 5."
    />
  );
};

export default StarRating;
