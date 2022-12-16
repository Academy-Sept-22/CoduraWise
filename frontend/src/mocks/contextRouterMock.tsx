import ResourcesContextProvider from "../store/ResourcesContext";
import React from "react";
import { MemoryRouter } from "react-router";
import ToastsContextProvider from "../store/ToastsContext";

export default function ContextRouterMock({
  children,
  initialEntries,
}: {
  children: JSX.Element;
  initialEntries?: Array<string>;
}) {
  return (
    <ToastsContextProvider>
      <ResourcesContextProvider>
        <MemoryRouter initialEntries={initialEntries}>{children}</MemoryRouter>
      </ResourcesContextProvider>
    </ToastsContextProvider>
  );
}
