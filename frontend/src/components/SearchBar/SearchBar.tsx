import React, { useState } from "react";
import useResources from "../../store/hooks/useResources";
import "./SearchBar.scss";

const SearchBar = () => {
  const [searchValue, setSearchValue] = useState("");
  const { searchByTitleAndTag } = useResources();

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const title = searchValue ?? null;
    const tag = searchValue ?? null;

    searchByTitleAndTag(title, tag);
  };
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchValue(event.target.value);
  };

  return (
    <section role="search">
      <form onSubmit={handleSubmit}>
        <label htmlFor="search">Search:</label>
        <input
          id="search"
          type="search"
          name="search"
          value={searchValue}
          onChange={handleChange}
        />
        <button type="submit">search</button>
      </form>
    </section>
  );
};

export default SearchBar;
