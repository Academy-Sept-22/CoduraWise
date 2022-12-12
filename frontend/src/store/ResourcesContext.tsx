import React, {
  createContext,
  Dispatch,
  SetStateAction,
  useState,
} from "react";
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

export default function ResourcesContextProvider({
  children,
}: {
  children: JSX.Element;
}) {
  const [resources, setResources] = useState<Array<Resource>>([]);
  const [tags, setTags] = useState<Array<Tag>>([]);

  return (
    <ResourcesContext.Provider
      value={{
        resources,
        setResources,
        tags,
        setTags,
      }}
    >
      {children}
    </ResourcesContext.Provider>
  );
}
export type ContextState = ResourceState & TagState;
export const ResourcesContext = createContext({} as ContextState);
