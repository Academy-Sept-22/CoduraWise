import React from "react";
import ResourceList from "../../components/ResourceList/ResourceList";
import useResources from "../../store/hooks/useResources";

const ResourceListPage = () => {
  const { resources } = useResources();

  return <ResourceList resources={resources} />;
};

export default ResourceListPage;
