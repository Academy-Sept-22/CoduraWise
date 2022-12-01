import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import React from "react";
import ResourceListPage from "../pages/ResourceListPage/ResourceListPage";
import { routerPaths } from "./paths";
import HomePage from "../pages/HomePage/HomePage";

const Routing = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path={routerPaths.noPath}
          element={<Navigate to={routerPaths.home} replace />}
        />
        <Route path={routerPaths.home} element={<HomePage />} />
        <Route path={routerPaths.resources} element={<ResourceListPage />} />
        <Route
          path={routerPaths.any}
          element={<Navigate to={routerPaths.noPath} replace />}
        />
        <Route
        path={routerPaths.tag}
        element={<ResourceListPage />}
         />
      </Routes>
    </BrowserRouter>
  );
};

export default Routing;
