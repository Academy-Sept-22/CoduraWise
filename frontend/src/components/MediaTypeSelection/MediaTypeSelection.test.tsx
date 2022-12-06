import TestRenderer from "react-test-renderer";
import MediaTypeSelection from "./MediaTypeSelection";
import React from "react";

test("snapshot test: the html matches", () => {
  const card = TestRenderer.create(
    <MediaTypeSelection/>,
  );

  expect(card.toJSON()).toMatchSnapshot();
});
