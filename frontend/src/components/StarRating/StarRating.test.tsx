import { render, screen } from "@testing-library/react";
import StarRating from "./StarRating";

describe("given the resource has a rating", () => {
  describe("when the rating is one", () => {
    test("then one star should be coloured", () => {
      const rating = 1;

      render(<StarRating rating={rating} />);

      const stars = screen.getAllByRole("radio");
      expect(stars).toBeInTheDocument();
      expect(stars[0]).toBeTruthy();
    });
  });
});

