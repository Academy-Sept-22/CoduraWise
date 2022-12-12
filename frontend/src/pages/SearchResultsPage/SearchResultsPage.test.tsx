import React from "react";
import TestRenderer from "react-test-renderer";
import SearchResultsPage from "./SearchResultsPage";
import { Resource } from "../../models/Resource";
import { setupServer } from "msw/node";
import { baseBackendUrl } from "../../routes/paths";
import { rest } from "msw";
import ContextRouterMock from "../../mocks/contextRouterMock";

describe("given the search results list page", () => {
  
  const textToSearch = 'agile'

  const server = setupServer(rest.get(`${baseBackendUrl}/search`, (req, res, ctx) => {
      const title = req.url.searchParams.get('title') as string;
      //
      // NOTE: this is not being called!!!!!
      //
      return res(ctx.json(setupData(title)));
  }));
  
  beforeEach(() => {
    server.listen();
  });
  
  test("snapshot test: the html matches", () => {
    const route = `/search/${textToSearch}`

    const searchListPage = TestRenderer.create(
        <ContextRouterMock initialEntries={[route]}>
        <SearchResultsPage />
      </ContextRouterMock>,
    );

    expect(searchListPage.toJSON()).toMatchSnapshot();
  });

  const setupData = (text: string) => {
    return [
      { id: 1, title: `Java ${text}`, link: "Java Link ", mediaType: "video", averageRating: 1 },
      { id: 3, title: `Javascript ${text}`, link: "Javascript Link ", mediaType: "course", averageRating: 3 },
    ] as Array<Resource>;
  }

});