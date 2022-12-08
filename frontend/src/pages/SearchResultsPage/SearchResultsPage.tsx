import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import ListingWithFilter from "../../components/ListingWithFilter/ListingWithFilter";
import useResources from "../../store/hooks/useResources";
import './SearchResultsPage.scss'

const SearchResultsPage = () => {
  const { resources, searchByTitleAndTag } = useResources();
  const { text } = useParams();
  
  console.log('page', text)

  useEffect(() => {
   text ? searchByTitleAndTag(text, text) : [];
  }, [text]);

  return (<section className="search-result-page">
    <h3>Search results for &ldquo;{text}&rdquo;:</h3>
    <ListingWithFilter resources={resources} />
  </section>);
};

export default SearchResultsPage;