import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import MediaTypeSelection from "../../components/MediaTypeSelection/MediaTypeSelection";
import ResourceList from "../../components/ResourceList/ResourceList";
import useResources from "../../store/hooks/useResources";
import "./ResourceListPage.scss";

const ResourceListPage = () => {
  const { resources, getResources, getResourcesByTag } = useResources();
  const { tag } = useParams();

  useEffect(() => {
    tag ? getResourcesByTag(tag) : getResources();
  }, []);

  return (
    <div className="container">
      <ResourceList resources={resources} />
      <MediaTypeSelection />
    </div>
  );
};

export default ResourceListPage;
