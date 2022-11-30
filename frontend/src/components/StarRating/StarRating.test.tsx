import { render, screen } from "@testing-library/react";
import StarRating from "./StarRating";
import TestRenderer from "react-test-renderer";
import React from "react";

describe("given the resource has a rating", () => {
  describe("when the rating is one", () => {
    test("then the aria label should convey that", () => {
      const rating = 1;

      render(<StarRating rating={rating} />);

      const stars = screen.getByLabelText(
        `Rating of this product is ${rating} out of 5.`,
      );

      expect(stars).toBeInTheDocument();
    });

    test("snapshot test: the html matches", () => {
      const rating = 1;
      const card = TestRenderer.create(<StarRating rating={rating} />);

      expect(card.toJSON()).toMatchSnapshot();
    });
  });
});

