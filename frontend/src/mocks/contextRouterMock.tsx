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

/* export default function ContextRouterMock({
  children,
}: {
  children: JSX.Element;
}) {
  const history = createMemoryHistory();

  return (
    <ResourcesContextProvider>
      <Router location={history.location} navigator={history}>
        {children}
      </Router>
    </ResourcesContextProvider>
  );
} */
