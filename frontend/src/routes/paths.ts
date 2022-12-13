export const baseBackendUrl = process.env.REACT_APP_BACKEND_URL;

export const backendUrls = {
  resourceListUrl: `${baseBackendUrl}/resources`,
  tagsUrl: `${baseBackendUrl}/tags/`,
  resourcesByTagUrl: (tag: string) => `${baseBackendUrl}/resources/tag/${tag}`,
  searchUrl: (text: string) => `${baseBackendUrl}/search?query=${text}`,
};

export const routerPaths = {
  noPath: "/",
  home: "/home",
  resources: "/resources",
  tag: "/resources/tag/:tag",
  search: "/search/:text",
  emptySearch: "/search",
  addResource: "/resources/new",
  any: "*",
};
