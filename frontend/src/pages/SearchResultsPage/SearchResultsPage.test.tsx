import React from "react";
import TestRenderer from "react-test-renderer";
import SearchResultsPage from "./SearchResultsPage";
import { MemoryRouter } from "react-router-dom";
import { ContextState, ResourcesContext } from "../../store/ResourcesContext";
import { MediaType } from "../../models/MediaTypes";
import { Resource } from "../../models/Resource";
import { setupServer } from "msw/node";
import { baseBackendUrl } from "../../routes/paths";
import { rest } from "msw";

describe("given the search results list page", () => {
  
  const textToSearch = 'agile'

  const server = setupServer(rest.get(`${baseBackendUrl}/search`, (req, res, ctx) => {
      const title = req.url.searchParams.get('title') as string;
      console.log('API call');
      //
      // NOTE: this is not being called!!!!!
      //
      return res(ctx.json(setupData(title)));
  }));
  
  beforeEach(() => {
    console.log('Before each')
    server.listen();
  });
  
  test("snapshot test: the html matches", () => {
    const route = `/search/${textToSearch}`

    const searchListPage = TestRenderer.create(
      <MemoryRouter initialEntries={[route]}>
        <ResourcesContext.Provider value={{filterMediaTypes : [] as Array<MediaType>} as ContextState}>
          <SearchResultsPage />
        </ResourcesContext.Provider>
      </MemoryRouter>
    );
    console.log('Test')

    expect(searchListPage.toJSON()).toMatchSnapshot();
  });

  const setupData = (text: string) => {
    return [
      { id: 1, title: `Java ${text}`, link: "Java Link ", mediaType: "video", averageRating: 1 },
      { id: 3, title: `Javascript ${text}`, link: "Javascript Link ", mediaType: "course", averageRating: 3 },
    ] as Array<Resource>;
  }

});