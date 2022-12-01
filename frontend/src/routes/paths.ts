const baseBackendUrl = process.env.REACT_APP_BACKEND_URL;

export const backendUrls = {
  resourceListUrl: baseBackendUrl + "/resources",
};

export const routerPaths = {
  noPath: "/",
  home: "/home",
  resources: "/resources",
  tag: "/resources/tag/:tag",
  any: "*",
};
