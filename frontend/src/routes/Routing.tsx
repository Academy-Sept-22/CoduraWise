import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import React from "react";
import ResourceListPage from "../pages/ResourceListPage/ResourceListPage";
import { routerPaths } from "./paths";
import HomePage from "../pages/HomePage/HomePage";
import Header from "../components/Header/Header";
import SearchResultsPage from "../pages/SearchResultsPage/SearchResultsPage";

const Routing = () => {
  return (
    <BrowserRouter>
      <Header />
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
        <Route path={routerPaths.tag} element={<ResourceListPage />} />
        <Route path={routerPaths.search} element={<SearchResultsPage />} />
      </Routes>
    </BrowserRouter>
  );
};

export default Routing;
