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
      <ResourceList resources={resources} />
      <MediaTypeSelection />
    </div>
  );
};

export default ListingWithFilter;

