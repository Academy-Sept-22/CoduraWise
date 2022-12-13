import React from "react";
import { Link } from "react-router-dom";
import { routerPaths } from "../../routes/paths";
import AddResourceButton from "../AddResourceButton/AddResourceButton";
import "./HeaderNavigation.scss";

const HeaderNavigation = () => {
  return (
    <nav className="header-nav">
      <Link className="header-nav__link" to={routerPaths.home}>
        Home
      </Link>
      <Link className="header-nav__link" to={routerPaths.resources}>
        Resources
      </Link>
      <AddResourceButton />
    </nav>
  );
};

export default HeaderNavigation;

