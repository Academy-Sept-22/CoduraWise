import { render, screen } from "@testing-library/react";
import React from "react";
import ContextRouterMock from "../../mocks/contextRouterMock";
import HeaderNavigation from "./HeaderNavigation";

describe("Given a Header navigation", () => {
  test("the 'Home' and 'Resources' links should be available", () => {
    render(
      <ContextRouterMock>
        <HeaderNavigation />
      </ContextRouterMock>,
    );

    const homeLink = screen.getByRole("link", { name: "Home" });
    const resourcesLink = screen.getByRole("link", { name: "Resources" });

    expect(homeLink).toBeInTheDocument();
    expect(resourcesLink).toBeInTheDocument();
  });

  test("should render a Add Resource Link", () => {
    render(
      <ContextRouterMock>
        <HeaderNavigation />
      </ContextRouterMock>,
    );

    const addResourceButton = screen.getByRole("link", {
      name: "Add Resource",
    });
    expect(addResourceButton).toBeInTheDocument();
  });
});
