import React from "react";
import { Link } from "react-router-dom";
import { routerPaths } from "../../routes/paths";
import HeaderNavigation from "../HeaderNavigation/HeaderNavigation";
import SearchBar from "../SearchBar/SearchBar";
import "./Header.scss";

const Header = () => {
  return (
    <header className="main-header">
      <div className="main-header__logo">
        <Link to={routerPaths.home}>
          <img
            src="/codurance-logo.png"
            alt="Codurance Logo"
            width="50px"
            height="50px"
          />
        </Link>
        <span>CoduraWise</span>
      </div>

      <HeaderNavigation />
      <SearchBar />
    </header>
  );
};

export default Header;

