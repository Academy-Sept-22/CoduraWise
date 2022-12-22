import React from "react";
import { MediaType } from "../../models/MediaTypes";
import { Resource } from "../../models/Resource";
import ResourceCard from "../ResourceCard/ResourceCard";
import "./ResourceList.scss";

interface ResourceListProps {
  resources: Array<Resource>;
  filterMediaTypes: Array<MediaType>;
}

const ResourceList = ({ resources, filterMediaTypes }: ResourceListProps) => {
  const isFilterEmpty = () => filterMediaTypes.length === 0;

  const resourceMediaTypeIsInFilter = (resource: Resource) => {
    return filterMediaTypes.includes(resource.mediaType);
  };

  const isResourceDisplayable = (resource: Resource) => {
    return isFilterEmpty() || resourceMediaTypeIsInFilter(resource);
  };

  return (
    <ul className="resource-list__container">
      {resources
        ? resources.filter(isResourceDisplayable).map((resource) => (
            <li className="resource-list__list-element" key={resource.id}>
              <ResourceCard
                title={resource.title}
                link={resource.link}
                rating={resource.averageRating}
                mediaType={resource.mediaType}
              />
            </li>
          ))
        : null}
    </ul>
  );
};

export default ResourceList;
