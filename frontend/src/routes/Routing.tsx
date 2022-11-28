import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import React from "react";
import ResourceListPage from "../pages/ResourceListPage/ResourceListPage";
import { routerPaths } from "./paths";

const Routing = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path={routerPaths.noPath}
          element={<Navigate to={routerPaths.resources} replace />}
        />
        <Route path={routerPaths.resources} element={<ResourceListPage />} />
        <Route
          path={routerPaths.any}
          element={<Navigate to={routerPaths.noPath} replace />}
        />
      </Routes>
    </BrowserRouter>
  );
};

export default Routing;
