import { render, screen } from "@testing-library/react";
import React from "react";
import Header from "./Header";

describe("given the homepage", () => {
  test("should render with the logo", () => {
    render(<Header />);

    const image = screen.getByAltText("Codurance Logo");
    expect(image).toBeInTheDocument();
  });

  test("should render a nav component", () => {
    render(<Header />);

    const navComponent = screen.getByRole("navigation");
    expect(navComponent).toBeInTheDocument();
  });

  test("should render a Heading of CoduraWise", () => {
    render(<Header />);

    const heading = screen.getByText("CoduraWise");
    expect(heading).toBeInTheDocument();
  });
});

