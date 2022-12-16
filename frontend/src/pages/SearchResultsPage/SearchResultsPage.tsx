import React, { useEffect, useContext } from "react";
import { useParams } from "react-router-dom";
import ListingWithFilter from "../../components/ListingWithFilter/ListingWithFilter";
import useResources from "../../store/hooks/useResources";
import { ResourcesContext } from "../../store/ResourcesContext";
import "./SearchResultsPage.scss";

const SearchResultsPage = () => {
  const { resources, searchByText } = useResources();
  const { setResources } = useContext(ResourcesContext);
  const { text } = useParams();

  useEffect(() => {
    if (text) {
      const formattedText = encodeURIComponent(text) ?? "";
      searchByText(formattedText);
    } else {
      setResources([]);
    }
  }, [text]);

  return (
    <section className="search-result-page">
      {text ? (
        <h1>Search results for &ldquo;{text}&rdquo;:</h1>
      ) : (
        <h1>No valid search term was provided</h1>
      )}
      <ListingWithFilter resources={resources} />
    </section>
  );
};

export default SearchResultsPage;
