import { render, screen } from "@testing-library/react";
import ResourceList from "./ResourceList";
import React from "react";
import { mockResources } from "../../mocks/mocks";

describe("given the resource list", () => {
  describe("when it receives resource data", () => {
    test("then the titles match the data", () => {
      render(<ResourceList resources={mockResources} />);

      const title1 = screen.getByRole("heading", { name: "Title: Java" });
      expect(title1).toBeInTheDocument();

      const title2 = screen.getByRole("heading", { name: "Title: Python" });
      expect(title2).toBeInTheDocument();

      const title3 = screen.getByRole("heading", { name: "Title: Javascript" });
      expect(title3).toBeInTheDocument();
    });

    test("then the url match the data", () => {
      render(<ResourceList resources={mockResources} />);

      const url1 = screen.getByRole("link", { name: "Title: Java" });
      expect(url1).toHaveAttribute("href", mockResources[0].link);

      const url2 = screen.getByRole("link", { name: "Title: Python" });
      expect(url2).toHaveAttribute("href", mockResources[1].link);

      const url3 = screen.getByRole("link", { name: "Title: Javascript" });
      expect(url3).toHaveAttribute("href", mockResources[2].link);
    });
  });
});
