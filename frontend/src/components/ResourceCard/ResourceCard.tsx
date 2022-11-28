import React from "react";
import "./ResourceCard.scss";

interface ResourceCardProps {
  title: string;
  link: string;
}

const ResourceCard = ({ title, link }: ResourceCardProps) => {
  return (
    <article className="resource-card__container">
      <h3 className="resource-card__title">
        <a className="resource-card__link" href={link}>
          Title: {title}
        </a>
      </h3>
    </article>
  );
};

export default ResourceCard;
