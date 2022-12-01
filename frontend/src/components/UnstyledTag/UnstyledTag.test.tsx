import { render, screen } from "@testing-library/react";
import React from "react";
import ContextRouterMock from "../../mocks/contextRouterMock";
import UnstyledTag from "./UnstyledTag";

describe("Given a 'Java' tag", () => {
  describe("when visiting the home page", () => {
    test("then it displays the name Java", () => {
      render(
        <ContextRouterMock>
          <UnstyledTag tagName={"Java"} />
        </ContextRouterMock>,
      );
      const javaTag = screen.getByRole("link", { name: "Java" });
      expect(javaTag).toBeInTheDocument();
    });
  });
});
