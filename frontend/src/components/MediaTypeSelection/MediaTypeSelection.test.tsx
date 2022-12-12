import TestRenderer, { act } from "react-test-renderer";
import MediaTypeSelection from "./MediaTypeSelection";
import React from "react";
import { render, screen } from "@testing-library/react";
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

  describe("When I check the video checkbox", () => {
    test("Then it updates the state to only show videos", () => {
      act(() => {
        render(
          <MediaTypeSelection setFilterMediaTypes={mockSetFilterMediaTypes} />,
        );
      });

      const videoCheckbox = screen.getByRole("checkbox", { name: "video" });
      act(() => {
        videoCheckbox.click();
      });
      const expectedArray: Array<MediaType> = ["video"];

      expect(mockSetFilterMediaTypes).toBeCalledWith(expectedArray);
    });
  });
});
