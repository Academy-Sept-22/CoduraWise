import React, {
  createContext,
  Dispatch,
  SetStateAction,
  useState,
} from "react";
import { MediaType } from "../models/MediaTypes";
import { Resource } from "../models/Resource";
import { Tag } from "../models/Tags";

interface ResourceState {
  resources: Array<Resource>;
  setResources: Dispatch<SetStateAction<Resource[]>>;
}
interface TagState {
  tags: Array<Tag>;
  setTags: Dispatch<SetStateAction<Tag[]>>;
}
interface MediaTypeFilterState {
  mediaTypes: Array<MediaType>;
  setMediaTypes: Dispatch<SetStateAction<MediaType[]>>;
}

export default function ResourcesContextProvider({
  children,
}: {
  children: JSX.Element;
}) {
  const [resources, setResources] = useState<Array<Resource>>([]);
  const [tags, setTags] = useState<Array<Tag>>([]);
  const [mediaTypes, setMediaTypes] = useState<Array<MediaType>>([]);

  return (
    <ResourcesContext.Provider value={{ 
      resources, setResources, 
      tags, setTags,
      mediaTypes, setMediaTypes,
      }}>
      {children}
    </ResourcesContext.Provider>
  );
}

export const ResourcesContext = createContext({} as ResourceState & TagState & MediaTypeFilterState);
