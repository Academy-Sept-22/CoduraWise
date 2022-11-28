import ResourcesContextProvider from "../store/ResourcesContext";
import React from "react";
import { MemoryRouter } from "react-router";

export default function ContextRouterMock({
  children,
  initialEntries,
}: {
  children: JSX.Element;
  initialEntries?: Array<string>;
}) {
  return (
    <ResourcesContextProvider>
      <MemoryRouter initialEntries={initialEntries}>{children}</MemoryRouter>
    </ResourcesContextProvider>
  );
}
