import React from "react";
import { Resource } from "../../models/Resource";
import MediaTypeSelection from "../MediaTypeSelection/MediaTypeSelection";
import ResourceList from "../ResourceList/ResourceList";
import "./ListingWithFilter.scss";

interface ListingWithFilterProps {
  resources: Array<Resource>;
}

const ListingWithFilter = ({ resources }: ListingWithFilterProps) => {
  return (
    <div className="container">
      <div className="resource-list">
        <ResourceList resources={resources} />
      </div>
      <MediaTypeSelection />
    </div>
  );
};

export default ListingWithFilter;

