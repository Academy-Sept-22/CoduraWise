import { render, screen } from "@testing-library/react";
import React from "react";
import ContextRouterMock from "../../mocks/contextRouterMock";
import Header from "./Header";

describe("given the homepage", () => {
  test("should render with the logo", () => {
    render(
      <ContextRouterMock>
        <Header />
      </ContextRouterMock>,
    );

    const image = screen.getByAltText("Codurance Logo");
    expect(image).toBeInTheDocument();
  });

  test("should render a nav component", () => {
    render(
      <ContextRouterMock>
        <Header />
      </ContextRouterMock>,
    );

    const navComponent = screen.getByRole("navigation");
    expect(navComponent).toBeInTheDocument();
  });

  test("should render a Heading of CoduraWise", () => {
    render(
      <ContextRouterMock>
        <Header />
      </ContextRouterMock>,
    );

    const heading = screen.getByText("CoduraWise");
    expect(heading).toBeInTheDocument();
  });

  test("should render a search bar", () => {
    render(
      <ContextRouterMock>
        <Header />
      </ContextRouterMock>,
    );

    const searchBar = screen.getByRole("search");
    expect(searchBar).toBeInTheDocument();
  });
});
