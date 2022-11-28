import React, { useEffect } from "react";
import ResourceList from "../../components/ResourceList/ResourceList";
import useResources from "../../store/hooks/useResources";

const ResourceListPage = () => {
  const {resources, getResources} = useResources();

  useEffect(() => {
    getResources();
  }, [getResources]);

  return <ResourceList resources={resources} />;
};

export default ResourceListPage;
