import React from "react";
import { Resource } from "../../models/Resource";
import ResourceCard from "../ResourceCard/ResourceCard";
import "./ResourceList.scss";

interface ResourceListProps {
  resources: Array<Resource>;
}

const resourcesFilter = (resource: Resource) => { return true; }

const ResourceList = ({ resources }: ResourceListProps) => {
  return (
    <ul className="resource-list__container">
      {resources
        ? resources.filter(resourcesFilter).map((resource) => (
            <li className="resource-list__list-element" key={resource.id}>
              <ResourceCard
                title={resource.title}
                link={resource.link}
                rating={resource.averageRating}
              />
            </li>
          ))
        : null}
    </ul>
  );
};

export default ResourceList;
