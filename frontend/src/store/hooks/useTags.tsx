import { useContext } from "react";
import { backendUrls } from "../../routes/paths";
import { ResourcesContext } from "../ResourcesContext";

function useTags() {
  const { tags, setTags } = useContext(ResourcesContext);

  const getTags = async () => {
    const response = await fetch(backendUrls.tagsUrl);
    const data = await response.json();
    if (data && setTags) {
      setTags(data);
    }
  }

  return {
    tags,
    getTags,
  };
}

export default useTags;