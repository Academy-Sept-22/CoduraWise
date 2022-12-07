import React from "react";
import HeaderNavigation from "../HeaderNavigation/HeaderNavigation";

const Header = () => {
  return (
    <header>
      <img src="../../../public/codurance-logo.png" alt="Codurance Logo" />;
      <HeaderNavigation />
      <span>CoduraWise</span>
    </header>
  );
};

export default Header;

