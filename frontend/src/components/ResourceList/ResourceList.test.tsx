import { render, screen } from "@testing-library/react";
import { Resource } from "../../models/Resource";
import ResourceList from "./ResourceList";
import React from "react";

describe("given the resorce list", () => {
  describe("when it receives resource data", () => {
    test("then the titles match the data", () => {
      const arrayList: Array<Resource> = [
        { id: 1, title: "Java", link: "Java Link" },
        { id: 2, title: "Python", link: "Python Link" },
        { id: 3, title: "Javascript", link: "Javascript Link" },
      ];

      render(<ResourceList resources={arrayList} />);

      const title1 = screen.getByRole("heading", { name: "Title: Java" });
      expect(title1).toBeInTheDocument();

      const title2 = screen.getByRole("heading", { name: "Title: Python" });
      expect(title2).toBeInTheDocument();

      const title3 = screen.getByRole("heading", { name: "Title: Javascript" });
      expect(title3).toBeInTheDocument();
    });

    test("then the url match the data", () => {
      const arrayList: Array<Resource> = [
        { id: 1, title: "Java", link: "Java Link" },
        { id: 2, title: "Python", link: "Python Link" },
        { id: 3, title: "Javascript", link: "Javascript Link" },
      ];

      render(<ResourceList resources={arrayList} />);

      const url1 = screen.getByRole("link", { name: "URL: Java Link" });
      expect(url1).toBeInTheDocument();

      const url2 = screen.getByRole("link", { name: "URL: Python Link" });
      expect(url2).toBeInTheDocument();

      const url3 = screen.getByRole("link", { name: "URL: Javascript Link" });
      expect(url3).toBeInTheDocument();
    });
  });
});
