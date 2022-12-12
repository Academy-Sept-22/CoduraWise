import React, { useState } from "react";
import { MediaType } from "../../models/MediaTypes";
import { Resource } from "../../models/Resource";
import MediaTypeSelection from "../MediaTypeSelection/MediaTypeSelection";
import ResourceList from "../ResourceList/ResourceList";
import "./ListingWithFilter.scss";

interface ListingWithFilterProps {
  resources: Array<Resource>;
}

const ListingWithFilter = ({ resources }: ListingWithFilterProps) => {
  const [filterMediaTypes, setFilterMediaTypes] = useState<Array<MediaType>>(
    [],
  );

  return (
    <div className="container">
      <div className="resource-list">
        <ResourceList
          resources={resources}
          filterMediaTypes={filterMediaTypes}
        />
      </div>
      <MediaTypeSelection setFilterMediaTypes={setFilterMediaTypes} />
    </div>
  );
};

export default ListingWithFilter;
