import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { routerPaths } from "../../routes/paths";
import { FaSearch } from "react-icons/fa";
import "./SearchBar.scss";

const SearchBar = () => {
  const [searchValue, setSearchValue] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const formattedSearchValue = encodeURIComponent(searchValue) ?? "";

    const path = routerPaths.search.replace(":text", formattedSearchValue);
    navigate(path);
    setSearchValue("");
  };
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchValue(event.target.value);
  };

  return (
    <section role="search">
      <form onSubmit={handleSubmit}>
        <label htmlFor="search"></label>
        <input
          id="search"
          type="search"
          name="search"
          value={searchValue}
          onChange={handleChange}
          placeholder="Search For Resources..."
        />
        <span className="caret"></span>
        <button type="submit">
          <FaSearch size={12} />
        </button>
      </form>
    </section>
  );
};

export default SearchBar;
