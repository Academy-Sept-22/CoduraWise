const baseBackendUrl = process.env.REACT_APP_BACKEND_URL;

export const backendUrls = {
  resourceListUrl: baseBackendUrl + "/resources",
  resourcesByTagUrl: baseBackendUrl + "/resources/tag/",
  tagsUrl: baseBackendUrl + "/tags/",
};

export const routerPaths = {
  noPath: "/",
  home: "/home",
  resources: "/resources",
  tag: "/resources/tag/:tag",
  any: "*",
};
