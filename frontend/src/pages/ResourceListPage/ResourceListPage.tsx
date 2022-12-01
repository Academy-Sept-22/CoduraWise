import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import ResourceList from "../../components/ResourceList/ResourceList";
import useResources from "../../store/hooks/useResources";

const ResourceListPage = () => {
  const { resources, getResources, getResourcesByTag } = useResources();
  const { tag } = useParams();

  useEffect(() => {
    tag ? getResourcesByTag(tag) : getResources();
  }, []);

  return <ResourceList resources={resources} />;
};

export default ResourceListPage;
