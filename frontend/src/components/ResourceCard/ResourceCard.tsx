import React from "react";
import StarRating from "../StarRating/StarRating";
import "./ResourceCard.scss";

interface ResourceCardProps {
  title: string;
  link: string;
  rating: number;
  mediaType: string;
}

const ResourceCard = ({
  title,
  link,
  rating,
  mediaType,
}: ResourceCardProps) => {
  const imageForCard = (mediaType: string) => {
    if (mediaType === "book") {
      return "book";
    }
    if (mediaType === "article") {
      return "article";
    }
    if (mediaType === "video") {
      return "video";
    }
    if (mediaType === "tutorial") {
      return "tutorial";
    }
    if (mediaType === "course") {
      return "course";
    }
  };

  return (
    <article className="resource-card__container">
      <a
        className="resource-card__link"
        href={link}
        target="_blank"
        rel="noreferrer"
      >
        <img
          className="resource-card__image"
          src={`/ResourceCardImage/${imageForCard(mediaType)}.jpg`}
          alt={imageForCard(mediaType)}
        />
        <StarRating rating={rating} />
        <h3 className="resource-card__title">{title}</h3>
      </a>
    </article>
  );
};

export default ResourceCard;
