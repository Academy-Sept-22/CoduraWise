import { render, screen } from "@testing-library/react";
import React from "react";
import ContextRouterMock from "../../mocks/contextRouterMock";
import AddResourceButton from "./AddResourceButton";

describe("given the header", () => {
  test("renders a add resource button", () => {
    render(
      <ContextRouterMock>
        <AddResourceButton />
      </ContextRouterMock>
    );

    const button = screen.getByRole("link");

    expect(button).toBeInTheDocument();
  })
})