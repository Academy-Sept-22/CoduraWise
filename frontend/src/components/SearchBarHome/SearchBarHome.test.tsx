import { render, screen } from "@testing-library/react";
import React from "react";
import ContextRouterMock from "../../mocks/contextRouterMock";
import SearchBarHome from "./SearchBarHome";

describe("given the searchbar", () => {
  test("should render with the searchbar", () => {
    render(
      <ContextRouterMock>
        <SearchBarHome />
      </ContextRouterMock>,
    );

    const input = screen.getByRole("search");
    expect(input).toBeInTheDocument();
  });
});

