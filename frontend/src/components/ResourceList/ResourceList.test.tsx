import React from "react";
import { render, screen } from "@testing-library/react";
import ResourceList from "./ResourceList";
import ContextRouterMock from "../../mocks/contextRouterMock";
import { Resource } from "../../models/Resource";

describe("given the resource list", () => {
 
  describe("when it receives resource data", () => {

    let mockResources : Array<Resource>;

    beforeEach(() => {
      mockResources = setupData();
    });

    test("then the titles match the data", () => {

      render(<ContextRouterMock>
        <ResourceList resources={mockResources} filterMediaTypes={[]} />
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
        <ResourceList resources={mockResources} filterMediaTypes={[]}/>
      </ContextRouterMock>);

      const url1 = screen.getByRole("link", { name: "Java" });
      expect(url1).toHaveAttribute("href", mockResources[0].link);

      const url2 = screen.getByRole("link", { name: "Python" });
      expect(url2).toHaveAttribute("href", mockResources[1].link);

      const url3 = screen.getByRole("link", { name: "Javascript" });
      expect(url3).toHaveAttribute("href", mockResources[2].link);
    });

    describe("then the context has video in filter", () => {
      test("then only titles for video match the data", () => {

        render(<ContextRouterMock>
            <ResourceList resources={mockResources} filterMediaTypes={["video"]}/>
        </ContextRouterMock>);

        const title1 = screen.getByRole("heading", { name: "Java" });
        expect(title1).toBeInTheDocument();

        const links = screen.getAllByRole("heading");
        expect(links).toHaveLength(1);
      });
    });

    describe("when has video and article in filter", () => {
      test("then only titles for video and article match the data", () => {

        render(<ContextRouterMock>
            <ResourceList resources={mockResources} filterMediaTypes={["video", "article"]}/>
        </ContextRouterMock>);

        const title1 = screen.getByRole("heading", { name: "Java" });
        expect(title1).toBeInTheDocument();

        const title2 = screen.getByRole("heading", { name: "Python" });
        expect(title2).toBeInTheDocument();

        const title3 = screen.getByRole("heading", { name: "C#" });
        expect(title3).toBeInTheDocument();

        const links = screen.getAllByRole("heading");
        expect(links).toHaveLength(3);
      });
    });

    const setupData = () => {
      return [
        { id: 1, title: "Java", link: "Java Link", mediaType: "video", averageRating: 1 },
        { id: 2, title: "Python", link: "Python Link", mediaType: "article", averageRating: 4 },
        { id: 3, title: "Javascript", link: "Javascript Link", mediaType: "course", averageRating: 3 },
        { id: 4, title: "C#", link: "C# Link", mediaType: "article", averageRating: 4 },
      ] as Array<Resource>;
    }
  });
});