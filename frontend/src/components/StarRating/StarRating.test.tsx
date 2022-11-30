import { render, screen } from "@testing-library/react";
import StarRating from "./StarRating";
import React from "react";

describe("given the resource has a rating", () => {
  describe("when the rating is one", () => {
    test("then one star should be coloured", () => {
      const rating = 1;

      render(<StarRating rating={rating} />);

      const stars = screen.getByLabelText(`Rating of this product is ${rating} out of 5.`)

      expect(stars).toBeInTheDocument();
    });
  });
});

