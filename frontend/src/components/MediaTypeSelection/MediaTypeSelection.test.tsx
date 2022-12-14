import TestRenderer from "react-test-renderer";
import MediaTypeSelection from "./MediaTypeSelection";
import React from "react";
import { fireEvent, render, screen } from "@testing-library/react";
import { MediaType } from "../../models/MediaTypes";

test("snapshot test: the html matches", () => {
  const card = TestRenderer.create(
    <MediaTypeSelection setFilterMediaTypes={jest.fn()} />,
  );

  expect(card.toJSON()).toMatchSnapshot();
});

describe("Given a media filter selection component", () => {
  let mockSetFilterMediaTypes = jest.fn();
  beforeEach(() => {
    mockSetFilterMediaTypes = jest.fn();
  });

  describe("When I check the checkboxes of video and book", () => {
    test("Then it updates the state to only show videos and books", () => {
      render(
        <MediaTypeSelection setFilterMediaTypes={mockSetFilterMediaTypes} />,
      );

      const videoCheckbox = screen.getByRole("checkbox", { name: "video" });
      const bookCheckBox = screen.getByRole("checkbox", { name: "book" });

      fireEvent.click(videoCheckbox);
      fireEvent.click(bookCheckBox);

      const expectedArrayVideo: Array<MediaType> = ["video"];
      const expectedArrayVideoAndBook: Array<MediaType> = ["video", "book"];

      expect(mockSetFilterMediaTypes).toHaveBeenNthCalledWith(
        1,
        expectedArrayVideo,
      );
      expect(mockSetFilterMediaTypes).toHaveBeenNthCalledWith(
        2,
        expectedArrayVideoAndBook,
      );
    });
  });

  describe("When I check the book checkbox and then uncheck the book checkbox", () => {
    test("Then it updates the state to show all resources", () => {
      render(
        <MediaTypeSelection setFilterMediaTypes={mockSetFilterMediaTypes} />,
      );

      const bookCheckBox = screen.getByRole("checkbox", { name: "book" });
      fireEvent.click(bookCheckBox);
      fireEvent.click(bookCheckBox);

      const expectedArrayBook: Array<MediaType> = ["book"];
      const expectedArrayNoFilterSelected: Array<MediaType> = [];

      expect(mockSetFilterMediaTypes).toHaveBeenNthCalledWith(
        1,
        expectedArrayBook,
      );
      expect(mockSetFilterMediaTypes).toHaveBeenNthCalledWith(
        2,
        expectedArrayNoFilterSelected,
      );
    });
  });
});

