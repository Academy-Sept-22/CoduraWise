import { useContext } from "react";
import { backendUrls } from "../../routes/paths";
import { ResourcesContext } from "../ResourcesContext";
import { AddResourceBodyDTO } from "../../models/DataTransferObject";
import { ToastsContext } from "../../store/ToastsContext";

function useResources() {
  const { resources, setResources } = useContext(ResourcesContext);
  const { notifyResourceSubmit } = useContext(ToastsContext);

  const getResources = async () => {
    const response = await fetch(backendUrls.resourceListUrl);
    const data = await response.json();
    if (data && setResources) {
      setResources(data);
    }
  };

  const getResourcesByTag = async (tag: string) => {
    const url = backendUrls.resourcesByTagUrl(tag);
    const response = await fetch(url);
    const data = await response.json();
    if (data && setResources) {
      setResources(data);
    }
  };

  const searchByText = async (text: string) => {
    if (!text) {
      setResources([]);
    } else {
      const url = backendUrls.searchUrl(text);
      const response = await fetch(url);
      const data = await response.json();
      if (data && setResources) {
        setResources(data);
      }
    }
  };

  const postNewResource = async (body: AddResourceBodyDTO) => {
    const response = await fetch(backendUrls.resourcesNew, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });
    notifyResourceSubmit(response.ok);
  };

  return {
    resources,
    getResources,
    getResourcesByTag,
    searchByText,
    postNewResource,
  };
}

export default useResources;
