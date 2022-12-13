import TestRenderer from "react-test-renderer";
import React from "react";
import ContextRouterMock from "../../mocks/contextRouterMock";
import AddResourceForm from "./AddResourceForm";

describe("given a resource form", () => {
  test("snapshot test: the html matches", () => {
    const resourceFormComponent = TestRenderer.create(
      <ContextRouterMock>
        <AddResourceForm />
      </ContextRouterMock>,
    );

    expect(resourceFormComponent.toJSON()).toMatchSnapshot();
  });
});

