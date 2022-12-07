import React from "react";
import "./SearchBar.scss";

const SearchBar = () => {
  const submitSearch = () => {
    console.log("test");
  };
  debugger;
  return (
    <section role="search">
      <form onSubmit={submitSearch}>
        <input
          type="search"
          name="whatever"
          value="hello world"
          onChange={submitSearch}
        />
        <button type="submit">search</button>
      </form>
    </section>
  );
};

export default SearchBar;

