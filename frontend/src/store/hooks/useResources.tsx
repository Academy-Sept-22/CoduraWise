import { useCallback, useContext, useEffect } from "react";
import { ResourcesContext } from "../ResourcesContext";
import { backendUrls } from "../../routes/paths";

function useResources() {
  const { resources, setResources } = useContext(ResourcesContext);

  const getResources = useCallback(async () => {
    const response = await fetch(backendUrls.resourceListUrl);
    const data = await response.json();
    if (data && setResources) {
      setResources(data);
    }
  }, []);

  useEffect(() => {
    getResources();
  }, []);

  return {
    resources,
  };
}

export default useResources;
