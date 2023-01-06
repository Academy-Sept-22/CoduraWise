import TestRenderer from "react-test-renderer";
import ResourceCard from "./ResourceCard";
import React from "react";

test("snapshot test: the html matches", () => {
  const card = TestRenderer.create(
    <ResourceCard link="url" title="title" rating={1} mediaType="video" />,
  );

  expect(card.toJSON()).toMatchSnapshot();
});
