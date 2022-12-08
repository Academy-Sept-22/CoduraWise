import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import ListingWithFilter from "../../components/ListingWithFilter/ListingWithFilter";
import useResources from "../../store/hooks/useResources";
import "./ResourceListPage.scss";

const ResourceListPage = () => {
  const { resources, getResources, getResourcesByTag } = useResources();
  const { tag } = useParams();

  useEffect(() => {
    tag ? getResourcesByTag(tag) : getResources();
  }, []);

  return <ListingWithFilter resources={resources} />;
};

export default ResourceListPage;
