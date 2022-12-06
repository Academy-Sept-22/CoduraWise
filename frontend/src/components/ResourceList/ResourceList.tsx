import React, { useContext } from "react";
import { Resource } from "../../models/Resource";
import { ResourcesContext } from "../../store/ResourcesContext";
import ResourceCard from "../ResourceCard/ResourceCard";
import "./ResourceList.scss";


interface ResourceListProps {
  resources: Array<Resource>;
}

const ResourceList = ({ resources }: ResourceListProps) => {
  const { filterMediaTypes } = useContext(ResourcesContext);

  const isFilterEmpty = () => {
    return filterMediaTypes.length === 0;
  }
  
  const resourceMediaTypeIsInFilter = (resource: Resource) => {
    return filterMediaTypes.includes(resource.mediaType);
  }
  
  const isResourceDisplayable = (resource: Resource) => { 
    return isFilterEmpty() || resourceMediaTypeIsInFilter(resource); 
  }
  

  return (
    <ul className="resource-list__container">
      { resources
        ? resources.filter(isResourceDisplayable).map((resource) => (
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
