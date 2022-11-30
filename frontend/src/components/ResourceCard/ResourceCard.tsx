import React from "react";
import StarRating from "../StarRating/StarRating";
import "./ResourceCard.scss";

interface ResourceCardProps {
  title: string;
  link: string;
  rating: number;
}

const ResourceCard = ({ title, link, rating }: ResourceCardProps) => {
  return (
    <article className="resource-card__container">
      <h3 className="resource-card__title">
        <a className="resource-card__link" href={link}>
          Title: {title}
        </a>
      </h3>
      <StarRating rating={rating} />
    </article>
  );
};

export default ResourceCard;
