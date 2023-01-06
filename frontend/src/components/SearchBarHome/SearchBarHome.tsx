import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { routerPaths } from "../../routes/paths";
import { FaSearch } from "react-icons/fa";
import "./SearchBarHome.scss";

const SearchBarHome = () => {
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
    <section className="search-bar-home" role="search">
      <form className="search-bar-home__form" onSubmit={handleSubmit}>
        <label className="search-bar-home__label">Search for a Resource</label>
        <div className="search-bar-home__container">
          <div className="search-bar-home__inputContainer">
            <input
              className="search-bar-home__input"
              id="search"
              type="search"
              name="search"
              value={searchValue}
              onChange={handleChange}
              placeholder="Search For Resources..."
              autoFocus
              required
            />
            <button className="search-bar-home__button" type="submit">
              Go
            </button>
          </div>
        </div>
      </form>
    </section>
  );
};

export default SearchBarHome;

