import React from "react";
import { Link } from "react-router-dom";
import { routerPaths } from "../../routes/paths";
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
      <Link className="header-nav__link" to={routerPaths.addResource}>
        Add Resource
      </Link>
    </nav>
  );
};

export default HeaderNavigation;
