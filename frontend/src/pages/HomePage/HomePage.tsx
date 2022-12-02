import React, { useEffect } from "react";
import TagCloud from "../../components/TagCloud/TagCloud";
import useTags from "../../store/hooks/useTags";

const HomePage = () => {
  const { tags, getTags } = useTags();

  useEffect(() => {
    getTags();
  }, []);

  return (
    <div className="home-page">
      <TagCloud tags={tags} />
    </div>
  );
};

export default HomePage;
