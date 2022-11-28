import { rest } from "msw";
import { backendUrls } from "../../routes/paths";
import { mockResources } from "../mocks";

const resourceListHandler = rest.get(
  backendUrls.resourceListUrl,
  async (req, res, ctx) => {
    const response = res(ctx.json(mockResources));
    return response;
  },
);

export const handlers = [resourceListHandler];
