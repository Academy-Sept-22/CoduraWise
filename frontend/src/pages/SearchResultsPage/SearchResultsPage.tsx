import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import ListingWithFilter from "../../components/ListingWithFilter/ListingWithFilter";
import useResources from "../../store/hooks/useResources";

const SearchResultsPage = () => {
  const { resources, searchByTitleAndTag } = useResources();
  const { text } = useParams();

  useEffect(() => {
   text ? searchByTitleAndTag(text, text) : [];
  }, []);

  return (<section>
    <h3>Search results for {text}:</h3>
    <ListingWithFilter resources={resources} />
  </section>);
};

export default SearchResultsPage;