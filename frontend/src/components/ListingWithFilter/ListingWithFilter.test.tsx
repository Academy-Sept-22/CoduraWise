import TestRenderer from "react-test-renderer";
import { mockResources } from "../../mocks/mocks";
import ListingWithFilter from "./ListingWithFilter";
import React from "react";
import ContextRouterMock from "../../mocks/contextRouterMock";

test("snapshot test: the html matches", () => {
  const listingWithFilterComponent = TestRenderer.create(
    <ContextRouterMock>
      <ListingWithFilter resources={mockResources} />,
    </ContextRouterMock>,
  );

  expect(listingWithFilterComponent.toJSON()).toMatchSnapshot();
});

