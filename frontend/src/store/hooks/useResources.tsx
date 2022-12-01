import { useContext } from "react";
import { ResourcesContext } from "../ResourcesContext";
import { backendUrls } from "../../routes/paths";

function useResources() {
  const { resources, setResources } = useContext(ResourcesContext);

  const getResources = async () => {
    const response = await fetch(backendUrls.resourceListUrl);
    const data = await response.json();
    if (data && setResources) {
      setResources(data);
    }
  };

  const getResourcesByTag = async (tag: string) => {
    const url = backendUrls.resourcesByTagUrl + tag;
    const response = await fetch(url);
    const data = await response.json();
    if (data && setResources) {
      setResources(data);
    }
  };

  return {
    resources,
    getResources,
    getResourcesByTag,
  };
}

export default useResources;
