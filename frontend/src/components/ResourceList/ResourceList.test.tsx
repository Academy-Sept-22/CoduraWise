import { render, screen } from "@testing-library/react";
import { Resource } from "../../models/Resource";
import ResourceList from "./ResourceList";
import React from "react";

describe("given the resource list", () => {
  describe("when it receives resource data", () => {
    test("then the titles match the data", () => {
      const arrayList: Array<Resource> = [
        { id: 1, title: "Java", link: "Java Link", rating: 1 },
        { id: 2, title: "Python", link: "Python Link", rating: 1 },
        { id: 3, title: "Javascript", link: "Javascript Link", rating: 1 },
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
        { id: 1, title: "Java", link: "Java Link", rating: 1 },
        { id: 2, title: "Python", link: "Python Link", rating: 1 },
        { id: 3, title: "Javascript", link: "Javascript Link", rating: 1 },
      ];

      render(<ResourceList resources={arrayList} />);

      const url1 = screen.getByRole("link", { name: "Title: Java" });
      expect(url1).toHaveAttribute("href", arrayList[0].link);

      const url2 = screen.getByRole("link", { name: "Title: Python" });
      expect(url2).toHaveAttribute("href", arrayList[1].link);

      const url3 = screen.getByRole("link", { name: "Title: Javascript" });
      expect(url3).toHaveAttribute("href", arrayList[2].link);
    });
  });
});
