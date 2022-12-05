import React, { useContext } from "react";
import { getAllByRole, render, screen } from "@testing-library/react";
import ResourceList from "./ResourceList";
import { mockResources } from "../../mocks/mocks";
import ContextRouterMock from "../../mocks/contextRouterMock";
import ResourcesContextProvider, { ResourcesContext } from "../../store/ResourcesContext";
import { MediaType } from "../../models/MediaTypes";

describe("given the resource list", () => {
  describe("when it receives resource data", () => {
    test("then the titles match the data", () => {
      render(<ContextRouterMock>
        <ResourceList resources={mockResources} />
      </ContextRouterMock>);

      const title1 = screen.getByRole("heading", { name: "Java" });
      expect(title1).toBeInTheDocument();

      const title2 = screen.getByRole("heading", { name: "Python" });
      expect(title2).toBeInTheDocument();

      const title3 = screen.getByRole("heading", { name: "Javascript" });
      expect(title3).toBeInTheDocument();
    });

    test("then the url match the data", () => {
      render(<ContextRouterMock>
        <ResourceList resources={mockResources} />
      </ContextRouterMock>);

      const url1 = screen.getByRole("link", { name: "Java" });
      expect(url1).toHaveAttribute("href", mockResources[0].link);

      const url2 = screen.getByRole("link", { name: "Python" });
      expect(url2).toHaveAttribute("href", mockResources[1].link);

      const url3 = screen.getByRole("link", { name: "Javascript" });
      expect(url3).toHaveAttribute("href", mockResources[2].link);
    });

    test("the", () => {
      const mockUseContext: jest.SpyInstance = jest.spyOn(React, "useContext");
      mockUseContext.mockReturnValue({ filterMediaTypes: [ "video" ] as Array<MediaType>})

      render(<ResourcesContextProvider>
        <ResourceList resources={mockResources} />
      </ResourcesContextProvider>);

      const url1 = screen.getByRole("link", { name: "Java" });
      expect(url1).toHaveAttribute("href", mockResources[0].link);

      const links = screen.getAllByRole("link");
      expect(links).toHaveLength(1);
    });

  });
});
