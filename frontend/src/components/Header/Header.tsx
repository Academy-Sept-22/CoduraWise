import React from "react";
import AddResourceButton from "../AddResourceButton/AddResourceButton";
import HeaderNavigation from "../HeaderNavigation/HeaderNavigation";
import SearchBar from "../SearchBar/SearchBar";
import "./Header.scss";

const Header = () => {
  return (
    <header className="main-header">
      <div className="main-header__logo">
        <img
          src="/codurance-logo.png"
          alt="Codurance Logo"
          width="50px"
          height="50px"
        />
        <span>CoduraWise</span>
      </div>

      <HeaderNavigation />
      <SearchBar />
    </header>
  );
};

export default Header;

