import React, { useState } from "react";
import "./SearchBar.scss";

const SearchBar = () => {
  const [searchValue, setSearchValue] = useState("");

  const submitSearch = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log("test");
  };
  const onChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchValue(event.target.value);
  };

  return (
    <section role="search">
      <form onSubmit={submitSearch}>
        <label htmlFor="search">Search Resources</label>
        <input
          id="search"
          type="search"
          name="search"
          value={searchValue}
          onChange={onChange}
        />
        <button type="submit">search</button>
      </form>
    </section>
  );
};

export default SearchBar;

