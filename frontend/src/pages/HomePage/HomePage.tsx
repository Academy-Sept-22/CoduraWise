import React from "react";
import TagCloud from "../../components/TagCloud/TagCloud";
import { mockTags } from "../../mocks/mocks";

const HomePage = () => {
  return (
    <div className="home-page">
      <TagCloud tags={mockTags} />
    </div>
  );
};

export default HomePage;
