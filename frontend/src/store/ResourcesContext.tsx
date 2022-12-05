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
interface FilterMediaTypeState {
  filterMediaTypes: Array<MediaType>;
  setFilterMediaTypes: Dispatch<SetStateAction<MediaType[]>>;
}

export default function ResourcesContextProvider({
  children,
}: {
  children: JSX.Element;
}) {
  const [resources, setResources] = useState<Array<Resource>>([]);
  const [tags, setTags] = useState<Array<Tag>>([]);
  const [filterMediaTypes, setFilterMediaTypes] = useState<Array<MediaType>>([]);

  return (
    <ResourcesContext.Provider value={{ 
      resources, setResources, 
      tags, setTags,
      filterMediaTypes, setFilterMediaTypes,
      }}>
      {children}
    </ResourcesContext.Provider>
  );
}
export type ContextState = ResourceState & TagState & FilterMediaTypeState;
export const ResourcesContext = createContext({} as ContextState);
