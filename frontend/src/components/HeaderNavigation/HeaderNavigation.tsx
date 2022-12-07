import React from "react";
import { Link } from "react-router-dom";
import { routerPaths } from "../../routes/paths";

const HeaderNavigation = () => {
  return (
    <nav>
      <Link to={routerPaths.home}>Home</Link>
      <Link to={routerPaths.resources}>Resources</Link>
    </nav>
  );
};

export default HeaderNavigation;
