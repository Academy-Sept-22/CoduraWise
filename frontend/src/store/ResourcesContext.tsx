import React, {
  createContext,
  Dispatch,
  SetStateAction,
  useState,
} from "react";
import { Resource } from "../models/Resource";

interface ResourceState {
  resources: Array<Resource>;
  setResources: Dispatch<SetStateAction<Resource[]>>;
}

export default function ResourcesContextProvider({
  children,
}: {
  children: JSX.Element;
}) {
  const [resources, setResources] = useState<Array<Resource>>([]);
  console.log(setResources);

  return (
    <ResourcesContext.Provider value={{ resources, setResources }}>
      {children}
    </ResourcesContext.Provider>
  );
}

export const ResourcesContext = createContext({} as ResourceState);
