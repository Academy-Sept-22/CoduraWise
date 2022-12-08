import TestRenderer from "react-test-renderer";
import SearchResultsPage from "./SearchResultsPage";
import React from "react";
import { server } from "../../mocks/server/server";
import { MemoryRouter } from "react-router-dom";
import { ContextState, ResourcesContext } from "../../store/ResourcesContext";
import { MediaType } from "../../models/MediaTypes";

describe("given the search results list page", () => {
  beforeEach(() => {
    server.listen();
  });

  test("snapshot test: the html matches", () => {
    const route = '/search/agile'

    const searchListPage = TestRenderer.create(
      <MemoryRouter initialEntries={[route]}>
        <ResourcesContext.Provider value={{filterMediaTypes : [] as Array<MediaType>} as ContextState}>
          <SearchResultsPage />
        </ResourcesContext.Provider>
      </MemoryRouter>
    );

    expect(searchListPage.toJSON()).toMatchSnapshot();
  });

});