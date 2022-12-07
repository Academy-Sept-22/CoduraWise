import React from "react";
import "./SearchBar.scss";

const SearchBar = () => {
  const submitSearch = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    console.log("test");
  };
  const onChange = () => {
    console.log("onchange");
  };
  //debugger;
  return (
    <section role="search">
      <form onSubmit={(event) => submitSearch(event)}>
        <input
          type="search"
          name="whatever"
          value="hello world"
          onChange={onChange}
        />
        <button type="submit">search</button>
      </form>
    </section>
  );
};

export default SearchBar;
