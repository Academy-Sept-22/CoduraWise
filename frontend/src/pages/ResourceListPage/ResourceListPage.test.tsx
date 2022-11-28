import { render, screen } from "@testing-library/react";
import ResourceListPage from "./ResourceListPage";
import React from "react";
import { server } from "../../mocks/server/server";
import ContextRouterMock from "../../mocks/contextRouterMock";

describe("given the resorce list page", () => {
  beforeEach(() => {
    server.listen();
  });

  test("it renders the resource list", () => {
    render(<ResourceListPage />);

    const resourceList = screen.getByRole("list");

    expect(resourceList).toHaveClass("resource-list__container");
  });

  test("the list will include the titles Java, Python and Javascript", async () => {
    render(
      <ContextRouterMock>
        <ResourceListPage />
      </ContextRouterMock>,
    );

    const title1 = await screen.findByRole("heading", { name: "Title: Java" });
    const title2 = await screen.findByRole("heading", {
      name: "Title: Python",
    });
    const title3 = await screen.findByRole("heading", {
      name: "Title: Javascript",
    });

    expect(title1).toBeInTheDocument();
    expect(title2).toBeInTheDocument();
    expect(title3).toBeInTheDocument();
  });
});
